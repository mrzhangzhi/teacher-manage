package com.zt.manage.domain.dto.user;

import lombok.Data;

/**
 * @author mrzhang
 */
@Data
public class UserUpdateDTO {

    private String userId;

    private String userName;

    private String password;

    private Integer lockStatus;
}
