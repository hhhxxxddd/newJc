package com.jc.api.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jc.api.entity.SwmsBasicSupplierInfo;
import com.jc.api.service.restservice.ISwmsBasicSupplierInfoService;
import com.jinchi.common.core.vo.Result;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import java.util.Set;

/**
 * @author XudongHu
 * @apiNote 物料供应商给controller
 * @className SwmsBasicSupplierInfoController
 * @modifier
 * @since 20.1.12日0:22
 */
@RestController
@Api(tags = "智能仓库-供货商信息管理界面")
@RequestMapping(value = "/SwmsBasicSupplierInfo")
@Slf4j
public class SwmsBasicSupplierInfoController {
    @Autowired
    private ISwmsBasicSupplierInfoService iSwmsBasicSupplierInfoService;


    @ApiOperation(value = "获取所有供应商", notes = "获取所有供应商")
    @ApiResponses(@ApiResponse(code = 200, message = "处理成功", response = Result.class))
    @GetMapping(value = "/getAll")
    public Result getAll(){
        return Result.success(iSwmsBasicSupplierInfoService.getAll(new SwmsBasicSupplierInfo()));
    }

    @ApiOperation(value = "获取所有供应商-条件", notes = "条件获取所有供应商")
    @ApiResponses(@ApiResponse(code = 200, message = "处理成功", response = Result.class))
    @PostMapping(value = "/conditions")
    public Result query(@RequestBody SwmsBasicSupplierInfo swmsBasicSupplierInfo){
        return Result.success(iSwmsBasicSupplierInfoService.getAll(swmsBasicSupplierInfo));
    }

    @ApiOperation(value = "获取供应商-条件/分页", notes = "条件获取供应商记录-分页")
    @ApiResponses(@ApiResponse(code = 200, message = "处理成功", response = Result.class))
    @PostMapping(value = "/pages")
    public Result queryPages(@RequestBody Page page,@RequestParam(required = false) String supplierName) {
        return Result.success(iSwmsBasicSupplierInfoService.getAllByPage(page,supplierName));
    }

    @ApiOperation(value = "新增供应商信息", notes = "新增")
    @ApiResponses(@ApiResponse(code = 200, message = "处理成功", response = Result.class))
    @PostMapping(value = "/add")
    public Result add(@RequestBody SwmsBasicSupplierInfo swmsBasicSupplierInfo) {
        swmsBasicSupplierInfo.setAutoFlag(false);
        return Result.success(iSwmsBasicSupplierInfoService.add(swmsBasicSupplierInfo));
    }

    @ApiOperation(value = "仅供测试使用-自动新增供应商信息", notes = "自动新增,存在则无动作,不存在则新增")
    @ApiResponses(@ApiResponse(code = 200, message = "处理成功", response = Result.class))
    @PostMapping(value = "/autoAdd")
    public Result autoAdd(@RequestParam @NotBlank(message = "供应商代号不能为空白") String supplierCode) {
        return Result.success(iSwmsBasicSupplierInfoService.autoAdd(new SwmsBasicSupplierInfo().setMaterialSupplierCode(supplierCode)));
    }

    @ApiOperation(value = "更新供应商信息", notes = "更新")
    @ApiResponses(@ApiResponse(code = 200, message = "处理成功", response = Result.class))
    @PutMapping(value = "/{id}")
    public Result update(@PathVariable String id,@RequestBody SwmsBasicSupplierInfo swmsBasicSupplierInfo) {
        swmsBasicSupplierInfo.setId(id);
        return Result.success(iSwmsBasicSupplierInfoService.update(swmsBasicSupplierInfo));
    }

    @ApiOperation(value = "删除供应商信息", notes = "根据id删除供应车间信息")
    @ApiResponses(@ApiResponse(code = 200, message = "处理成功", response = Result.class))
    @DeleteMapping(value = "/{id}")
    public Result delete(@PathVariable Integer id) {
        log.debug("根据id删除供应车间记录:{}", id);
        return Result.success(iSwmsBasicSupplierInfoService.delete(id));
    }

    @ApiOperation(value = "批量删除供应商信息",notes = "根据id集合删除")
    @DeleteMapping(value = "/batchDelete")
    public Result batchDelete(@RequestParam Set<String> ids){
        return Result.success(iSwmsBasicSupplierInfoService.batchDelete(ids));
    }

}
