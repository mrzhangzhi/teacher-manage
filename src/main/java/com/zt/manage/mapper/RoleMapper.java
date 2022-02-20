package com.zt.manage.mapper;


import com.zt.manage.domain.dto.role.UserRoleDTO;
import com.zt.manage.domain.pojo.role.SysRole;
import com.zt.manage.domain.pojo.user.SysUser;
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
    List<SysUser> selectListByQuery(RoleListQueryReq req);

    /**
     * 新增权限
     *
     * @param req
     * @return
     */
    int insert(SysRole req);

    /**
     * 编辑权限信息
     *
     * @param sysRole
     * @return
     */
    int update(SysRole sysRole);

    /**
     * 删除权限
     *
     * @param roleId
     */
    void delete(@Param("roleId") Integer roleId);

    /**
     * 查询权限绑定的用户数
     *
     * @param roleId
     * @return
     */
    int selectUserCountByRoleId(@Param("roleId") Integer roleId);

    /**
     * 根据权限id删除权限与菜单的关系
     *
     * @param roleId
     */
    void deleteRoleMenu(@Param("roleId") Integer roleId);

    /**
     * 批量新增权限与菜单的关系数据
     *
     * @param roleId
     * @param menuList
     */
    void insertRoleMenu(@Param("roleId") Integer roleId, @Param("menuList") List<Integer> menuList);
}
