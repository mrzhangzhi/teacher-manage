package com.zt.manage.mapper;


import com.zt.manage.domain.dto.role.RoleDTO;
import com.zt.manage.domain.dto.role.UserRoleDTO;
import com.zt.manage.domain.req.role.RoleListQueryReq;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author mrzhang
 */
public interface RoleMapper {

    /**
     * 查询权限列表
     *
     * @param userId 非必传
     * @return
     */
    List<UserRoleDTO> selectRoleList(@Param("userId") String userId);

    /**
     * 根据条件查询权限总数
     *
     * @param req
     * @return
     */
    int selectCountByQuery(RoleListQueryReq req);

    /**
     * 根据条件查询权限列表
     *
     * @param req
     * @return
     */
    List<RoleDTO> selectListByQuery(RoleListQueryReq req);
}
