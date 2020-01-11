package com.jinchi.common.controller;

import com.jinchi.common.domain.EquipmentBaseInstrument;
import com.jinchi.common.dto.Result;
import com.jinchi.common.service.EquipmentBaseInstrumentService;
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
 * @author：XudongHu
 * @className:EquipmentBaseInstrumentController
 * @description: 设备基本设备controller
 * @date:14:48 2019/1/11
 */
@RestController
@RequestMapping(value = "/equipmentBaseInstrument")
@Api(tags = "设备管理-基础信息-基本设备")
public class EquipmentBaseInstrumentController {
    @Autowired
    EquipmentBaseInstrumentService equipmentBaseInstrumentService;

    /**
     * 新增
     * @param equipmentBaseInstrument
     * @return
     */
    @ApiOperation(value = "新增")
    @PostMapping
    public Result<EquipmentBaseInstrument> add(@RequestBody @Valid EquipmentBaseInstrument equipmentBaseInstrument){
        return ResultUtil.success(equipmentBaseInstrumentService.add(equipmentBaseInstrument));
    }

    @ApiOperation(value = "更新")
    @PutMapping
    public Result<EquipmentBaseInstrument> update(@RequestBody @Valid EquipmentBaseInstrument equipmentBaseInstrument){
        return ResultUtil.success(equipmentBaseInstrumentService.update(equipmentBaseInstrument));
    }

    @ApiOperation(value = "删除")
    @DeleteMapping(value = "/{id}")
    public Result<EquipmentBaseInstrument> delete(@ApiParam(name = "id",value = "主键") @PathVariable Integer id){
        equipmentBaseInstrumentService.delete(id);
        return ResultUtil.success();
    }

    @ApiOperation(value = "批量删除")
    @DeleteMapping
    public Result<EquipmentBaseInstrument> batchDelete(@ApiParam(value = "主键数组") @RequestParam Integer[] ids){
        equipmentBaseInstrumentService.batchDelete(Arrays.asList(ids));
        return ResultUtil.success();
    }

    @ApiOperation(value = "详情",notes = "根据主键")
    @GetMapping(value = "/{id}")
    public Result<EquipmentBaseInstrument> byId(@ApiParam(value = "主键",name = "id") @PathVariable Integer id){
        return ResultUtil.success(equipmentBaseInstrumentService.byId(id));
    }

    @ApiOperation(value = "查询所有")
    @GetMapping
    public Result<List<EquipmentBaseInstrument>> all(){
        return ResultUtil.success(equipmentBaseInstrumentService.all());
    }
}
