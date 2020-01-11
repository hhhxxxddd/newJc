package com.jinchi.common.controller;

import com.jinchi.common.dto.DeviceSpotcheckModelsDTO;
import com.jinchi.common.dto.DeviceSpotcheckModelsHeadDTO;
import com.jinchi.common.dto.Page;
import com.jinchi.common.dto.Result;
import com.jinchi.common.service.DeviceSpotcheckModelsService;
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

@RestController
@RequestMapping(value = "/deviceSpotCheck")
@Api(tags = "设备管理-设备点检-点检模板")
public class DeviceSpotcheckModelsController {
    @Autowired
    DeviceSpotcheckModelsService deviceSpotcheckModelsService;

    @PostMapping(value = "/addCheckModel")
    @ApiOperation(value = "点检模板-新增")
    public Result<DeviceSpotcheckModelsHeadDTO> addCheckModels(@RequestBody @Valid DeviceSpotcheckModelsHeadDTO deviceSpotcheckModelsHeadDTO, BindingResult bindingResult) {
        return ResultUtil.success(deviceSpotcheckModelsService.add(deviceSpotcheckModelsHeadDTO));
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation(value = "点检模板-删除")
    public Result deleteById(@ApiParam(name = "id", value = "点检模板主键") @PathVariable Long id ){
        deviceSpotcheckModelsService.deleteById(id);
        return ResultUtil.success();
    }


    @DeleteMapping(value = "/deleteByIds")
    @ApiOperation(value = "点检模板-批量删除")
    public Result deleteByIds(@RequestBody @Valid Long[] ids, BindingResult bindingResultLong){
        deviceSpotcheckModelsService.deleteByIds(ids);
        return ResultUtil.success();
    }

    @PutMapping(value = "/updateCheckModel")
    @ApiOperation(value = "点检模板-编辑,通过code更新数据，先删除所有的detail，请传所有的detail")
    public Result<DeviceSpotcheckModelsHeadDTO> updateById(@RequestBody @Valid DeviceSpotcheckModelsHeadDTO deviceSpotcheckModelsHeadDTO, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
        }

        return ResultUtil.success(deviceSpotcheckModelsService.updataById(deviceSpotcheckModelsHeadDTO));
    }

    @GetMapping(value = "/checkModelDetail")
    @ApiOperation(value = "点检模板-详情")
    public Result<DeviceSpotcheckModelsHeadDTO> detail(@ApiParam(name = "id", value = "点检模板主键") @RequestParam Long id ){
        return ResultUtil.success( deviceSpotcheckModelsService.detail(id));
    }


    @GetMapping(value = "/getAllDevices")
    @ApiOperation(value = "设备-获取所有一级部门设备")
    public Result<List<DeviceSpotcheckModelsDTO>> getAllDevices() {
        return ResultUtil.success(deviceSpotcheckModelsService.getAllDevices());
    }

    @PostMapping(value = "/upload")
    @ApiOperation(value = "点检查询-上传")
    public Result<String> upLoadPic(@ApiParam(value = "上传文件") MultipartFile file) throws IOException {
        return ResultUtil.success(deviceSpotcheckModelsService.upload(file));
    }

    @GetMapping(value = "/getPage")
    @ApiOperation(value = "设备点检--分页")
    public Result<Page> getPage(@ApiParam(name = "deviceName", value = "设备名称") @RequestParam String deviceName,
                                                             @ApiParam(name = "deptId",value = "部门主键") @RequestParam Integer deptId,
                                                             @ApiParam(name = "page", value = "页码") @RequestParam(name = "page", defaultValue = "1") Integer page,
                                                             @ApiParam(name = "size", value = "条目数") @RequestParam(name = "size", defaultValue = "10") Integer size){

        return ResultUtil.success(deviceSpotcheckModelsService.getByPage(deviceName,deptId,page,size));

    }

    @GetMapping(value = "/{deptId}")
    @ApiOperation(value = "根据部门code查询部门下所有模板")
    public Result<List<DeviceSpotcheckModelsHeadDTO>>getModelsByDeptId(@ApiParam(name = "deptId", value = "部门主键") @PathVariable Integer deptId){
        return ResultUtil.success(deviceSpotcheckModelsService.getModelsByDeptId(deptId));
    }

    @GetMapping("/getAllByDeviceName")
    @ApiOperation(value = "根据设备名模糊查询-左侧按钮")
    public  Result<List<DeviceSpotcheckModelsDTO>> getByDeviceName(@ApiParam(name = "deviceName",value = "设备名称")@RequestParam(required=false)  String deviceName){
        return ResultUtil.success(deviceSpotcheckModelsService.getByDeviceName(deviceName));
    }

    @DeleteMapping(value = "/deleteDetailId")
    @ApiOperation(value = "点检模板-删除单条detail")
    public Result deleteDetailById(Long headCode, Long id){
        deviceSpotcheckModelsService.deleteDetailById(headCode,id);
        return ResultUtil.success();
    }


    @GetMapping(value = "cancelLoad")
    @ApiOperation(value = "取消上传")
    public Result cancelLoad(@RequestParam String path){
        deviceSpotcheckModelsService.cancelLoad(path);
        return ResultUtil.success();
    }

    @GetMapping(value = "/getModelByDeviceName")
    @ApiOperation(value = "根据部门id和设备类名查询模板")
    public Result getModelByDeviceName(@RequestParam Integer deptCode,@RequestParam String deviceName){
        return ResultUtil.success(deviceSpotcheckModelsService.getModelByDeviceName(deptCode,deviceName));
    }

}
