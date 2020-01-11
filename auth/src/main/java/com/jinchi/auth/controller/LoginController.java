package com.jinchi.auth.controller;

import com.jinchi.auth.domain.MyUser;
import com.jinchi.auth.domain.Result;
import com.jinchi.auth.dto.PasswordChangeDTO;
import com.jinchi.auth.service.UserService;
import com.jinchi.auth.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 跟登陆有关的都写在这里
 *
 * @author：XudongHu
 * @description: 因为默认的登陆接口为/login /logout
 * @date:16:09 2018/11/6
 */
@RestController
@Api(tags = "登陆")
public class LoginController {
    @Autowired
    private UserService userService;

    @PostMapping(value = "/login")
    @ApiOperation(value = "登陆")
    public Result<Object> login(@RequestBody MyUser myUser) {
        return ResultUtil.success(userService.login(myUser));
    }

    @PutMapping(value = "/passwordChange")
    @ApiOperation(value = "修改密码")
    public Result<Map<Object,Object>> passwordChange(@RequestBody PasswordChangeDTO passwordChangeDTO){
        return ResultUtil.success(userService.passwordChange(passwordChangeDTO));
    }
}
