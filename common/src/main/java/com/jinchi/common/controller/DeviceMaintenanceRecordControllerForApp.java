package com.jinchi.common.controller;

import com.jinchi.common.dto.DeviceMaintenanceDetailsDTO;
import com.jinchi.common.dto.Result;
import com.jinchi.common.service.DeviceMaintenanceRecordHeadService;
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
@RequestMapping(value = "/maintenanceRecordApp")
@Api(tags = "设备管理-设备维修-保养单-app")
public class DeviceMaintenanceRecordControllerForApp {
    @Autowired
    DeviceMaintenanceRecordHeadService deviceMaintenanceRecordHeadService;


    @GetMapping(value = "/records")
    @ApiOperation(value = "保养单-app-按用户id，状态")
    public List<DeviceMaintenanceDetailsDTO> recordPage(@ApiParam(name = "userId", value = "用户id") @RequestParam Integer userId,
                                                        @ApiParam(name = "statusId", value = "状态编码") @RequestParam Integer statusId,
                                                        @ApiParam(name = "condition", value = "查询条件") @RequestParam(defaultValue = "") String condition) {
        return   deviceMaintenanceRecordHeadService.getByIdStatusCondition(userId,statusId,condition);
    }




    @PutMapping(value = "/updateRecord")
    @ApiOperation(value = "保养单-app-编辑,通过code更新数据，edit-flag由前端传过来为2,先会删除所有的配件，请传所有的配件")
    public Result<DeviceMaintenanceDetailsDTO> updateRecord(@RequestBody @Valid DeviceMaintenanceDetailsDTO deviceMaintenanceDetailsDTO, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
        }
        return ResultUtil.success(deviceMaintenanceRecordHeadService.updateById(deviceMaintenanceDetailsDTO));
    }
    @GetMapping(value = "/recordDetail")
    @ApiOperation(value = "保养单-详情-app/web")
    public Result<DeviceMaintenanceDetailsDTO> recordDetail(@ApiParam(name = "id", value = "保养单主键") @RequestParam Long id ){
        return ResultUtil.success(deviceMaintenanceRecordHeadService.detail(id));
    }


}
