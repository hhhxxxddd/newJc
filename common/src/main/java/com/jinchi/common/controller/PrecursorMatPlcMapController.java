package com.jinchi.common.controller;

import com.jinchi.common.domain.BasicInfoPrecursorMaterialPlcMap;
import com.jinchi.common.dto.Result;
import com.jinchi.common.service.PrecursorMatPlcMapService;
import com.jinchi.common.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/matPlcMap")
@Api(tags = "成本核算-基础数据-物料plc映射")
public class PrecursorMatPlcMapController {
    @Autowired
    PrecursorMatPlcMapService matPlcMapService;

    @PostMapping
    @ApiOperation(value = "新增")
    public Result add(@RequestBody BasicInfoPrecursorMaterialPlcMap basicInfoPrecursorMaterialPlcMap){
        return ResultUtil.success(matPlcMapService.add(basicInfoPrecursorMaterialPlcMap));
    }

    @GetMapping(value = "page")
    @ApiOperation(value = "按照物料名称，plc地址模糊分页查询")
    public Result page(@RequestParam(defaultValue = "",required = false) String condition,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer size){
        return ResultUtil.success(matPlcMapService.getAllByPage(condition,page,size));
    }

    @DeleteMapping
    @ApiOperation(value = "删除")
    public Result delete(@RequestParam Integer id){
        matPlcMapService.delete(id);
        return ResultUtil.success();
    }

    @DeleteMapping(value = "ids")
    @ApiOperation(value = "批量删除")
    public Result deleteByIds(@RequestBody Integer[] ids){
        matPlcMapService.deleteByIds(ids);
        return ResultUtil.success();
    }

    @PutMapping
    @ApiOperation(value = "更新")
    public Result update(@RequestBody BasicInfoPrecursorMaterialPlcMap basicInfoPrecursorMaterialPlcMap){
        return ResultUtil.success(matPlcMapService.update(basicInfoPrecursorMaterialPlcMap));
    }

    @GetMapping(value = "getRecordById")
    @ApiOperation(value = "根据id查询一条记录")
    public Result getOne(@RequestParam Integer id){
        return ResultUtil.success(matPlcMapService.getById(id));
    }
}
