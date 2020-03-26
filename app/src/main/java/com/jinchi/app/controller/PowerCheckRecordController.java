package com.jinchi.app.controller;

import com.jinchi.app.dto.IdDto;
import com.jinchi.app.dto.PowerCheckRecordDTO;
import com.jinchi.app.dto.QueryDTO;
import com.jinchi.app.dto.Result;
import com.jinchi.app.service.PowerCheckRecordService;
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
 * @create_time: 2019-12-22 14:01
 * @description:
 **/
@RestController
@Api(tags = "动力点检-动力点检")
@RequestMapping(value = "/checkRecord")
public class PowerCheckRecordController {

    @Autowired
    PowerCheckRecordService recordService;

    @PostMapping(value = "commit")
    @ApiOperation(value = "提交")
    public Result add(@RequestBody PowerCheckRecordDTO dto) {
        return ResultUtil.success(recordService.add(dto));
    }


    @PostMapping(value = "detail")
    @ApiOperation(value = "详情")
    public Result detail(@RequestBody IdDto dto) {
        return ResultUtil.success(recordService.detail(dto.getId()));
    }


    @PostMapping(value = "page")
    @ApiOperation(value = "分页")
    public Result page(@RequestBody QueryDTO dto) {
        return ResultUtil.success(recordService.page(dto));
    }
}
