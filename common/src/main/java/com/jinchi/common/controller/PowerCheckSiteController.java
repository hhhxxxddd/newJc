package com.jinchi.common.controller;

import com.jinchi.common.domain.PowerCheckSite;
import com.jinchi.common.dto.Result;
import com.jinchi.common.service.PowerCheckSiteService;
import com.jinchi.common.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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

    @PostMapping(value = "add")
    @ApiOperation(value = "新增")
    public Result add(@RequestBody PowerCheckSite checkSite) {
        return ResultUtil.success(siteService.add(checkSite));
    }

    @DeleteMapping(value = "delete")
    @ApiOperation(value = "删除")
    public Result delete(@RequestParam Long id) {
        siteService.deleteById(id);
        return ResultUtil.success();
    }

    @DeleteMapping(value = "deletes")
    @ApiOperation(value = "批量删除")
    public Result delete(@RequestBody Long[] ids) {
        siteService.deleteByIds(ids);
        return ResultUtil.success();
    }

    @GetMapping(value = "byId")
    @ApiOperation(value = "根据id获取单条数据")
    public Result byId(@RequestParam Long id) {
        return ResultUtil.success(siteService.getOne(id));
    }

    @PutMapping(value = "update")
    @ApiOperation(value = "编辑")
    public Result edit(@RequestBody PowerCheckSite checkSite) {
        return ResultUtil.success(siteService.update(checkSite));
    }

    @GetMapping(value = "all")
    @ApiOperation(value = "查询所有")
    public Result getAll(@RequestParam(defaultValue = "", required = false) String condition) {
        return ResultUtil.success(siteService.getAll(condition));
    }

    @GetMapping(value = "page")
    @ApiOperation(value = "分页")
    public Result page(@ApiParam(name = "condition", value = "站点名称") @RequestParam(name = "condition", defaultValue = "", required = false) String condition,
                       @ApiParam(name = "page", value = "页码") @RequestParam(name = "page", defaultValue = "1") Integer page,
                       @ApiParam(name = "size", value = "条目数") @RequestParam(name = "size", defaultValue = "10") Integer size) {
        return ResultUtil.success(siteService.listByPage(condition, page, size));
    }
}
