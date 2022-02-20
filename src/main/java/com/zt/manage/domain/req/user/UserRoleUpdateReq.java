package com.zt.manage.domain.req.user;

import lombok.Data;

import java.util.List;

@Data
public class UserRoleUpdateReq {

    private String userId;

    private List<Integer> roleList;
 }
