package com.jinchi.common.controller;

import com.jinchi.common.dto.Result;
import com.jinchi.common.service.PrecursorCostAccountService;
import com.jinchi.common.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: LiuTaoYi
 * @create_time: 2019-12-12 20:08
 * @description:
 **/
@RestController
@RequestMapping(value = "/precursorCostAccount")
@Api(tags = "前驱体-成本核算")
public class PrecursorCostAccountController {
    @Autowired
    PrecursorCostAccountService accountService;

    @GetMapping(value = "getStartDate")
    @ApiOperation(value = "获取开始日期")
    public Result getDate(@RequestParam Integer periodCode) {
        return ResultUtil.success(accountService.getStartDate(periodCode));
    }

    //Integer lineCode, Integer periodCode, String startTime
    @GetMapping(value = "mainMatConfirm")
    @ApiOperation(value = "主材确定")
    public Result mainConfirm(@RequestParam Integer lineCode, @RequestParam Integer periodCode, @RequestParam String startTime) {
        List res = accountService.mainMaterialConfirm(lineCode, periodCode, startTime);
        if (res.size() != 0) {
            return ResultUtil.success(res);
        } else {
            return ResultUtil.error("基础数据不全，没有成本核算数据");
        }
    }

    @GetMapping(value = "auxMatConfirm")
    @ApiOperation(value = "辅材确定")
    public Result auxConfirm(@RequestParam Integer lineCode, @RequestParam Integer periodCode, @RequestParam String startTime) {
        List res = accountService.auxiliaryConfirm(lineCode, periodCode, startTime);
        if (res.size() != 0) {
            return ResultUtil.success(res);
        } else {
            return ResultUtil.error("基础数据不全，没有成本核算数据");
        }
    }
}
