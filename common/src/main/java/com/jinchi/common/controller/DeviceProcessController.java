package com.jinchi.common.controller;

import com.jinchi.common.dto.Result;
import com.jinchi.common.service.DeviceProcessService;
import com.jinchi.common.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/deviceProcess")
@Api(tags = "设备管理-基础数据-设备工序")
public class DeviceProcessController {
    @Autowired
    DeviceProcessService deviceProcessService;

    @GetMapping(value = "getPageByProcessDeptId")
    @ApiOperation(value = "分页获取该部门工序下的所有设备")
    public Result getPageByProcessDeptId(@RequestParam Integer processDeptId,
                                         @RequestParam(defaultValue = "",required = false) String condition,
                                         @RequestParam(defaultValue = "1",required = false) Integer page,
                                         @RequestParam(defaultValue = "10",required = false) Integer size){
        return ResultUtil.success(deviceProcessService.getPageByProcessDeptId(processDeptId,condition,page,size));
    }

    @GetMapping(value = "getDeviceAssign")
    @ApiOperation(value = "根据工序部门id查询已分配，未分配设备")
    public Result getDeviceByProIdByPage(@RequestParam Integer processDeptId){
        return ResultUtil.success(deviceProcessService.getDeviceByProId(processDeptId));
    }

    @PutMapping(value = "assign")
    @ApiOperation(value = "分配工序")
    public Result assign(@RequestParam Integer processDeptId,
                         @RequestBody Long[] deviceIds){
        deviceProcessService.assign(processDeptId,deviceIds);
        return ResultUtil.success();
    }

    @GetMapping(value = "getByProcessCodeByDeptCode")
    @ApiOperation(value = "根据工序id和部门id获取设备（工艺参数）")
    public Result getByProcessCodeByDeptCode(@RequestParam Integer processCode,@RequestParam Integer deptCode){
        return ResultUtil.success(deviceProcessService.getByProcessCodeByDeptCode(processCode,deptCode));
    }

    @GetMapping(value = "getDeviceByDeptCode")
    @ApiOperation(value = "根据部门id获取设备")
    public Result getDeviceByDeptCode(@RequestParam Integer deptId){
        return ResultUtil.success(deviceProcessService.getDeviceByDeptCode(deptId));
    }

}
