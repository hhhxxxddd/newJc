package com.jc.api.controller;

import com.jc.api.service.restservice.imp.SwmsDullStatisticService;
import com.jinchi.common.core.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "智能仓库-数据统计-呆滞统计")
@RequestMapping(value = "/swmsDullStatistic")
@Slf4j
public class SwmsDullStatisticController {

    @Autowired
    SwmsDullStatisticService service;

    @ApiOperation(value = "查询按钮", notes = "查询按钮")
    @ApiResponses(@ApiResponse(code = 200, message = "处理成功", response = Result.class))
    @GetMapping(value = "/query")
    public Result query(@RequestParam(required = false,defaultValue = "") Integer type,
                        @RequestParam(required = false,defaultValue = "") Integer subType){
        return Result.success(service.query(type,subType));
    }

}
