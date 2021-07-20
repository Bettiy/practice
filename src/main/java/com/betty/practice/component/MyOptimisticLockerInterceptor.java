package com.betty.practice.component;

import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.TableFieldInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfoHelper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.core.toolkit.ExceptionUtils;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import lombok.SneakyThrows;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.RowBounds;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Betty
 * @date 2021年07月15日
 */
public class MyOptimisticLockerInterceptor extends OptimisticLockerInnerInterceptor {
    private static final String PARAM_UPDATE_METHOD_NAME = "update";

    @SneakyThrows
    @Override
    @SuppressWarnings("unchecked")
    public void beforeUpdate(Executor executor, MappedStatement ms, Object parameter) {
        if (SqlCommandType.UPDATE != ms.getSqlCommandType()) {
            return;
        }
        if (parameter instanceof Map) {
            Map<String, Object> map = (Map<String, Object>) parameter;

            //获得参数的entity的CLass对象
            Object et = map.getOrDefault(Constants.ENTITY, null);
            Object id = null;
            Field etversionField = null;
            if (et == null) {
                // 只有ew方式不能获取到entity，无法实现乐观锁
                //如果是ew方式没有Entity则强行取id,这里可以忽略不计
                /*AbstractWrapper<?, ?, ?> aw = (AbstractWrapper<?, ?, ?>) map.getOrDefault(Constants.WRAPPER, null);
                String sqlSegment = aw.getExpression().getSqlSegment();
                String idSegment = sqlSegment.substring(sqlSegment.indexOf("id = #{ew.paramNameValuePairs.") + 30, sqlSegment.indexOf("id = #{ew.paramNameValuePairs.") + 39);
                id = aw.getParamNameValuePairs().get(idSegment);*/
                return;
            } else {
                TableInfo ettableInfo = TableInfoHelper.getTableInfo(et.getClass());
                if (ettableInfo == null || !ettableInfo.isWithVersion()) {
                    return;
                }
                TableFieldInfo etfieldInfo = ettableInfo.getVersionFieldInfo();
                etversionField = etfieldInfo.getField();
                Field idFiled = et.getClass().getDeclaredField("id");
                idFiled.setAccessible(true);
                id = idFiled.get(et);
            }
            //获得当前confuguration
            Configuration configuration1 = ms.getConfiguration();
            //Configuration configuration = GlobalConfigUtils.currentSessionFactory(et.getClass()).getConfiguration();
            //获得mapper完整路径
            String pNameRef = ms.getId();
            //获得包名
            String pName = pNameRef.substring(0, pNameRef.lastIndexOf(".") + 1);
            //封装一个查询命令
            MappedStatement ms2 = configuration1.getMappedStatement(pName + "selectById");
            /*TableInfo tableInfo = TableInfoHelper.getTableInfo(et.getClass());
            TableFieldInfo idFieldInfo = tableInfo.getFieldList().stream().filter(x -> x.getProperty().equals("id")).findFirst().get();
            Object id = idFieldInfo.getField().get(et);*/
            //执行查询,查到version
            List<Object> query = executor.query(ms2, id, new RowBounds(), Executor.NO_RESULT_HANDLER);
            if (!CollectionUtils.isEmpty(query)) {
                Object obj = query.get(0);
                TableInfo tableInfo = TableInfoHelper.getTableInfo(obj.getClass());
                if (tableInfo == null || !tableInfo.isWithVersion()) {
                    return;
                }
                TableFieldInfo fieldInfo = tableInfo.getVersionFieldInfo();
                Field versionField = fieldInfo.getField();
                Object versionVal = versionField.get(obj);
                //注入version
                etversionField.set(et, versionVal);
                //继续乐观锁
                doOptimisticLocker(map, ms.getId());
            }
        }
    }

    protected void doOptimisticLocker(Map<String, Object> map, String msId) {
        //updateById(et), update(et, wrapper);
        Object et = map.getOrDefault(Constants.ENTITY, null);
        if (et != null) {
            // entity
            String methodName = msId.substring(msId.lastIndexOf(StringPool.DOT) + 1);
            TableInfo tableInfo = TableInfoHelper.getTableInfo(et.getClass());
            if (tableInfo == null || !tableInfo.isWithVersion()) {
                return;
            }
            try {
                TableFieldInfo fieldInfo = tableInfo.getVersionFieldInfo();
                Field versionField = fieldInfo.getField();
                // 旧的 version 值
                Object originalVersionVal = versionField.get(et);
                if (originalVersionVal == null) {
                    return;
                }
                String versionColumn = fieldInfo.getColumn();
                // 新的 version 值
                Object updatedVersionVal = this.getUpdatedVersionVal(fieldInfo.getPropertyType(), originalVersionVal);
                if (PARAM_UPDATE_METHOD_NAME.equals(methodName)) {
                    AbstractWrapper<?, ?, ?> aw = (AbstractWrapper<?, ?, ?>) map.getOrDefault(Constants.WRAPPER, null);
                    if (aw == null) {
                        UpdateWrapper<?> uw = new UpdateWrapper<>();
                        uw.eq(versionColumn, originalVersionVal);
                        map.put(Constants.WRAPPER, uw);
                    } else {
                        aw.apply(versionColumn + " = {0}", originalVersionVal);
                    }
                } else {
                    map.put(Constants.MP_OPTLOCK_VERSION_ORIGINAL, originalVersionVal);
                }
                versionField.set(et, updatedVersionVal);
            } catch (IllegalAccessException e) {
                throw ExceptionUtils.mpe(e);
            }
        }
    }

    /**
     * This method provides the control for version value.<BR>
     * Returned value type must be the same as original one.
     *
     * @param originalVersionVal ignore
     * @return updated version val
     */
    protected Object getUpdatedVersionVal(Class<?> clazz, Object originalVersionVal) {
        if (long.class.equals(clazz) || Long.class.equals(clazz)) {
            return ((long) originalVersionVal) + 1;
        } else if (int.class.equals(clazz) || Integer.class.equals(clazz)) {
            return ((int) originalVersionVal) + 1;
        } else if (Date.class.equals(clazz)) {
            return new Date();
        } else if (Timestamp.class.equals(clazz)) {
            return new Timestamp(System.currentTimeMillis());
        } else if (LocalDateTime.class.equals(clazz)) {
            return LocalDateTime.now();
        }
        //not supported type, return original val.
        return originalVersionVal;
    }
}
