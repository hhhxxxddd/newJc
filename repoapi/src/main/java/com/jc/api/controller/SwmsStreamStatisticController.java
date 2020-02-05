package com.jc.api.controller;

import com.jinchi.common.core.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "智能仓库-数据统计-流量统计")
@RequestMapping(value = "/SwmsStream")
@Slf4j
public class SwmsStreamStatisticController {

    @GetMapping(value = "monthView")
    @ApiOperation(value = "月视图")
    @ApiResponses(@ApiResponse(code = 200, message = "处理成功", response = Result.class))
    public Result monthView(@RequestParam Integer year,@RequestParam Integer month){
        return Result.success();
    }

    @GetMapping(value = "yearView")
    @ApiOperation(value = "年视图")
    @ApiResponses(@ApiResponse(code = 200, message = "处理成功", response = Result.class))
    public Result yearView(@RequestParam Integer year){
        return Result.success();
    }
}
