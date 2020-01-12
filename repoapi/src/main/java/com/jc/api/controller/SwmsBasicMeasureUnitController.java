package com.jc.api.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jc.api.entity.SwmsBasicMeasureUnit;
import com.jc.api.service.restservice.ISwmsBasicMeasureUnitService;
import com.jinchi.common.core.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * @author XudongHu
 * @apiNote 计量单位controller
 * @className SwmsBasicMeasureUnitController
 * @modifier
 * @since 20.1.12日15:08
 */
@RestController
@Api(tags = "智能仓库-计量单位管理界面")
@RequestMapping(value = "/SwmsBasicMeasureUnit")
@Slf4j
public class SwmsBasicMeasureUnitController {
    @Autowired
    private ISwmsBasicMeasureUnitService iSwmsBasicMeasureUnitService;


    @ApiOperation(value = "获取所有计量单位", notes = "获取所有计量单位")
    @ApiResponses(@ApiResponse(code = 200, message = "处理成功", response = Result.class))
    @GetMapping(value = "/getAll")
    public Result getAll(){
        return Result.success(iSwmsBasicMeasureUnitService.getAll(null));
    }

    @ApiOperation(value = "获取所有计量单位-名称模糊")
    @ApiResponses(@ApiResponse(code = 200, message = "处理成功", response = Result.class))
    @PostMapping(value = "/conditions")
    public Result query(@RequestParam String name){
        return Result.success(iSwmsBasicMeasureUnitService.getAll(name));
    }

    @ApiOperation(value = "获取计量单位-条件/分页", notes = "条件获取计量单位记录-分页")
    @ApiResponses(@ApiResponse(code = 200, message = "处理成功", response = Result.class))
    @PostMapping(value = "/pages")
    public Result queryPages(@RequestBody Page page, @RequestParam(required = false) String supplierName) {
        return Result.success(iSwmsBasicMeasureUnitService.getAllByPage(page,supplierName));
    }

    @ApiOperation(value = "新增计量单位", notes = "新增")
    @ApiResponses(@ApiResponse(code = 200, message = "处理成功", response = Result.class))
    @PostMapping(value = "/add")
    public Result add(@RequestBody SwmsBasicMeasureUnit swmsBasicSupplierInfo) {
        swmsBasicSupplierInfo.setAutoFlag(false);
        return Result.success(iSwmsBasicMeasureUnitService.add(swmsBasicSupplierInfo));
    }

    @ApiOperation(value = "仅供测试使用-自动新增计量单位", notes = "自动新增,存在则无动作,不存在则新增")
    @ApiResponses(@ApiResponse(code = 200, message = "处理成功", response = Result.class))
    @PostMapping(value = "/autoAdd")
    public Result autoAdd(@RequestBody SwmsBasicMeasureUnit swmsBasicMeasureUnit) {
        return Result.success(iSwmsBasicMeasureUnitService.autoAdd(swmsBasicMeasureUnit));
    }

    @ApiOperation(value = "更新计量单位", notes = "更新")
    @ApiResponses(@ApiResponse(code = 200, message = "处理成功", response = Result.class))
    @PutMapping(value = "/{id}")
    public Result update(@PathVariable String id,@RequestBody SwmsBasicMeasureUnit swmsBasicSupplierInfo) {
        swmsBasicSupplierInfo.setId(id);
        return Result.success(iSwmsBasicMeasureUnitService.update(swmsBasicSupplierInfo));
    }

    @ApiOperation(value = "删除计量单位", notes = "根据id删除供应车间信息")
    @ApiResponses(@ApiResponse(code = 200, message = "处理成功", response = Result.class))
    @DeleteMapping(value = "/{id}")
    public Result delete(@PathVariable String id) {
        return Result.success(iSwmsBasicMeasureUnitService.delete(id));
    }

    @ApiOperation(value = "批量删除计量单位",notes = "根据id集合删除")
    @DeleteMapping(value = "/batchDelete")
    public Result batchDelete(@RequestParam Set<String> ids){
        return Result.success(iSwmsBasicMeasureUnitService.batchDelete(ids));
    }
}
