package com.jinchi.auth.dto;


import java.util.List;

/**
 * @author：XudongHu
 * @description: 登陆时需要返回给前端的DTO
 *               {
 *                   userid;
 *                   username;
 *                   menuList[];
 *               }
 * @date:13:29 2018/11/13
 */
public class LoginInitialDTO {
    private Integer userId;
    private String name;
    private String username;
    //所有拥有的角色
    List<SimpleRoleDTO> RoleList;
    //所有拥有的菜单
    List<MenuDTO> menuList;

    public Integer getUserId() {
        return userId;
    }

    public LoginInitialDTO setUserId(Integer userId) {
        this.userId = userId;
        return this;
    }

    public String getName() {
        return name;
    }

    public LoginInitialDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public LoginInitialDTO setUsername(String username) {
        this.username = username;
        return this;
    }

    public List<SimpleRoleDTO> getRoleList() {
        return RoleList;
    }

    public LoginInitialDTO setRoleList(List<SimpleRoleDTO> roleList) {
        RoleList = roleList;
        return this;
    }

    public List<MenuDTO> getMenuList() {
        return menuList;
    }

    public LoginInitialDTO setMenuList(List<MenuDTO> menuList) {
        this.menuList = menuList;
        return this;
    }

    @Override
    public String toString() {
        return "LoginInitialDTO{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", RoleList=" + RoleList +
                ", menuList=" + menuList +
                '}';
    }
}
