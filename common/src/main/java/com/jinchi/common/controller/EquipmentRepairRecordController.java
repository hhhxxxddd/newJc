package com.jinchi.common.controller;

import com.jinchi.common.domain.EquipmentRepairRecord;
import com.jinchi.common.dto.PageBean;
import com.jinchi.common.dto.Result;
import com.jinchi.common.service.EquipmentRepairRecordService;
import com.jinchi.common.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

/**
 * @author：XudongHu
 * @className:EquipmentRepairRecordController
 * @description: 设备维修controller
 * @date:14:53 2019/3/11
 * @Modifer:
 */
@RestController
@RequestMapping(value = "/equipmentRepair")
@Api(tags = "设备管理-设备维修-外部接口")
public class EquipmentRepairRecordController {
    @Autowired
    private EquipmentRepairRecordService equipmentRepairRecordService;

    @PostMapping
    @ApiOperation(value = "app报修")
    public Result<EquipmentRepairRecord> add(@RequestBody @Valid EquipmentRepairRecord e){
        return ResultUtil.success(equipmentRepairRecordService.callForRepair(e));
    }

    @PutMapping(value = "/{id}")
    @ApiOperation(value = "app接单")
    public Result<EquipmentRepairRecord> order(@ApiParam(name = "id",value = "主键") @PathVariable Integer id){
        return ResultUtil.success(equipmentRepairRecordService.orderRepair(id));
    }

    @PutMapping(value = "/finished")
    @ApiOperation(value = "app完工")
    public Result<EquipmentRepairRecord> finished(@ApiParam(name = "id",value = "主键") @RequestParam Integer id,
                                                  @ApiParam(name = "conclusion",value = "完工结论") @RequestParam String conclusion){
        return ResultUtil.success(equipmentRepairRecordService.finishedRepair(id,conclusion));
    }

    @PutMapping(value = "/rate")
    @ApiOperation(value = "app评价")
    public Result<EquipmentRepairRecord> rate(@ApiParam(name = "id",value = "主键") @RequestParam Integer id,
                                              @ApiParam(name = "rate",value = "评价") @RequestParam String rate){
        return ResultUtil.success(equipmentRepairRecordService.rate(id,rate));
    }

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "详情")
    public Result<Map<Object,Object>> byId(@ApiParam(name = "id",value = "主键") @PathVariable Integer id){
        return ResultUtil.success(equipmentRepairRecordService.detailById(id));
    }

    @GetMapping
    @ApiOperation(value = "查询所有",notes = "根据设备名称模糊")
    public Result<PageBean> byEquipmentNameLike(@ApiParam(name = "equipmentName",value = "设备名称") @RequestParam(required = false) String equipmentName, PageBean pageBean){
        return ResultUtil.success(equipmentRepairRecordService.byEquipNameLike(equipmentName,pageBean));
    }

}
