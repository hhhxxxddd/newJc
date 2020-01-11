package com.jinchi.common.domain;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

/**
 * @author ZSCDumin
 * @version 1.0
 * @description: 技术基本原材料
 * @date 2018/12/21 15:38
 */
public class TechniqueBaseRawMaterial {
    @ApiModelProperty("主键")
    private Integer id;

    @ApiModelProperty("名称")
    @NotBlank(message = "原材料名称不能为空")
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "TechniqueBaseRawMaterial{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
