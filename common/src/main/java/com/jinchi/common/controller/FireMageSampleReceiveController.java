package com.jinchi.common.controller;

import com.jinchi.common.dto.Result;
import com.jinchi.common.service.FireMageCheckManageService;
import com.jinchi.common.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "sampleReceive")
@Api(tags = "火法质量-检验管理-样品接收")
public class FireMageSampleReceiveController {

    @Autowired
    FireMageCheckManageService service;

    @GetMapping(value = "page")
    @ApiOperation(value = "分页搜索")
    public Result page(@RequestParam(required = false,defaultValue = "") String condition,
                       @RequestParam(required = false,defaultValue = "1") Integer page,
                       @RequestParam(required = false,defaultValue = "10") Integer size){
        return ResultUtil.success(service.checkPage(condition,"-1",page,size));
    }

    @GetMapping(value = "detail")
    @ApiOperation(value = "详情")
    public Result detail(@RequestParam Long id){
        return ResultUtil.success(service.checkDetail(id));
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation(value = "删除")
    public Result delete(@PathVariable Long id){
        service.delete(id);
        return ResultUtil.success();
    }

    @DeleteMapping(value = "ids")
    @ApiOperation(value = "删除")
    public Result delete(@RequestBody Long[] ids){
        service.deleteByIds(ids);
        return ResultUtil.success();
    }

    @GetMapping(value = "print")
    @ApiOperation(value = "打印")
    public Result print(@RequestParam Long id){
        return ResultUtil.success(service.printLabels(id));
    }

    @PutMapping
    @ApiOperation(value = "接收或拒收")
    public Result acceptOrReject(@RequestParam Long id,@RequestParam @ApiParam(value = "1 接收 2 拒收") Integer flag ,@RequestParam(required = false)String reason){
        service.acceptOrReject(id,flag,reason);
        return ResultUtil.success();
    }
}
