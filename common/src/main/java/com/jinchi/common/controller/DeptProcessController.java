package com.jinchi.common.controller;

import com.jinchi.common.domain.BasicInfoDeviceProcess;
import com.jinchi.common.dto.Result;
import com.jinchi.common.service.DeptProcessService;
import com.jinchi.common.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "deptProcess")
@Api(tags = "设备管理-部门分配工序")
public class DeptProcessController {

    @Autowired
    DeptProcessService deptProcessService;

    @PostMapping
    @ApiOperation(value = "新增")
    public Result add(@RequestBody BasicInfoDeviceProcess basicInfoDeviceProcess){
        return ResultUtil.success(deptProcessService.add(basicInfoDeviceProcess));
    }

    @DeleteMapping
    @ApiOperation(value = "删除")
    public Result delete(@RequestParam Integer id){
        deptProcessService.delete(id);
        return ResultUtil.success();
    }

    @DeleteMapping(value = "ids")
    @ApiOperation(value = "批量删除")
    public Result deleteByIds(@RequestBody Integer[] ids){
        deptProcessService.deleteByIds(ids);
        return ResultUtil.success();
    }

    @GetMapping
    @ApiOperation(value = "详情")
    public Result detail(@RequestParam Integer id){
        return ResultUtil.success(deptProcessService.detail(id));
    }

    @GetMapping(value = "page")
    @ApiOperation(value = "分页")
    public Result page(@RequestParam Integer deptId,
                       @RequestParam(required = false,defaultValue = "1") Integer page,
                       @RequestParam(required = false,defaultValue = "10") Integer size){
        return ResultUtil.success(deptProcessService.page(deptId,page,size));
    }

    @GetMapping(value = "getAllByDept")
    @ApiOperation(value = "获取所有")
    public Result getAll(@RequestParam Integer deptId){
        return ResultUtil.success(deptProcessService.getAll(deptId));
    }

    @PutMapping
    @ApiOperation(value = "更新")
    public Result update(@RequestBody BasicInfoDeviceProcess basicInfoDeviceProcess){
        return ResultUtil.success(deptProcessService.update(basicInfoDeviceProcess));
    }
}
