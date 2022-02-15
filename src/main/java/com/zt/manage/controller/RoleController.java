package com.zt.manage.controller;

import com.zt.manage.domain.req.role.RoleListQueryReq;
import com.zt.manage.domain.req.role.RoleMenuReq;
import com.zt.manage.domain.resp.ResultResp;
import com.zt.manage.enums.ResultCodeEnum;
import com.zt.manage.service.MenuService;
import com.zt.manage.service.RoleService;
import com.zt.manage.utils.ResultUtil;
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
    public ResultResp roleMenuList(@RequestBody RoleMenuReq req) {
        return ResultUtil.build(ResultCodeEnum.OK, menuService.selectRoleMenuList(req));
    }
}
