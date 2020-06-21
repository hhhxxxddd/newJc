package com.jinchi.common.controller;

import com.jinchi.common.domain.BasicInfoAnodeBurningLossRate;
import com.jinchi.common.dto.Result;
import com.jinchi.common.service.AnodeBurningLossRateService;
import com.jinchi.common.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: LiuTaoYi
 * @time: 2020/6/21 9:30
 * @description:
 */
@RestController
@RequestMapping(value = "anodeBurningLossRate")
@Api(tags = "正极成本-基础数据-窑炉烧损系数")
public class AnodeBurningLossRateController {

    @Autowired
    AnodeBurningLossRateService rateService;

    @PostMapping(value = "add")
    @ApiOperation(value = "新增")
    public Result add(@RequestBody BasicInfoAnodeBurningLossRate value) {
        return ResultUtil.success(rateService.add(value));
    }

    @DeleteMapping(value = "delete")
    @ApiOperation(value = "删除")
    public Result delete(@RequestParam Integer id) {
        rateService.delete(id);
        return ResultUtil.success();
    }

    @DeleteMapping(value = "ids")
    @ApiOperation(value = "批量删除")
    public Result deleteByIds(@RequestBody Integer[] ids) {
        rateService.batchDelete(ids);
        return ResultUtil.success();
    }

    @PutMapping(value = "update")
    @ApiOperation(value = "编辑")
    public Result update(@RequestBody BasicInfoAnodeBurningLossRate value) {
        return ResultUtil.success(rateService.update(value));
    }


    @GetMapping(value = "page")
    @ApiOperation(value = "分页")
    public Result getPage(@ApiParam(name = "condition", value = "查询条件") @RequestParam(name = "condition", defaultValue = "", required = false) String condition,
                          @ApiParam(name = "page", value = "页码") @RequestParam(name = "page", defaultValue = "1") Integer page,
                          @ApiParam(name = "size", value = "条目数") @RequestParam(name = "size", defaultValue = "10") Integer size) {
        return ResultUtil.success(rateService.page(condition, page, size));
    }
}
