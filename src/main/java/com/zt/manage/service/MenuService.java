package com.zt.manage.service;

import com.google.common.collect.Lists;
import com.zt.manage.constants.CommonConstant;
import com.zt.manage.domain.dto.menu.MenuDTO;
import com.zt.manage.domain.dto.menu.ParentMenuDTO;
import com.zt.manage.domain.req.menu.MenuListQueryReq;
import com.zt.manage.domain.req.role.RoleMenuReq;
import com.zt.manage.domain.resp.PageResp;
import com.zt.manage.mapper.MenuMapper;
import com.zt.manage.mapper.UserMapper;
import com.zt.manage.service.inner.MenuInnerService;
import com.zt.manage.utils.UserInfoUtil;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @author mrzhang
 */
@Service
public class MenuService {

    @Resource
    private MenuMapper menuMapper;
    @Resource
    private UserMapper userMapper;
    @Resource
    private MenuInnerService menuInnerService;

    public List<ParentMenuDTO> selectUserMenuList() {
        List<Integer> roleIds = userMapper.selectRoleByUserId(UserInfoUtil.getUserId());
        if (CollectionUtils.isEmpty(roleIds)) {
            return Lists.newArrayList();
        }
        //判断是否为管理员
        if (roleIds.contains(CommonConstant.ADMIN_ROLE_ID)) {
            roleIds = Lists.newArrayList();
        }
        List<MenuDTO> menuList = menuMapper.selectMenuByRoleIds(roleIds);
        if (CollectionUtils.isEmpty(menuList)) {
            return Lists.newArrayList();
        }
        return menuInnerService.formatMenu(menuList);
    }

    public PageResp<ParentMenuDTO> selectMenuList(MenuListQueryReq req) {
        PageResp<ParentMenuDTO> resp = new PageResp<>();
        resp.setPageNum(req.getPageNum());
        int total = menuMapper.selectCountByQuery(req);
        resp.setTotal(total);
        if (total == 0) {
            return resp;
        }
        List<MenuDTO> list = menuMapper.selectListByQuery(req);
        if (CollectionUtils.isEmpty(list)) {
            return resp;
        }
        List<MenuDTO> secondMenuList = menuMapper.selectListByParentIdList(list.stream().map(MenuDTO::getId).collect(Collectors.toList()));
        if (CollectionUtils.isNotEmpty(secondMenuList)) {
            list.addAll(secondMenuList);
            List<MenuDTO> threeMenuList = menuMapper.selectListByParentIdList(secondMenuList.stream().map(MenuDTO::getId).collect(Collectors.toList()));
            if (CollectionUtils.isNotEmpty(threeMenuList)) {
                list.addAll(threeMenuList);
            }
        }
        resp.setData(menuInnerService.formatMenu(list));
        return resp;
    }

    public List<ParentMenuDTO> selectRoleMenuList(RoleMenuReq req) {
        List<MenuDTO> menuList = menuMapper.selectMenuByRoleId(req.getRoleId());
        if (CollectionUtils.isEmpty(menuList)) {
            return Lists.newArrayList();
        }
        return menuInnerService.formatMenu(menuList);
    }
}
