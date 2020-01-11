package com.jinchi.common.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author：XudongHu
 * @className:AuthDepartmentDTO
 * @description: 权限 部门DTO
 * @date:22:52 2019/3/8
 * @Modifer:
 */
public class AuthDepartmentDTO {
    @ApiModelProperty("主键")
    private Integer id;
    @ApiModelProperty("部门名")
    private String departmentName;

    public Integer getId() {
        return id;
    }

    public AuthDepartmentDTO setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public AuthDepartmentDTO setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
        return this;
    }

    @Override
    public String toString() {
        return "AuthDepartmentDTO{" +
                "id=" + id +
                ", departmentName='" + departmentName + '\'' +
                '}';
    }
}
