package com.jinchi.common.controller;

import com.jinchi.common.dto.Result;
import com.jinchi.common.service.DevicePatrolQueryService;
import com.jinchi.common.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/devicePatrolQuery")
@Api(tags = "设备管理-设备巡检-巡检查询")
public class DevicePatrolQureyController {

    @Autowired
    DevicePatrolQueryService devicePatrolQueryService;

    @GetMapping(value = "/page")
    @ApiOperation(value = "根据部门，状态，时段，计划名称模糊查询分页")
    public Result page(@ApiParam(name = "deptId", value = "部门主键") @RequestParam Integer deptId,
                       @ApiParam(name = "status", value = "状态") @RequestParam(defaultValue = "-1") Integer status,
                       @ApiParam(name = "startDate", value = "开始时间") @RequestParam(defaultValue = "") String startDate,
                       @ApiParam(name = "endDate", value = "结束时间") @RequestParam(defaultValue = "") String endDate,
                       @ApiParam(name = "condition", value = "查询条件") @RequestParam(defaultValue = "") String condition,
                       @ApiParam(name = "page", value = "页码") @RequestParam(name = "page", defaultValue = "1") Integer page,
                       @ApiParam(name = "size", value = "条目数") @RequestParam(name = "size", defaultValue = "10") Integer size){
        return ResultUtil.success(devicePatrolQueryService.getAllByPage(deptId,status,startDate,endDate,condition,page,size));
    }

    @GetMapping(value = "/detail")
    @ApiOperation(value = "详情")
    public Result detail( @ApiParam(name = "id", value = "计划主键") @RequestParam Long id){
        return ResultUtil.success(devicePatrolQueryService.detail(id));
    }
}
