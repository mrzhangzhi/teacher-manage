package com.zt.manage.controller;

import com.zt.manage.domain.req.user.*;
import com.zt.manage.domain.resp.ResultResp;
import com.zt.manage.enums.ResultCodeEnum;
import com.zt.manage.service.RoleService;
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
@RequestMapping("/user")
@RestController
public class UserController {

    @Resource
    private UserService userService;
    @Resource
    private RoleService roleService;

    @PostMapping("/list")
    public ResultResp userList(@RequestBody UserListQueryReq req) {
        return ResultUtil.build(ResultCodeEnum.OK, userService.selectListByQuery(req));
    }

    @PostMapping("/updateLockStatus")
    public ResultResp updateLockStatus(@RequestBody UserStatusUpdateReq req) {
        return ResultUtil.build(ResultCodeEnum.OK, userService.updateLockStatus(req));
    }

    @PostMapping("/roleList")
    public ResultResp userRoleList() {
        return ResultUtil.build(ResultCodeEnum.OK, roleService.selectUserRoleList());
    }

    @PostMapping("/insertInfo")
    public ResultResp updateInfo(@RequestBody UserInsertReq req) {
        return ResultUtil.build(ResultCodeEnum.OK, userService.insert(req));
    }

    @PostMapping("/updateInfo")
    public ResultResp updateInfo(@RequestBody UserUpdateReq req) {
        return ResultUtil.build(ResultCodeEnum.OK, userService.update(req));
    }

    @PostMapping("/updatePassword")
    public ResultResp updateInfo(@RequestBody UserPasswordUpdateReq req) {
        Integer count = userService.updatePassword(req);
        if (count == null) {
            return ResultUtil.error(ResultCodeEnum.USER_PASSWORD_ERROR);
        } else {
            return ResultUtil.build(ResultCodeEnum.OK, count);
        }
    }
}
