package com.jinchi.common.controller;


import com.jinchi.common.domain.DeviceMainAccessory;
import com.jinchi.common.domain.DeviceUnitAccessory;
import com.jinchi.common.dto.*;
import com.jinchi.common.service.DeviceDocumentAccService;
import com.jinchi.common.service.DeviceDocumentManageService;
import com.jinchi.common.service.DeviceDocumentUnitService;
import com.jinchi.common.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/device")
@Api(tags = "设备管理-设备档案管理")
public class DeviceDocumentManageController {

    @Autowired
    DeviceDocumentManageService deviceDocumentManageService;
    @Autowired
    DeviceDocumentUnitService deviceDocumentUnitService;
    @Autowired
    DeviceDocumentAccService deviceDocumentAccService;

    @GetMapping(value = "/{deptId}")
    @ApiOperation(value = "主设备-按照部门id获取所有设备名称")
    public Result<Map<String,Integer>> getDeviceByDeptId(@ApiParam(name = "deptId", value = "部门主键") @PathVariable Integer deptId){
        return ResultUtil.success(deviceDocumentManageService.getDeviceNameByDeptId(deptId));
    }

    @GetMapping(value = "/all")
    @ApiOperation(value = "主设备-按照部门id,设备名称获取设备")
    public Result<List<DeviceDocumentMainDTO>> getDeviceByDeptIdByDeviceName(@ApiParam(name = "deptId", value = "部门主键") @RequestParam Integer deptId,
                                                                  @ApiParam(name = "deviceName", value = "设备名称") @RequestParam String deviceName){
        return ResultUtil.success(deviceDocumentManageService.getByDeptIdByDeviceName(deptId,deviceName,""));
    }

     //firefly
    @PostMapping("/addUnit")
    @ApiOperation(value = "部件-新增部件档案")
    public Result<DeviceDocumentAddUnitDTO> addUnit(@RequestBody @Valid DeviceDocumentAddUnitDTO deviceDocumentAddUnitDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
        }
        return ResultUtil.success(deviceDocumentUnitService.addOne(deviceDocumentAddUnitDTO));
    }

    @PutMapping("/updateUnit")
    @ApiOperation(value = "通过ID更新部件档案")
    public Result<DeviceDocumentAddUnitDTO> Update(@RequestBody @Valid DeviceDocumentAddUnitDTO deviceDocumentAddUnitDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
        }

        return ResultUtil.success(deviceDocumentUnitService.updateUnit(deviceDocumentAddUnitDTO));
    }

    @PutMapping("/updateUnitAccessoryByid")
    @ApiOperation(value = "通过ID更新部件的配件档案")
    public Result<DeviceUnitAccessory> updateUnitAccessoryById(@RequestBody @Valid DeviceUnitAccessory deviceUnitAccessory, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
        }
        return ResultUtil.success(deviceDocumentAccService.updateUnitAccById(deviceUnitAccessory));
    }
    /*@PutMapping("/updateMainAccessoryByid")
    @ApiOperation(value = "通过ID更新设备的配件档案")
    public Result<DeviceMainAccessory> updateUnitAccessoryById(@RequestBody @Valid DeviceMainAccessory deviceMainAccessory, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
        }
        return ResultUtil.success(deviceDocumentAccService.updateMainAccById(deviceMainAccessory));
    }*/






    @DeleteMapping (value = "/deleteUnit")
    @ApiOperation(value = "通过部件code删除设备部件")
    public Result<String> deleteUnitById(@ApiParam(name = "code", value = "设备部件主键") @RequestParam Long code){
        return ResultUtil.success(deviceDocumentUnitService.deleteById(code));
    }

    @DeleteMapping("/deleteUnits")
    @ApiOperation(value = "部件-批量删除")
    public Result deleteUnitsByIds(@RequestBody @Valid Long[] ids, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
        }
        deviceDocumentUnitService.deleteByIds(ids);
        return ResultUtil.success();
    }
    @GetMapping(value = "/unitDetail")
    @ApiOperation("部件-获取详情")
    public Result<DeviceDocumentUnitDTO> getDetailsByCode(@ApiParam( name = "id",value = "部件主键") @RequestParam Long id) {
        return  ResultUtil.success(deviceDocumentUnitService.getDetailById(id));
    }




    @PostMapping
    @ApiOperation(value = "主设备-新增主设备档案")
    public Result<DeviceDocumentMainDTO> add(@RequestBody @Valid DeviceDocumentMainDTO deviceDocumentMainDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
        }
        return ResultUtil.success(deviceDocumentManageService.addOne(deviceDocumentMainDTO));
    }





    @DeleteMapping(value = "/{id}")
    @ApiOperation(value = "主设备-删除设备（包括全部配件,部件）")
    public Result<String> deleteById(@ApiParam(name = "id", value = "设备主键") @PathVariable Long id){
        return ResultUtil.success(deviceDocumentManageService.deleteById(id));
    }

    @GetMapping(value = "/page")
    @ApiOperation(value = "主设备-根据部门id，设备名分页查询")
    public Result<Page> getAllByDeptIdByDeviceName(@ApiParam(name = "deviceName", value = "设备名称") @RequestParam String deviceName,
                                                   @ApiParam(name = "deptId", value = "部门主键") @RequestParam Integer deptId,
                                                   @ApiParam(name = "condition", value = "查询条件") @RequestParam(defaultValue = "") String condition,
                                                   @ApiParam(name = "page", value = "页码") @RequestParam(name = "page", defaultValue = "1") Integer page,
                                                   @ApiParam(name = "size", value = "条目数") @RequestParam(name = "size", defaultValue = "10") Integer size,
                                                   @ApiParam(name = "orderField", value = "排序属性") @RequestParam(name = "orderField", defaultValue = "code") String orderField,
                                                   @ApiParam(name = "orderType", value = "排序方法,只能是asc升序或者desc降序") @RequestParam(name = "orderType", defaultValue = "desc") String orderType){
        return ResultUtil.success(deviceDocumentManageService.getAllByDeptIdByDeviceNameByPage(deptId,deviceName,condition ,page,size,orderField,orderType));
    }

    @PutMapping
    @ApiOperation(value = "主设备-更新")
    public Result<DeviceDocumentMainDTO> updateMainDevice(@RequestBody @Valid DeviceDocumentMainDTO deviceDocumentMainDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
        }
        return ResultUtil.success(deviceDocumentManageService.update(deviceDocumentMainDTO));
    }

    @PutMapping("/updateMainAccessory")
    @ApiOperation(value = "主设备配件-更新")
    public Result<DeviceMainAccessory> updateMainAccessory(@RequestBody @Valid DeviceMainAccessory deviceMainAccessory, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
        }
        return ResultUtil.success(deviceDocumentManageService.updateMainAccessory(deviceMainAccessory));
    }
    @PutMapping("/updateUnitAccessory")
    @ApiOperation(value = "主设备部件的配件-更新")
    public Result<DeviceUnitAccessory> updateUnitAccessory(@RequestBody @Valid DeviceUnitAccessory deviceUnitAccessory, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
        }
        return ResultUtil.success(deviceDocumentUnitService.updateUnitAccessory(deviceUnitAccessory));
    }




    @GetMapping(value = "/units/{deptId}/{deviceId}")
    @ApiOperation(value = "部件-获取所有部件-分页")
    public Result<Page> getUnitByDeptIdByDeviceId(@ApiParam(name = "deptId", value = "部门主键") @PathVariable Integer deptId,
                                                      @ApiParam(name = "deviceId", value = "主设备主键") @PathVariable Long deviceId,
                                                      @ApiParam(name = "page", value = "页码") @RequestParam(name = "page", defaultValue = "1") Integer page,
                                                      @ApiParam(name = "condition", value = "查询条件") @RequestParam(defaultValue = "") String condition,
                                                      @ApiParam(name = "size", value = "条目数") @RequestParam(name = "size", defaultValue = "10") Integer size,
                                                      @ApiParam(name = "orderField", value = "排序属性") @RequestParam(name = "orderField", defaultValue = "code") String orderField,
                                                      @ApiParam(name = "orderType", value = "排序方法,只能是asc升序或者desc降序") @RequestParam(name = "orderType", defaultValue = "desc") String orderType){
        return ResultUtil.success(deviceDocumentUnitService.getUnitByDeptIdByDeviceIdByPage(deptId,deviceId,condition,page,size,orderField,orderType));
    }

    @GetMapping(value = "/accsMain/{deviceId}")
    @ApiOperation(value = "主设备-获取所有主设备配件-分页")
    public Result<Page> getMainAccsByDeviceId(@ApiParam(name = "deviceId", value = "主设备主键") @PathVariable Long deviceId,@ApiParam(name = "page", value = "页码") @RequestParam(name = "page", defaultValue = "1") Integer page,
                                                      @ApiParam(name = "size", value = "条目数") @RequestParam(name = "size", defaultValue = "10") Integer size,
                                                      @ApiParam(name = "orderField", value = "排序属性") @RequestParam(name = "orderField", defaultValue = "code") String orderField,
                                                      @ApiParam(name = "orderType", value = "排序方法,只能是asc升序或者desc降序") @RequestParam(name = "orderType", defaultValue = "desc") String orderType){
        return ResultUtil.success(deviceDocumentAccService.getMainAccByDeviceIdByPage(deviceId,page,size,orderField,orderType));
    }

    @GetMapping(value = "/accsUnit/{unitId}")
    @ApiOperation(value = "部件-获取所有部件配件-分页")
    public Result<Page> getUnitAccsByUnitId(@ApiParam(name = "unitId", value = "部件主键") @PathVariable Long unitId,@ApiParam(name = "page", value = "页码") @RequestParam(name = "page", defaultValue = "1") Integer page,
                                                      @ApiParam(name = "size", value = "条目数") @RequestParam(name = "size", defaultValue = "10") Integer size,
                                                      @ApiParam(name = "orderField", value = "排序属性") @RequestParam(name = "orderField", defaultValue = "code") String orderField,
                                                      @ApiParam(name = "orderType", value = "排序方法,只能是asc升序或者desc降序") @RequestParam(name = "orderType", defaultValue = "desc") String orderType){
        return ResultUtil.success(deviceDocumentAccService.getUnitAccByUnitIdByPage(unitId,page,size,orderField,orderType));
    }

    @GetMapping(value = "/detail/{id}")
    @ApiOperation(value = "主设备-详情")
    public Result<DeviceDocumentMainDTO> getDetailById(@ApiParam(name = "id", value = "设备主键") @PathVariable Long id){
        return ResultUtil.success(deviceDocumentManageService.getDetailById(id));
    }

    @DeleteMapping("/delete")
    @ApiOperation(value = "主设备-批量删除")
    public Result deleteByIds(@RequestBody @Valid Long[] ids, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
        }
        deviceDocumentManageService.deleteByIds(ids);
        return ResultUtil.success();
    }

    @PostMapping(value = "/upload")
    @ApiOperation(value = "主设备-上传")
    public Result<DeviceMainManualDTO> upLoadManual(@ApiParam(value = "上传文件") MultipartFile file) throws IOException {
        return ResultUtil.success(deviceDocumentManageService.upLoad(file));
    }

    @PostMapping(value = "/addMainAcc")
    @ApiOperation(value = "配件-新增主设备配件")
    public Result<DeviceMainAccessory> addMainAcc(@RequestBody @Valid DeviceMainAccessory deviceMainAccessory, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
        }
        return ResultUtil.success(deviceDocumentAccService.add(deviceMainAccessory));
    }


    @DeleteMapping( value = "/delMainAcc/{id}")
    @ApiOperation(value = "删除主设备配件")
    public Result<DeviceMainAccessory> delMainAcc(@ApiParam(name = "id", value = "主设备配件主键") @PathVariable Long id ){
        deviceDocumentAccService.deleteMainAccById(id);
        return ResultUtil.success();
    }


    @PostMapping(value = "/addUnitAcc")
    @ApiOperation(value = "配件-新增部件配件")
    public Result<DeviceUnitAccessory> addUnitAcc(@RequestBody @Valid DeviceUnitAccessory deviceUnitAccessory, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
        }
        return ResultUtil.success(deviceDocumentAccService.add(deviceUnitAccessory));
    }

    @DeleteMapping(value = "/delUnitAcc")
    @ApiOperation(value = "删除部件配件")
    public Result<DeviceUnitAccessory> delUnitAcc(@ApiParam(name = "id", value = "部件配件主键") @RequestParam Long id){
        deviceDocumentAccService.deleteUnitAccById(id);
        return ResultUtil.success();
    }

    @GetMapping(value = "/duplicateDevice")
    @ApiOperation(value = "主设备的复制功能")
    public Result duplicateDevice(@ApiParam(name = "id", value = "主设备主键") @RequestParam Long id){
        deviceDocumentManageService.duplicateDevice(id);
        return ResultUtil.success();
    }

    @GetMapping(value = "/getDeviceByUnitName")
    @ApiOperation(value = "根据部件名称搜索主设备")
    public Result<Page> getDeviceByUnitName(@ApiParam(name = "unitName", value = "部件名称") @RequestParam String unitName,
                                      @ApiParam(name = "page", value = "页码") @RequestParam(name = "page", defaultValue = "1") Integer page,
                                      @ApiParam(name = "size", value = "条目数") @RequestParam(name = "size", defaultValue = "10") Integer size){
        return ResultUtil.success(deviceDocumentManageService.getByPageByUnitName(unitName,page,size));
    }

    @GetMapping(value = "/getDeviceByAccName")
    @ApiOperation(value = "根据配件名称搜索主设备")
    public Result<Page> getDeviceByAccName(@ApiParam(name = "accName", value = "配件名称") @RequestParam String accName,
                                     @ApiParam(name = "page", value = "页码") @RequestParam(name = "page", defaultValue = "1") Integer page,
                                     @ApiParam(name = "size", value = "条目数") @RequestParam(name = "size", defaultValue = "10") Integer size){
        return ResultUtil.success(deviceDocumentManageService.getByPageByAccName(accName,page,size));
    }

    @GetMapping(value = "duplicateDeviceAcc")
    @ApiOperation(value = "复制主设备的配件")
    public Result duplicateDeviceAcc(@ApiParam(name = "originDeviceId", value = "原设备id") @RequestParam Long originDeviceId,
                                     @ApiParam(name = "newDeviceId", value = "新设备id") @RequestParam Long newDeviceId){
        return ResultUtil.success(deviceDocumentManageService.duplicateDeviceAcc(originDeviceId,newDeviceId));
    }

    @GetMapping(value = "duplicateDeviceUnit")
    @ApiOperation(value = "复制主设备的部件")
    public Result duplicateDeviceUnit(@ApiParam(name = "originDeviceId", value = "设备id") @RequestParam Long originDeviceId,
                                     @ApiParam(name = "newUnitId", value = "部件id") @RequestParam Long newUnitId,
                                      @ApiParam(name = "flag", value = "标记") @RequestParam Integer flag){
        return ResultUtil.success(deviceDocumentManageService.duplicateDeviceUnit(originDeviceId,newUnitId,flag));
    }

    @GetMapping(value = "duplicateUnitAcc")
    @ApiOperation(value = "复制部件的配件")
    public Result duplicateUnitAcc(@ApiParam(name = "originUnitId", value = "原部件id") @RequestParam Long originUnitId,
                                      @ApiParam(name = "newUnitId", value = "新部件id") @RequestParam Long newUnitId){
        return ResultUtil.success(deviceDocumentManageService.duplicateUnitAcc(originUnitId,newUnitId));
    }

    @GetMapping(value = "getAllUnitByDeptCodeByDeviceName")
    @ApiOperation(value = "根据部门id，设备类名，获取所有这种设备的部件")
    public Result getAllUnitByDeptCodeByDeviceName(@ApiParam(name = "deviceName", value = "设备类名") @RequestParam String deviceName){
        return ResultUtil.success(deviceDocumentManageService.getAllUnitByDeptCodeByDeviceName(deviceName));
    }

    @GetMapping(value = "getAllMainByDeptCodeByDeviceName")
    @ApiOperation(value = "根据部门id，设备类名，获取所有这种设备的主设备")
    public Result getAllMainByDeptCodeByDeviceName(@ApiParam(name = "deviceName", value = "设备类名") @RequestParam String deviceName){
        return ResultUtil.success(deviceDocumentManageService.getAllMainByDeptCodeByDeviceName(deviceName));
    }

    @GetMapping(value = "duplicateMutipleDevice")
    @ApiOperation(value = "复制多个主设备")
    public Result duplicateMutipleDevice(@ApiParam(name = "deviceId", value = "设备id") @RequestParam Long deviceId,
                                         @ApiParam(name = "cnt", value = "复制数量") @RequestParam Integer cnt){
        return ResultUtil.success(deviceDocumentManageService.duplicateMutipleDevice(deviceId,cnt));
    }
}
