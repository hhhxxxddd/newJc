package com.jinchi.app.controller;

import com.jinchi.app.dto.Result;
import com.jinchi.app.service.PowerCheckModelService;
import com.jinchi.app.service.PowerCheckSiteService;
import com.jinchi.app.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: LiuTaoYi
 * @create_time: 2019-12-21 13:21
 * @description:
 **/
@RestController
@Api(tags = "动力点检-点检站点")
@RequestMapping(value = "/checkSite")
public class PowerCheckSiteController {

    @Autowired
    PowerCheckSiteService siteService;
    @Autowired
    PowerCheckModelService modelService;

    @PostMapping(value = "all")
    @ApiOperation(value = "查询所有")
    public Result getAll(@RequestParam(defaultValue = "", required = false) String condition) {
        return ResultUtil.success(siteService.getAll(condition));
    }

    @PostMapping(value = "page")
    @ApiOperation(value = "分页--名称模糊查询")
    public Result page(@ApiParam(name = "condition", value = "站点名称") @RequestParam(name = "condition", defaultValue = "", required = false) String condition,
                       @ApiParam(name = "page", value = "页码") @RequestParam(name = "page", defaultValue = "1") Integer page,
                       @ApiParam(name = "size", value = "条目数") @RequestParam(name = "size", defaultValue = "10") Integer size) {
        return ResultUtil.success(siteService.listByPage(condition, page, size));
    }

    @PostMapping(value = "bySiteCode")
    @ApiOperation(value = "根据站点id获取模板")
    public Result bySiteCode(@RequestParam Long siteCode) {
        return ResultUtil.success(modelService.getModelBySiteCode(siteCode));
    }
}
