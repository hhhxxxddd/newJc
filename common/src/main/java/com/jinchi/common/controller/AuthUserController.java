package com.jinchi.common.controller;

import com.jinchi.common.dto.AuthUserDTO;
import com.jinchi.common.dto.Result;
import com.jinchi.common.service.AuthUserService;
import com.jinchi.common.service.EquipmentInstructorRecordService;
import com.jinchi.common.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author：XudongHu
 * @className:AuthUserController
 * @description: 使用这个
 * @date:11:22 2018/11/23
 */
@RestController
@RequestMapping(value = "/authUser")
@Api(tags = "权限模块-用户")
public class AuthUserController {
    @Autowired
    private AuthUserService authUserService;
    private EquipmentInstructorRecordService equipmentInstructorRecordService;

    @GetMapping(value = "/getById")
    @ApiOperation(value = "根据id查询用户")
    public Result<AuthUserDTO> getById(@ApiParam(name = "id", value = "用户主键") @RequestParam Integer id) {
        return ResultUtil.success(authUserService.findById(id));
    }

    @GetMapping(value = "/getAll")
    @ApiOperation(value = "查询所有用户")
    public Result<List<AuthUserDTO>> getAll() {
        return ResultUtil.success(authUserService.findAll());
    }

}
