package com.zt.manage.mapper;


import com.zt.manage.domain.dto.menu.UserMenuDTO;
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
    List<UserMenuDTO> selectMenuByRoleIds(@Param("roleIds") List<Integer> roleIds);
}
