package com.zt.manage.interceptors;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.zt.manage.annotations.PassToken;
import com.zt.manage.domain.pojo.user.User;
import com.zt.manage.domain.resp.ResultResp;
import com.zt.manage.enums.ResultCodeEnum;
import com.zt.manage.service.UserService;
import com.zt.manage.utils.JWTUtil;
import com.zt.manage.utils.JsonUtils;
import com.zt.manage.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;

public class AuthenticationInterceptor implements HandlerInterceptor {
    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
        // 从 http 请求头中取出 token
        String token = request.getHeader("Authorization");
        // 如果不是映射到方法直接通过
        if (!(object instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) object;
        Method method = handlerMethod.getMethod();
        //检查是否有passtoken注释，有则跳过认证
        if (method.isAnnotationPresent(PassToken.class)) {
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (passToken.required()) {
                return true;
            }
        }
        //检查有没有需要用户权限的注解
//        if (method.isAnnotationPresent(UserLoginToken.class)) {
//            UserLoginToken userLoginToken = method.getAnnotation(UserLoginToken.class);
//            if (userLoginToken.required()) {
        // 执行认证
        if (token == null) {
            result(response, ResultUtil.error(ResultCodeEnum.USER_TOKEN_ERROR));
            return false;
        }
        DecodedJWT tokenInfo = JWTUtil.getTokenInfo(token);
        if (tokenInfo != null) {
            String userId = JWTUtil.getUserId(tokenInfo);
            User user = userService.selectByUserId(userId);
            //验证是否修改过密码
            if (!JWTUtil.isUpdatedPassword(tokenInfo, user)) {
                //如果需要重新创建一个token 则通知客户端保存新的token 并且将新的token返回
                if (JWTUtil.needCreate(tokenInfo)) {
                    JSONObject tokenJson = new JSONObject();
                    tokenJson.put("token", JWTUtil.createToken(user));
                    result(response, ResultUtil.build(ResultCodeEnum.OK,tokenJson));
                    return false;
                } else {
                    return true;
                }
            }
        }
        //验证未通过
        result(response, ResultUtil.error(ResultCodeEnum.USER_TOKEN_ERROR));
        return false;
    }

    private void result(HttpServletResponse response, ResultResp result) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        response.setStatus(200);
        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.append(JsonUtils.toJSONString(result));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest,
                           HttpServletResponse httpServletResponse,
                           Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest,
                                HttpServletResponse httpServletResponse,
                                Object o, Exception e) throws Exception {
    }
}
