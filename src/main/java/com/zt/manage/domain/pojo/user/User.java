package com.zt.manage.domain.pojo.user;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.util.Date;

/**
 * @author mrzhang
 */
@Alias("user")
@Data
public class User {

    private Integer id;

    private String userId;

    private String userName;

    private String phoneNo;

    private String password;

    private Integer lockStatus;

    private Date createTime;

    private Date updateTime;
}
