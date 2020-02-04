package com.jc.api.controller;

import com.jc.api.entity.SwmsBasicStockAgingRange;
import com.jc.api.service.restservice.imp.SwmsStockAgeStatisticService;
import com.jinchi.common.core.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = "智能仓库-数据统计-库龄统计")
@RequestMapping(value = "/swmsStockAgeStatistic")
@Slf4j
public class SwmsStockAgeStatisticController {

    @Autowired
    SwmsStockAgeStatisticService service;

    @ApiOperation(value = "查询周转率", notes = "查询周转率")
    @ApiResponses(@ApiResponse(code = 200, message = "处理成功", response = Result.class))
    @GetMapping(value = "/turnoverRate")
    public Result turnoverRate(@RequestParam(required = true) Integer type,
                               @RequestParam(required = false,defaultValue = "") Integer subType,
                               @RequestParam String time){
        return Result.success(service.turnoverRate(type,subType,time));
    }

    @ApiOperation(value = "库龄详情", notes = "库龄详情")
    @ApiResponses(@ApiResponse(code = 200, message = "处理成功", response = Result.class))
    @PostMapping(value = "/distribution")
    public Result distribution(@RequestBody SwmsBasicStockAgingRange range){
        return Result.success(service.distribution(range));
    }
}
