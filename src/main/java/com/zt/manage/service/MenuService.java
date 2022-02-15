package com.zt.manage.service;

import com.google.common.collect.Lists;
import com.zt.manage.constants.CommonConstant;
import com.zt.manage.domain.dto.menu.MenuDTO;
import com.zt.manage.domain.dto.menu.UserMenuDTO;
import com.zt.manage.domain.req.role.RoleMenuReq;
import com.zt.manage.domain.resp.PageResp;
import com.zt.manage.mapper.MenuMapper;
import com.zt.manage.mapper.UserMapper;
import com.zt.manage.utils.UserInfoUtil;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * @author mrzhang
 */
@Service
public class MenuService {

    @Resource
    private MenuMapper menuMapper;
    @Resource
    private UserMapper userMapper;

    public List<UserMenuDTO> selectUserMenuList() {
        List<Integer> roleIds = userMapper.selectRoleByUserId(UserInfoUtil.getUserId());
        if (CollectionUtils.isEmpty(roleIds)) {
            return Lists.newArrayList();
        }
        //判断是否为管理员
        if (roleIds.contains(CommonConstant.ADMIN_ROLE_ID)) {
            roleIds = Lists.newArrayList();
        }
        return menuMapper.selectMenuByRoleIds(roleIds);
    }

    public PageResp<MenuDTO> selectMenuList() {
        //TODO
        return null;
    }

    public Object selectRoleMenuList(RoleMenuReq req) {

        return null;
    }
}
