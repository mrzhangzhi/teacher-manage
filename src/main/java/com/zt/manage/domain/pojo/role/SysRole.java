package com.zt.manage.domain.pojo.role;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.util.Date;

/**
 * @author mrzhang
 */
@Alias("role")
@Data
public class SysRole {

    private Integer id;

    private String roleName;

    private String roleDesc;

    private Date createTime;

    private Date updateTime;

    public SysRole() {
    }

    public SysRole(String roleName, String roleDesc) {
        this.roleName = roleName;
        this.roleDesc = roleDesc;
    }
}
