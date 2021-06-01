package com.betty.practice.interceptor;

import com.alibaba.fastjson.JSON;
import com.betty.practice.utils.result.ResultInfo;
import com.betty.practice.utils.result.CommonEnum;
import com.betty.practice.target.AccessLimit;
import com.betty.practice.utils.redis.RedisUtils;
import com.betty.practice.utils.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;

/**
 * @desc 防刷接口拦截器
 * @author Betty
 */
@Component
public class BrushProofInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private RedisUtils redisUtils;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod hm = (HandlerMethod) handler;

            //获取方法中的注解,看是否有该注解
            AccessLimit accessLimit = hm.getMethodAnnotation(AccessLimit.class);
            if (accessLimit == null) {
                return true;
            }
            Long seconds = accessLimit.seconds();
            int maxCount = accessLimit.maxCount();
            boolean login = accessLimit.needLogin();
            String key = "url:" + request.getRequestURI() + ":";

            //如果需要登录
            if (login) {
                //获取登录的session进行判断
                //.....
                key += "" + "1";  //这里假设用户是1,项目中是动态获取的userId
            }

            Integer count = (Integer) redisUtils.get(key);
            if (count == null) {
                //第一次访问
                redisUtils.set(key, 1, seconds);
            } else if (count < maxCount) {
                count += 1;
                redisUtils.set(key, count, seconds);
            } else {
                render(response, CommonEnum.SERVER_BUSY);
                return false;
            }
        }

        return true;
    }

    private void render(HttpServletResponse response, ResultInfo cm) throws Exception {
        response.setContentType("application/json;charset=UTF-8");
        OutputStream out = response.getOutputStream();
        String str = JSON.toJSONString(Result.error(cm));
        out.write(str.getBytes("UTF-8"));
        out.flush();
        out.close();
    }

}
