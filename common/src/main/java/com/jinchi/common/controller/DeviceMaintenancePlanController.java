package com.jinchi.common.controller;

import com.jinchi.common.domain.DeviceDocumentMain;
import com.jinchi.common.dto.DeviceMaintenancePlansHeadDTO;
import com.jinchi.common.dto.Page;
import com.jinchi.common.dto.Result;
import com.jinchi.common.service.DeviceMaintenancePlanService;
import com.jinchi.common.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/maintenancePlan")
@Api(tags = "设备管理-设备维修-保养计划")
public class DeviceMaintenancePlanController {

    @Autowired
    DeviceMaintenancePlanService deviceMaintenancePlanService;

    @GetMapping(value = "/planPage")
    @ApiOperation(value = "保养计划-按部门编号,状态，模糊查询分页")
    public Result<Page> planPage(@ApiParam(name = "deptId", value = "部门主键") @RequestParam Integer deptId,
                                 @ApiParam(name = "statusId", value = "设备状态") @RequestParam(defaultValue = "-1") Integer statusId,
                                 @ApiParam(name = "condition", value = "查询条件") @RequestParam(defaultValue = "") String condition,
                                 @ApiParam(name = "page", value = "页码") @RequestParam(name = "page", defaultValue = "1") Integer page,
                                 @ApiParam(name = "size", value = "条目数") @RequestParam(name = "size", defaultValue = "10") Integer size,
                                 @ApiParam(name = "orderField", value = "排序属性") @RequestParam(name = "orderField", defaultValue = "code") String orderField,
                                 @ApiParam(name = "orderType", value = "排序方法,只能是asc升序或者desc降序") @RequestParam(name = "orderType", defaultValue = "desc") String orderType) {
        return ResultUtil.success(deviceMaintenancePlanService.getByPage(deptId,statusId,condition,page,size));
    }
    @GetMapping(value = "/getDeviceByDeptCode")
    @ApiOperation(value = "通过部门的code获取所有设备")
    public Result<List<DeviceDocumentMain>> getDeviceByDeptCode(@ApiParam(name = "code", value = "部门code" ) @RequestParam int code) {

        return ResultUtil.success(deviceMaintenancePlanService.getMainDeviceByDeptCode(code));

    }


    @PostMapping(value = "/addPlan")
    @ApiOperation(value = "保养计划-新增")
    public Result<DeviceMaintenancePlansHeadDTO> addPlan(@RequestBody @Valid DeviceMaintenancePlansHeadDTO deviceMaintenancePlansHeadDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
        }
        return ResultUtil.success(deviceMaintenancePlanService.add(deviceMaintenancePlansHeadDTO));

    }

    @PutMapping(value = "/updatePlan")
    @ApiOperation(value = "保养计划-编辑")
    public Result<DeviceMaintenancePlansHeadDTO> updatePlan(@RequestBody @Valid DeviceMaintenancePlansHeadDTO deviceMaintenancePlansHeadDTO, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
        }
        return ResultUtil.success(deviceMaintenancePlanService.update(deviceMaintenancePlansHeadDTO));
    }

    @GetMapping(value = "/planDetail/{id}")
    @ApiOperation(value = "保养计划-详情")
    public Result<DeviceMaintenancePlansHeadDTO> planDetail(@ApiParam(name = "id", value = "保养计划主键") @PathVariable Long id){
        return ResultUtil.success(deviceMaintenancePlanService.detail(id));
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation(value = "保养计划-删除")
    public Result deleteById(@ApiParam(name = "id", value = "保养计划主键") @PathVariable Long id ){
        deviceMaintenancePlanService.delete(id);
        return ResultUtil.success();
    }

    @DeleteMapping(value = "/deleteByIds")
    @ApiOperation(value = "保养计划-批量删除")
    public Result deleteByIds(@RequestBody @Valid Long[] ids, BindingResult bindingResultLong){

        return null;
    }

    @PostMapping(value = "/generatorMaint")
    @ApiOperation(value = "生成保养单")
    public Result<DeviceMaintenancePlansHeadDTO> generatorMaint(@RequestBody @Valid DeviceMaintenancePlansHeadDTO deviceMaintenancePlansHeadDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
        }
        return ResultUtil.success(deviceMaintenancePlanService.generatorMaint(deviceMaintenancePlansHeadDTO));
    }
}
