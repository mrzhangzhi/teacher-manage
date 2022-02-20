package com.zt.manage.domain.req.user;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserDeleteReq {

    @NotBlank
    private String userId;
}