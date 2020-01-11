package com.jinchi.common.controller;

import com.jinchi.common.domain.BasicInfoPrecursorProcessType;
import com.jinchi.common.dto.Result;
import com.jinchi.common.service.PrecursorProcessTypeService;
import com.jinchi.common.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: LiuTaoYi
 * @create_time: 2019-09-26 17:15
 * @description:
 **/
@RestController
@RequestMapping(value = "/precursorProcessType")
@Api(tags = "成本核算-基础数据-工序名称")
public class PrecursorProcessTypeController {

    @Autowired
    PrecursorProcessTypeService precursorProcessTypeService;

    @PostMapping(value = "add")
    @ApiOperation(value = "新增")
    public Result<BasicInfoPrecursorProcessType> addPeriod(@RequestBody BasicInfoPrecursorProcessType basicInfoPrecursorProcessType) {
        return ResultUtil.success(precursorProcessTypeService.add(basicInfoPrecursorProcessType));
    }

    @DeleteMapping(value = "delete")
    @ApiOperation(value = "删除")
    public Result delete(@ApiParam(name = "id", value = "主键") @RequestParam Integer id) {
        precursorProcessTypeService.delete(id);
        return ResultUtil.success();
    }

    @PutMapping(value = "update")
    @ApiOperation(value = "编辑")
    public Result<BasicInfoPrecursorProcessType> update(@RequestBody BasicInfoPrecursorProcessType basicInfoPrecursorProcessType) {
        return ResultUtil.success(precursorProcessTypeService.update(basicInfoPrecursorProcessType));
    }

    @GetMapping(value = "page")
    @ApiOperation(value = "分页查询")
    public Result page(@ApiParam(name = "page", value = "页码") @RequestParam(name = "page", defaultValue = "1") Integer page,
                       @ApiParam(name = "size", value = "条目数") @RequestParam(name = "size", defaultValue = "10") Integer size) {
        return ResultUtil.success(precursorProcessTypeService.page(page, size));
    }

    @GetMapping(value = "all")
    @ApiOperation(value = "查询所有")
    public Result getAll() {
        return ResultUtil.success(precursorProcessTypeService.getAll());
    }

    @GetMapping(value = "getByType")
    @ApiOperation(value = "根据主材辅材选择工序")
    public Result getByType(@RequestParam Integer flag) {
        return ResultUtil.success(precursorProcessTypeService.getByType(flag));
    }
}
