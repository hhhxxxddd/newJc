package com.jinchi.common.controller;

import com.jinchi.common.domain.BasicInfoDeviceStatus;
import com.jinchi.common.dto.Result;
import com.jinchi.common.service.DeviceStatusService;
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
@RequestMapping(value = "/deviceStatus")
@Api(tags = "设备管理-设备状态")
public class DeviceStatusController {
    @Autowired
    DeviceStatusService deviceStatusService;

    @PostMapping
    @ApiOperation(value = "新增")
    public Result<BasicInfoDeviceStatus> add(@RequestBody @Valid BasicInfoDeviceStatus basicInfoDeviceStatus, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
        }
        return ResultUtil.success(deviceStatusService.addOne(basicInfoDeviceStatus));
    }

    @PutMapping
    @ApiOperation(value = "更新")
    public Result<BasicInfoDeviceStatus> update(@RequestBody @Valid BasicInfoDeviceStatus basicInfoDeviceStatus, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
        }
        return ResultUtil.success(deviceStatusService.update(basicInfoDeviceStatus));
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation(value = "删除")
    public Result<BasicInfoDeviceStatus> deleteByIddeleteById(@ApiParam(name = "id", value = "状态主键") @PathVariable Integer id) {
        deviceStatusService.deleteById(id);
        return ResultUtil.success();
    }

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "查询")
    public Result<BasicInfoDeviceStatus> getDeptById(@ApiParam(name = "id", value = "状态主键") @PathVariable Integer id){
        return ResultUtil.success(deviceStatusService.getStatusById(id));
    }

    @GetMapping
    @ApiOperation(value = "查询所有")
    public Result<List<BasicInfoDeviceStatus>> getAllStatus() {
        return ResultUtil.success(deviceStatusService.getAll());
    }

    @GetMapping(value = "/getByNameLike")
    @ApiOperation(value = "根据状态名称模糊查询")
    public Result<List<BasicInfoDeviceStatus>> getByNameLike(@ApiParam(name = "name", value = "状态名称")@RequestParam(name = "name") String name){
        return ResultUtil.success(deviceStatusService.getBybNameLike(name));
    }

    @DeleteMapping(value = "/delete")
    @ApiOperation(value = "批量删除")
    public Result deleteByIds(@RequestBody @Valid Integer[] ids, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
        }
        deviceStatusService.deleteByIds(ids);
        return ResultUtil.success();
    }
}
