package com.jinchi.app.controller;

import com.jinchi.app.dto.DeptConditionDTO;
import com.jinchi.app.dto.DeviceDocumentStatisticConditionDTO;
import com.jinchi.app.dto.Result;
import com.jinchi.app.service.DeviceDocumentStatisticService;
import com.jinchi.app.service.DeviceSpotcheckStatisticService;
import com.jinchi.app.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: LiuTaoYi
 * @create_time: 2019-09-12 14:17
 * @description:
 **/
@RestController
@RequestMapping(value = "/deviceSpotcheckStatistic")
@Api(tags = "设备点检统计-app")
public class DeviceSpotcheckStatisticController {
    @Autowired
    DeviceDocumentStatisticService deviceDocumentStatisticService;

    @Autowired
    DeviceSpotcheckStatisticService deviceSpotcheckStatisticService;

    @PostMapping(value = "allDept")
    @ApiOperation(value = "查询所有设备部门")
    public Result getAllDept(@RequestBody DeptConditionDTO dto){
        return ResultUtil.success(deviceDocumentStatisticService.getAll(dto));
    }

    @PostMapping(value = "spotcheckStatistic")
    @ApiOperation(value = "设备点检统计")
    public Result getData(@RequestBody DeviceDocumentStatisticConditionDTO dto){
        return ResultUtil.success(deviceSpotcheckStatisticService.getDataByMonth(dto));
    }

    @GetMapping(value = "spotcheckStatisticforPc")
    @ApiOperation(value = "设备档案-点检统计")
    public Result spotcheckStatisticforPc(@RequestParam Integer deptId,
                                          @RequestParam Integer year,
                                          @RequestParam Integer month,
                                          @RequestParam(required = false, defaultValue = "") String condition) {
        return ResultUtil.success(deviceSpotcheckStatisticService.getDataByMonthforPc(deptId, year, month, condition));
    }
}
