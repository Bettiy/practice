package com.betty.practice.utils.result;

/**
 * @author Betty
 * @date 2021年04月26日
 */
public interface ResultInfo {
    /**
     * 状态码
     */
    Integer getResultCode();

    /**
     * 描述
     */
    String getResultMsg();
}
