package com.zt.manage.mapper;


import com.zt.manage.domain.dto.menu.MenuDTO;
import com.zt.manage.domain.req.menu.MenuListQueryReq;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author mrzhang
 */
public interface MenuMapper {

    /**
     * 根据权限id查询菜单信息
     *
     * @param roleIds
     * @return
     */
    List<MenuDTO> selectMenuByRoleIds(@Param("roleIds") List<Integer> roleIds);

    /**
     * 根据权限id查询菜单信息
     *
     * @param roleId
     * @return
     */
    List<MenuDTO> selectMenuByRoleId(@Param("roleId") Integer roleId);

    /**
     * 查询菜单总数
     *
     * @param req
     * @return
     */
    int selectCountByQuery(MenuListQueryReq req);

    /**
     * 根据条件查询菜单列表
     *
     * @param req
     * @return
     */
    List<MenuDTO> selectListByQuery(MenuListQueryReq req);

    /**
     * 根据父菜单id集合查询菜单信息
     *
     * @param parentIdList
     * @return
     */
    List<MenuDTO> selectListByParentIdList(@Param("parentIdList") List<Integer> parentIdList);
}
