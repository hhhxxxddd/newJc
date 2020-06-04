package com.jc.api.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jc.api.service.restservice.ISwmsStockInOutReportsService;
import com.jinchi.common.core.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 物料进出库查询报表 前端控制器
 * </p>
 *
 * @author LiuTaoYi
 * @since 2020-01-13
 */
@RestController
@RequestMapping("/swms-stock-in-out-reports")
@Api(tags = "智能仓库-数据查询-进出库查询")
public class SwmsStockInOutReportsController {

    @Autowired
    ISwmsStockInOutReportsService inOutReportsService;

    @ApiOperation(value = "进出库查询-条件/分页", notes = "进出库查询-条件/分页")
    @ApiResponses(@ApiResponse(code = 200, message = "处理成功", response = Result.class))
    @PostMapping(value = "/pages")
    public Result queryPagesIn(@RequestBody Page page, @RequestParam(required = false) Integer typeId,
                               @RequestParam(required = false) Integer subTypeId,
                               @RequestParam(required = false) Integer materialCode,
                               @RequestParam(required = false) Integer supplierCode,
                               @RequestParam(required = false) String batch) {
        return Result.success(inOutReportsService.selectByPage(page, typeId, subTypeId, materialCode, supplierCode, batch));
    }
}

