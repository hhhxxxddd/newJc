package com.jinchi.app.controller;

import com.jinchi.app.dto.*;
import com.jinchi.app.service.DeviceRepairReportService;
import com.jinchi.app.service.DeviceRepairedService;
import com.jinchi.app.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;

@RestController
@RequestMapping(value = "/repaired")
@Api(tags = "设备维修（维修人）-app")
public class DeviceRepairedController {

    @Autowired
    DeviceRepairedService deviceRepairedService;

    @Autowired
    DeviceRepairReportService deviceRepairReportService;

    @PostMapping(value = "page")
    @ApiOperation(value = "查询")
    public Result getByPage(@RequestBody @Valid RepairPostDTO repairPostDTO){
        RepairPageDataDTO ans = deviceRepairReportService.page(repairPostDTO);
        if(ans != null) {
            return ResultUtil.success(ans);
        }
        return ResultUtil.success(new ArrayList<>());
    }

    @PostMapping(value = "detail")
    @ApiOperation(value = "详情")
    public Result detail(@RequestBody @Valid IdDto idDto){
        return ResultUtil.success(deviceRepairedService.detail(idDto.getId()));
    }

    @PostMapping(value = "commit")
    @ApiOperation(value = "提交/暂存")//待维修的接单，已接单的接单和暂存
    public Result commit(@RequestBody @Valid DeviceRepairedApplyDTO deviceRepairedApplyDTO){
        deviceRepairedService.commit(deviceRepairedApplyDTO);
        return ResultUtil.success(new IdDto());
    }

    /*@PostMapping(value = "partner")
    @ApiOperation(value = "获取合作维修人列表")
    public Result partner(@RequestBody @Valid IdDto idDto){
        return ResultUtil.success(deviceRepairedService.getPartnerById(idDto.getUserId()));
    }

    @PostMapping(value = "cooperation")
    @ApiOperation(value = "选择合作人 提交")
    public Result cooperation(@RequestBody @Valid DeviceRepairHelperDTO helperDTO){
        deviceRepairedService.cooperation(helperDTO);
        return ResultUtil.success(new IdDto());
    }*/
}
