//package com.zt.manage.utils;
//
//import com.auth0.jwt.JWT;
//import com.auth0.jwt.JWTVerifier;
//import com.auth0.jwt.algorithms.Algorithm;
//import com.auth0.jwt.interfaces.Claim;
//import com.auth0.jwt.interfaces.DecodedJWT;
//import com.company.erp.constants.CacheConstant;
//import com.company.erp.constants.CommonConstant;
//import com.google.common.collect.Maps;
//
//import java.util.Calendar;
//import java.util.Date;
//import java.util.Map;
//
///**
// * @author
// */
//public class TokenUtils {
//
//    /**
//     * 密钥
//     */
//    public static final String SECRET = "cwffclihqlxoj;o634624";
//
//    public static String getToken(String userId) {
//        Calendar nowTime = Calendar.getInstance();
//        nowTime.add(Calendar.SECOND, (int) CacheConstant.HALF_AN_HOUR);
//        Date expireDate = nowTime.getTime();
//
//        Map<String, Object> map = Maps.newHashMap();
//        map.put("alg", "HS256");
//        map.put("typ", "JWT");
//
//        return JWT.create()
//                .withHeader(map)
//                .withClaim(CommonConstant.USER_ID, userId)
//                .withIssuedAt(new Date())
//                .withExpiresAt(expireDate)
//                .sign(Algorithm.HMAC256(SECRET));
//    }
//
//    /**
//     * 验证Token
//     *
//     * @param token
//     * @return
//     * @throws Exception
//     */
//    public static Map<String, Claim> verifyToken(String token) throws Exception {
//        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
//        DecodedJWT jwt = null;
//        try {
//            jwt = verifier.verify(token);
//        } catch (Exception e) {
//            throw new RuntimeException("凭证已过期，请重新登录");
//        }
//        return jwt.getClaims();
//    }
//}
