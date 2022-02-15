package com.zt.manage.domain.req.user;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserInsertReq {

    @NotBlank
    private String userName;

    @NotBlank
    private String phoneNo;

    private String userId;

    private String password;
}
