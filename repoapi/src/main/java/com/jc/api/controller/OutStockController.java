package com.jc.api.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jc.api.service.restservice.imp.OutStockService;
import com.jinchi.common.core.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/outStock")
@Api(tags = "出库管理-出库查询")
public class OutStockController {
    @Autowired
    OutStockService stockService;

    @PostMapping("page")
    @ApiOperation("分页接口")
    public Result page(@RequestParam(required = false) Integer deptCode, @RequestParam(required = false) String date, @RequestBody Page page){
        return Result.success(stockService.getPage(deptCode,date,page));
    }

    @GetMapping("detail")
    @ApiOperation("详情")
    public Result detail(@RequestParam Long headId){
        return Result.success(stockService.detail(headId));
    }

    @GetMapping("commonBatchDetail")
    @ApiOperation("根据批号查询详情")
    public Result commonBatchDetail(@RequestParam Integer commonBatchId){
        return Result.success(stockService.getByCommonBatchId(commonBatchId));
    }
}
