package com.jinchi.common.controller;

import com.jinchi.common.domain.DeviceMaintenanceItems;
import com.jinchi.common.dto.Page;
import com.jinchi.common.dto.Result;
import com.jinchi.common.service.DeviceMaintenanceItemsService;
import com.jinchi.common.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "/maintenance")
@Api(tags = "设备管理-设备维修-项目录入")
public class DeviceMaintenanceController {

    @Autowired
    DeviceMaintenanceItemsService deviceMaintenanceItemsService;

    @PostMapping("/addOne")
    @ApiOperation(value = "新增保养项目")
    public Result<DeviceMaintenanceItems> addItems(@RequestBody @Valid DeviceMaintenanceItems deviceMaintenanceItems, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
        }
        return ResultUtil.success(deviceMaintenanceItemsService.addOne(deviceMaintenanceItems));
    }

    @GetMapping("/queryAll")
    @ApiOperation(value = "查询保养项目设备的名称")
    public Result<Set<String>> queryAllDeviceName(){
              return ResultUtil.success(deviceMaintenanceItemsService.getAllMaintenanceItems());
    }

    @DeleteMapping (value = "/{id}")
    @ApiOperation(value = "删除设备保养")
    public Result<DeviceMaintenanceItems> delMaintenanceDev(@ApiParam(name = "id", value = "设备主键") @PathVariable Long id){
        deviceMaintenanceItemsService.delMaintenanceDevById(id);
        return ResultUtil.success();
    }

    @DeleteMapping (value = "/deleteIds")
    @ApiOperation(value = "批量删除设备保养")
    public Result delMaintenanceDevs(@RequestBody @Valid Long[] ids, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
        }
        deviceMaintenanceItemsService.delMaintenanceDevByIds(ids);
        return ResultUtil.success();
    }

    @PutMapping
    @ApiOperation(value = "更新设备保养")
    public Result<DeviceMaintenanceItems> update(@RequestBody @Valid DeviceMaintenanceItems deviceMaintenanceItems, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
        }
        return ResultUtil.success(deviceMaintenanceItemsService.update(deviceMaintenanceItems));
    }

    @GetMapping(value = "/page")
    @ApiOperation(value = "根据设备名分页查询-模糊查询")
    public Result<Page> page(@ApiParam(name = "deviceName", value = "设备名称") @RequestParam String deviceName,
                             @ApiParam(name = "condition", value = "查询条件") @RequestParam(defaultValue = "") String condition,
                             @ApiParam(name = "page", value = "页码") @RequestParam(name = "page", defaultValue = "1") Integer page,
                             @ApiParam(name = "size", value = "条目数") @RequestParam(name = "size", defaultValue = "10") Integer size,
                             @ApiParam(name = "orderField", value = "排序属性") @RequestParam(name = "orderField", defaultValue = "code") String orderField,
                             @ApiParam(name = "orderType", value = "排序方法,只能是asc升序或者desc降序") @RequestParam(name = "orderType", defaultValue = "desc") String orderType){
        return ResultUtil.success(deviceMaintenanceItemsService.getByDeviceNameByPage(deviceName,condition,page,size,orderField,orderType));
    }

    @GetMapping(value = "/getAll")
    @ApiOperation(value = "根据设备名查询-右侧模糊查询")
    public Result<List<DeviceMaintenanceItems>> page(@ApiParam(name = "deviceName", value = "设备名称") @RequestParam String deviceName,
                             @ApiParam(name = "condition", value = "查询条件") @RequestParam(defaultValue = "") String condition){
        return ResultUtil.success(deviceMaintenanceItemsService.getByDeviceName(deviceName,condition));
    }

    @GetMapping("/getAllByDeviceName")
    @ApiOperation(value = "根据设备名模糊查询-左侧按钮")
    public Result<Set<String>> getAllByDeviceName(@ApiParam(name = "deviceName",value = "设备名称")@RequestParam  String deviceName){
        return ResultUtil.success(deviceMaintenanceItemsService.getAllByDeviceName(deviceName));
    }



    @GetMapping(value = "/maintenanceDetailById")
    @ApiOperation("根据ID获取设备保养详情")
    public Result<List<DeviceMaintenanceItems>> getDetailsById(@ApiParam( name = "id",value = "设备主键") @RequestParam Long id) {
        return  ResultUtil.success(deviceMaintenanceItemsService.getDetailById(id));
    }

}
