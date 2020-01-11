package com.jinchi.app.controller;

import com.jinchi.app.dto.*;
import com.jinchi.app.service.LoginService;
import com.jinchi.app.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@Api(tags = "登陆")
public class LoginController {

    @Value("${app.url}")
    String url;
    @Autowired
    LoginService loginService;

    @PostMapping(value = "/login")
    @ApiOperation(value = "登陆")
    public Result login(@RequestBody @Valid LoginDTO loginDTO){
        LoginAuthDTO ans = loginService.login(loginDTO,url);
        if(ans == null){
            return ResultUtil.error("账号或密码错误");
        }
        return ResultUtil.success(ans);
    }

    @PostMapping(value = "version")
    @ApiOperation(value = "版本更新")
    public Result versionInfo(@RequestBody RepairPostDTO repairPostDTO){
        return  ResultUtil.success(loginService.versionInfo(repairPostDTO.getCondition()));
    }

    @PostMapping(value = "pwdChange")
    @ApiOperation(value = "修改密码")
    public Result pwdChange(@RequestBody PasswordChangeDTO passwordChangeDTO){
        return ResultUtil.success(loginService.pwdChange(passwordChangeDTO,url));
    }


}
