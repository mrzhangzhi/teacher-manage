package com.zt.manage.domain.req.role;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class RoleMenuReq {

    @NotNull
    private Integer roleId;
}
