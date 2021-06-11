package com.betty.practice.exception;

import com.betty.practice.utils.result.CommonEnum;
import com.betty.practice.utils.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;

/**
 * @author Betty
 * @date 2021年04月26日
 */
@Slf4j
public class MyExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public Result exceptionHandler(Exception e) {
        log.error("未知异常，原因：{}", e.getMessage());
        return Result.error(CommonEnum.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = IOException.class)
    public Result IOExceptionHandler(IOException e) {
        log.error("传输异常，原因：{}", e.getMessage());
        return Result.error(CommonEnum.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = NullPointerException.class)
    public Result NullPointerExceptionHandler(NullPointerException e) {
        log.error("空指针异常，原因：{}", e.getMessage());
        return Result.error(CommonEnum.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = ArithmeticException.class)
    public Result ArithmeticExceptionHandler(ArithmeticException e) {
        log.error("自定义异常，原因：{}", e.getMessage());
        return Result.error(CommonEnum.INTERNAL_SERVER_ERROR);
    }

    // HttpRequestMethodNotSupportedException
    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public Result HttpRequestMethodNotSupportedExceptionHandler(HttpRequestMethodNotSupportedException e) {
        log.error("请求方法不被允许，原因：{}", e.getMessage());
        return Result.error(CommonEnum.NOT_ALLOWED_METHOD);
    }

}
