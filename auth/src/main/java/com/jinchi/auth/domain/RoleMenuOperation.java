package com.jinchi.auth.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 说明:某个角色的三级菜单的操作权限实体类
 * <br>
 *
 * @author ZSCDumin
 * <br>
 * 邮箱: 2712220318@qq.com
 * <br>
 * 日期: 2018/11/12
 * <br>
 * 版本: 1.0
 */
@ApiModel(description = "角色菜单操作权限实体")
public class RoleMenuOperation {

    @ApiModelProperty("操作主键,自增长")
    private Integer id;

    @ApiModelProperty("角色ID")
    private int roleId;

    @ApiModelProperty("菜单ID")
    private int menuId;

    @ApiModelProperty("后端接口ID")
    private int operationId;


    public RoleMenuOperation(){

    }
    /**
     * 构造函数
     *
     * @param roleId 角色ID
     * @param menuId 菜单ID
     * @param operationId 操作ID
     */
    public RoleMenuOperation(int roleId, int menuId, int operationId) {
        this.roleId = roleId;
        this.menuId = menuId;
        this.operationId = operationId;
    }

    @Override
    public String toString() {
        return "RoleMenuOperation{" +
                "id=" + id +
                ", roleId=" + roleId +
                ", menuId=" + menuId +
                ", operationId=" + operationId +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public int getOperationId() {
        return operationId;
    }

    public void setOperationId(int operationId) {
        this.operationId = operationId;
    }
}
