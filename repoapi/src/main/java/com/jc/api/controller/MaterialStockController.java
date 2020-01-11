package com.jc.api.controller;

import com.jc.api.entity.form.MaterialStockQueryForm;
import com.jc.api.entity.param.MaterialStockQueryParam;
import com.jc.api.entity.po.MaterialStock;
import com.jc.api.service.restservice.IMaterialStockService;
import com.jinchi.common.core.vo.Result;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author XudongHu
 * @className MaterialStockController
 * @apiNote 物料库存Controller
 * @modifer
 * @since 2019/10/31日5:33
 */
@RestController
@Api(tags = "(废弃)智能仓库-总库存管理界面")
@RequestMapping(value = "/stock")
@Slf4j
public class MaterialStockController {
    @Autowired
    private IMaterialStockService iMaterialStockService;


    @ApiOperation(value = "获取所有库存", notes = "获取所有库存")
    @ApiResponses(@ApiResponse(code = 200, message = "处理成功", response = Result.class))
    @GetMapping(value = "/all")
    public Result all() {
        return Result.success(iMaterialStockService.getAll(new MaterialStockQueryParam()));
    }


    @ApiOperation(value = "获取库存-条件", notes = "条件查询")
    @ApiResponses(@ApiResponse(code = 200, message = "处理成功", response = Result.class))
    @ApiImplicitParam(name = "materialStockQueryForm",value = "条件参数", required = true, dataType = "MaterialStockQueryForm")
    @PostMapping(value = "/conditions")
    public Result query(@Valid @RequestBody MaterialStockQueryForm materialStockQueryForm) {
        return Result.success(iMaterialStockService.getAll(materialStockQueryForm.toParam(MaterialStockQueryParam.class)));
    }


    @ApiOperation(value = "获取库存-条件/分页", notes = "条件/分页查询")
    @ApiResponses(@ApiResponse(code = 200, message = "处理成功", response = Result.class))
    @ApiImplicitParam(name = "materialStockQueryForm",value = "条件参数", required = true, dataType = "MaterialStockQueryForm")
    @PostMapping(value = "/pages")
    public Result pages(@Valid @RequestBody MaterialStockQueryForm materialStockQueryForm) {
        return Result.success(iMaterialStockService.getAllByPage(materialStockQueryForm.getPage(),materialStockQueryForm.toParam(MaterialStockQueryParam.class)));
    }

    @ApiOperation(value = "获取目标库存", notes = "根据物料信息id")
    @ApiResponses(@ApiResponse(code = 200, message = "处理成功", response = Result.class))
    @ApiImplicitParam(name = "materialInfoId",type = "path",value = "物料信息id", required = true, dataType = "Long")
    @GetMapping(value = "/{materialInfoId}")
    public Result get(@PathVariable Integer materialInfoId) {
        return Result.success(iMaterialStockService.getByInfoId(materialInfoId));
    }

    @ApiOperation(value = "仅供测试使用-设置库存", notes = "设置一种物料的库存,设置为负数即为减,正数为增,结果不会使库存小于0,放心减")
    @ApiResponses(@ApiResponse(code = 200, message = "处理成功", response = Result.class))
    @ApiImplicitParam(name = "materialStock", value = "库存Po", required = true, dataType = "MaterialStock")
    @PutMapping(value = "/setting")
    public Result setting(@RequestBody MaterialStock materialStock) {
        return Result.success(iMaterialStockService.stockSetting(materialStock));
    }

    @ApiOperation(value = "仅供测试使用-清空所有库存", notes = "清空所有库存,非删除,将袋数与重量置0")
    @ApiResponses(@ApiResponse(code = 200, message = "处理成功", response = Result.class))
    @GetMapping(value = "/truncate")
    public Result stockTruncate() {
        return Result.success(iMaterialStockService.stockTruncate());
    }
}
