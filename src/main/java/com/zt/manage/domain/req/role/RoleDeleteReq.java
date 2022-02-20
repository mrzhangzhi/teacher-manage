package com.zt.manage.domain.req.role;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author mrzhang
 */
@Data
public class RoleDeleteReq {

    @NotNull
    private Integer roleId;
}
