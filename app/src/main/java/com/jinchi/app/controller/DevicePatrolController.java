package com.jinchi.app.controller;

import com.jinchi.app.dto.*;
import com.jinchi.app.service.DevicePatrolService;
import com.jinchi.app.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/patrol")
@Api(tags = "设备巡检-app")
public class DevicePatrolController {

    @Autowired
    DevicePatrolService devicePatrolService;

    @PostMapping(value = "page")
    @ApiOperation(value = "根据用户id，状态分页")
    public Result page(@RequestBody @Valid PatrolPostDTO patrolPostDTO){
        return ResultUtil.success(devicePatrolService.page(patrolPostDTO));
    }

    @PostMapping(value = "detail")
    @ApiOperation(value = "详情")
    public Result detail(@RequestBody @Valid IdDto idDto){
        return ResultUtil.success(devicePatrolService.detail(idDto.getId()));
    }

    @PostMapping(value = "commit")
    @ApiOperation(value = "提交/暂存")
    public Result commit(@RequestBody PatrolDTO patrolDTO){
        devicePatrolService.commit(patrolDTO);
        return ResultUtil.success();
    }

    @PostMapping(value = "getLocationByIdCard")
    @ApiOperation(value = "刷NFC")
    public Result getLocationByIdCard(@RequestBody @Valid PatrolPostDTO patrolPostDTO){
        PatrolLocationDTO ans = devicePatrolService.getLocationByIdCard(patrolPostDTO);
        if(ans == null){
            ResultUtil.error("找不到该位置或该位置不属于该巡检计划");
        }
        return ResultUtil.success(ans);
    }
}
