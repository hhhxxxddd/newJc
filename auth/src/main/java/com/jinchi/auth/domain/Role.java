package com.jinchi.auth.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

/**
 * @author：XudongHu
 * @description: 不解释
 * @date:20:59 2018/10/29
 */
@ApiModel(description = "角色实体")
public class Role {
    @ApiModelProperty("用户主键,自增长")
    private Integer id;


    @ApiModelProperty("角色名称")
    @NotNull(message = "角色名称不能为空")
    private String roleName;

    @ApiModelProperty("角色描述")
    private String description;

    public Integer getId() {
        return id;
    }

    public Role setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getRoleName() {
        return roleName;
    }

    public Role setRoleName(String roleName) {
        this.roleName = roleName;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Role setDescription(String description) {
        this.description = description;
        return this;
    }
}
