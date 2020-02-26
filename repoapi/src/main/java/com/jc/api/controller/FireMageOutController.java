package com.jc.api.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jc.api.service.restservice.imp.FireMageOutService;
import com.jinchi.common.core.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/fire")
@Api(tags = "出库管理-火法出库")
public class FireMageOutController {

    @Autowired
    FireMageOutService service;

    @GetMapping(value = "/query")
    @ApiOperation(value = "查询按钮")
    @ApiResponses(@ApiResponse(code = 200, message = "处理成功", response = Result.class))
    public Result query(@RequestParam(required = false) Integer type,
                        @RequestParam(required = false) Integer subType,
                        @RequestParam(required = false) Integer matId,
                        @RequestParam(required = false) Integer supplierId){
        return Result.success(service.getData(type,subType,matId,supplierId));
    }

    @PostMapping(value = "/queryDown")
    @ApiOperation(value= "查询下方的表格")
    @ApiResponses(@ApiResponse(code = 200, message = "处理成功", response = Result.class))
    public Result queryDown(@RequestParam Integer matId, @RequestBody Page page){
        return Result.success(service.getDataByMatid(matId, page));
    }
}
