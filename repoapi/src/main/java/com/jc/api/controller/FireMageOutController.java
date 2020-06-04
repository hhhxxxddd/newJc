package com.jc.api.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jc.api.entity.dto.AuditDTO;
import com.jc.api.service.restservice.imp.FireMageOutService;
import com.jinchi.common.core.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public Result queryDown(@RequestParam Integer matId,
                            @RequestParam(required = false,defaultValue = "") Integer supplierCode
            ,@RequestParam(required = false,defaultValue = "") Integer type
            ,@RequestParam(required = false,defaultValue = "") Integer subType){
        return Result.success(service.getDataByMatid(type,subType,matId,supplierCode));
    }

    @PostMapping(value = "page")
    @ApiOperation(value= "火法出库单查询")
    @ApiResponses(@ApiResponse(code = 200, message = "处理成功", response = Result.class))
    public Result page(@RequestParam(required = false)Integer deptCode,@RequestParam(required = false)String date,
                       @RequestBody Page page){
        return Result.success(service.getByPage(deptCode,date,page));
    }

    @PostMapping(value = "audit")
    @ApiOperation(value= "送审")
    @ApiResponses(@ApiResponse(code = 200, message = "处理成功", response = Result.class))
    public Result audit(@RequestBody List<AuditDTO> mats,
                        @RequestParam Integer deptCode,
                        @RequestParam Integer lineCode,
                        @RequestParam Integer outPoint,
                        @RequestParam Integer outType,
                        @RequestParam Integer isUrgent,
                        @RequestParam Integer auditId,
                        @RequestParam Integer userId){
        return Result.success(service.sendAudit(mats, deptCode, lineCode, outPoint, outType, isUrgent, auditId, userId));
    }

    @GetMapping(value = "detail")
    @ApiOperation(value= "送审详情")
    @ApiResponses(@ApiResponse(code = 200, message = "处理成功", response = Result.class))
    public Result audit(@RequestParam Long id){
        return Result.success(service.detail(id));
    }
}
