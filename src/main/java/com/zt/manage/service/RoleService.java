package com.zt.manage.service;

import com.zt.manage.constants.CommonConstant;
import com.zt.manage.domain.dto.role.UserRoleDTO;
import com.zt.manage.domain.pojo.role.SysRole;
import com.zt.manage.domain.pojo.user.SysUser;
import com.zt.manage.domain.req.role.*;
import com.zt.manage.domain.req.user.UserRoleListReq;
import com.zt.manage.domain.resp.PageResp;
import com.zt.manage.mapper.RoleMapper;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * @author mrzhang
 */
@Service
public class RoleService {

    @Resource
    private RoleMapper roleMapper;

    public List<UserRoleDTO> selectUserRoleList(UserRoleListReq req) {
        return roleMapper.selectRoleList(req.getUserId());
    }

    public PageResp<SysUser> selectListByQuery(RoleListQueryReq req) {
        PageResp<SysUser> resp = new PageResp<>();
        resp.setPageNum(req.getPageNum());
        int total = roleMapper.selectCountByQuery(req);
        resp.setTotal(total);
        if (total == 0) {
            return resp;
        }
        List<SysUser> list = roleMapper.selectListByQuery(req);
        resp.setData(list);
        return resp;
    }

    public int insert(RoleInsertReq req) {
        return roleMapper.insert(new SysRole(req.getRoleName(), req.getRoleDesc()));
    }

    public int update(RoleUpdateReq req) {
        SysRole sysRole = new SysRole(req.getRoleName(), req.getRoleDesc());
        sysRole.setId(req.getId());
        return roleMapper.update(sysRole);
    }

    public int delete(RoleDeleteReq req) {
        int count = roleMapper.selectUserCountByRoleId(req.getRoleId());
        if (count != CommonConstant.ZERO) {
            return CommonConstant.ZERO;
        }
        roleMapper.delete(req.getRoleId());
        return CommonConstant.ONE;
    }

    public boolean updateRoleMenu(UpdateRoleMenuReq req) {
        roleMapper.deleteRoleMenu(req.getRoleId());
        if (CollectionUtils.isNotEmpty(req.getMenuList())) {
            roleMapper.insertRoleMenu(req.getRoleId(), req.getMenuList());
        }
        return true;
    }
}
