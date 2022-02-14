package com.zt.manage.domain.resp.user;

import lombok.Data;

import java.util.Date;

/**
 * @author mrzhang
 */
@Data
public class UserListResp {

    private String userId;

    private String userName;

    private String phoneNo;

    private Integer lockStatus;

    private Date createTime;
}
