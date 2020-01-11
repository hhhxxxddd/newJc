package com.jinchi.common.controller;

import com.jinchi.common.domain.QualityBaseDetectItem;
import com.jinchi.common.dto.Result;
import com.jinchi.common.service.DetectItemService;
import com.jinchi.common.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/detectItem")
@Api(tags = "质量流程-基础数据-检测项目（新）")
public class QualityBaseDetectItemController {
    @Autowired
    DetectItemService detectItemService;

    @PostMapping
    @ApiOperation(value = "新增")
    public Result add(@RequestBody QualityBaseDetectItem item){
        return ResultUtil.success(detectItemService.add(item));
    }

    @PutMapping
    @ApiOperation(value = "更新")
    public Result update(@RequestBody QualityBaseDetectItem item){
        return ResultUtil.success(detectItemService.update(item));
    }

    @DeleteMapping
    @ApiOperation(value = "删除")
    public Result delete(@RequestParam Long id){
        detectItemService.delete(id);
        return ResultUtil.success();
    }

    @DeleteMapping(value = "deleteByIds")
    @ApiOperation(value = "批量删除")
    public Result deleteByIds(@RequestBody Long[] ids){
        detectItemService.deleteByIds(ids);
        return ResultUtil.success();
    }

    @GetMapping(value = "page")
    @ApiOperation(value = "分页")
    public Result add(@RequestParam(defaultValue = "",required = false) String condition,
                      @RequestParam(defaultValue = "1",required = false) Integer page,
                      @RequestParam(defaultValue = "10",required = false)Integer size){
        return ResultUtil.success(detectItemService.page(condition,page,size));
    }

    @GetMapping(value = "detail")
    @ApiOperation(value = "详情")
    public Result add(@RequestParam Long id){
        return ResultUtil.success(detectItemService.detail(id));
    }

    @GetMapping(value = "all")
    @ApiOperation(value = "获取所有")
    public Result getAll(@RequestParam(defaultValue = "",required = false)String condition){
        return ResultUtil.success(detectItemService.getAll(condition));
    }
}
