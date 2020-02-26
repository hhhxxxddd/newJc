package com.jinchi.common.controller;

import com.jinchi.common.domain.BasicInfoProcessInitValues;
import com.jinchi.common.dto.Result;
import com.jinchi.common.service.BasicInfoProcessInitValuesService;
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
 * @time: 2020/2/26 18:03
 * @description:
 */

@RestController
@RequestMapping(value = "/initValues")
@Api(tags = "生产管理-基础数据-工序上期初始值")
public class BasicInfoProcessInitValuesController {

    @Autowired
    BasicInfoProcessInitValuesService valuesService;

    @PostMapping(value = "all")
    @ApiOperation(value = "查询")
    public Result getAll() {
        return ResultUtil.success(valuesService.getAll());
    }

    @PostMapping(value = "edit")
    @ApiOperation(value = "修改")
    public Result update(@RequestBody BasicInfoProcessInitValues values) {
        valuesService.update(values);
        return ResultUtil.success();
    }
}
