package com.zt.manage.utils;

import java.util.UUID;

public class UUIDUtil {

    /**
     * 获取uuid
     *
     * @return
     */
    public static String getUuid() {
        String id = UUID.randomUUID().toString();
        String uid = id.replaceAll("-", "");

        return uid;
    }
}
