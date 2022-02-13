package com.zt.manage.constants;

/**
 * 缓存key常量
 *
 * @author mrzhang
 */
public class CacheConstant {

    /**
     * 半小时
     */
    public static final long HALF_AN_HOUR = 60 * 30;

    /**
     * 1小时
     */
    public static final long ONE_HOUR = 60 * 60;

    /**
     * 1天
     */
    public static final long ONE_DAY = 60 * 60 * 24;


    /**
     * 登陆token缓存
     */
    public static final String TOKEN_REDIS_KEY = "AUTH_TOKEN_%s";

    /**
     * 用户信息缓存
     */
    public static final String USER_INFO_KEY = "USER_INFO_%s";

}
