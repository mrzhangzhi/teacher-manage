package com.zt.manage.domain.pojo.user;

import lombok.Data;

import java.util.Date;

@Data
public class UserRoleMapperList {

    private Integer id;

    private String userId;

    private Integer roleId;

    private Date createTime;

    private Date updateTime;
}
