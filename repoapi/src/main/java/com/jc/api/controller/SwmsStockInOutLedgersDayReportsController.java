package com.jc.api.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jc.api.service.restservice.ISwmsStockInLedgersDayReportsService;
import com.jc.api.service.restservice.ISwmsStockOutLedgersDayReportsService;
import com.jinchi.common.core.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = "智能仓库-数据查询-进出日报")
@RequestMapping(value = "/SwmsStockInOutLedgersDayReports")
@Slf4j
public class SwmsStockInOutLedgersDayReportsController {

    @Autowired
    ISwmsStockInLedgersDayReportsService reportsService;
    @Autowired
    ISwmsStockOutLedgersDayReportsService outLedgersDayReportsService;


    @ApiOperation(value = "入库日报-条件/分页", notes = "入库日报-条件/分页")
    @ApiResponses(@ApiResponse(code = 200, message = "处理成功", response = Result.class))
    @PostMapping(value = "/pagesIn")
    public Result queryPagesIn(@RequestBody Page page, @RequestParam(required = false) Integer typeId,
                             @RequestParam(required = false) Integer subTypeId,
                             @RequestParam(required = false) Integer supplierId,
                             @RequestParam(required = false) String  startTime,
                             @RequestParam(required = false) String  endTime) {
        return Result.success(reportsService.selectByPage(page,typeId,subTypeId,supplierId,startTime,endTime));
    }

    @ApiOperation(value = "出库日报-条件/分页", notes = "出库日报-条件/分页")
    @ApiResponses(@ApiResponse(code = 200, message = "处理成功", response = Result.class))
    @PostMapping(value = "/pagesOut")
    public Result queryPagesOut(@RequestBody Page page, @RequestParam(required = false) Integer typeId,
                             @RequestParam(required = false) Integer subTypeId,
                             @RequestParam(required = false) Integer deptId,
                             @RequestParam(required = false) String  startTime,
                             @RequestParam(required = false) String  endTime) {
        return Result.success(outLedgersDayReportsService.selectByPage(page,typeId,subTypeId,deptId,startTime,endTime));
    }
}
