package com.jinchi.auth.dto;

import com.jinchi.auth.domain.Operation;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author：XudongHu
 * @className:Authorities
 * @description: 权限类
 * @date:10:32 2019/3/29
 * @Modifer:
 */
@ApiModel(description = "权限类")
public class Authorities {
    @ApiModelProperty("菜单id")
    private Integer menuId;
    @ApiModelProperty("菜单名称")
    private String menuName;
    @ApiModelProperty("菜单前缀")
    private String prefix;
    @ApiModelProperty("操作集合")
    private List<Operation> operations;


    public Integer getMenuId() {
        return menuId;
    }

    public Authorities setMenuId(Integer menuId) {
        this.menuId = menuId;
        return this;
    }

    public String getMenuName() {
        return menuName;
    }

    public Authorities setMenuName(String menuName) {
        this.menuName = menuName;
        return this;
    }

    public String getPrefix() {
        return prefix;
    }

    public Authorities setPrefix(String prefix) {
        this.prefix = prefix;
        return this;
    }

    public List<Operation> getOperations() {
        return operations;
    }

    public Authorities setOperations(List<Operation> operations) {
        this.operations = operations;
        return this;
    }

    @Override
    public String toString() {
        return "Authorities{" +
                "menuId=" + menuId +
                ", menuName='" + menuName + '\'' +
                ", prefix='" + prefix + '\'' +
                ", operations=" + operations +
                '}';
    }
}
