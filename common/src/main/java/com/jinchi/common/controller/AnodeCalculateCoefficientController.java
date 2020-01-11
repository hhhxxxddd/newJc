package com.jinchi.common.controller;

import com.jinchi.common.domain.BasicInfoAnodeCalculateCoefficient;
import com.jinchi.common.dto.Result;
import com.jinchi.common.service.AnodeCalculateCoefficientService;
import com.jinchi.common.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: LiuTaoYi
 * @create_time: 2019-11-25 23:23
 * @description:
 **/
@RestController
@RequestMapping(value = "/anodeOthers")
@Api(tags = "正极成本-基础数据-其它基础数据")
public class AnodeCalculateCoefficientController {

    @Autowired
    AnodeCalculateCoefficientService coefficientService;

    @PostMapping(value = "add")
    @ApiOperation(value = "新增")
    public Result add(@RequestBody BasicInfoAnodeCalculateCoefficient coefficient) {
        return ResultUtil.success(coefficientService.add(coefficient));
    }

    @PutMapping(value = "update")
    @ApiOperation(value = "编辑")
    public Result update(@RequestBody BasicInfoAnodeCalculateCoefficient coefficient) {
        return ResultUtil.success(coefficientService.update(coefficient));
    }

    @GetMapping(value = "getCurrent")
    @ApiOperation(value = "查询当前记录")
    public Result getAll() {
        return ResultUtil.success(coefficientService.getOne());
    }
}
