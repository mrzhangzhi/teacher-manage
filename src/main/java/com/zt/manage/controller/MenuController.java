package com.zt.manage.controller;

import com.zt.manage.domain.req.menu.MenuListQueryReq;
import com.zt.manage.domain.req.role.RoleListQueryReq;
import com.zt.manage.domain.resp.ResultResp;
import com.zt.manage.enums.ResultCodeEnum;
import com.zt.manage.service.MenuService;
import com.zt.manage.utils.ResultUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 菜单服务
 *
 * @author
 */
@CrossOrigin
@RequestMapping("/menu")
@RestController
public class MenuController {

    @Resource
    private MenuService menuService;

    @PostMapping("/list")
    public ResultResp menuList(@RequestBody MenuListQueryReq req) {
        return ResultUtil.build(ResultCodeEnum.OK, menuService.selectMenuList(req));
    }

    @PostMapping("/userMenuList")
    public ResultResp userMenuList() {
        return ResultUtil.build(ResultCodeEnum.OK, menuService.selectUserMenuList());
    }

}
