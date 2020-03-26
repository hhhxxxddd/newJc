package com.jinchi.app.controller;

import com.jinchi.app.dto.IdDto;
import com.jinchi.app.dto.QueryDTO;
import com.jinchi.app.dto.Result;
import com.jinchi.app.service.PowerCheckModelService;
import com.jinchi.app.service.PowerCheckSiteService;
import com.jinchi.app.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public Result page(@RequestBody QueryDTO dto) {
        return ResultUtil.success(siteService.listByPage(dto));
    }

    @PostMapping(value = "bySiteCode")
    @ApiOperation(value = "根据站点id获取模板")
    public Result bySiteCode(@RequestBody IdDto dto) {
        return ResultUtil.success(modelService.getModelBySiteCode(dto.getId()));
    }
}
