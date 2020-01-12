package com.jc.api.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jc.api.entity.SwmsBasicDeliveryAddressInfo;
import com.jc.api.entity.SwmsBasicSafetyStock;
import com.jc.api.service.restservice.ISwmsBasicSafetyStockService;
import com.jc.api.service.restservice.imp.SwmsBasicSafetyStockService;
import com.jinchi.common.core.vo.Result;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

/**
 * @Auther: River
 * @Date: 2020/1/12 16:42
 * @Description:    安全库存controller层
 */
@RestController
@Api(tags = "智能仓库-安全库存信息管理界面")
@RequestMapping(value = "/swmsBasicSafetyStock")
@Slf4j
public class SwmsBasicSafetyStockController {

    @Autowired
    private ISwmsBasicSafetyStockService swmsBasicSafetyStockService;

    @ApiOperation(value = "获取所有安全库存", notes = "获取所有安全库存")
    @ApiResponses(@ApiResponse(code = 200, message = "处理成功", response = Result.class))
    @GetMapping(value = "/getAll")
    public Result getAll(){
        return Result.success(swmsBasicSafetyStockService.getAll(""));
    }

    @ApiOperation(value = "获取安全库存-条件/分页", notes = "条件获取安全库存-分页")
    @ApiResponses(@ApiResponse(code = 200, message = "处理成功", response = Result.class))
    @PostMapping(value = "/pages")
    public Result queryPages(@RequestBody Page page, @RequestParam(required = false) String materialName) {
        return Result.success(swmsBasicSafetyStockService.getAllByPage(page, materialName));
    }

    @ApiOperation(value = "新增安全库存", notes = "新增")
    @ApiImplicitParam(name = "swmsBasicSafetyStock", value = "新增参数", required = true, dataType = "SwmsBasicSafetyStock")
    @ApiResponses(@ApiResponse(code = 200, message = "处理成功", response = Result.class))
    @PostMapping(value = "/add")
    public Result add(@Valid @RequestBody SwmsBasicSafetyStock swmsBasicSafetyStock) {
        return Result.success(swmsBasicSafetyStockService.add(swmsBasicSafetyStock));
    }

    @ApiOperation(value = "更新安全库存信息", notes = "更新")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "swmsBasicSafetyStock", value = "更新参数", required = true, dataType = "SwmsBasicSafetyStock")})
    @ApiResponses(@ApiResponse(code = 200, message = "处理成功", response = Result.class))
    @PutMapping(value = "/{id}")
    public Result update(@RequestBody SwmsBasicSafetyStock swmsBasicSafetyStock) {
        return Result.success(swmsBasicSafetyStockService.update(swmsBasicSafetyStock));
    }

    @ApiOperation(value = "删除安全库存信息", notes = "根据id删除安全库存信息")
    @ApiImplicitParam(name = "id", value = "物料子类型信息id", required = true, dataType = "long")
    @ApiResponses(@ApiResponse(code = 200, message = "处理成功", response = Result.class))
    @DeleteMapping(value = "/{id}")
    public Result delete(@PathVariable Integer id) {
        log.debug("根据id删除物料子类型记录:{}", id);
        return Result.success(swmsBasicSafetyStockService.delete(id));
    }

    @ApiOperation(value = "批量删除安全库存信息",notes = "根据id集合删除")
    @DeleteMapping(value = "/batchDelete")
    public Result batchDelete(@RequestParam Set<String> ids){
        return Result.success(swmsBasicSafetyStockService.batchDelete(ids));
    }
}
