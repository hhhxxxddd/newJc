package com.jinchi.common.controller;

import com.jinchi.common.domain.FireMageDept;
import com.jinchi.common.dto.Result;
import com.jinchi.common.service.FireMageBatchItemsService;
import com.jinchi.common.service.FireMageDeptService;
import com.jinchi.common.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequestMapping(value = "fireMageBatchItems")
@Api(tags = "火法质量-批号与检测项目")
public class FireMageBatchItemsController {

    @Autowired
    FireMageBatchItemsService itemsService;

    @PostMapping
    @ApiOperation(value = "新增")
    public Result add(@RequestParam Integer processId,
                      @RequestParam Integer productId,
                      @RequestBody Long[] ids){
        return ResultUtil.success(itemsService.add(processId,productId, Arrays.asList(ids)));
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
    public Result update(@RequestParam Long id,
                         @RequestBody Long[] ids){
        itemsService.update(id,Arrays.asList(ids));
        return ResultUtil.success();
    }
}
