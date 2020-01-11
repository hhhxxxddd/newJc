package com.jinchi.auth.dto;


import com.jinchi.auth.domain.Menu;
import com.jinchi.auth.domain.Operation;

import java.util.List;

/**
 * @author：XudongHu
 * @description: 菜单的DTO(父菜单带子菜单)
 * @date:13:46 2018/11/13
 */
public class MenuDTO {
    private Integer menuId;
    private String menuName;
    private Integer menuType;
    private Integer parent;
    private String parentName;
    private String path;
    private List<MenuDTO> menuList;
    private List<Operation> operations;

    public MenuDTO() {
    }

    public MenuDTO(Menu menu){
        this.menuId = menu.getId();
        this.menuName = menu.getMenuName();
        this.menuType = menu.getMenuType();
        this.parent = menu.getParent();
        this.path = menu.getPath();
    }

    public Integer getMenuId() {
        return menuId;
    }

    public MenuDTO setMenuId(Integer menuId) {
        this.menuId = menuId;
        return this;
    }

    public String getMenuName() {
        return menuName;
    }

    public MenuDTO setMenuName(String menuName) {
        this.menuName = menuName;
        return this;
    }

    public Integer getMenuType() {
        return menuType;
    }

    public MenuDTO setMenuType(Integer menuType) {
        this.menuType = menuType;
        return this;
    }

    public Integer getParent() {
        return parent;
    }

    public MenuDTO setParent(Integer parent) {
        this.parent = parent;
        return this;
    }

    public String getParentName() {
        return parentName;
    }

    public MenuDTO setParentName(String parentName) {
        this.parentName = parentName;
        return this;
    }

    public String getPath() {
        return path;
    }

    public MenuDTO setPath(String path) {
        this.path = path;
        return this;
    }

    public List<MenuDTO> getMenuList() {
        return menuList;
    }

    public MenuDTO setMenuList(List<MenuDTO> menuList) {
        this.menuList = menuList;
        return this;
    }

    public List<Operation> getOperations() {
        return operations;
    }

    public MenuDTO setOperations(List<Operation> operations) {
        this.operations = operations;
        return this;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MenuDTO)) return false;

        MenuDTO menuDTO = (MenuDTO) o;

        if (getMenuId() != null ? !getMenuId().equals(menuDTO.getMenuId()) : menuDTO.getMenuId() != null) return false;
        if (getMenuName() != null ? !getMenuName().equals(menuDTO.getMenuName()) : menuDTO.getMenuName() != null)
            return false;
        if (getMenuType() != null ? !getMenuType().equals(menuDTO.getMenuType()) : menuDTO.getMenuType() != null)
            return false;
        if (getParent() != null ? !getParent().equals(menuDTO.getParent()) : menuDTO.getParent() != null) return false;
        if (getParentName() != null ? !getParentName().equals(menuDTO.getParentName()) : menuDTO.getParentName() != null)
            return false;

        return getPath() != null ? getPath().equals(menuDTO.getPath()) : menuDTO.getPath() == null;
    }

    @Override
    public int hashCode() {
        int result = getMenuId() != null ? getMenuId().hashCode() : 0;
        result = 31 * result + (getMenuName() != null ? getMenuName().hashCode() : 0);
        result = 31 * result + (getMenuType() != null ? getMenuType().hashCode() : 0);
        result = 31 * result + (getParent() != null ? getParent().hashCode() : 0);
        result = 31 * result + (getParentName() != null ? getParentName().hashCode() : 0);
        result = 31 * result + (getPath() != null ? getPath().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "MenuDTO{" +
                "menuId=" + menuId +
                ", menuName='" + menuName + '\'' +
                ", menuType=" + menuType +
                ", parent=" + parent +
                ", parentName='" + parentName + '\'' +
                ", path='" + path + '\'' +
                ", menuList=" + menuList +
                ", operations=" + operations +
                '}';
    }
}
