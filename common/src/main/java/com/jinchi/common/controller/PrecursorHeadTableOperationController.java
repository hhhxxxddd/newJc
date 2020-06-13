package com.jinchi.common.controller;

import com.jinchi.common.dto.Result;
import com.jinchi.common.service.PrecursorHeadTableOperationService;
import com.jinchi.common.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: LiuTaoYi
 * @time: 2020/6/12 17:05
 * @description:
 */
@RestController
@RequestMapping(value = "/precursorHeadCheck")
@Api(tags = "前驱体-各模块头表操作")
public class PrecursorHeadTableOperationController {

    @Autowired
    PrecursorHeadTableOperationService operationService;

    @GetMapping(value = "/check")
    @ApiOperation(value = "根据期数和周期类型来判断该期的头表是否可以编辑结束时间")
    public Result getLastMonPotency(@RequestParam Integer periodId,
                                    @RequestParam Integer periods) {
        Map<String, String> map = new HashMap<>();
        map.put("flag", String.valueOf(operationService.checkDate(periodId, periods)));
        return ResultUtil.success(map);
    }
}
