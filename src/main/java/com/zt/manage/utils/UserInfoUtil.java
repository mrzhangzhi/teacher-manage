package com.zt.manage.utils;

/**
 * 用户信息获取工具
 *
 * @author
 */
public class UserInfoUtil {

    private static final ThreadLocal<String> USER_THREAD_LOCAL = new ThreadLocal<>();


    public static void setUserId(String userId) {
        USER_THREAD_LOCAL.set(userId);
    }

    public static String getUserId() {
        return USER_THREAD_LOCAL.get();
    }


    public static void deleteUserInfo() {
        USER_THREAD_LOCAL.remove();
    }

}
