package com.jinchi.common.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author：XudongHu
 * @className:UserDTO
 * @description:
 * @date:13:00 2018/11/21
 */
@ApiModel(description = "权限模块用户")
public class AuthUserDTO implements Serializable {
    @ApiModelProperty("用户id")
    private Integer id;

    @ApiModelProperty("用户名称")
    private String name;

    @ApiModelProperty("部门id")
    private Integer departmentId;

    @ApiModelProperty("部门名称")
    private String departmentName;

    @ApiModelProperty("手机号码")
    private String phone;

    public Integer getId() {
        return id;
    }

    public AuthUserDTO setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public AuthUserDTO setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public AuthUserDTO setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
        return this;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public AuthUserDTO setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public AuthUserDTO setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    @Override
    public String toString() {
        return "AuthUserDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", departmentId=" + departmentId +
                ", departmentName='" + departmentName + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
