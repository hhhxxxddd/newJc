package com.jinchi.common.controller;

import com.jinchi.common.domain.FireMageDetectInfo;
import com.jinchi.common.dto.Result;
import com.jinchi.common.service.FireMageCheckManageService;
import com.jinchi.common.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = "fireMageCheck")
@Api(tags = "火法质量-检验管理-送检登记")
public class FireMageCheckController {

    @Autowired
    FireMageCheckManageService service;

    @PostMapping(value = "add")
    @ApiOperation(value = "新增")
    public Result add(@RequestBody List<FireMageDetectInfo> infos, @RequestParam Long[] ids){
        service.add(infos, Arrays.asList(ids));
        return ResultUtil.success();
    }

    @GetMapping(value = "check")
    @ApiOperation(value = "批号检查")
    public Result check(@RequestParam String batch){
        return ResultUtil.success(service.check(batch));
    }

    @GetMapping(value = "page")
    @ApiOperation(value = "分页查询")
    public Result page(@RequestParam(required = false,defaultValue = "") String condition,
                       @RequestParam(required = false,defaultValue = "1") Integer page,
                       @RequestParam(required = false,defaultValue = "10") Integer size){
        return ResultUtil.success(service.checkPage(condition,"0",page,size));
    }

    @GetMapping(value = "detail")
    @ApiOperation(value = "详情")
    public Result detail(@RequestParam Long id){
        return ResultUtil.success(service.checkDetail(id));
    }

}

/*
[
  {
    "batch": "g1dev1dev2pro12011u1line1s1",
    "day": "1",
    "delieryPeople": 1,
    "deptCode": 8,
    "dev1": "dev1",
    "dev2": "dev2",
    "line": "line1",
    "month": "1",
    "other": "-1",
    "process": "g1",
    "product": "pro1",
    "stream": "s1",
    "unit": "u1",
    "year": "20"
  }
]
 */
