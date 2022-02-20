package com.zt.manage.domain.req.role;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author mrzhang
 */
@Data
public class RoleUpdateReq {

    @NotNull
    private Integer id;

    @NotBlank
    private String roleName;

    private String roleDesc;
}
