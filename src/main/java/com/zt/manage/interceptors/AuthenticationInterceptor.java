package com.zt.manage.interceptors;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.zt.manage.annotations.PassToken;
import com.zt.manage.constants.CommonConstant;
import com.zt.manage.domain.pojo.user.SysUser;
import com.zt.manage.domain.resp.ResultResp;
import com.zt.manage.enums.ResultCodeEnum;
import com.zt.manage.service.UserService;
import com.zt.manage.utils.JWTUtil;
import com.zt.manage.utils.JsonUtils;
import com.zt.manage.utils.ResultUtil;
import com.zt.manage.utils.UserInfoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;

/**
 * 登陆拦截器
 */
public class AuthenticationInterceptor implements HandlerInterceptor {
    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) {
        // 从 http 请求头中取出 token
        String token = request.getHeader(CommonConstant.AUTH_TOKEN);
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
        // 执行认证
        if (token == null) {
            result(response, ResultUtil.error(ResultCodeEnum.USER_TOKEN_ERROR));
            return false;
        }
        DecodedJWT tokenInfo = JWTUtil.getTokenInfo(token);
        if (tokenInfo != null) {
            String userId = JWTUtil.getUserId(tokenInfo);
            SysUser sysUser = userService.selectByUserId(userId);

            //验证是否修改过密码
            if (sysUser != null) {
                //账号是否被锁定
                if (sysUser.getLockStatus() == CommonConstant.ONE) {
                    result(response, ResultUtil.error(ResultCodeEnum.USER_IS_LOCK));
                    return false;
                }
                //密码被修改
                if (JWTUtil.isUpdatedPassword(tokenInfo, sysUser)) {
                    result(response, ResultUtil.error(ResultCodeEnum.USER_PASSWORD_UPDATE));
                    return false;
                }
                //如果需要重新创建一个token 则通知客户端保存新的token 并且将新的token返回
                if (JWTUtil.needCreate(tokenInfo)) {
                    result(response, ResultUtil.error(ResultCodeEnum.USER_TOKEN_ERROR));
                    return false;
                }
                UserInfoUtil.setUserId(userId);
                return true;
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
        UserInfoUtil.deleteUserInfo();
    }
}
