package com.jinchi.common.controller;

import com.jinchi.common.dto.Page;
import com.jinchi.common.dto.Result;
import com.jinchi.common.service.DeviceSpotCheckQueryService;
import com.jinchi.common.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/deviceSpotCheckQuery")
@Api(tags = "设备管理-设备维修-点检查询")
public class DeviceSpotCheckQueryController {
    @Autowired
    DeviceSpotCheckQueryService deviceSpotCheckQueryService;

    @GetMapping(value = "/deviceDetailPage")
    @ApiOperation(value = "点检查询-根据设备主键查询点检记录")
    public Result<Page> detailPage(@ApiParam(name = "id", value = "设备主键") @RequestParam Long id,
                                   @ApiParam(name = "page", value = "页数") @RequestParam(defaultValue = "1") Integer page,
                                   @ApiParam(name = "size", value = "页面大小") @RequestParam(defaultValue = "10") Integer size){
        return ResultUtil.success(deviceSpotCheckQueryService.deviceDetailPage(id,page,size));
    }

    @GetMapping(value = "/deviceRecordDetail/{id}")
    @ApiOperation(value = "点检查询-根据主设备的点检记录id查询详情")
    public Result deviceRecordDetail(@ApiParam(name = "id", value = "点检记录主键") @PathVariable Long id){
        return ResultUtil.success(deviceSpotCheckQueryService.deviceRecordDetail(id));
    }
}
