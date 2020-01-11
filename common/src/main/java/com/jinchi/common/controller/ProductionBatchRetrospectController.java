package com.jinchi.common.controller;

import com.jinchi.common.dto.Result;
import com.jinchi.common.service.ProductionBatchRetrospectService;
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
 * @create_time: 2019-10-15 15:58
 * @description:
 **/
@RestController
@RequestMapping(value = "/productionBatchRetrospect")
@Api(tags = "生产管理-批次追溯")
public class ProductionBatchRetrospectController {

    @Autowired
    ProductionBatchRetrospectService retrospectService;

    @GetMapping(value = "page")
    @ApiOperation(value = "根据batch分页查询")
    public Result page(@RequestParam(defaultValue = "", required = false) String condition,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer size) {
        return ResultUtil.success(retrospectService.page(condition, page, size));
    }
}
