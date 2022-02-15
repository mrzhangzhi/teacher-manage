package com.zt.manage.domain.req.user;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class UserStatusUpdateReq {

    @NotBlank
    private String userId;

    @NotNull
    private Integer lockStatus;
}
