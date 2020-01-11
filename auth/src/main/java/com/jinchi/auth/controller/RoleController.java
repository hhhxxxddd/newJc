package com.jinchi.auth.controller;

import com.jinchi.auth.domain.Result;
import com.jinchi.auth.domain.Role;
import com.jinchi.auth.dto.Authorities;
import com.jinchi.auth.dto.PageBean;
import com.jinchi.auth.service.RoleService;
import com.jinchi.auth.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author：XudongHu
 * @description: 不解释
 * @date:11:45 2018/10/31
 */
@RestController
@RequestMapping(value = "/role")
@Api(tags = "角色接口")
public class RoleController {
    @Autowired
    private RoleService roleService;

    /**
     * 新增角色
     */
    @PreAuthorize("hasRole(\"ROLE_ADMIN\") or hasRole(\"ROLE_AUTH_ROLE_SAVE\")")
    @PostMapping
    @ApiOperation(value = "新增")
    public Result<Role> add(@RequestBody @Valid Role role) {
        return ResultUtil.success(roleService.add(role));
    }

    /**
     * 更新角色
     */
    @PreAuthorize("hasRole(\"ROLE_ADMIN\") or hasRole(\"ROLE_AUTH_ROLE_UPDATE\")")
    @PutMapping
    @ApiOperation(value = "更新")
    public Result<Role> update(@RequestBody @Valid Role role) {
        return ResultUtil.success(roleService.update(role));
    }

    /**
     * 删除角色
     */
    @PreAuthorize("hasRole(\"ROLE_ADMIN\") or hasRole(\"ROLE_AUTH_ROLE_DELETE\")")
    @DeleteMapping(value = "/{id}")
    @ApiOperation(value = "删除")
    public Result<Object> delete(@ApiParam(name = "id", value = "角色主键") @PathVariable Integer id) {
        roleService.deleteById(id);
        return ResultUtil.success();
    }

    /**
     * 批量删除角色
     */
    @PreAuthorize("hasRole(\"ROLE_ADMIN\") or hasRole(\"ROLE_AUTH_ROLE_DELETE\")")
    @DeleteMapping
    @ApiOperation(value = "批量删除")
    public Result<Object> batchDelete(@ApiParam(name = "ids",value = "主键数组") @RequestBody List<Integer> ids){
        roleService.deleteByIds(ids);
        return ResultUtil.success();
    }

    /**
     * 取得一该角色下所有权限
     */
    //todo
    @PreAuthorize("hasRole(\"ROLE_ADMIN\") or hasRole(\"ROLE_AUTH_ROLE_QUERY\")")
    @GetMapping(value = "/{id}")
    @ApiOperation(value = "角色权限")
    public Result<Map<Object,Object>> authority(@ApiParam(name = "id", value = "角色主键") @PathVariable Integer id) {
        return ResultUtil.success(roleService.authoritiesOfRole(id));
    }


    /**
     * 查询所有角色
     */
    @PreAuthorize("hasRole(\"ROLE_ADMIN\") or hasRole(\"ROLE_AUTH_ROLE_QUERY\")")
    @GetMapping
    @ApiOperation(value = "查询所有")
    public Result<List<Role>> findAll() {
        return ResultUtil.success(roleService.findAll());
    }


    /**
     * 根据角色名称模糊查询所有角色-分页
     */
    @PreAuthorize("hasRole(\"ROLE_ADMIN\") or hasRole(\"ROLE_AUTH_ROLE_QUERY\")")
    @GetMapping(value = "/byNameLikeByPage")
    @ApiOperation(value = "查询所有-分页")
    public Result<PageBean> byNameLikeByPage(@ApiParam(value = "角色名称") @RequestParam(required = false) String roleName, PageBean pageBean) {
        return ResultUtil.success(roleService.byNameLikeByPage(roleName, pageBean));
    }

    /**
     * 查询所有用户(包含已分配和未分配)
     */
    @PreAuthorize("hasRole(\"ROLE_ADMIN\") or hasRole(\"ROLE_AUTH_ROLE_QUERY\")")
    @GetMapping(value = "/usersOfRole")
    @ApiOperation(value = "查询分配的用户")
    public Result<Object> usersOfRole(@ApiParam(name = "id", value = "角色id") @RequestParam Integer id) {
        return ResultUtil.success(roleService.findAssignUsers(id));
    }

    /**
     * 将角色分配给用户
     */
    @PreAuthorize("hasRole(\"ROLE_ADMIN\") or hasRole(\"ROLE_AUTH_ROLE_QUERY\")")
    @PostMapping(value="/assignRoleToUser")
    @ApiOperation(value = "角色分配")
    public Result<Object> assignRoleToUser(@ApiParam(name = "roleId",value = "角色id") @RequestParam Integer roleId,
                                           @ApiParam(name = "userIds",value = "用户ids数组") @RequestParam(required = false) Integer[] userIds){
        if(userIds==null){
            roleService.assignUser(roleId,null);
        }
        else
        roleService.assignUser(roleId,Arrays.asList(userIds));
        return ResultUtil.success();
    }

    /**
     * 查询所有权限
     *
     */
    //todo
    @PreAuthorize("hasRole(\"ROLE_ADMIN\") or hasRole(\"ROLE_AUTH_ROLE_QUERY\")")
    @GetMapping(value = "/authorities")
    @ApiOperation(value = "所有权限")
    public Result<List<Authorities>> authorities() {
        return ResultUtil.success(roleService.allAuthorities());
    }

    /**
     * 增加一条权限
     */
    @PreAuthorize("hasRole(\"ROLE_ADMIN\") or hasRole(\"ROLE_AUTH_ROLE_SAVE\")")
    @PostMapping(value = "/addAuthority")
    @ApiOperation(value = "增加权限")
    public Result<Object> addAuthority(@ApiParam(name = "roleId",value = "角色id") @RequestParam Integer roleId,
                                          @ApiParam(name = "menuId",value = "菜单id") @RequestParam Integer menuId,
                                          @ApiParam(name = "operationId",value = "操作id") @RequestParam Integer operationId){
        roleService.addOneAuth(roleId,menuId,operationId);
        return ResultUtil.success();
    }
    /**
     * 取消一条权限
     */
    @PreAuthorize("hasRole(\"ROLE_ADMIN\") or hasRole(\"ROLE_AUTH_ROLE_DELETE\")")
    @DeleteMapping(value = "/removeAuthority")
    @ApiOperation(value = "取消权限")
    public Result<Object> removeAuthority(@ApiParam(name = "roleId",value = "角色id") @RequestParam Integer roleId,
                                          @ApiParam(name = "menuId",value = "菜单id") @RequestParam Integer menuId,
                                          @ApiParam(name = "operationId",value = "操作id") @RequestParam Integer operationId){
        roleService.deleteOneAuth(roleId,menuId,operationId);
        return ResultUtil.success();
    }
}
