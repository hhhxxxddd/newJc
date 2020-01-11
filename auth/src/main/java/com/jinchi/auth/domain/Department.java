package com.jinchi.auth.domain;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

@ApiModel(description = "部门实体")
public class Department {

    @ApiModelProperty("部门主键,自增长")
    private Integer id;

    @ApiModelProperty("部门名称")
    @NotBlank(message = "部门名称不能为空")
    private String departmentName;

    @ApiModelProperty("额外信息")
    private String extraInfo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getExtraInfo() {
        return extraInfo;
    }

    public void setExtraInfo(String extraInfo) {
        this.extraInfo = extraInfo;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
}