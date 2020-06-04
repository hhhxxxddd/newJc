package com.jc.api.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jc.api.service.restservice.ISwmsStockInventoryReallyReportsService;
import com.jinchi.common.core.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 物料实际库存报表 前端控制器
 * </p>
 *
 * @author LiuTaoYi
 * @since 2020-01-13
 */
@RestController
@RequestMapping("/swms-stock-inventory-really-reports")
@Api(tags = "智能仓库-数据查询-实际库存查询")
public class SwmsStockInventoryReallyReportsController {


    @Autowired
    ISwmsStockInventoryReallyReportsService reallyReportsService;

    @ApiOperation(value = "实际库存-条件/分页", notes = "实际库存-条件/分页")
    @ApiResponses(@ApiResponse(code = 200, message = "处理成功", response = Result.class))
    @PostMapping(value = "/pages")
    public Result queryPages(@RequestBody Page page,
                             @RequestParam(required = false) Integer typeId,
                             @RequestParam(required = false) Integer subTypeId,
                             @RequestParam(required = false) Integer supplierId,
                             @RequestParam(required = false) Integer materialNameCode) {
        return Result.success(reallyReportsService.selectByPage(page, typeId, subTypeId, materialNameCode, supplierId));
    }

    @ApiOperation(value = "详情", notes = "详情")
    @ApiResponses(@ApiResponse(code = 200, message = "处理成功", response = Result.class))
    @PostMapping(value = "/details")
    public Result getDetails(@RequestParam Integer materialCode) {
        return Result.success(reallyReportsService.getByBatch(materialCode));
    }
}

