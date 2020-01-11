package com.jinchi.common.controller;

import com.jinchi.common.domain.BasicInfoRawmaterial;
import com.jinchi.common.dto.Result;
import com.jinchi.common.service.PrecursorRawmaterialService;
import com.jinchi.common.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: LiuTaoYi
 * @create_time: 2019-10-29 15:12
 * @description:
 **/
@RestController
@RequestMapping(value = "/precursorRawMaterial")
@Api(tags = "成本核算-基础数据-原材料名称")
public class PrecursorRawMaterialController {

    @Autowired
    PrecursorRawmaterialService rawmaterialService;

    @PostMapping(value = "add")
    @ApiOperation(value = "新增")
    public Result add(@RequestBody BasicInfoRawmaterial basicInfoRawmaterial) {
        return ResultUtil.success(rawmaterialService.add(basicInfoRawmaterial));
    }

    @GetMapping(value = "page")
    @ApiOperation(value = "根据原材料名称模糊查询分页")
    public Result page(@RequestParam(defaultValue = "", required = false) String condition,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer size) {
        return ResultUtil.success(rawmaterialService.page(condition, page, size));
    }

    @GetMapping(value = "getOne")
    @ApiOperation(value = "根据id获取记录")
    public Result getOne(@RequestParam Integer id) {
        return ResultUtil.success(rawmaterialService.getById(id));
    }

    @DeleteMapping(value = "deleteById")
    @ApiOperation(value = "根据id删除")
    public Result deleteById(@RequestParam Integer id) {
        rawmaterialService.deleteById(id);
        return ResultUtil.success();
    }

    @DeleteMapping(value = "deleteByIds")
    @ApiOperation(value = "根据id 批量删除")
    public Result deleteByIds(@RequestBody Integer[] ids) {
        rawmaterialService.deleteByIds(ids);
        return ResultUtil.success();
    }

    @PutMapping(value = "update")
    @ApiOperation(value = "编辑")
    public Result update(@RequestBody BasicInfoRawmaterial basicInfoRawmaterial) {
        return ResultUtil.success(rawmaterialService.update(basicInfoRawmaterial));
    }

    @GetMapping(value = "byType")
    @ApiOperation(value = "根据材料类型查询")
    public Result getByType(@RequestParam Integer type) {
        return ResultUtil.success(rawmaterialService.getByTypeCode(type));
    }

    @GetMapping(value = "all")
    @ApiOperation(value = "查询所有")
    public Result getAll(@RequestParam(defaultValue = "", required = false) String condition) {
        return ResultUtil.success(rawmaterialService.getAll(condition));
    }

    @GetMapping(value = "getByDatatype")
    @ApiOperation(value = "根据数据类型查询")
    public Result getByDatatype(@RequestParam Integer flag){
        return ResultUtil.success(rawmaterialService.getByDatatype(flag));
    }
}
