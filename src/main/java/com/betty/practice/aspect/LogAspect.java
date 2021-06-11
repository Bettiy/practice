package com.betty.practice.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author Betty
 * @date 2021年06月07日
 */
@Aspect
@Component
@Slf4j
public class LogAspect {

    @Pointcut("@annotation(com.betty.practice.annotation.Log)")
    public void log(){}


    @Before(value = "log()")
    public void before() {
        log.info("执行aop");
    }

}
