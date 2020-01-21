package com.jc.api.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jc.api.service.restservice.ISwmsStockInventoryDailyReportsService;
import com.jinchi.common.core.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 物料库存日报表 前端控制器
 * </p>
 *
 * @author LiuTaoYi
 * @since 2020-01-13
 */
@RestController
@Api(tags = "智能仓库-数据查询-库存日报")
@RequestMapping("/swms-stock-inventory-daily-reports")
public class SwmsStockInventoryDailyReportsController {

    @Autowired
    ISwmsStockInventoryDailyReportsService inventoryDailyReportsService;

    @ApiOperation(value = "库存日报-条件/分页", notes = "库存日报-条件/分页")
    @ApiResponses(@ApiResponse(code = 200, message = "处理成功", response = Result.class))
    @PostMapping(value = "/pagesIn")
    public Result queryPages(@RequestBody Page page, @RequestParam(required = false) Integer typeId,
                             @RequestParam(required = false) Integer subTypeId,
                             @RequestParam(required = false) String startTime,
                             @RequestParam(required = false) String endTime) {
        return Result.success(inventoryDailyReportsService.selectByPage(page, typeId, subTypeId, startTime, endTime));
    }

    @ApiOperation(value = "编辑", notes = "编辑")
    @ApiResponses(@ApiResponse(code = 200, message = "处理成功", response = Result.class))
    @PostMapping(value = "/edit")
    public Result update(@RequestParam(required = true) Long id,
                             @RequestParam(required = true) String comments) {
        inventoryDailyReportsService.update(id,comments);
        return Result.success();
    }
}

