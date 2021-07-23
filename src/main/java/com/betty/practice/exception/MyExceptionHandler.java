package com.betty.practice.exception;

import com.betty.core.entity.AjaxResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;

/**
 * @author Betty
 * @date 2021年04月26日
 */
@Slf4j
@RestControllerAdvice
public class MyExceptionHandler {

	@ExceptionHandler(value = Exception.class)
	public AjaxResult exceptionHandler(Exception e) {
		log.error("未知异常，原因：{}", e.getMessage());
		return AjaxResult.error(e.getMessage());
	}

	@ExceptionHandler(value = IOException.class)
	public AjaxResult IOExceptionHandler(IOException e) {
		log.error("传输异常，原因：{}", e.getMessage());
		return AjaxResult.error(e.getMessage());
	}

	@ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
	public AjaxResult HttpRequestMethodNotSupportedExceptionHandler(HttpRequestMethodNotSupportedException e) {
		return AjaxResult.error("请求方法不被允许");
	}

	@ExceptionHandler(PracticeException.class)
	public AjaxResult practiceExceptionHandler(PracticeException e) {
		return AjaxResult.error(e.getMessage());
	}
}
