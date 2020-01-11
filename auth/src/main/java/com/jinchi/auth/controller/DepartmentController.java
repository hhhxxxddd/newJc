package com.jinchi.auth.controller;


import com.jinchi.auth.domain.Department;
import com.jinchi.auth.domain.Result;
import com.jinchi.auth.dto.PageBean;
import com.jinchi.auth.service.DepartmentService;
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

@RestController
@RequestMapping(value = "/department")
@Api(tags = "部门接口")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    /**
     * 新增部门
     */
    @PreAuthorize("hasRole(\"ROLE_ADMIN\") or hasRole(\"ROLE_AUTH_DEPARTMENT_SAVE\")")
    @PostMapping
    @ApiOperation(value = "新增")
    public Result<Department> add(@RequestBody @Valid Department department){
        return ResultUtil.success(departmentService.add(department));
    }

    /**
     * 更新部门
     */
    @PreAuthorize("hasRole(\"ROLE_ADMIN\") or hasRole(\"ROLE_AUTH_DEPARTMENT_UPDATE\")")
    @PutMapping
    @ApiOperation(value = "更新")
    public Result<Department> update(@RequestBody @Valid Department department){
        return ResultUtil.success(departmentService.update(department));
    }

    /**
     * 删除部门
     */
    @PreAuthorize("hasRole(\"ROLE_ADMIN\") or hasRole(\"ROLE_AUTH_DEPARTMENT_DELETE\")")
    @DeleteMapping(value = "/{id}")
    @ApiOperation(value = "删除")
    public Result<Object> deleteById(@ApiParam(name = "id",value = "部门主键") @PathVariable Integer id){
        departmentService.deleteById(id);
        return ResultUtil.success();
    }

    /**
     * 查询所有部门
     */
    @PreAuthorize("hasRole(\"ROLE_ADMIN\") or hasRole(\"ROLE_AUTH_DEPARTMENT_QUERY\")")
    @GetMapping
    @ApiOperation(value = "查询所有")
    public Result<List<Department>> findAll(){
        return ResultUtil.success(departmentService.findAll());
    }


    /**
     * 根据部门名称模糊查询所有部门-分页
     */
    @PreAuthorize("hasRole(\"ROLE_ADMIN\") or hasRole(\"ROLE_AUTH_DEPARTMENT_QUERY\")")
    @GetMapping(value = "/byNameLikeByPage")
    @ApiOperation(value = "查询所有-分页",notes = "根据部门名称模糊,可为空")
    public Result<PageBean<Department>> findDepartmentsByNameLikeByPage(@ApiParam(value = "部门名称") @RequestParam(required = false) String departmentName,
                                                                        PageBean pageBean){
        return ResultUtil.success(departmentService.byNameLikeByPage(departmentName,pageBean));
    }



    /**
     * 根据ids删除多个部门
     */
    @PreAuthorize("hasRole(\"ROLE_ADMIN\") or hasRole(\"ROLE_AUTH_DEPARTMENT_DELETE\")")
    @DeleteMapping(value = "/batchDelete")
    @ApiOperation(value = "批量删除")
    public Result<Object> deleteByIds(@ApiParam(name = "ids",value = "主键数组") @RequestBody Integer[] ids){
        departmentService.batchDelete(Arrays.asList(ids));
        return ResultUtil.success();
    }

}