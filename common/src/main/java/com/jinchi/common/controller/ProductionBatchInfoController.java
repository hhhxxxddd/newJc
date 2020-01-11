package com.jinchi.common.controller;


import com.jinchi.common.dto.*;
import com.jinchi.common.service.ProductionBatchInfoService;
import com.jinchi.common.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/productionBatchInfo")
@Api(tags = "生产管理-批次信息")
public class ProductionBatchInfoController {

    @Autowired
    private ProductionBatchInfoService productionBatchInfoService;

    @PostMapping("/save")
    @ApiOperation(value = "增加一条信息， 两个编制人前端传， batch后端 拼接， flag标记位前端传")
    public Result<Object> addOne(@RequestBody @Valid ProductionBatchInfoDateTostring productionBatchInfoDateTostring, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
        }
        Integer save = productionBatchInfoService.save(productionBatchInfoDateTostring);
        return ResultUtil.success(save);
    }


    @GetMapping("/getAllInfo")
    @ApiOperation(value = "分页查询出所有的信息")
    public Result<Page> getAllInfo(@ApiParam(value = "页数") @RequestParam Integer page,
                                   @ApiParam(value = "大小") @RequestParam Integer size) {

        return ResultUtil.success(productionBatchInfoService.getAllInfo(page, size));
    }


    @GetMapping("/ByCode")
    @ApiOperation(value = "获得一条批次信息记录,批次信息编辑界面")
    public Result<ProductionBatchInfoDTO> ByCode(@ApiParam(value = "code") @RequestParam Integer code) {
        return ResultUtil.success(productionBatchInfoService.getDetailByCode(code));
    }


    @GetMapping("/GetAllBatchRuleData")
    @ApiOperation(value = "查询批次规则数据明细表，获取所有批次规则")
    public Result<ProductionBatchRuleDetailsDTO> ByCode() {
        return ResultUtil.success(productionBatchInfoService.GetAllBatchRuleData());
    }

    @GetMapping("/getAllInfoByCondition")
    @ApiOperation(value = "根据查询条件，获取查询条件的查询数据")
    public Result<Page> getAllInfoByCondition(@ApiParam(value = "condition") @RequestParam String condition,
                                              @ApiParam(value = "页数") @RequestParam Integer page,
                                              @ApiParam(value = "大小") @RequestParam Integer size) {
        return ResultUtil.success(productionBatchInfoService.getAllInfoByCondition(condition, page, size));
    }


    @DeleteMapping("/delOneByCode")
    @ApiOperation(value = "删除一条信息，通过code")
    public Result<Object> delOneByCode(@ApiParam(value = "code") @RequestParam long code) {
        productionBatchInfoService.delOneByCode(code);
        return ResultUtil.success();
    }

    @DeleteMapping("/delSomeByCodes")
    @ApiOperation(value = "批量删除信息，通过codes")
    public Result<Object> delSomeByCodes(@RequestBody @Valid List<Long> codes) {
        productionBatchInfoService.delSomeByCodes(codes);
        return ResultUtil.success();
    }

    @PostMapping("/updateByCode")
    @ApiOperation(value = "更新一条数据，全部数据都要传过来")
    public Result<Object> updateByCode(@RequestBody @Valid BatchDTO batchDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
        }
        productionBatchInfoService.update(batchDTO);
        return ResultUtil.success();
    }

    @PostMapping(value = "preview")
    @ApiOperation(value = "批次追溯预览")
    public Result batchRetrospectPreview(@ApiParam(value = "creater") @RequestParam String creater,
                                         @ApiParam(value = "batch") @RequestParam String batch) {
        return ResultUtil.success(productionBatchInfoService.getRecord(creater, batch));
    }

    @GetMapping(value = "getDetail")
    @ApiOperation(value = "批次追溯预览详情")
    public Result getDetailById(@ApiParam(value = "batchId") @RequestParam Long batchId) {
        return ResultUtil.success(productionBatchInfoService.getAllRecordsById(batchId));
    }

    @GetMapping(value = "getInstrument")
    @ApiOperation(value = "仪器仪表数据详情")
    public Result getDetailByInstrumentCode(@ApiParam(value = "instrumentCode") @RequestParam Long instrumentCode) {
        return ResultUtil.success(productionBatchInfoService.getById(instrumentCode));
    }

    @GetMapping(value = "getInstrumentChart")
    @ApiOperation(value = "仪器仪表数据详情-图表")
    public Result getInstrumentChartData(@ApiParam(value = "槽号") @RequestParam String cellNum,
                                         @ApiParam(value = "工序开始时间") @RequestParam String startTime,
                                         @ApiParam(value = "工序完成时间") @RequestParam String endTime) {
        return ResultUtil.success(productionBatchInfoService.getByCondition(cellNum, startTime, endTime));
    }

    @GetMapping(value = "getInfoByBatch")
    @ApiOperation(value = "根据批号获取解析信息")
    public Result getInfo(@RequestParam String batch){
        return ResultUtil.success(productionBatchInfoService.getInfo(batch));
    }

    @GetMapping(value = "getBatchNumber")
    @ApiOperation(value = "跳批数")
    public Result batchNumber(@RequestParam String process) {
        return ResultUtil.success(productionBatchInfoService.getJumpBatchNumber(process));
    }
}
