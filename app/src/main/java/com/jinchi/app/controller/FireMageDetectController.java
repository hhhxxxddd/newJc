package com.jinchi.app.controller;

import com.jinchi.app.dto.QueryDTO;
import com.jinchi.app.dto.Result;
import com.jinchi.app.service.FireMageDetectService;
import com.jinchi.app.utils.ResultUtil;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("fireMage")
@Api(tags = "火法质量-化验数据")
public class FireMageDetectController {

    @Autowired
    FireMageDetectService service;

    @PostMapping("page")
    public Result page(@RequestBody QueryDTO queryDTO){
        return ResultUtil.success(service.page(queryDTO));
    }

    @PostMapping("detail")
    public Result detail(@RequestBody QueryDTO queryDTO){
        return ResultUtil.success(service.detail(queryDTO.getId()));
    }
}
