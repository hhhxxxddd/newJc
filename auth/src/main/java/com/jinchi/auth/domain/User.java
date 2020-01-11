package com.jinchi.auth.domain;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * @author：XudongHu
 * @description: 用户实体
 * @date:10:42 2018/10/24
 */

@ApiModel(description = "用户实体")
public class User implements Serializable {
    @ApiModelProperty("用户主键,自增长")
    private Integer id;


    @ApiModelProperty("用户账号")
    private String username;

    @ApiModelProperty("所属部门")
    private Integer departmentId;

    @ApiModelProperty("密码")
    //默认密码123456
    private String password="123456";

    @ApiModelProperty("手机号码")
    private String phone;


    @ApiModelProperty("拥有的角色")
    private List<Role> roles;

    @ApiModelProperty("用户名称")
    private String name;


    @ApiModelProperty("id卡号")
    private String idCardNumber;

    public String getIdCardNumber() {
        return idCardNumber;
    }

    public void setIdCardNumber(String idCardNumber) {
        this.idCardNumber = idCardNumber;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", departmentId=" + departmentId +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", roles=" + roles +
                ", name='" + name + '\'' +
                '}';
    }
}
