package com.jinchi.common.controller;

import com.jinchi.common.dto.Result;
import com.jinchi.common.service.AnodeCostAccountService;
import com.jinchi.common.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: LiuTaoYi
 * @create_time: 2019-12-30 10:40
 * @description:
 **/
@RestController
@RequestMapping(value = "/anodeCostAccount")
@Api(tags = "正极-成本核算")
public class AnodeCostAccountController {

    @Autowired
    AnodeCostAccountService costAccountService;

    @GetMapping(value = "getDate")
    @ApiOperation(value = "获取日期")
    public Result getDate(@RequestParam Integer periodId) {
        return ResultUtil.success(costAccountService.getDate(periodId));
    }

    @GetMapping(value = "confirm")
    @ApiOperation(value = "确定")
    public Result confirm(@RequestParam Integer lineCode, @RequestParam Integer periodId, @RequestParam Integer periods) {
        return ResultUtil.success(costAccountService.confirm(lineCode, periodId, periods));
    }
}
