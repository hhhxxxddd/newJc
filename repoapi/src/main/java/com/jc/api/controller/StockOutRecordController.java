package com.jc.api.controller;

import com.jc.api.entity.form.StockOutRecordQueryForm;
import com.jc.api.entity.param.StockOutRecordQueryParam;
import com.jc.api.service.restservice.IStockOutRecordService;
import com.jinchi.common.core.vo.Result;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author XudongHu
 * @apiNote 物料出库记录界面
 * @className StockOutRecordController
 * @modifier
 * @since 19.12.10日9:25
 */
@RestController
@RequestMapping(value = "/outRecord")
@Api(tags = "(废弃)智能仓库-出库记录界面")
@Slf4j
public class StockOutRecordController {
    @Autowired
    private IStockOutRecordService iStockOutRecordService;

    @ApiOperation(value = "获取所有出库记录", notes = "获取所有出库记录")
    @ApiResponses(@ApiResponse(code = 200, message = "处理成功", response = Result.class))
    @GetMapping(value = "/all")
    public Result all() {
        return Result.success(iStockOutRecordService.getAll(new StockOutRecordQueryParam()));
    }

    @ApiOperation(value = "获取出库记录-条件", notes = "条件获取所有出库记录")
    @ApiResponses(@ApiResponse(code = 200, message = "处理成功", response = Result.class))
    @ApiImplicitParam(name = "stockOutRecordQueryForm", value = "查询条件", required = true, dataType = "StockOutRecordQueryForm")
    @PostMapping(value = "/conditions")
    public Result query(@Valid @RequestBody StockOutRecordQueryForm stockOutRecordQueryForm) {
        log.debug("条件获取出库记录:{}",stockOutRecordQueryForm);
        return Result.success(iStockOutRecordService.getAll(stockOutRecordQueryForm.toParam(StockOutRecordQueryParam.class)));
    }

    @ApiOperation(value = "获取出库记录-条件/分页", notes = "条件获取出库记录-分页")
    @ApiImplicitParam(name = "stockOutRecordQueryForm", value = "查询条件", required = true, dataType = "StockOutRecordQueryForm")
    @ApiResponses(@ApiResponse(code = 200, message = "处理成功", response = Result.class))
    @PostMapping(value = "/pages")
    public Result pages(@Valid @RequestBody StockOutRecordQueryForm stockOutRecordQueryForm) {
        log.debug("条件获取出库记录-分页:{}", stockOutRecordQueryForm);
        return Result.success(iStockOutRecordService.getAllByPage(stockOutRecordQueryForm.getPage(),stockOutRecordQueryForm.toParam(StockOutRecordQueryParam.class)));
    }
}
