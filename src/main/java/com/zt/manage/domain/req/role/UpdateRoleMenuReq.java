package com.zt.manage.domain.req.role;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;


@Data
public class UpdateRoleMenuReq {

    @NotNull
    private Integer roleId;

    @NotEmpty
    private List<Integer> menuList;
}
