package com.zt.manage.domain.dto.role;

import lombok.Data;

import java.util.Date;

@Data
public class RoleDTO {

    private Integer roleId;

    private String roleName;

    private String roleDesc;

    private Date createTime;

}
