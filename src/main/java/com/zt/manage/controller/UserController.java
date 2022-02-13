package com.zt.manage.controller;

import com.zt.manage.constants.CommonConstant;
import com.zt.manage.constants.RespMsgConstant;
import com.zt.manage.domain.pojo.user.User;
import com.zt.manage.domain.req.user.LoginReq;
import com.zt.manage.domain.resp.ResultResp;
import com.zt.manage.enums.ResultCodeEnum;
import com.zt.manage.service.UserService;
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

    @PostMapping("/login")
    public ResultResp<String> login(@RequestBody LoginReq req) {
        User user = userService.login(req);
        if (user == null) {
            return ResultUtil.error(ResultCodeEnum.USER_LOGIN_ERROR);
        }
        if (CommonConstant.ONE == user.getLockStatus()) {
            return ResultUtil.error(ResultCodeEnum.USER_IS_LOCK);
        }
        return ResultUtil.build(ResultCodeEnum.OK.code, RespMsgConstant.LOGIN_OK, "1111");
    }
}
