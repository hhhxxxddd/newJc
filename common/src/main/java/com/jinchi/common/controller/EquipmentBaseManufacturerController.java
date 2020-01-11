package com.jinchi.common.controller;

import com.jinchi.common.domain.EquipmentBaseManufacturer;
import com.jinchi.common.dto.Result;
import com.jinchi.common.service.EquipmentBaseManufacturerService;
import com.jinchi.common.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

/**
 * @author：Fangle
 * @className:EquipmentBaseManufacturerController
 * @description:
 * @date:4:27 PM 2019/1/11
 */
@RestController
@RequestMapping(value = "/equipmentBaseManufacturer")
@Api(tags = "设备管理-基础信息-基本厂商")
public class EquipmentBaseManufacturerController {
    @Autowired
    EquipmentBaseManufacturerService equipmentBaseManufacturerService;

    //新增 更新 删除 批量删 详情 查询所有
    @ApiOperation(value = "新增")
    @PostMapping
    public Result<EquipmentBaseManufacturer> add(@RequestBody @Valid EquipmentBaseManufacturer equipmentBaseManufacturer){
        return ResultUtil.success(equipmentBaseManufacturerService.add(equipmentBaseManufacturer));
    }

    @ApiOperation(value = "更新")
    @PutMapping
    public Result<EquipmentBaseManufacturer> update(@RequestBody @Valid EquipmentBaseManufacturer EquipmentBaseManufacturer){
        return ResultUtil.success(equipmentBaseManufacturerService.update(EquipmentBaseManufacturer));
    }

    @ApiOperation(value = "删除")
    @DeleteMapping(value = "/{id}")
    public Result<EquipmentBaseManufacturer> delete(@ApiParam(name = "id",value = "主键") @PathVariable Integer id){
        equipmentBaseManufacturerService.delete(id);
        return ResultUtil.success();
    }

    @ApiOperation(value = "批量删除")
    @DeleteMapping
    public Result<EquipmentBaseManufacturer> batchDelete(@ApiParam(value = "主键数组") @RequestParam Integer[] ids){
        equipmentBaseManufacturerService.batchDelete(Arrays.asList(ids));
        return ResultUtil.success();
    }

    @ApiOperation(value = "详情",notes = "根据主键")
    @GetMapping(value = "/{id}")
    public Result<EquipmentBaseManufacturer> byId(@ApiParam(value = "主键",name = "id") @PathVariable Integer id){
        return ResultUtil.success(equipmentBaseManufacturerService.byId(id));
    }

    @ApiOperation(value = "查询所有")
    @GetMapping
    public Result<List<EquipmentBaseManufacturer>> all(){
        return ResultUtil.success(equipmentBaseManufacturerService.all());
    }

    @ApiOperation(value = "根据厂商类型查询所有")
    @GetMapping(value = "type/{type}")
    public Result<List<EquipmentBaseManufacturer>> byType(@ApiParam(value = "厂商类型id",name = "type") @PathVariable Integer type){
        return ResultUtil.success(equipmentBaseManufacturerService.byType(type));
    }
}
