package com.zt.manage.controller;

import com.zt.manage.constants.CommonConstant;
import com.zt.manage.domain.req.role.*;
import com.zt.manage.domain.resp.ResultResp;
import com.zt.manage.enums.ResultCodeEnum;
import com.zt.manage.service.MenuService;
import com.zt.manage.service.RoleService;
import com.zt.manage.utils.ResultUtil;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 登陆服务
 *
 * @author
 */
@CrossOrigin
@RequestMapping("/role")
@RestController
public class RoleController {

    @Resource
    private RoleService roleService;
    @Resource
    private MenuService menuService;

    @PostMapping("/list")
    public ResultResp roleList(@RequestBody RoleListQueryReq req) {
        return ResultUtil.build(ResultCodeEnum.OK, roleService.selectListByQuery(req));
    }

    @PostMapping("/roleMenuList")
    public ResultResp roleMenuList(@Validated @RequestBody RoleMenuReq req) {
        return ResultUtil.build(ResultCodeEnum.OK, menuService.selectRoleMenuList(req));
    }

    @PostMapping("/insert")
    public ResultResp insert(@Validated @RequestBody RoleInsertReq req) {
        return ResultUtil.build(ResultCodeEnum.OK, roleService.insert(req));
    }

    @PostMapping("/update")
    public ResultResp update(@Validated @RequestBody RoleUpdateReq req) {
        return ResultUtil.build(ResultCodeEnum.OK, roleService.update(req));
    }

    @PostMapping("/delete")
    public ResultResp delete(@Validated @RequestBody RoleDeleteReq req) {
        if (roleService.delete(req) == CommonConstant.ZERO) {
            return ResultUtil.error(ResultCodeEnum.ROLE_HAVE_USER);
        }
        return ResultUtil.build(ResultCodeEnum.OK, true);
    }

    @PostMapping("/updateRoleMenu")
    public ResultResp updateRoleMenu(@Validated @RequestBody UpdateRoleMenuReq req) {
        return ResultUtil.build(ResultCodeEnum.OK, roleService.updateRoleMenu(req));
    }
}
