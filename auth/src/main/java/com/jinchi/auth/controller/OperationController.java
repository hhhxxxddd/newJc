package com.jinchi.auth.controller;

import com.github.pagehelper.PageInfo;
import com.jinchi.auth.domain.Operation;
import com.jinchi.auth.domain.Result;
import com.jinchi.auth.service.OperationService;
import com.jinchi.auth.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * @author：XudongHu
 * @description: 不解释
 * @date:23:11 2018/11/14
 */
@RestController
@RequestMapping(value = "/operation")
@Api(tags = "操作接口")
public class OperationController {
    @Autowired
    private OperationService operationService;

    /**
     * 新增
     */
    @PreAuthorize("hasRole(\"ROLE_ADMIN\")")
    @PostMapping(value = "/add")
    @ApiOperation(value = "新增一个操作")
    public Result<Operation> add(@RequestBody Operation operation, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
        }
        return ResultUtil.success(operationService.add(operation));
    }
    /**
     * 更新
     */
    @PreAuthorize("hasRole(\"ROLE_ADMIN\")")
    @PostMapping(value = "/update")
    @ApiOperation(value = "更新一个操作")
    public Result<Operation> update(@RequestBody Operation operation, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
        }
        return ResultUtil.success(operationService.update(operation));
    }
    /**
     * 删除
     */
    @PreAuthorize("hasRole(\"ROLE_ADMIN\")")
    @DeleteMapping(value = "/{id}")
    @ApiOperation(value = "删除一个操作")
    public Result<Object> deleteById(@ApiParam(name = "id",value = "操作主键") @PathVariable Integer id){
        operationService.deleteById(id);
        return ResultUtil.success();
    }
    /**
     * 批量删除
     */
    @PreAuthorize("hasRole(\"ROLE_ADMIN\")")
    @DeleteMapping(value = "/deleteByIds")
    @ApiOperation(value = "批量删除")
    public Result<Object> deleteByIds(@ApiParam(name = "ids",value = "操作主键数组") @RequestBody Integer[] ids){
        operationService.deleteByIds(Arrays.asList(ids));
        return ResultUtil.success();
    }

    /**
     * 查询所有
     */
    @PreAuthorize("hasRole(\"ROLE_ADMIN\")")
    @GetMapping(value = "/getAll")
    @ApiOperation(value = "查询所有")
    public Result<List<Operation>> getAll(){

        return ResultUtil.success(operationService.findAll());
    }
    /**
     * 查询所有-分页
     */
    @PreAuthorize("hasRole(\"ROLE_ADMIN\")")
    @GetMapping(value = "/getOperationsByPage")
    @ApiOperation(value = "查询所有操作-分页")
    public Result<PageInfo<Operation>> findOperationsByPage(@ApiParam(name = "page", value = "页码") @RequestParam(name = "page", defaultValue = "0") Integer page,
                                                       @ApiParam(name = "size", value = "条目数") @RequestParam(name = "size", defaultValue = "10") Integer size,
                                                       @ApiParam(name = "orderField", value = "排序属性") @RequestParam(name = "orderField", defaultValue = "id") String orderField,
                                                       @ApiParam(name = "orderType", value = "排序方法,只能是asc升序或者desc降序") @RequestParam(name = "orderType", defaultValue = "desc") String orderType) {

        return ResultUtil.success(operationService.findAllByPage(page,size,orderField,orderType));
    }

    /**
     * 查询所有-根据名称模糊-分页
     */
    @PreAuthorize("hasRole(\"ROLE_ADMIN\") or hasRole(\"ROLE_AUTH_ROLE_QUERY\")")
    @GetMapping(value = "/pagesByName")
    @ApiOperation(value = "查询所有操作-名称模糊-分页")
    public Result<PageInfo<Operation>> findOperationsByNameLikeByPage(@ApiParam(name = "operationName", value = "操作名称") @RequestParam String operationName,
                                                            @ApiParam(name = "page", value = "页码") @RequestParam(name = "page", defaultValue = "0") Integer page,
                                                            @ApiParam(name = "size", value = "条目数") @RequestParam(name = "size", defaultValue = "10") Integer size,
                                                            @ApiParam(name = "orderField", value = "排序属性") @RequestParam(name = "orderField", defaultValue = "id") String orderField,
                                                            @ApiParam(name = "orderType", value = "排序方法,只能是asc升序或者desc降序") @RequestParam(name = "orderType", defaultValue = "desc") String orderType) {



        return ResultUtil.success(operationService.findByNameLikeByPage(operationName,page,size,orderField,orderType));
    }


}
