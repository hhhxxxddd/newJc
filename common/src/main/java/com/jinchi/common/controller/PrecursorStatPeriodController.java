package com.jinchi.common.controller;

import com.jinchi.common.domain.BasicInfoPrecursorPeriod;
import com.jinchi.common.dto.Result;
import com.jinchi.common.service.PrecursorStatPeriodService;
import com.jinchi.common.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: LiuTaoYi
 * @create_time: 2019-09-26 12:10
 * @description:
 **/
@RestController
@RequestMapping(value = "/precursorBasicDataStatPeriod")
@Api(tags = "成本核算-基础数据-统计周期")
public class PrecursorStatPeriodController {

    @Autowired
    PrecursorStatPeriodService precursorStatPeriodService;

    @PostMapping(value = "add")
    @ApiOperation(value = "新增")
    public Result<BasicInfoPrecursorPeriod> addPeriod(@RequestBody BasicInfoPrecursorPeriod basicInfoPrecursorPeriod) {
        return ResultUtil.success(precursorStatPeriodService.add(basicInfoPrecursorPeriod));
    }

    @DeleteMapping(value = "delete")
    @ApiOperation(value = "删除")
    public Result delete(@ApiParam(name = "id", value = "主键") @RequestParam Integer id) {
        precursorStatPeriodService.delete(id);
        return ResultUtil.success();
    }

    @PutMapping(value = "update")
    @ApiOperation(value = "编辑")
    public Result<BasicInfoPrecursorPeriod> update(@RequestBody BasicInfoPrecursorPeriod basicInfoPrecursorPeriod) {
        return ResultUtil.success(precursorStatPeriodService.update(basicInfoPrecursorPeriod));
    }

    @GetMapping(value = "page")
    @ApiOperation(value = "分页查询")
    public Result page(@ApiParam(name = "page", value = "页码") @RequestParam(name = "page", defaultValue = "1") Integer page,
            @ApiParam(name = "size", value = "条目数") @RequestParam(name = "size", defaultValue = "10") Integer size) {
        return ResultUtil.success(precursorStatPeriodService.page(page, size));
    }

    @GetMapping(value = "all")
    @ApiOperation(value = "查询所有")
    public Result getAll() {
        return ResultUtil.success(precursorStatPeriodService.getAll());
    }

    @GetMapping(value = "getRecordById")
    @ApiOperation(value = "根据id查询一条记录")
    public Result getOne(@RequestParam Integer id){
        return ResultUtil.success(precursorStatPeriodService.getDataById(id));
    }
}
