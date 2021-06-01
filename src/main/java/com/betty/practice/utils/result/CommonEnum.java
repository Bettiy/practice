package com.betty.practice.utils.result;

/**
 * @author Betty
 * @date 2021年04月26日
 */
public enum CommonEnum implements ResultInfo {
    /*数据操作定义*/
    SUCCESS(200, "成功!"),
    BODY_NOT_MATCH(400,"请求的数据格式不符!"),
    SIGNATURE_NOT_MATCH(401,"请求的数字签名不匹配!"),
    METHOD_NOT_ALLOWED(405,"客户端请求中的方法被禁止!"),
    FORBIDDEN(403, "请求被拒绝!"),
    NOT_FOUND(404, "未找到该资源!"),
    INTERNAL_SERVER_ERROR(500, "服务器内部错误!"),
    SERVER_BUSY(503,"服务器正忙，请稍后再试!"),
    NOT_ALLOWED_METHOD(406, "方法不被允许")
    ;

    /** 状态码 */
    private Integer resultCode;

    /** 描述 */
    private String resultMsg;

    CommonEnum(Integer resultCode, String resultMsg) {
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
    }

    @Override
    public Integer getResultCode() {
        return resultCode;
    }

    @Override
    public String getResultMsg() {
        return resultMsg;
    }
}
