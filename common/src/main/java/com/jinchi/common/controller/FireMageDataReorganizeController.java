package com.jinchi.common.controller;

import com.jinchi.common.dto.Result;
import com.jinchi.common.service.FireMageCheckManageService;
import com.jinchi.common.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = "dataReorganize")
@Api(tags = "火法质量-检验管理-数据整理")
public class FireMageDataReorganizeController {

    @Autowired
    FireMageCheckManageService service;

    @PostMapping(value = "export")
    @ApiOperation(value = "导出")
    public Result export(@RequestBody List<String> batches){
        return ResultUtil.success(service.reExport(batches));
    }

    @PostMapping(value = "import")
    @ApiOperation(value = "导入检验状态")
    public Result importMethod(@RequestBody MultipartFile excel) throws IOException {
        return ResultUtil.success(service.reImport(excel));
    }

    @GetMapping(value ="page")
    @ApiOperation(value ="分页查询")
    public Result page(@RequestParam(required = false,defaultValue = "")Integer deptCode,
                       @RequestParam(required = false,defaultValue = "")String process,
                       @RequestParam(required = false,defaultValue = "")String product,
                       @RequestParam(required = false,defaultValue = "")String date,
                       @RequestParam(defaultValue = "1",required = false)Integer page,
                       @RequestParam(defaultValue = "10",required = false)Integer size){
        return ResultUtil.success(service.rePage(deptCode,process,product,date,page,size));
    }

    @GetMapping(value = "detail")
    @ApiOperation(value  = "详情")
    public Result detai(@RequestParam Long id){
        return ResultUtil.success(service.reDetail(id));
    }

    @PutMapping(value = "update")
    @ApiOperation(value = "更新")
    public Result update(@RequestParam Long id,@RequestParam Long[] items,@RequestParam Float[] values){
        service.reUpdate(id, Arrays.asList(items),Arrays.asList(values));
        return ResultUtil.success();
    }
}
