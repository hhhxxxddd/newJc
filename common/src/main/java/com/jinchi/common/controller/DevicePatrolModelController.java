package com.jinchi.common.controller;

import com.jinchi.common.dto.DevicePatrolModelDTO;
import com.jinchi.common.dto.Page;
import com.jinchi.common.dto.Result;
import com.jinchi.common.service.DevicePatrolModelService;
import com.jinchi.common.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/devicePatrolModel")
@Api(tags = "设备管理-设备巡检-巡检模板")
public class DevicePatrolModelController {

    @Autowired
    DevicePatrolModelService devicePatrolModelService;

    @PostMapping(value = "/add")
    @ApiOperation(value = "新增巡检模板")
    public Result<DevicePatrolModelDTO> add(@RequestBody @Valid DevicePatrolModelDTO devicePatrolModelDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
        }
        return ResultUtil.success(devicePatrolModelService.add(devicePatrolModelDTO));
    }

    @PutMapping(value = "/update")
    @ApiOperation(value = "编辑")
    public Result<DevicePatrolModelDTO> update(@RequestBody @Valid DevicePatrolModelDTO devicePatrolModelDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
        }
        return ResultUtil.success(devicePatrolModelService.update(devicePatrolModelDTO));
    }

    @GetMapping(value = "/page")
    @ApiOperation(value = "按照类型，模板名称模糊分页查询")
    public Result<Page> page(@ApiParam(name = "deptId", value = "部门主键") @RequestParam Integer deptId,
                             @ApiParam(name = "status", value = "状态") @RequestParam(defaultValue = "-1") Integer status,
                             @ApiParam(name = "condition", value = "查询条件") @RequestParam(defaultValue = "") String condition,
                             @ApiParam(name = "page", value = "页码") @RequestParam(name = "page", defaultValue = "1") Integer page,
                             @ApiParam(name = "size", value = "条目数") @RequestParam(name = "size", defaultValue = "10") Integer size){
        return ResultUtil.success(devicePatrolModelService.page(deptId,status,condition,page,size));
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation(value = "删除")
    public Result deleteById(@ApiParam(name = "id", value = "模板主键") @RequestParam Long id){
        devicePatrolModelService.deleteById(id);
        return ResultUtil.success();
    }

    @DeleteMapping(value = "/deleteByIds")
    @ApiOperation(value = "批量删除")
    public Result deleteByIds(@RequestBody @Valid Long[] ids, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
        }
        devicePatrolModelService.deleteByIds(ids);
        return null;
    }

    @GetMapping(value = "/detail")
    @ApiOperation(value = "详情")
    public Result<DevicePatrolModelDTO> detail(@ApiParam(name = "id", value = "模板主键") @RequestParam Long id){
        return ResultUtil.success(devicePatrolModelService.detail(id));
    }

    @GetMapping(value = "/getAllByDeptCode")
    @ApiOperation(value = "获取所有巡检模板")
    public Result<List> getAllByDeptCode(@ApiParam(name = "id", value = "部门主键") @RequestParam Integer id){
        return  ResultUtil.success(devicePatrolModelService.getAll(id,-1,""));
    }
}
