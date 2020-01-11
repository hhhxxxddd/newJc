package com.jinchi.common.controller;

import com.jinchi.common.dto.LineWeightDTO;
import com.jinchi.common.dto.Result;
import com.jinchi.common.service.AnodeMaterialTypesService;
import com.jinchi.common.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: LiuTaoYi
 * @create_time: 2019-11-25 17:02
 * @description:
 **/
@RestController
@RequestMapping(value = "/anodeMaterialTypes")
@Api(tags = "正极成本-基础数据-物料种类")
public class AnodeMaterialTypesController {

    @Autowired
    AnodeMaterialTypesService typesService;

    @PostMapping(value = "add")
    @ApiOperation(value = "新增")
    public Result add(@RequestBody LineWeightDTO dto) {
        return ResultUtil.success(typesService.add(dto));
    }

    @DeleteMapping(value = "delete")
    @ApiOperation(value = "删除")
    public Result delete(@ApiParam(name = "id", value = "物料id") @RequestParam Integer id) {
        typesService.deleteById(id);
        return ResultUtil.success();
    }

    @DeleteMapping(value = "ids")
    @ApiOperation(value = "批量删除")
    public Result deleteByIds(@RequestBody Integer[] ids) {
        typesService.deleteByIds(ids);
        return ResultUtil.success();
    }

    @PutMapping(value = "update")
    @ApiOperation(value = "编辑")
    public Result update(@RequestBody LineWeightDTO dto) {
        return ResultUtil.success(typesService.update(dto));
    }

    @GetMapping(value = "all")
    @ApiOperation(value = "查询所有")
    public Result getAll(@ApiParam(name = "condition", value = "查询条件") @RequestParam(name = "condition", defaultValue = "", required = false) String condition) {
        return ResultUtil.success(typesService.getAll(condition));
    }

    @GetMapping(value = "page")
    @ApiOperation(value = "分页")
    public Result getPage(@ApiParam(name = "condition", value = "查询条件") @RequestParam(name = "condition", defaultValue = "", required = false) String condition,
                          @ApiParam(name = "page", value = "页码") @RequestParam(name = "page", defaultValue = "1") Integer page,
                          @ApiParam(name = "size", value = "条目数") @RequestParam(name = "size", defaultValue = "10") Integer size) {
        return ResultUtil.success(typesService.page(condition, page, size));
    }
}
