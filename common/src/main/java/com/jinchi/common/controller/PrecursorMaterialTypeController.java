package com.jinchi.common.controller;

import com.jinchi.common.domain.BasicInfoMaterialType;
import com.jinchi.common.dto.Result;
import com.jinchi.common.service.PrecursorMaterialTypeService;
import com.jinchi.common.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: LiuTaoYi
 * @create_time: 2019-10-28 17:04
 * @description:
 **/
@RestController
@RequestMapping(value = "/precursorMaterialType")
@Api(tags = "成本核算-基础数据-材料类别")
public class PrecursorMaterialTypeController {

    @Autowired
    PrecursorMaterialTypeService materialTypeService;

    @PostMapping(value = "add")
    @ApiOperation(value = "新增")
    public Result add(@RequestBody BasicInfoMaterialType basicInfoMaterialType) {
        return ResultUtil.success(materialTypeService.add(basicInfoMaterialType));
    }

    @DeleteMapping(value = "delete")
    @ApiOperation(value = "删除")
    public Result delete(@RequestParam Integer id) {
        materialTypeService.deleteById(id);
        return ResultUtil.success();
    }

    @DeleteMapping(value = "ids")
    @ApiOperation(value = "批量删除")
    public Result deleteByIds(@RequestBody Integer[] ids) {
        materialTypeService.deleteByIds(ids);
        return ResultUtil.success();
    }

    @GetMapping(value = "all")
    @ApiOperation(value = "查询所有")
    public Result getAll() {
        return ResultUtil.success(materialTypeService.getAll());
    }

    @GetMapping(value = "getRecordById")
    @ApiOperation(value = "根据id查询一条记录")
    public Result getOne(@RequestParam Integer id) {
        return ResultUtil.success(materialTypeService.getByCode(id));
    }

    @GetMapping(value = "getRecordsByTypes")
    @ApiOperation(value = "根据物料来源类别查询材料类别列表")
    public Result getRecords(@RequestParam Byte dataType) {
        return ResultUtil.success(materialTypeService.getRecordsByTypes(dataType));
    }

    @PutMapping(value = "update")
    @ApiOperation(value = "编辑")
    public Result update(@RequestBody BasicInfoMaterialType basicInfoMaterialType) {
        return ResultUtil.success(materialTypeService.update(basicInfoMaterialType));
    }

    @GetMapping(value = "page")
    @ApiOperation(value = "分页查询")
    public Result page(@ApiParam(name = "page", value = "页码") @RequestParam(name = "page", defaultValue = "1") Integer page,
                       @ApiParam(name = "size", value = "条数") @RequestParam(name = "size", defaultValue = "10") Integer size) {
        return ResultUtil.success(materialTypeService.page(page, size));
    }
}
