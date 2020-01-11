package com.jinchi.common.controller;


import com.jinchi.common.dto.DeviceSpotcheckMainDTO;
import com.jinchi.common.dto.Page;
import com.jinchi.common.dto.Result;
import com.jinchi.common.service.DeviceSpotcheckPlansService;
import com.jinchi.common.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/SpotcheckPlan")
@Api(tags = "设备管理-设备点检-点检计划")
public class DeviceSpotcheckPlansController {

    @Autowired
    DeviceSpotcheckPlansService deviceSpotcheckPlansService;


    @PostMapping
    @ApiOperation(value = "增加一个点检计划 ")
    public Result addOne(@ApiParam(name = "deviceCode", value = "主设备的编号") @RequestParam long deviceCode,
                          @ApiParam(name = "deviceName", value = "设备名称") @RequestParam String deviceName,
                         @ApiParam(name = "modelId",value = "模板名称") @RequestParam long modelId) {
        return ResultUtil.success(deviceSpotcheckPlansService.addOne(deviceCode,deviceName,modelId));
    }


    @GetMapping(value = "/getAddMsg")
    @ApiOperation(value = "检查是否能新增, 数据只需要传 deptcode 和 设备名称 ")
    public Result<List<DeviceSpotcheckMainDTO>> checkAddStatus(@ApiParam(name = "deptCode", value = "部门的编号") @RequestParam int deptCode,
                                                               @ApiParam(name = "deviceName", value = "设备名称") @RequestParam String deviceName) {

        return ResultUtil.success(deviceSpotcheckPlansService.getAddMsg(deptCode, deviceName));

//        List<Boolean> flag = deviceSpotcheckPlansService.checkAddStatus(deptCode, deviceName);
//        Integer temp;
//        if (flag.size() == 3 && flag.get(2) == true) {
//            temp = 2;
//        } else if (flag.size() == 3 && flag.get(2) == false) {
//            temp = 1;
//        } else {
//            temp = 0;
//        }
//        //System.out.println(flag.get(2));
//        return ResultUtil.success(temp);
    }

    @DeleteMapping(value = "/deleteByCode")
    @ApiOperation(value = "通过Id删除点检计划，如果返回null，不可删除 , 传没有的数据返回true")
    public Result<Boolean> deleteByCode(long code) {
        return ResultUtil.success(deviceSpotcheckPlansService.deleteByCode(code));
    }

    @GetMapping(value = "/page")
    @ApiOperation(value = "点检计划-根据部门主键，设备名，状态，条件分页查询计划")
    public Result<Page> detailPage(@ApiParam(name = "deptId", value = "部门主键") @RequestParam Integer deptId,
                                   @ApiParam(name = "deviceName", value = "设备名称") @RequestParam String deviceName,
                                   @ApiParam(name = "status", value = "状态") @RequestParam(defaultValue = "-1") Integer status,
                                   @ApiParam(name = "condition", value = "查询条件") @RequestParam(defaultValue = "") String condition,
                                   @ApiParam(name = "page", value = "页数") @RequestParam(defaultValue = "1") Integer page,
                                   @ApiParam(name = "size", value = "页面大小") @RequestParam(defaultValue = "10") Integer size){
        return ResultUtil.success(deviceSpotcheckPlansService.getPlanByConditionsByPage(deptId,deviceName,status,condition,page,size));
    }

    @GetMapping(value = "/getDeviceCount")
    @ApiOperation(value = "获取部门的设备类名称和数量")
    public Result<Map<String, Integer>> getDeviceNameByDeptId(@ApiParam(name = "deptId", value = "部门id") @RequestParam Integer deptId){
        return ResultUtil.success(deviceSpotcheckPlansService.getDeviceNameByDeptId(deptId));
    }

    @PutMapping(value = "update")
    @ApiOperation(value = "更新计划状态")
    public Result update(@RequestParam Long planId){
        deviceSpotcheckPlansService.update(planId);
        return ResultUtil.success();
    }

}
