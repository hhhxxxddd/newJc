package com.jc.api.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jc.api.entity.SwmsBasicMaterialInfo;
import com.jc.api.service.restservice.ISwmsBasicMaterialInfoService;
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
 * @apiNote 物料信息界面
 * @className SwmsBasicMaterialInfoController
 * @modifier
 * @since 20.1.12日4:58
 */
@RestController
@Api(tags = "智能仓库-物料信息管理界面")
@RequestMapping(value = "/SwmsBasicMaterialInfo")
@Slf4j
public class SwmsBasicMaterialInfoController {
    @Autowired
    private ISwmsBasicMaterialInfoService iSwmsBasicMaterialInfoService;


    @ApiOperation(value = "获取所有物料信息", notes = "获取所有物料信息")
    @ApiResponses(@ApiResponse(code = 200, message = "处理成功", response = Result.class))
    @GetMapping(value = "/getAll")
    public Result getAll(){
        return Result.success(iSwmsBasicMaterialInfoService.getAll(null));
    }

    @ApiOperation(value = "获取所有物料信息-名称模糊")
    @ApiResponses(@ApiResponse(code = 200, message = "处理成功", response = Result.class))
    @PostMapping(value = "/conditions")
    public Result query(@RequestParam String name){
        return Result.success(iSwmsBasicMaterialInfoService.getAll(name));
    }

    @ApiOperation(value = "获取物料信息-条件/分页", notes = "条件获取物料信息记录-分页")
    @ApiResponses(@ApiResponse(code = 200, message = "处理成功", response = Result.class))
    @PostMapping(value = "/pages")
    public Result queryPages(@RequestBody Page page, @RequestParam(required = false) String supplierName) {
        return Result.success(iSwmsBasicMaterialInfoService.getAllByPage(page,supplierName));
    }

    @ApiOperation(value = "新增物料信息", notes = "新增")
    @ApiResponses(@ApiResponse(code = 200, message = "处理成功", response = Result.class))
    @PostMapping(value = "/add")
    public Result add(@RequestBody SwmsBasicMaterialInfo swmsBasicSupplierInfo) {
        swmsBasicSupplierInfo.setAutoFlag(false);
        return Result.success(iSwmsBasicMaterialInfoService.add(swmsBasicSupplierInfo,swmsBasicSupplierInfo.getSupplierIds()));
    }

    @ApiOperation(value = "仅供测试使用-自动新增物料信息", notes = "自动新增,存在则无动作,不存在则新增")
    @ApiResponses(@ApiResponse(code = 200, message = "处理成功", response = Result.class))
    @PostMapping(value = "/autoAdd")
    public Result autoAdd(@RequestBody SwmsBasicMaterialInfo swmsBasicMaterialInfo) {
        return Result.success(iSwmsBasicMaterialInfoService.autoAdd(swmsBasicMaterialInfo));
    }

    @ApiOperation(value = "更新物料信息", notes = "更新")
    @ApiResponses(@ApiResponse(code = 200, message = "处理成功", response = Result.class))
    @PutMapping(value = "/{id}")
    public Result update(@PathVariable String id,@RequestBody SwmsBasicMaterialInfo swmsBasicSupplierInfo) {
        swmsBasicSupplierInfo.setId(id);
        return Result.success(iSwmsBasicMaterialInfoService.update(swmsBasicSupplierInfo,swmsBasicSupplierInfo.getSupplierIds()));
    }

    @ApiOperation(value = "删除物料信息", notes = "根据id删除供应车间信息")
    @ApiResponses(@ApiResponse(code = 200, message = "处理成功", response = Result.class))
    @DeleteMapping(value = "/{id}")
    public Result delete(@PathVariable String id) {
        return Result.success(iSwmsBasicMaterialInfoService.delete(id));
    }

    @ApiOperation(value = "批量删除物料信息",notes = "根据id集合删除")
    @DeleteMapping(value = "/batchDelete")
    public Result batchDelete(@RequestParam Set<String> ids){
        return Result.success(iSwmsBasicMaterialInfoService.batchDelete(ids));
    }

    @ApiOperation(value = "根据物料大类搜索",notes = "根据物料大类搜索")
    @GetMapping(value = "/getByType")
    public Result getByType(@RequestParam Integer type){
        return Result.success(iSwmsBasicMaterialInfoService.getByType(type));
    }
}
