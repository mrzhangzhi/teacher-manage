package com.zt.manage.domain.dto.menu;

import lombok.Data;

import java.util.List;

@Data
public class UserMenuDTO {

    private Integer id;

    private String menuName;

    private String menuPath;

    private Integer menuSort;

    private List<ChildMenuDTO> childMenuList;
}
