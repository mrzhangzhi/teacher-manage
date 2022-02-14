package com.zt.manage.domain.req.user;

import com.zt.manage.domain.req.PageReq;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserListQueryReq extends PageReq {

    private String query;
}
