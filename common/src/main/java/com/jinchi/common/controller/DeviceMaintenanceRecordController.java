package com.jinchi.common.controller;

import com.jinchi.common.dto.DeviceMaintenanceDetailsDTO;
import com.jinchi.common.dto.DeviceMaintenanceRecordHeadDTO;
import com.jinchi.common.dto.Page;
import com.jinchi.common.dto.Result;
import com.jinchi.common.service.DeviceMaintenanceRecordHeadService;
import com.jinchi.common.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;

@RestController
@RequestMapping(value = "/maintenanceRecord")
@Api(tags = "设备管理-设备维修-保养单")
public class DeviceMaintenanceRecordController {
    @Autowired
    DeviceMaintenanceRecordHeadService deviceMaintenanceRecordHeadService;


    @GetMapping(value = "/recordPage")
    @ApiOperation(value = "保养单-按部门编号，状态,开始结束时间分页")
    public Result<Page> recordPage(@ApiParam(name = "deptId", value = "部门主键") @RequestParam Integer deptId,
                                   @ApiParam(name = "statusId", value = "状态编码") @RequestParam Integer statusId,
                                   @ApiParam(name = "startDate", value = "开始时间") @RequestParam(defaultValue = "") String startDate,
                                   @ApiParam(name = "endDate", value = "结束时间") @RequestParam(defaultValue = "") String endDate,
                                   @ApiParam(name = "condition", value = "查询条件") @RequestParam(defaultValue = "") String condition,
                                   @ApiParam(name = "page", value = "页码") @RequestParam(name = "page", defaultValue = "1") Integer page,
                                   @ApiParam(name = "size", value = "条目数") @RequestParam(name = "size", defaultValue = "10") Integer size,
                                   @ApiParam(name = "orderField", value = "排序属性") @RequestParam(name = "orderField", defaultValue = "code") String orderField,
                                   @ApiParam(name = "orderType", value = "排序方法,只能是asc升序或者desc降序") @RequestParam(name = "orderType", defaultValue = "desc") String orderType) {
        return ResultUtil.success(deviceMaintenanceRecordHeadService.getByDeptBystatusByPage(deptId,statusId,condition,startDate,endDate,page,size));
    }




    @PostMapping(value = "/addRecord")
    @ApiOperation(value = "保养单-新增")
    public Result<DeviceMaintenanceDetailsDTO> addRecord(@RequestBody @Valid DeviceMaintenanceRecordHeadDTO deviceMaintenanceRecordHeadDTO, BindingResult bindingResult) {

        return null;

    }



    @GetMapping(value = "/recordDetail")
    @ApiOperation(value = "保养单-详情-app/web")
    public Result<DeviceMaintenanceDetailsDTO> recordDetail(@ApiParam(name = "id", value = "保养单主键") @RequestParam Long id ){
        return ResultUtil.success(deviceMaintenanceRecordHeadService.detail(id));
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation(value = "保养单-删除")
    public Result deleteById(@ApiParam(name = "id", value = "保养单主键") @PathVariable Long id ){
        deviceMaintenanceRecordHeadService.delete(id);
        return ResultUtil.success();
    }

    @DeleteMapping(value = "/deleteByIds")
    @ApiOperation(value = "保养单-批量删除")
    public Result deleteByIds(@RequestBody @Valid Long[] ids, BindingResult bindingResultLong){

        return null;
    }

    @GetMapping(value = "/getRecordByDeviceCode")
    @ApiOperation(value = "根据部件id获取所有以完成保养单")
    public Result getRecordByDeviceCode(@ApiParam(name = "id", value = "保养单主键") @RequestParam Long id ){
        return null;
    }

    @GetMapping(value = "getByCondition")
    @ApiOperation(value = "按设备编号，开始结束时间查询")
    public Result getByCondition(@ApiParam(name = "startTime", value = "开始时间") @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startTime,
                                 @ApiParam(name = "endTime", value = "结束时间") @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endTime,
                                 @ApiParam(name = "deviceCode", value = "设备编号") @RequestParam Long deviceCode) {
        return ResultUtil.success(deviceMaintenanceRecordHeadService.getRecordsByCondition(deviceCode, startTime, endTime));
    }

    @GetMapping(value = "byConditions")
    @ApiOperation(value = "按设备编号，开始结束时间查询，批次追溯调用这个")
    public Result getByConditions(@ApiParam(name = "startTime", value = "开始时间") @RequestParam String startTime,
                                  @ApiParam(name = "endTime", value = "结束时间") @RequestParam String endTime,
                                  @ApiParam(name = "deviceCode", value = "设备编号") @RequestParam Long deviceCode) {
        return ResultUtil.success(deviceMaintenanceRecordHeadService.listRecordsByIdAndTime(deviceCode, startTime, endTime));
    }
}
