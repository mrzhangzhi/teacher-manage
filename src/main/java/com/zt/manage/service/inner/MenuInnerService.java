package com.zt.manage.service.inner;

import com.google.common.collect.Lists;
import com.zt.manage.constants.CommonConstant;
import com.zt.manage.domain.dto.menu.ButtonMenuDTO;
import com.zt.manage.domain.dto.menu.ChildMenuDTO;
import com.zt.manage.domain.dto.menu.MenuDTO;
import com.zt.manage.domain.dto.menu.ParentMenuDTO;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author mrzhang
 */
@Service
public class MenuInnerService {

    /**
     * 格式化目录结构
     *
     * @param menuList
     * @return
     */
    public List<ParentMenuDTO> formatMenu(List<MenuDTO> menuList) {
        List<ParentMenuDTO> list = Lists.newArrayList();
        if (CollectionUtils.isEmpty(menuList)) {
            return list;
        }
        Map<Integer, List<MenuDTO>> menuMap = menuList.stream().collect(Collectors.groupingBy(MenuDTO::getParentId));
        List<MenuDTO> parentMenuList = menuMap.get(CommonConstant.ZERO);
        for (MenuDTO parentMenu : parentMenuList) {
            ParentMenuDTO parentMenuDTO = new ParentMenuDTO();
            parentMenuDTO.setId(parentMenu.getId());
            parentMenuDTO.setMenuPath(parentMenu.getMenuPath());
            parentMenuDTO.setMenuName(parentMenu.getMenuName());
            parentMenuDTO.setMenuSort(parentMenu.getMenuSort());
            parentMenuDTO.setSelectFlag(parentMenu.getRoleMenuId() != null);
            //获取二级目录
            parentMenuDTO.setChildMenuList(getChildMenuList(menuMap, parentMenu));
            list.add(parentMenuDTO);
        }
        list = list.stream().sorted(Comparator.comparing(ParentMenuDTO::getMenuSort)).collect(Collectors.toList());
        return list;
    }

    /**
     * 获取二级目录集合
     *
     * @param menuMap
     * @param parentMenu
     * @return
     */
    private List<ChildMenuDTO> getChildMenuList(Map<Integer, List<MenuDTO>> menuMap, MenuDTO parentMenu) {
        List<ChildMenuDTO> childMenuList = Lists.newArrayList();
        List<MenuDTO> childMenus = menuMap.get(parentMenu.getId());
        if (CollectionUtils.isEmpty(childMenus)) {
            return childMenuList;
        }
        for (MenuDTO childMenu : childMenus) {
            ChildMenuDTO childMenuDTO = new ChildMenuDTO();
            childMenuDTO.setId(childMenu.getId());
            childMenuDTO.setMenuPath(childMenu.getMenuPath());
            childMenuDTO.setMenuName(childMenu.getMenuName());
            childMenuDTO.setMenuSort(childMenu.getMenuSort());
            childMenuDTO.setSelectFlag(childMenu.getRoleMenuId() != null);
            //获取三级目录
            childMenuDTO.setChildMenuList(getButtonMenuList(menuMap, childMenu));
            childMenuList.add(childMenuDTO);
        }
        return childMenuList.stream().sorted(Comparator.comparing(ChildMenuDTO::getMenuSort)).collect(Collectors.toList());
    }

    /**
     * 获取三级目录集合
     *
     * @param menuMap
     * @param childMenu
     * @return
     */
    private List<ButtonMenuDTO> getButtonMenuList(Map<Integer, List<MenuDTO>> menuMap, MenuDTO childMenu) {
        List<ButtonMenuDTO> buttonMenuList = Lists.newArrayList();
        List<MenuDTO> buttonMenus = menuMap.get(childMenu.getId());
        if (CollectionUtils.isEmpty(buttonMenus)) {
            return buttonMenuList;
        }
        for (MenuDTO buttonMenu : buttonMenus) {
            ButtonMenuDTO buttonMenuDTO = new ButtonMenuDTO();
            buttonMenuDTO.setId(buttonMenu.getId());
            buttonMenuDTO.setMenuPath(buttonMenu.getMenuPath());
            buttonMenuDTO.setMenuName(buttonMenu.getMenuName());
            buttonMenuDTO.setMenuSort(buttonMenu.getMenuSort());
            buttonMenuDTO.setSelectFlag(buttonMenu.getRoleMenuId() != null);
            buttonMenuList.add(buttonMenuDTO);
        }
        return buttonMenuList.stream().sorted(Comparator.comparing(ButtonMenuDTO::getMenuSort)).collect(Collectors.toList());
    }
}
