package com.jinchi.common.controller;

import com.jinchi.common.dto.FireNumberBase;
import com.jinchi.common.dto.Result;
import com.jinchi.common.service.FireMageNumberService;
import com.jinchi.common.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "fireMageNumber")
@Api(tags = "火法质量-编号规则")
public class FireMageNumberController {

    @Autowired
    FireMageNumberService numberService;

    @GetMapping(value = "getHead")
    @ApiOperation(value = "主界面-获取所有规则")
    public Result getHead(){
        return ResultUtil.success(numberService.getHead());
    }

    @PutMapping(value = "isEnable")    @ApiOperation(value = "主界面-启用与否")
    public Result isEnable(@RequestParam Integer position){
        numberService.isEnable(position);
        return ResultUtil.success();
    }

    @GetMapping(value = "detail")
    @ApiOperation(value = "主界面-详情")
    public Result detail(@RequestParam Integer position){
        return ResultUtil.success(numberService.detail(position));
    }

    @PutMapping(value = "save")
    @ApiOperation(value = "编辑-保存")
    public Result addRule(@RequestParam Integer position, @RequestBody List<FireNumberBase> bases, @RequestParam(required = false, defaultValue = "") String strs) {
        numberService.save(position, bases, strs);
        return ResultUtil.success();
    }

    @GetMapping(value = "getAllInfos")
    @ApiOperation(value = "获取所有批次规则")
    public Result getAllInfos(){
        return ResultUtil.success(numberService.getAllInfos());
    }
}
