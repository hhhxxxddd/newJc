package com.jinchi.common.controller;

import com.jinchi.common.dto.DeviceRepairApplicationDTO;
import com.jinchi.common.dto.DeviceRepairEvaluationsDTO;
import com.jinchi.common.dto.Page;
import com.jinchi.common.dto.Result;
import com.jinchi.common.service.DeviceRepairService;
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
@RequestMapping(value = "/deviceRepair")
@Api(tags = "设备维修")
public class DeviceRepairController {
    @Autowired
    DeviceRepairService deviceRepairService;


    @GetMapping(value = "/getPage")
    @ApiOperation(value = "设备维修--查询分页")
    public Result<Page> getPage(@ApiParam(name = "repairStatus", value = "维修状态") @RequestParam(value ="repairStatus",defaultValue = "1")Integer repairStatus,
                                @ApiParam(name = "secondDeptId",value = "二级部门主键") @RequestParam Integer secondDeptId,
                                @ApiParam(name = "condition",value = "查询条件") @RequestParam(defaultValue = "") String condition,
                                @ApiParam(name = "startTime",value = "开始时间") @RequestParam(defaultValue = "") String startTime,
                                @ApiParam(name = "endTime",value = "结束时间") @RequestParam(defaultValue = "") String endTime,
                                @ApiParam(name = "page", value = "页码") @RequestParam(name = "page", defaultValue = "1") Integer page,
                                @ApiParam(name = "size", value = "条目数") @RequestParam(name = "size", defaultValue = "10") Integer size){
        return ResultUtil.success(deviceRepairService.getPage(repairStatus,secondDeptId,condition,startTime,endTime,page,size));
    }

    @GetMapping(value = "/getRepairByDeptCodeAndDeviceId")
    @ApiOperation(value = "设备维修-根据deptcode，deviceId，status获取所有repairApplications")
    public  Result<Page> getPage(@ApiParam(name = "status", value = "维修状态") @RequestParam(value ="status",defaultValue = "3")Integer status,
                                                                     @ApiParam(name = "deptCode",value = "部门主键") @RequestParam Integer deptCode,
                                                                     @ApiParam(name = "deviceId",value = "设备编号") @RequestParam Long deviceId,
                                                                     @ApiParam(name = "page",value = "页码") @RequestParam Integer page,
                                                                     @ApiParam(name = "size",value = "条目数") @RequestParam Integer size) {
        return ResultUtil.success(deviceRepairService.getPageByDeviceId(status,deptCode,deviceId,page,size));
    }


    @GetMapping(value = "/deviceRepairApplication")
    @ApiOperation(value = "设备维修-详情")
    public Result<DeviceRepairApplicationDTO> detail(@ApiParam(name = "id", value = "设备维修申请主键") @RequestParam Long id ){
        return ResultUtil.success(deviceRepairService.detail(id));
    }

    @GetMapping(value = "/byConditions")
    @ApiOperation(value = "根据开始结束时间和设备id查询维修记录")
    public Result getById(@ApiParam(name = "deviceId", value = "设备编号") @RequestParam Long deviceId,
                          @ApiParam(name = "startTime", value = "开始时间") @RequestParam String startTime,
                          @ApiParam(name = "endTime", value = "结束时间") @RequestParam String endTime) {
        return ResultUtil.success(deviceRepairService.listRepairInfosByIdAndTime(deviceId, startTime, endTime));
    }

    @GetMapping(value = "/evaluations")
    @ApiOperation(value = "设备维修-评价")
    public Result<DeviceRepairEvaluationsDTO> evaluations(@ApiParam(name = "id", value = "设备维修申请主键") @RequestParam Long id ){
        return ResultUtil.success(deviceRepairService.evaluations(id));
    }

    @GetMapping(value = "export")
    @ApiOperation(value = "导出")
    public Result export(@ApiParam(name = "repairStatus", value = "维修状态") @RequestParam(value ="repairStatus",defaultValue = "1")Integer repairStatus,
                         @ApiParam(name = "secondDeptId",value = "二级部门主键") @RequestParam Integer secondDeptId,
                         @ApiParam(name = "condition",value = "查询条件") @RequestParam(defaultValue = "") String condition,
                         @ApiParam(name = "startTime",value = "开始时间") @RequestParam(defaultValue = "") String startTime,
                         @ApiParam(name = "endTime",value = "结束时间") @RequestParam(defaultValue = "") String endTime){
        return ResultUtil.success(deviceRepairService.export(repairStatus,secondDeptId,condition,startTime,endTime));
    }
}
