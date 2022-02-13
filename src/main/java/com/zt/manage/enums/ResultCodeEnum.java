package com.zt.manage.enums;

/**
 * 业务错误码枚举
 *
 * @author mrzhang
 */
public enum ResultCodeEnum {

    /**
     * 成功
     */
    OK(200, "操作成功！"),
    /**
     * 用户失败
     */
    USER_LOGIN_ERROR(20001, "登陆失败！"),
    /**
     * 账号已冻结
     */
    USER_IS_LOCK(20002, "账号已冻结，请联系管理员！"),
    /**
     * 用户权限不足
     */
    USER_AUTH_ERROR(20004, "用户权限不足！"),
    ;

    public int code;
    public String msg;

    ResultCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
