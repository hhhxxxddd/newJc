package com.jinchi.common.controller;

import com.jinchi.common.domain.BasicInfoBatchConfig;
import com.jinchi.common.dto.Result;
import com.jinchi.common.service.BasicInfoBatchConfigService;
import com.jinchi.common.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: LiuTaoYi
 * @create_time: 2019-12-20 17:13
 * @description:
 **/
@RestController
@RequestMapping(value = "/batchConfig")
@Api(tags = "批次信息基础数据")
public class BasicInfoBatchConfigController {

    @Autowired
    BasicInfoBatchConfigService configService;

    @PostMapping(value = "add")
    @ApiOperation(value = "新增")
    public Result add(@RequestBody BasicInfoBatchConfig config) {
        return ResultUtil.success(configService.add(config));
    }

    @PutMapping(value = "update")
    @ApiOperation(value = "编辑")
    public Result update(@RequestBody BasicInfoBatchConfig config) {
        return ResultUtil.success(configService.update(config));
    }

    @GetMapping(value = "getCurrent")
    @ApiOperation(value = "查询当前记录")
    public Result getAll() {
        return ResultUtil.success(configService.getOne());
    }

    @GetMapping(value = "getValueByProcess")
    @ApiOperation(value = "查询基数")
    public Result getValueByProcess(@RequestParam String process) {
        return ResultUtil.success(configService.getValueByProcess(process));
    }
}
