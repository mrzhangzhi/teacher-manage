package com.zt.manage.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.zt.manage.domain.pojo.user.SysUser;

import java.util.Calendar;
import java.util.Date;

/**
 * @author
 */
public class JWTUtil {

    /**
     * 加密secret
     */
    private static final String SECRET = "ASD@Dik921c8qwinw19";
    /**
     * 过期时间
     */
    private static final Integer TIME_OUT_DAY = 30;
    /**
     * 需要重新生成的天数 如果token的时间超过这个 则重新生成token
     */
    private static final Integer NEED_CREATE_DAY = 7;

    /**
     * 生成token
     *
     * @param sysUser
     * @return
     */
    public static String createToken(SysUser sysUser) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, TIME_OUT_DAY);
        return JWT.create()
                .withClaim("userId", sysUser.getUserId())
                .withClaim("password", sysUser.getPassword())
                .withExpiresAt(calendar.getTime())
                .sign(Algorithm.HMAC256(SECRET));
    }


    /**
     * 获取token信息 如果token有误则返回null
     *
     * @param token
     * @return
     */
    public static DecodedJWT getTokenInfo(String token) {
        try {
            return JWT.require(Algorithm.HMAC256(SECRET)).build().verify(token);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 获取用户ID
     *
     * @param decodedJWT
     * @return
     */
    public static String getUserId(DecodedJWT decodedJWT) {
        return decodedJWT.getClaim("userId").asString();
    }


    /**
     * 验证是否修改过密码
     *
     * @param decodedJWT
     * @param sysUser
     * @return
     */
    public static boolean isUpdatedPassword(DecodedJWT decodedJWT, SysUser sysUser) {
        String oldPwd = decodedJWT.getClaim("password").asString();
        return !oldPwd.equals(sysUser.getPassword());
    }


    /**
     * 是否需要重新生成token （为了延续token时长）
     *
     * @param decodedJWT
     * @return
     */
    public static boolean needCreate(DecodedJWT decodedJWT) {
        Date timeoutDate = decodedJWT.getExpiresAt();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, TIME_OUT_DAY - NEED_CREATE_DAY);
        if (timeoutDate.before(calendar.getTime())) {
            return true;
        }
        return false;
    }

}
