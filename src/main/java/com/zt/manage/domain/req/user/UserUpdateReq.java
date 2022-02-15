package com.zt.manage.domain.req.user;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserUpdateReq {

    @NotBlank
    private String userId;

    @NotBlank
    private String userName;
}
