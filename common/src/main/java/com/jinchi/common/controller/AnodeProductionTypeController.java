package com.jinchi.common.controller;


import com.jinchi.common.domain.BasicInfoAnodeProductionType;
import com.jinchi.common.dto.Result;
import com.jinchi.common.service.AnodeProductionTypeService;
import com.jinchi.common.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: LiuTaoYi
 * @create_time: 2019-12-04 10:50
 * @description:
 **/
@RestController
@RequestMapping(value = "/anodeProductionType")
@Api(tags = "正极成本-基础数据-产品型号")
public class AnodeProductionTypeController {

    @Autowired
    AnodeProductionTypeService typeService;

    @PostMapping(value = "add")
    @ApiOperation(value = "新增")
    public Result add(@RequestBody BasicInfoAnodeProductionType type) {
        return ResultUtil.success(typeService.add(type));
    }

    @DeleteMapping(value = "delete")
    @ApiOperation(value = "删除")
    public Result delete(@ApiParam(name = "id", value = "主键") @RequestParam Integer id) {
        typeService.delete(id);
        return ResultUtil.success();
    }

    @DeleteMapping(value = "ids")
    @ApiOperation(value = "批量删除")
    public Result deleteByIds(@RequestBody Integer[] ids) {
        typeService.deleteByIds(ids);
        return ResultUtil.success();
    }

    @PutMapping(value = "update")
    @ApiOperation(value = "编辑")
    public Result update(@RequestBody BasicInfoAnodeProductionType type) {
        return ResultUtil.success(typeService.update(type));
    }

    @GetMapping(value = "all")
    @ApiOperation(value = "查询所有")
    public Result all() {
        return ResultUtil.success(typeService.getAll(""));
    }

    @GetMapping(value = "page")
    @ApiOperation(value = "分页")
    public Result page(@ApiParam(name = "condition", value = "查询条件") @RequestParam(name = "condition", defaultValue = "", required = false) String condition,
                       @ApiParam(name = "page", value = "页码") @RequestParam(name = "page", defaultValue = "1") Integer page,
                       @ApiParam(name = "size", value = "条目数") @RequestParam(name = "size", defaultValue = "10") Integer size) {
        return ResultUtil.success(typeService.page(condition, page, size));
    }

}
