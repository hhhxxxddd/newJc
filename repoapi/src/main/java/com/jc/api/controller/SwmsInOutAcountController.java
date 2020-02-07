package com.jc.api.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jc.api.service.restservice.imp.InOutAcountService;
import com.jinchi.common.core.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = "智能仓库-数据查询-进出存台账")
@RequestMapping(value = "inOutAccout")
public class SwmsInOutAcountController {

    @Autowired
    InOutAcountService service;

    @GetMapping(value = "addStatistic")
    @ApiResponses(@ApiResponse(code = 200, message = "处理成功", response = Result.class))
    @ApiOperation(value = "新增统计")
    public Result addStatistic(@RequestParam Integer year,
                               @RequestParam Integer month,
                               @RequestParam String startTime,
                               @RequestParam String endTime){
        return Result.success(service.addStatistic(year,month,startTime,endTime));
    }

    @PostMapping(value = "page")
    @ApiResponses(@ApiResponse(code = 200, message = "处理成功", response = Result.class))
    @ApiOperation(value = "分页")
    public Result page(@RequestParam(required = false,defaultValue = "") Integer type,
                       @RequestParam(required = false,defaultValue = "") Integer subType,
                       @RequestParam(required = false,defaultValue = "") Integer matId,
                       @RequestParam(required = false,defaultValue = "") Integer month,
                       @RequestBody Page page
                       ){
        return Result.success(service.pages(type,subType,matId,month,page));
    }
}
