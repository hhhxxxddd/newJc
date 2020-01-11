package com.jinchi.common.controller;

import com.jinchi.common.domain.BasicInfoVgaPoint;
import com.jinchi.common.dto.Result;
import com.jinchi.common.service.PrecursorVagService;
import com.jinchi.common.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/vga")
@Api(tags = "成本核算-基础数据-VGA")
public class PrecursorVagController {

    @Autowired
    PrecursorVagService precursorVagService;

    @PostMapping
    @ApiOperation(value = "新增")
    public Result add(@RequestBody BasicInfoVgaPoint basicInfoVgaPoint){
        return ResultUtil.success(precursorVagService.add(basicInfoVgaPoint));
    }

    @GetMapping(value = "page")
    @ApiOperation(value = "根据名称分页查询")
    public Result page(@RequestParam(defaultValue = "",required = false) String condition,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer size){
        return ResultUtil.success(precursorVagService.getAllByPage(condition,page,size));
    }

    @DeleteMapping
    @ApiOperation(value = "删除")
    public Result delete(@RequestParam Integer id){
        precursorVagService.delete(id);
        return ResultUtil.success();
    }

    @DeleteMapping(value = "ids")
    @ApiOperation(value = "批量删除")
    public Result deleteByIds(@RequestBody Integer[] ids){
        precursorVagService.deleteByIds(ids);
        return ResultUtil.success();
    }

    @PutMapping
    @ApiOperation(value = "更新")
    public  Result update(@RequestBody BasicInfoVgaPoint basicInfoVgaPoint){
        return ResultUtil.success(precursorVagService.update(basicInfoVgaPoint));
    }

    @GetMapping(value = "getRecordById")
    @ApiOperation(value = "根据id查询一条记录")
    public Result getOne(@RequestParam Integer id){
        return ResultUtil.success(precursorVagService.getById(id));
    }

    @GetMapping(value = "all")
    @ApiOperation(value = "查询所有")
    public Result getAll(@RequestParam(defaultValue = "", required = false) String condition) {
        return ResultUtil.success(precursorVagService.getAll(condition));
    }
}
