package com.jinchi.app.controller;

import com.jinchi.app.dto.*;
import com.jinchi.app.service.DeviceMaintenanceRecordService;
import com.jinchi.app.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * @author: LiuTaoYi
 * @create_time: 2019-08-26 12:15
 * @description:
 **/

@RestController
@RequestMapping(value = "/maintenanceRecord")
@Api(tags = "设备保养模块-app")
public class DeviceMaintenanceRecordController {

    @Autowired
    DeviceMaintenanceRecordService deviceMaintenanceRecordService;


    @PostMapping(value = "/records")
    @ApiOperation(value = "保养单分页查询-app-按用户id，状态，查询条件")
    public Result<List<DeviceMaintenanceFormatDto>> recordPage(@RequestBody @Valid DeviceMaintenancePostDto deviceMaintenancePostDto){
        return ResultUtil.success(deviceMaintenanceRecordService.getByIdStatusCondition(deviceMaintenancePostDto));
    }

    @PostMapping(value = "/recordDetail")
    @ApiOperation(value = "保养单详情查询--根据保养单id")
    public Result<Object> recordDetail(@RequestBody @Valid DeviceMaintenanceDetailPostDto deviceMaintenanceDetailPostDto){
        return ResultUtil.success(deviceMaintenanceRecordService.getRecordDetail(deviceMaintenanceDetailPostDto));
    }

    @PostMapping(value = "/receiveRecord")
    @ApiOperation(value="保养单接单，通过保养单id 将保养单状态改为已接单，并添加接单时间")
    public Result<Object> receiveRecord(@RequestBody IdDto idDto){
        return ResultUtil.success(deviceMaintenanceRecordService.receiveRecordById(idDto));
    }
    @PostMapping(value = "/updateRecord")
    @ApiOperation(value = "保养单及其详情更新,通过code更新数据，edit-flag由前端传过来为2,先会删除所有的配件，请传所有的配件")
    public Result<Object> updateRecord(@RequestBody @Valid DeviceMaintenanceRecordDetailUpdateDto updateDto, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
        }
        if (deviceMaintenanceRecordService.updateById(updateDto) != null){
            return ResultUtil.success("");
        }else {
            return ResultUtil.error("操作失败");
        }

    }
}
