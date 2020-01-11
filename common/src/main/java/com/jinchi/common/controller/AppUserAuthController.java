package com.jinchi.common.controller;

import com.jinchi.common.dto.Result;
import com.jinchi.common.service.AppUserAuthService;
import com.jinchi.common.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/appUserAuth")
@Api(tags = "权限模块-app用户")
public class AppUserAuthController {
    @Autowired
    AppUserAuthService appUserAuthService;

    @GetMapping(value = "getAllAuth")
    @ApiOperation(value = "获取所有权限")
    public Result getAllAuth(){
        return ResultUtil.success(appUserAuthService.getAllAuth());
    }

    @GetMapping(value = "getAuthByUserId")
    @ApiOperation(value = "根据用户id获取所有权限")
    public Result getAuthByUserId(@RequestParam Integer userId){
        return ResultUtil.success(appUserAuthService.getAuthByUserId(userId));
    }

    @PutMapping(value = "update")
    @ApiOperation(value = "更新用户权限")
    public Result update(@RequestParam Integer userId,@RequestBody @Valid Integer[] authIds){
        appUserAuthService.update(userId,authIds);
        return ResultUtil.success();
    }

    @GetMapping(value = "getUser")
    @ApiOperation(value = "根据部门id查询所有用户")
    public Result getUser(@RequestParam Integer deptCode){
        return ResultUtil.success(appUserAuthService.getUser(deptCode));
    }

    @PutMapping(value = "assign")
    @ApiOperation(value = "根据部门id和用户ids分配权限")
    public Result assign(@RequestParam Integer deptCode,@RequestBody Integer[] userIds){
        appUserAuthService.assign(deptCode,userIds);
        return ResultUtil.success();
    }
}
