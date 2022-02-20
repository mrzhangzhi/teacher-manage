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
    /**
     * token过期
     */
    USER_TOKEN_ERROR(20005, "token过期！"),
    /**
     * 密码修改
     */
    USER_PASSWORD_UPDATE(20006, "密码被修改，请重新登录！"),
    /**
     * 密码错误
     */
    USER_PASSWORD_ERROR(20007, "密码错误！"),
    /**
     * 账号已存在
     */
    USER_IS_EXIST(20008, "账号已存在！"),
    /**
     * 权限存在绑定用户
     */
    ROLE_HAVE_USER(20008, "请先解绑所有用户！"),
    ;

    public int code;
    public String msg;

    ResultCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
