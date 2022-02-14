package com.zt.manage.controller;

import com.zt.manage.domain.resp.ResultResp;
import com.zt.manage.enums.ResultCodeEnum;
import com.zt.manage.service.MenuService;
import com.zt.manage.utils.ResultUtil;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 菜单服务
 *
 * @author
 */
@CrossOrigin
@RestController
public class MenuController {

    @Resource
    private MenuService menuService;

    @PostMapping("/menuList")
    public ResultResp menuList() {
        return ResultUtil.build(ResultCodeEnum.OK, menuService.selectMenuList());
    }

}
