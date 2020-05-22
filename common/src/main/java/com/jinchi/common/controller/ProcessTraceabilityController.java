package com.jinchi.common.controller;

import com.jinchi.common.domain.ProcessTraceability;
import com.jinchi.common.dto.Result;
import com.jinchi.common.service.ProcessTraceabilityService;
import com.jinchi.common.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/ProcessTraceabilityController")
@Api(tags = "拆解回溯-过程溯源")
public class ProcessTraceabilityController {

    @Autowired
    ProcessTraceabilityService processTraceabilityService;

    @PostMapping
    @ApiOperation(value = "新增")
    public Result add(@RequestBody ProcessTraceability items){
        return ResultUtil.success(processTraceabilityService.add(items));
    }

    @DeleteMapping(value = "{id}")
    @ApiOperation(value = "删除")
    public Result delete(@PathVariable Long id){
        processTraceabilityService.deleteById(id);
        return ResultUtil.success();
    }

    @DeleteMapping(value ="ids")
    @ApiOperation(value = "批量删除")
    public Result deleteByIds(@RequestBody Long[] ids){
        processTraceabilityService.deleteByIds(ids);
        return ResultUtil.success();
    }

    @GetMapping(value = "page")
    @ApiOperation(value = "分页查询")
    public Result page(@RequestParam(required = false,defaultValue = "")String condition,
                       @RequestParam(required = false,defaultValue = "1") Integer page,
                       @RequestParam(required = false,defaultValue = "10") Integer size){
        return ResultUtil.success(processTraceabilityService.page(condition,page,size));
    }

    @PutMapping
    @ApiOperation(value = "更新")
    public Result update(@RequestBody ProcessTraceability items){
        return ResultUtil.success(processTraceabilityService.update(items));
    }

    @GetMapping(value = "getAll")
    @ApiOperation(value = "获取所有")
    public Result getAll(){
        return ResultUtil.success(processTraceabilityService.getAll(""));
    }



}
