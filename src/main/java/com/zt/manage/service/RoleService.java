package com.zt.manage.service;

import com.zt.manage.domain.dto.role.RoleDTO;
import com.zt.manage.domain.dto.role.UserRoleDTO;
import com.zt.manage.domain.req.role.RoleListQueryReq;
import com.zt.manage.domain.resp.PageResp;
import com.zt.manage.mapper.RoleMapper;
import com.zt.manage.utils.UserInfoUtil;
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

    public List<UserRoleDTO> selectUserRoleList() {
        return roleMapper.selectRoleList(UserInfoUtil.getUserId());
    }

    public PageResp<RoleDTO> selectListByQuery(RoleListQueryReq req) {
        PageResp<RoleDTO> resp = new PageResp<>();
        resp.setPageNum(req.getPageNum());
        int total = roleMapper.selectCountByQuery(req);
        resp.setTotal(total);
        if (total == 0) {
            return resp;
        }
        List<RoleDTO> list = roleMapper.selectListByQuery(req);
        resp.setData(list);
        return resp;
    }
}
