package com.jinchi.common.controller;

import com.jinchi.common.domain.FireMageTestItems;
import com.jinchi.common.dto.Result;
import com.jinchi.common.service.FireMageTestItemsService;
import com.jinchi.common.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value ="fireMageTestItems")
@Api(tags = "火法质量-检测项目")
public class FireMageTestItemsController {

    @Autowired
    FireMageTestItemsService itemsService;

    @PostMapping
    @ApiOperation(value = "新增")
    public Result add(@RequestBody FireMageTestItems items){
        return ResultUtil.success(itemsService.add(items));
    }

    @DeleteMapping(value = "{id}")
    @ApiOperation(value = "删除")
    public Result delete(@PathVariable Long id){
        itemsService.delete(id);
        return ResultUtil.success();
    }

    @DeleteMapping(value ="ids")
    @ApiOperation(value = "批量删除")
    public Result deleteByIds(@RequestBody Long[] ids){
        itemsService.deleteByIds(ids);
        return ResultUtil.success();
    }

    @GetMapping(value = "{id}")
    @ApiOperation(value = "详情")
    public Result detail(@PathVariable Long id){
        return ResultUtil.success(itemsService.detail(id));
    }

    @GetMapping(value = "page")
    @ApiOperation(value = "分页查询")
    public Result page(@RequestParam(required = false,defaultValue = "") String condition,
                       @RequestParam(required = false,defaultValue = "1") Integer page,
                       @RequestParam(required = false,defaultValue = "10") Integer size){
        return ResultUtil.success(itemsService.page(condition,page,size));
    }

    @PutMapping
    @ApiOperation(value = "更新")
    public Result update(@RequestBody FireMageTestItems items){
        return ResultUtil.success(itemsService.update(items));
    }

    @GetMapping(value = "getAll")
    @ApiOperation(value = "获取所有")
    public Result getAll(){
        return ResultUtil.success(itemsService.getAll(""));
    }

    @GetMapping(value = "getAllByProcessByProdut")
    @ApiOperation(value= "根据工序和产品获取检测项目")
    public Result getAllByProcessByProdut(@RequestParam Integer processCode,@RequestParam Integer productCode){
        return ResultUtil.success(itemsService.getAllByProcessByProdut(processCode,productCode));
    }
}