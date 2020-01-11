package com.jinchi.common.controller;

import com.jinchi.common.domain.FireMageDept;
import com.jinchi.common.dto.Result;
import com.jinchi.common.service.FireMageDeptService;
import com.jinchi.common.service.FireMageLabelService;
import com.jinchi.common.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequestMapping(value = "fireMageLabel")
@Api(tags = "火法质量-标签与检测项目")
public class FireMageLabelController {

    @Autowired
    FireMageLabelService itemsService;

    @PostMapping
    @ApiOperation(value = "新增")
    public Result add(@RequestBody Long[] items){
        return ResultUtil.success(itemsService.add(Arrays.asList(items)));
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

    @PutMapping
    @ApiOperation(value = "更新")
    public Result update(@RequestParam Long id,@RequestBody Long[] items){
        itemsService.update(id,Arrays.asList(items));
        return ResultUtil.success();
    }

    @GetMapping(value = "getAll")
    @ApiOperation(value  = "获取所有")
    public Result getAll(){
        return ResultUtil.success(itemsService.getAll(""));
    }

}
