package com.zt.manage.domain.pojo.menu;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.util.Date;

@Alias("menu")
@Data
public class SysMenu {

    private Integer id;

    private Integer parentId;

    private String menuName;

    private String menuPath;

    private String menuType;

    private String menuSort;

    private Date createTime;

}
