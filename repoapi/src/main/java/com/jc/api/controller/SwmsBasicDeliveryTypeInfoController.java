package com.jc.api.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jc.api.entity.SwmsBasicDeliveryTypeInfo;
import com.jc.api.service.restservice.ISwmsBasicDeliveryTypeInfoService;
import com.jinchi.common.core.vo.Result;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

/**
 * @Auther: River
 * @Date: 2020/1/12 18:08
 * @Description:    出库类别controller层
 */
@RestController
@Api(tags = "智能仓库-出库类别信息管理界面")
@RequestMapping(value = "/swmsBasicDeliveryTypeInfo")
@Slf4j
public class SwmsBasicDeliveryTypeInfoController {

    @Autowired
    private ISwmsBasicDeliveryTypeInfoService swmsBasicDeliveryTypeInfoService;

    @ApiOperation(value = "获取所有出库点", notes = "获取所有出库类别")
    @ApiResponses(@ApiResponse(code = 200, message = "处理成功", response = Result.class))
    @GetMapping(value = "/getAll")
    public Result getAll(){
        return Result.success(swmsBasicDeliveryTypeInfoService.getAll(""));
    }

    @ApiOperation(value = "获取出库类别-条件/分页", notes = "条件获取出库类别-分页")
    @ApiResponses(@ApiResponse(code = 200, message = "处理成功", response = Result.class))
    @PostMapping(value = "/pages")
    public Result queryPages(@RequestBody Page page, @RequestParam(required = false) String deliveryTypeName) {
        return Result.success(swmsBasicDeliveryTypeInfoService.getAllByPage(page, deliveryTypeName));
    }

    @ApiOperation(value = "新增出库类别", notes = "新增")
    @ApiResponses(@ApiResponse(code = 200, message = "处理成功", response = Result.class))
    @PostMapping(value = "/add")
    public Result add(@Valid @RequestBody SwmsBasicDeliveryTypeInfo swmsBasicDeliveryTypeInfo) {
        return Result.success(swmsBasicDeliveryTypeInfoService.add(swmsBasicDeliveryTypeInfo));
    }

    @ApiOperation(value = "更新出库类别信息", notes = "更新")
    @ApiResponses(@ApiResponse(code = 200, message = "处理成功", response = Result.class))
    @PutMapping(value = "/{id}")
    public Result update(@RequestBody SwmsBasicDeliveryTypeInfo swmsBasicDeliveryTypeInfo) {
        return Result.success(swmsBasicDeliveryTypeInfoService.update(swmsBasicDeliveryTypeInfo));
    }

    @ApiOperation(value = "删除出库类别信息", notes = "根据id删除出库类别信息")
    @ApiImplicitParam(name = "id", value = "物料子类型信息id", required = true, dataType = "long")
    @ApiResponses(@ApiResponse(code = 200, message = "处理成功", response = Result.class))
    @DeleteMapping(value = "/{id}")
    public Result delete(@PathVariable Integer id) {
        log.debug("根据id删除出库类别记录:{}", id);
        return Result.success(swmsBasicDeliveryTypeInfoService.delete(id));
    }

    @ApiOperation(value = "批量删除出库类别信息",notes = "根据id集合删除")
    @DeleteMapping(value = "/batchDelete")
    public Result batchDelete(@RequestParam Set<String> ids){
        return Result.success(swmsBasicDeliveryTypeInfoService.batchDelete(ids));
    }
}
