package com.jinchi.common.controller;


import com.jinchi.common.dto.PageBean;
import com.jinchi.common.dto.RawTestReportDTO;
import com.jinchi.common.dto.Result;
import com.jinchi.common.service.RawTestReportRecordService;
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
@RequestMapping(value = "/rawTestReport")
@Api(tags = "质量流程-数据录入-原材料检验")
public class RawTestReportController {
    @Autowired
    private RawTestReportRecordService rawTestReportRecordService;

    @GetMapping
    @ApiOperation(value = "查询所有")
    public Result<List<RawTestReportDTO>> findAll() {
        return ResultUtil.success(rawTestReportRecordService.findAll());
    }

    @GetMapping(value = "details")
    @ApiOperation(value = "根据样品送检id查看详情")
    public Result<RawTestReportDTO> findDetailsById(@ApiParam(name = "id", value = "样品送检主键") @RequestParam Integer id) {
        return ResultUtil.success(rawTestReportRecordService.findDetailsById(id));
    }

    @GetMapping(value = "/pages")
    @ApiOperation(value = "查询所有-分页", notes = "根据送样工厂名称模糊")
    public Result<PageBean<RawTestReportDTO>> getAllByPages(@ApiParam(name = "factoryName", value = "送样工厂名称") @RequestParam(required = false) String factoryName, PageBean pageBean) {
        return ResultUtil.success(rawTestReportRecordService.findByFactoryNameByPage(factoryName, pageBean));
    }

    @PutMapping
    @ApiOperation(value = "保存")
    public Result<RawTestReportDTO> update(@Valid @RequestBody RawTestReportDTO rawTestReportDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
        }
        return ResultUtil.success(rawTestReportRecordService.updateResults(rawTestReportDTO));
    }

    @GetMapping(value = "detailsByBatchNumberId")
    @ApiOperation(value = "根据批号id查看详情")
    public Result<RawTestReportDTO> findDetails(@ApiParam(name = "id", value = "批号主键") @RequestParam Integer id) {
        return ResultUtil.success(rawTestReportRecordService.details(id));
    }

}