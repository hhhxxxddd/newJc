package com.jinchi.auth.dto;

/**
 * @author：XudongHu
 * @description: 不解释
 * @date:22:47 2018/11/13
 */
public class SimpleRoleDTO {
    private Integer roleId;
    private String roleName;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String toString() {
        return "SimpleRoleDTO{" +
                "roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                '}';
    }
}
