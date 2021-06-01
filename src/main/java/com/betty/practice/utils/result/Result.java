package com.betty.practice.utils.result;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Betty
 */
@Data
public class Result<T> implements Serializable {
    private Integer code;
    private String message;
    private T data;

    private Result(ResultInfo be) {
        this.code = be.getResultCode();
        this.message = be.getResultMsg();
    }

    private Result(ResultInfo be, T data) {
        this.code = be.getResultCode();
        this.message = be.getResultMsg();
        this.data = data;
    }

    private Result(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    private Result(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> Result<T> success() {
        return new Result<T>(CommonEnum.SUCCESS);
    }

    public static <T> Result<T> success(T data) {
        return new Result<T>(CommonEnum.SUCCESS, data);
    }

    public static <T> Result<T> success(ResultInfo be, T data) {
        return new Result<T>(be, data);
    }

    public static <T> Result<T> error() {
        return new Result<T>(CommonEnum.INTERNAL_SERVER_ERROR);
    }

    public static <T> Result<T> error(ResultInfo be) {
        return new Result<T>(be);
    }

    public static <T> Result<T> error(T data) {
        return new Result<T>(CommonEnum.INTERNAL_SERVER_ERROR, data);
    }

    public static <T> Result<T> error(ResultInfo be, T data) {
        return new Result<T>(be, data);
    }

    public static <T> Result<T> success(String message) {
        return new Result<T>(200, message);
    }

    public static <T> Result<T> success(String message, T data) {
        return new Result<T>(200, message, data);
    }

    public static <T> Result<T> error(String message) {
        return new Result<T>(588, message);
    }

    public static <T> Result<T> error(String message, T data) {
        return new Result<T>(588, message, data);
    }

}