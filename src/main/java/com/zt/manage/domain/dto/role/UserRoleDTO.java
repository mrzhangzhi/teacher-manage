package com.zt.manage.domain.dto.role;

import lombok.Data;

@Data
public class UserRoleDTO {

    private Integer roleId;

    /**
     * 用户是否有该角色
     */
    private Integer selectFlag;

    private String roleName;

    private String roleDesc;
}
