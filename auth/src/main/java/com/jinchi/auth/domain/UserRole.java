package com.jinchi.auth.domain;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author：XudongHu
 * @description: 不解释
 * @date:15:35 2018/11/14
 */
@ApiModel(description = "用户角色实体")
public class UserRole {

    @ApiModelProperty("用户角色主键")
    private Integer id;

    @ApiModelProperty("用户id外键")
    private Integer userId;

    @ApiModelProperty("角色id外键")
    private Integer roleId;

    public UserRole(Integer userId, Integer roleId) {
        this.userId = userId;
        this.roleId = roleId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "UserRole{" +
                "id=" + id +
                ", userId=" + userId +
                ", roleId=" + roleId +
                '}';
    }
}
