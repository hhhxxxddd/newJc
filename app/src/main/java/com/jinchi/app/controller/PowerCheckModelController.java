package com.jinchi.app.controller;

import com.jinchi.app.dto.IdDto;
import com.jinchi.app.dto.Result;
import com.jinchi.app.service.PowerCheckModelService;
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
 * @create_time: 2019-12-22 13:02
 * @description:
 **/
@RestController
@Api(tags = "动力点检-点检模板")
@RequestMapping(value = "/checkModel")
public class PowerCheckModelController {

    @Autowired
    PowerCheckModelService modelService;

    @PostMapping(value = "detail")
    @ApiOperation(value = "详情")
    public Result detail(@RequestBody IdDto dto) {
        return ResultUtil.success(modelService.detail(dto.getId()));
    }

}
