package com.jinchi.common.controller;

import com.jinchi.common.domain.DevicePatrolPlanRecordHead;
import com.jinchi.common.dto.DevicePatrolPlanDTO;
import com.jinchi.common.dto.Result;
import com.jinchi.common.service.DevicePatrolPlanService;
import com.jinchi.common.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/devicePatrolPlan")
@Api(tags = "设备管理-设备巡检-巡检计划")
public class DevicePatrolPlanController {

    @Autowired
    DevicePatrolPlanService devicePatrolPlanService;

    @PostMapping("/add")
    @ApiOperation(value = "新增巡检计划")
    public Result<DevicePatrolPlanRecordHead> add(@ApiParam(name = "deptId", value = "部门主键") @RequestParam Integer deptId,
                                                  @ApiParam(name = "planName", value = "计划名称") @RequestParam String planName,
                                                  @ApiParam(name = "checkType", value = "计划类型") @RequestParam Integer checkType,
                                                  @ApiParam(name = "planDate", value = "计划日期") @RequestParam String planDate,
                                                  @ApiParam(name = "endDate", value = "结束日期") @RequestParam String endDate,
                                                  @ApiParam(name = "modelId", value = "巡检模板id") @RequestParam Long modelId,
                                                  @ApiParam(name = "userId", value = "用户id") @RequestParam Integer userId) {
        return ResultUtil.success(devicePatrolPlanService.add(deptId, planName, checkType, planDate, endDate, modelId, userId));
    }

    @GetMapping(value = "/detail")
    @ApiOperation(value = "详情")
    public Result<DevicePatrolPlanDTO> detail(@ApiParam(name = "planId", value = "计划主键") @RequestParam Long planId) {
        return ResultUtil.success(devicePatrolPlanService.detail(planId));
    }

    @GetMapping(value = "/page")
    @ApiOperation(value = "根据部门id，状态，条件模糊查询分页")
    public Result page(@ApiParam(name = "deptId", value = "部门主键") @RequestParam Integer deptId,
                       @ApiParam(name = "status", value = "状态") @RequestParam(defaultValue = "-1") Integer status,
                       @ApiParam(name = "condition", value = "查询条件") @RequestParam(defaultValue = "") String condition,
                       @ApiParam(name = "page", value = "页码") @RequestParam(name = "page", defaultValue = "1") Integer page,
                       @ApiParam(name = "size", value = "条目数") @RequestParam(name = "size", defaultValue = "10") Integer size) {
        return ResultUtil.success(devicePatrolPlanService.page(deptId, status, condition, page, size));
    }

    @PutMapping(value = "/update")
    @ApiOperation(value = "编辑")
    public Result<DevicePatrolPlanDTO> update(@ApiParam(name = "planId", value = "计划主键") @RequestParam Long planId,
                                              @ApiParam(name = "planName", value = "计划名称") @RequestParam String planName,
                                              @ApiParam(name = "planDate", value = "计划日期") @RequestParam String planDate,
                                              @ApiParam(name = "endDate", value = "结束日期") @RequestParam String endDate) {
        return ResultUtil.success(devicePatrolPlanService.update(planId, planName, planDate,endDate));
    }

    @DeleteMapping(value = "/delete")
    @ApiOperation(value = "删除")
    public Result delete(@ApiParam(name = "planId", value = "计划主键") @RequestParam Long planId) {
        devicePatrolPlanService.delete(planId);
        return ResultUtil.success();
    }

    @DeleteMapping(value = "/deleteByIds")
    @ApiOperation(value = "删除")
    public Result delete(@RequestBody @Valid Long[] planIds) {
        devicePatrolPlanService.deleteByIds(planIds);
        return ResultUtil.success();
    }

}
