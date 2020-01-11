package com.jinchi.common.controller;

import com.jinchi.common.dto.PowerCheckModelDTO;
import com.jinchi.common.dto.Result;
import com.jinchi.common.service.PowerCheckModelService;
import com.jinchi.common.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: LiuTaoYi
 * @create_time: 2019-12-22 13:02
 * @description:
 **/
@RestController
@Api(tags = "动力点检-点检模板")
@RequestMapping(value = "/checkModel")
public class PowerCheckModelController {

    @Autowired
    PowerCheckModelService modelService;

    @PostMapping(value = "add")
    @ApiOperation(value = "新增点检模板")
    public Result add(@RequestBody PowerCheckModelDTO dto) {
        return ResultUtil.success(modelService.add(dto));
    }

    @DeleteMapping(value = "delete")
    @ApiOperation(value = "删除")
    public Result delete(@RequestParam Long id) {
        modelService.delete(id);
        return ResultUtil.success();
    }

    @DeleteMapping(value = "deletes")
    @ApiOperation(value = "批量删除")
    public Result delete(@RequestBody Long[] ids) {
        modelService.deleteByIds(ids);
        return ResultUtil.success();
    }

    @GetMapping(value = "detail")
    @ApiOperation(value = "详情")
    public Result detail(@RequestParam Long id) {
        return ResultUtil.success(modelService.detail(id));
    }

    @GetMapping(value = "bySiteCode")
    @ApiOperation(value = "根据站点id获取模板")
    public Result bySiteCode(@RequestParam Long siteCode) {
        return ResultUtil.success(modelService.getModelBySiteCode(siteCode));
    }

    @PostMapping(value = "update")
    @ApiOperation(value = "编辑")
    public Result edit(@RequestBody PowerCheckModelDTO dto) {
        return ResultUtil.success(modelService.update(dto));
    }

    @GetMapping(value = "page")
    @ApiOperation(value = "分页")
    public Result page(@ApiParam(name = "condition", value = "模板名称") @RequestParam(name = "condition", defaultValue = "", required = false) String condition,
                       @ApiParam(name = "page", value = "页码") @RequestParam(name = "page", defaultValue = "1") Integer page,
                       @ApiParam(name = "size", value = "条目数") @RequestParam(name = "size", defaultValue = "10") Integer size) {
        return ResultUtil.success(modelService.byPage(condition, page, size));
    }
}
