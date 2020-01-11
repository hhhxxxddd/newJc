package com.jinchi.common.controller;

import com.jinchi.common.domain.BasicInfoPrecursorPlcAddress;
import com.jinchi.common.dto.Result;
import com.jinchi.common.service.PrecursorPlcAddressService;
import com.jinchi.common.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/plcAddress")
@Api(tags = "成本核算-基础数据-PLC地址")
public class PrecursorPlcAddressController {

    @Autowired
    PrecursorPlcAddressService precursorPlcAddressService;

    @PostMapping
    @ApiOperation(value = "新增")
    public Result add(@RequestBody BasicInfoPrecursorPlcAddress basicInfoPrecursorPlcAddress){
        return ResultUtil.success(precursorPlcAddressService.add(basicInfoPrecursorPlcAddress));
    }

    @DeleteMapping
    @ApiOperation(value = "删除")
    public Result delete(@RequestParam Integer id){
        precursorPlcAddressService.delete(id);
        return ResultUtil.success();
    }

    @DeleteMapping("/ids")
    @ApiOperation(value = "批量删除")
    public Result deleteByIds(@RequestBody Integer[] ids){
        precursorPlcAddressService.deleteByIds(ids);
        return ResultUtil.success();
    }

    @GetMapping
    @ApiOperation(value = "分页模糊查询")
    public Result page(@RequestParam(defaultValue = "",required = false) String condition,
                      @RequestParam(defaultValue = "1") Integer page,
                      @RequestParam(defaultValue = "10") Integer size){
        return ResultUtil.success(precursorPlcAddressService.getByPage(condition,page,size));
    }

    @GetMapping(value = "all")
    @ApiOperation(value = "查询所有")
    public Result getAll(@RequestParam(defaultValue = "", required = false) String condition) {
        return ResultUtil.success(precursorPlcAddressService.getAll(condition));
    }

    @PutMapping
    @ApiOperation(value = "更新")
    public Result update(@RequestBody BasicInfoPrecursorPlcAddress basicInfoPrecursorPlcAddress){
        return ResultUtil.success(precursorPlcAddressService.update(basicInfoPrecursorPlcAddress));
    }

    @GetMapping(value = "getRecordById")
    @ApiOperation(value = "根据id查询一条记录")
    public Result getOne(@RequestParam Integer id){
        return ResultUtil.success(precursorPlcAddressService.getById(id));
    }
}
