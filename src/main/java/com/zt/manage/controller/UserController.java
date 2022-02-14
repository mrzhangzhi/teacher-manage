package com.zt.manage.controller;

import com.zt.manage.annotations.PassToken;
import com.zt.manage.constants.CommonConstant;
import com.zt.manage.constants.RespMsgConstant;
import com.zt.manage.domain.pojo.user.User;
import com.zt.manage.domain.req.user.LoginReq;
import com.zt.manage.domain.req.user.UserListQueryReq;
import com.zt.manage.domain.resp.ResultResp;
import com.zt.manage.enums.ResultCodeEnum;
import com.zt.manage.service.UserService;
import com.zt.manage.utils.JWTUtil;
import com.zt.manage.utils.ResultUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 登陆服务
 *
 * @author
 */
@CrossOrigin
@RestController
public class UserController {

    @Resource
    private UserService userService;

    @PassToken
    @PostMapping("/login")
    public ResultResp login(@RequestBody LoginReq req) {
        User user = userService.login(req);
        if (user == null) {
            return ResultUtil.error(ResultCodeEnum.USER_LOGIN_ERROR);
        }
        if (CommonConstant.ONE == user.getLockStatus()) {
            return ResultUtil.error(ResultCodeEnum.USER_IS_LOCK);
        }
        return ResultUtil.build(ResultCodeEnum.OK.code, RespMsgConstant.LOGIN_OK, JWTUtil.createToken(user));
    }

    @PostMapping("/userList")
    public ResultResp userService(@RequestBody UserListQueryReq req) {
        return ResultUtil.build(ResultCodeEnum.OK.code, RespMsgConstant.LOGIN_OK, userService.selectListByQuery(req));
    }

}
