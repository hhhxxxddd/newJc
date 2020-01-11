package com.jinchi.common.controller;

import com.jinchi.common.domain.TestReportRecord;
import com.jinchi.common.dto.PageBean;
import com.jinchi.common.dto.Result;
import com.jinchi.common.dto.quality.product.ProductReportHeadDTO;
import com.jinchi.common.service.ProductRecordService;
import com.jinchi.common.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author：XudongHu
 * @className:ProductRecordController
 * @description:
 * @date:17:37 2019/1/10
 */
@RestController
@RequestMapping(value = "/productRecord")
@Api(tags = "质量流程-数据录入-成品检验")
public class ProductRecordController {
    @Autowired
    private ProductRecordService productRecordService;

    @ApiOperation(value = "查询所有-分页",notes = "送检工厂名称模糊")
    @GetMapping(value = "/pages")
    public Result<PageBean> pagesByFactory(@ApiParam(name = "factorypattern",value = "送检工厂名称") @RequestParam(required = false) String factory, PageBean pageBean){
        return ResultUtil.success(productRecordService.pagesByFactory(factory,pageBean));
    }

    @ApiOperation(value = "详情",notes = "批号id")
    @GetMapping(value = "/{batchNumberId}")
    public Result<ProductReportHeadDTO> byBatchNumberId(@ApiParam(name = "batchNumberId",value = "批号id") @PathVariable Integer batchNumberId){
        return ResultUtil.success(productRecordService.byBatchNumberId(batchNumberId));
    }

    @ApiOperation(value = "更新",notes = "包括是否合格验证")
    @PutMapping
    public Result<ProductReportHeadDTO> update(@RequestBody ProductReportHeadDTO productReportHeadDTO ){
        return ResultUtil.success(productRecordService.updateValue(productReportHeadDTO));
    }

    @ApiOperation(value = "更新择优人",notes = "只更新择优人")
    @PutMapping(value = "/rate")
    public Result<TestReportRecord> updateRate(@RequestBody TestReportRecord testReportRecord) {
        return ResultUtil.success(productRecordService.updateRate(testReportRecord));
    }

}
