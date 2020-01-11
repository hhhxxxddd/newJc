package com.jc.api.controller;

import com.jc.api.entity.form.StockInRecordAccountQueryForm;
import com.jc.api.entity.param.StockInRecordAccountQueryParam;
import com.jc.api.service.restservice.IStockInRecordAccountService;
import com.jinchi.common.core.vo.Result;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author XudongHu
 * @apiNote 物料库存台账界面
 * @className MaterialStockAccountController
 * @modifier
 * @since 19.12.10日2:40
 */
@RestController
@Api(tags = "(废弃)智能仓库-库存台账管理界面")
@RequestMapping(value = "/stockAccount")
@Slf4j
public class MaterialStockAccountController {
    @Autowired
    private IStockInRecordAccountService iStockInRecordAccountService;

    @ApiOperation(value = "获取所有台账", notes = "获取所有台账")
    @ApiResponses(@ApiResponse(code = 200, message = "处理成功", response = Result.class))
    @GetMapping(value = "/all")
    public Result all() {
        return Result.success(iStockInRecordAccountService.getAll(new StockInRecordAccountQueryParam()));
    }

    @ApiOperation(value = "获取台账-条件", notes = "条件获取所有台账")
    @ApiResponses(@ApiResponse(code = 200, message = "处理成功", response = Result.class))
    @ApiImplicitParam(name = "stockInRecordAccountQueryForm", value = "查询条件", required = true, dataType = "StockInRecordAccountQueryForm")
    @PostMapping(value = "/conditions")
    public Result query(@Valid @RequestBody StockInRecordAccountQueryForm stockInRecordAccountQueryForm) {
        log.debug("条件获取台账:{}",stockInRecordAccountQueryForm);
        return Result.success(iStockInRecordAccountService.getAll(stockInRecordAccountQueryForm.toParam(StockInRecordAccountQueryParam.class)));
    }

    @ApiOperation(value = "获取台账-条件/分页", notes = "条件获取台账-分页")
    @ApiImplicitParam(name = "stockInRecordAccountQueryForm", value = "查询条件", required = true, dataType = "StockInRecordAccountQueryForm")
    @ApiResponses(@ApiResponse(code = 200, message = "处理成功", response = Result.class))
    @PostMapping(value = "/pages")
    public Result queryPages(@Valid @RequestBody StockInRecordAccountQueryForm stockInRecordAccountQueryForm) {
        log.debug("条件获取台账分页:{}", stockInRecordAccountQueryForm);
        return Result.success(iStockInRecordAccountService.getAllByPage(stockInRecordAccountQueryForm.getPage(),stockInRecordAccountQueryForm.toParam(StockInRecordAccountQueryParam.class)));
    }
}
