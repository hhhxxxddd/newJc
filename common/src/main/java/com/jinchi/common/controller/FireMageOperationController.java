package com.jinchi.common.controller;

import com.jinchi.common.domain.FireMageOperationManual;
import com.jinchi.common.dto.Result;
import com.jinchi.common.service.FireMageOperationService;
import com.jinchi.common.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "fireMageOperation")
@Api(tags = "火法质量-操作手册")
public class FireMageOperationController {

    @Autowired
    FireMageOperationService operationService;

    @PostMapping
    @ApiOperation(value = "新增")
    public Result add(@RequestBody FireMageOperationManual manual){
        return ResultUtil.success(operationService.add(manual));
    }

    @DeleteMapping(value = "{id}")
    @ApiOperation(value = "删除")
    public Result delete(@PathVariable Long id){
        operationService.delete(id);
        return ResultUtil.success();
    }

    @DeleteMapping(value ="ids")
    @ApiOperation(value = "批量删除")
    public Result deleteByIds(@RequestBody Long[] ids){
        operationService.deleteByIds(ids);
        return ResultUtil.success();
    }

    @GetMapping(value = "{id}")
    @ApiOperation(value = "详情")
    public Result detail(@PathVariable Long id){
        return ResultUtil.success(operationService.detail(id));
    }

    @GetMapping(value = "page")
    @ApiOperation(value = "分页查询")
    public Result page(@RequestParam(required = false,defaultValue = "") String condition,
                       @RequestParam(required = false,defaultValue = "1") Integer page,
                       @RequestParam(required = false,defaultValue = "10") Integer size){
        return ResultUtil.success(operationService.page(condition,page,size));
    }

    @PutMapping
    @ApiOperation(value = "更新")
    public Result update(@RequestBody FireMageOperationManual manual){
        return ResultUtil.success(operationService.update(manual));
    }
}
