package com.zt.manage.domain.dto.menu;

import lombok.Data;

@Data
public class ChildMenuDTO {

    private Integer id;

    private String menuName;

    private String menuPath;

    private Integer menuSort;

}
