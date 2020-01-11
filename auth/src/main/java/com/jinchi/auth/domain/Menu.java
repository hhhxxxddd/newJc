package com.jinchi.auth.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

/**
 * 说明: 菜单实体类
 * <br>
 *
 * @author huxudong
 *
 * 日期: 2018/11/12
 * <br>
 * 版本: 1.0
 */
@ApiModel(description = "菜单实体")
public class Menu {
    @ApiModelProperty("菜单主键,自增长")
    private Integer id;

    @ApiModelProperty("菜单名称")
    @NotBlank(message = "菜单名称不能为空")
    private String menuName;

    //1是一级菜单,2是二级菜单,3是三级菜单,以此类推
    @ApiModelProperty("菜单类型")
    @Min(value = 1,message = "菜单类型为大于等于1的整数")
    private Integer menuType;

    //父类菜单，没有父菜单则为-1
    @ApiModelProperty("菜单的父类")
    private Integer parent;

    @ApiModelProperty("菜单的简称")
    private String prefix;

    @ApiModelProperty("路径")
    private String path;

    public Integer getId() {
        return id;
    }

    public Menu setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getMenuName() {
        return menuName;
    }

    public Menu setMenuName(String menuName) {
        this.menuName = menuName;
        return this;
    }

    public Integer getMenuType() {
        return menuType;
    }

    public Menu setMenuType(Integer menuType) {
        this.menuType = menuType;
        return this;
    }

    public Integer getParent() {
        return parent;
    }

    public Menu setParent(Integer parent) {
        this.parent = parent;
        return this;
    }

    public String getPrefix() {
        return prefix;
    }

    public Menu setPrefix(String prefix) {
        this.prefix = prefix;
        return this;
    }

    public String getPath() {
        return path;
    }

    public Menu setPath(String path) {
        this.path = path;
        return this;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", menuName='" + menuName + '\'' +
                ", menuType=" + menuType +
                ", parent=" + parent +
                ", prefix='" + prefix + '\'' +
                ", path='" + path + '\'' +
                '}';
    }
}
