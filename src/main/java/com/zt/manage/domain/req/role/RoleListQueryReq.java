package com.zt.manage.domain.req.role;

import com.zt.manage.domain.req.PageReq;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class RoleListQueryReq extends PageReq {

    private String query;
}
