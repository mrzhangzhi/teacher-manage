package com.zt.manage.controller;

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
    public ResultResp menuList() {
        return ResultUtil.build(ResultCodeEnum.OK, menuService.selectMenuList());
    }

    @PostMapping("/userMenuList")
    public ResultResp userMenuList() {
        return ResultUtil.build(ResultCodeEnum.OK, menuService.selectUserMenuList());
    }

}
