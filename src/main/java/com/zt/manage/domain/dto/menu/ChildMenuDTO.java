package com.zt.manage.domain.dto.menu;

import lombok.Data;

import java.util.List;

@Data
public class ChildMenuDTO {

    private Integer id;

    private String menuName;

    private String menuPath;

    private Integer menuSort;

    private boolean selectFlag;

    private List<ButtonMenuDTO> childMenuList;
}
