package com.jinchi.app.controller;

import com.jinchi.app.dto.DeviceConditionDTO;
import com.jinchi.app.dto.DeviceIDCardDTO;
import com.jinchi.app.dto.Result;
import com.jinchi.app.service.DeviceIDCardManageService;
import com.jinchi.app.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: LiuTaoYi
 * @create_time: 2019-11-14 10:10
 * @description:
 **/
@RestController
@RequestMapping(value = "/deviceIDCardManage")
@Api(tags = "设备ID卡管理-app")
public class DeviceIDCardManageController {

    @Autowired
    DeviceIDCardManageService manageService;

    @PostMapping(value = "page")
    @ApiOperation(value = "按要求分页查询")
    public Result page(@RequestBody DeviceConditionDTO conditionDTO) {
        return ResultUtil.success(manageService.getDataByCondition1(conditionDTO));
    }

    @PostMapping(value = "detail")
    @ApiOperation(value = "根据设备id查询详情")
    public Result detail(@RequestBody DeviceConditionDTO conditionDTO) {
        return ResultUtil.success(manageService.getDetailByCode(conditionDTO));
    }

    @PostMapping(value = "update")
    @ApiOperation(value = "更新")
    public Result update(@RequestBody DeviceIDCardDTO dto) {
        return ResultUtil.success(manageService.updateByCode(dto));
    }

    @PostMapping(value = "getProcess")
    @ApiOperation(value = "获取工序")
    public Result getProcess(@RequestBody DeviceConditionDTO conditionDTO) {
        return ResultUtil.success(manageService.getProcessByDept(conditionDTO));
    }

    @PostMapping(value = "deptTree")
    @ApiOperation(value = "获取部门树")
    public Result getTree() {
        return ResultUtil.success(manageService.getDeptCata());
    }
}
