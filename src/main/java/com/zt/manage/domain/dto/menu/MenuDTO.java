package com.zt.manage.domain.dto.menu;

import lombok.Data;

@Data
public class MenuDTO {

    private Integer id;

    private Integer parentId;

    private String menuName;

    private String menuPath;

    private Integer menuType;

    private Integer menuSort;

    private Integer roleMenuId;

}
