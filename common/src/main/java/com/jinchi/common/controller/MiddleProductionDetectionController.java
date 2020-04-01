package com.jinchi.common.controller;


import com.jinchi.common.dto.PageBean;
import com.jinchi.common.dto.RawTestReportDTO;
import com.jinchi.common.dto.Result;
import com.jinchi.common.service.MiddleProductionDetectionService;
import com.jinchi.common.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/middleProductionDetection")
@Api(tags = "质量流程-数据录入-中间品检验")
public class MiddleProductionDetectionController {
    @Autowired
    private MiddleProductionDetectionService middleProductionDetectionService;

    @GetMapping(value = "")
    @ApiOperation(value = "查询所有中间件检验")
    public Result<List<RawTestReportDTO>> findAll() {
        return ResultUtil.success(middleProductionDetectionService.findAll());
    }

    @GetMapping(value = "/pages")
    @ApiOperation(value = "查询所有-分页", notes = "根据送样工厂名称模糊")
    public Result<PageBean<RawTestReportDTO>> getAllByPages(@ApiParam(name = "factoryName", value = "送样工厂名称") @RequestParam(required = false) String factoryName, PageBean pageBean) {
        return ResultUtil.success(middleProductionDetectionService.findByFactoryNameByPage(factoryName, pageBean));
    }

    @GetMapping(value = "/details/{id}")
    @ApiOperation(value = "详情")
    public Result<Object> findDetailById(@ApiParam(name = "id", value = "样品送检主键") @PathVariable Integer id) {
        return ResultUtil.success(middleProductionDetectionService.findDetailsById(id));
    }

    @GetMapping(value = "/detailsByBatchNumberId/{id}")
    @ApiOperation(value = "详情")
    public Result<Object> findDetailByBatchNumberId(@ApiParam(name = "id", value = "batchNumber主键") @PathVariable Integer id) {
        return ResultUtil.success(middleProductionDetectionService.findDetailsByBatchNumberIdForClient(id));
    }

    @PutMapping
    @ApiOperation(value = "保存")
    public Result<RawTestReportDTO> update(@Valid @RequestBody RawTestReportDTO rawTestReportDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
        }
        return ResultUtil.success(middleProductionDetectionService.update(rawTestReportDTO));
    }

    @PostMapping
    @ApiOperation(value = "发布")
    public Result<Object> publish(@ApiParam(name = "id", value = "样品送检主键") @RequestParam Integer id) {
        return ResultUtil.success(middleProductionDetectionService.publish(id));

    }


}