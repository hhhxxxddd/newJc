package com.jinchi.common.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author：XudongHu
 * @className:AuthRoleDTO
 * @description: 权限模块, 角色DTO
 * @date:22:26 2018/12/7
 */
public class AuthRoleDTO {

    @ApiModelProperty("角色主键")
    private Integer roleId;

    @ApiModelProperty("角色名称")
    private String roleName;

    @ApiModelProperty("角色描述")
    private String description;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "AuthRoleDTO{" +
                "roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AuthRoleDTO)) return false;
        AuthRoleDTO that = (AuthRoleDTO) o;

        if (getRoleId() != null ? !getRoleId().equals(that.getRoleId()) : that.getRoleId() != null) return false;
        if (getRoleName() != null ? !getRoleName().equals(that.getRoleName()) : that.getRoleName() != null)
            return false;
        return getDescription() != null ? getDescription().equals(that.getDescription()) : that.getDescription() == null;
    }

    @Override
    public int hashCode() {
        int result = getRoleId() != null ? getRoleId().hashCode() : 0;
        result = 31 * result + (getRoleName() != null ? getRoleName().hashCode() : 0);
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        return result;
    }
}
