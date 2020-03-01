package com.jinchi.common.controller;

import com.jinchi.common.domain.BasicInfoProcessBaseValues;
import com.jinchi.common.dto.Result;
import com.jinchi.common.service.BasicInfoProcessBaseValuesService;
import com.jinchi.common.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: LiuTaoYi
 * @time: 2020/3/1 11:27
 * @description:
 */

@RestController
@RequestMapping(value = "/baseValues")
@Api(tags = "生产管理-基础数据-工序计算基准")
public class BasicInfoProcessBaseValuesController {

    @Autowired
    BasicInfoProcessBaseValuesService valuesService;


    @PostMapping(value = "update")
    @ApiOperation(value = "编辑")
    public Result update(@RequestBody BasicInfoProcessBaseValues values) {
        return ResultUtil.success(valuesService.update(values));
    }

    @PostMapping(value = "all")
    @ApiOperation(value = "查询所有")
    public Result getAll() {
        return ResultUtil.success(valuesService.getAll());
    }
}
