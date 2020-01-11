package com.jinchi.app.controller;

import com.jinchi.app.dto.DeptConditionDTO;
import com.jinchi.app.dto.DeviceDocumentStatisticConditionDTO;
import com.jinchi.app.dto.Result;
import com.jinchi.app.service.DeviceDocumentStatisticService;
import com.jinchi.app.utils.ResultUtil;
import com.netflix.discovery.converters.Auto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: LiuTaoYi
 * @create_time: 2019-09-11 16:48
 * @description:
 **/
@RestController
@RequestMapping(value = "/deviceDocumentStatistic")
@Api(tags = "设备档案统计-app")
public class DeviceDocumentStatisticController {

    @Autowired
    DeviceDocumentStatisticService deviceDocumentStatisticService;

    @PostMapping(value = "allDept")
    @ApiOperation(value = "查询所有设备部门")
    public Result getAllDept(@RequestBody DeptConditionDTO dto){
        return ResultUtil.success(deviceDocumentStatisticService.getAll(dto));
    }

    @PostMapping(value = "documentStatistic")
    @ApiOperation(value = "设备档案统计")
    public Result getDataByDateAndDept(@RequestBody DeviceDocumentStatisticConditionDTO dto){
        return ResultUtil.success(deviceDocumentStatisticService.getDataByDateAndDept(dto));
    }
}
