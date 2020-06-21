package com.jinchi.common.controller;

import com.jinchi.common.domain.BasicInfoAnodeCoefficientRate;
import com.jinchi.common.dto.Result;
import com.jinchi.common.service.AnodeCoefficientRateService;
import com.jinchi.common.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: LiuTaoYi
 * @time: 2020/6/20 19:05
 * @description:
 */
@RestController
@RequestMapping(value = "anodeCoefficientRate")
@Api(tags = "正极成本-基础数据-折算比例关系")
public class AnodeCoefficientRateController {

    @Autowired
    AnodeCoefficientRateService rateService;

    @PostMapping(value = "add")
    @ApiOperation(value = "新增")
    public Result add(@RequestBody BasicInfoAnodeCoefficientRate value) {
        return ResultUtil.success(rateService.add(value));
    }

    @DeleteMapping(value = "delete")
    @ApiOperation(value = "删除")
    public Result delete(@RequestParam Integer id) {
        rateService.delete(id);
        return ResultUtil.success();
    }


    @PutMapping(value = "update")
    @ApiOperation(value = "编辑")
    public Result update(@RequestBody BasicInfoAnodeCoefficientRate value) {
        return ResultUtil.success(rateService.update(value));
    }


    @GetMapping(value = "page")
    @ApiOperation(value = "分页")
    public Result getPage(@ApiParam(name = "page", value = "页码") @RequestParam(name = "page", defaultValue = "1") Integer page,
                          @ApiParam(name = "size", value = "条目数") @RequestParam(name = "size", defaultValue = "10") Integer size) {
        return ResultUtil.success(rateService.page(page, size));
    }

}
