package com.jinchi.common.controller;

import com.jinchi.common.domain.TraceabilityBeforeDisassembly;
import com.jinchi.common.dto.Result;

import com.jinchi.common.service.TraceabilityBeforeDisassemblyService;
import com.jinchi.common.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/TraceabilityBeforeDisassemblyController")
@Api(tags = "拆解回溯-拆解前溯源")
public class TraceabilityBeforeDisassemblyController {

    @Autowired
    TraceabilityBeforeDisassemblyService traceabilityBeforeDisassemblyService;

    @PostMapping
    @ApiOperation(value = "新增")
    public Result add(@RequestBody TraceabilityBeforeDisassembly items){
        return ResultUtil.success(traceabilityBeforeDisassemblyService.add(items));
    }

    @DeleteMapping(value = "{id}")
    @ApiOperation(value = "删除")
    public Result delete(@PathVariable Long id){
        traceabilityBeforeDisassemblyService.deleteById(id);
        return ResultUtil.success();
    }

    @DeleteMapping(value ="ids")
    @ApiOperation(value = "批量删除")
    public Result deleteByIds(@RequestBody Long[] ids){
        traceabilityBeforeDisassemblyService.deleteByIds(ids);
        return ResultUtil.success();
    }

    @GetMapping(value = "page")
    @ApiOperation(value = "分页查询")
    public Result page(@RequestParam(required = false,defaultValue = "")String condition,
                       @RequestParam(required = false,defaultValue = "1") Integer page,
                       @RequestParam(required = false,defaultValue = "10") Integer size){
        return ResultUtil.success(traceabilityBeforeDisassemblyService.page(condition,page,size));
    }

    @PutMapping
    @ApiOperation(value = "更新")
    public Result update(@RequestBody TraceabilityBeforeDisassembly items){
        return ResultUtil.success(traceabilityBeforeDisassemblyService.update(items));
    }

    @GetMapping(value = "getAll")
    @ApiOperation(value = "获取所有")
    public Result getAll(){
        return ResultUtil.success(traceabilityBeforeDisassemblyService.getAll(""));
    }



}
