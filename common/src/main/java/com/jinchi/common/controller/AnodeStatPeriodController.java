package com.jinchi.common.controller;

import com.jinchi.common.domain.BasicInfoAnodePeriod;
import com.jinchi.common.dto.Result;
import com.jinchi.common.service.AnodeStatPeriodService;
import com.jinchi.common.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: LiuTaoYi
 * @create_time: 2019-11-25 15:25
 * @description:
 **/

@RestController
@RequestMapping(value = "/anodeBasicDataStatPeriod")
@Api(tags = "正极成本-基础数据-统计周期")
public class AnodeStatPeriodController {

    @Autowired
    AnodeStatPeriodService periodService;

    @PostMapping(value = "add")
    @ApiOperation(value = "新增")
    public Result add(@RequestBody BasicInfoAnodePeriod period) {
        return ResultUtil.success(periodService.add(period));
    }

    @DeleteMapping(value = "delete")
    @ApiOperation(value = "删除")
    public Result delete(@ApiParam(name = "id", value = "主键") @RequestParam Integer id) {
        periodService.delete(id);
        return ResultUtil.success();
    }

    @PutMapping(value = "update")
    @ApiOperation(value = "编辑")
    public Result update(@RequestBody BasicInfoAnodePeriod period) {
        return ResultUtil.success(periodService.update(period));
    }

    @GetMapping(value = "all")
    @ApiOperation(value = "查询所有")
    public Result getAll() {
        return ResultUtil.success(periodService.getAll());
    }
}
