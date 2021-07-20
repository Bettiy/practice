package com.betty.practice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.betty.practice.bean.M_User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Betty
 * @date 2021年04月30日
 */
@Mapper
public interface M_UserMapper extends BaseMapper<M_User> {
}
