package com.jinchi.common.controller;

import com.jinchi.common.dto.Result;
import com.jinchi.common.dto.fireMage.FireMagePost;
import com.jinchi.common.mapper.FireMageDetectInfoMapper;
import com.jinchi.common.service.FireMageCheckManageService;
import com.jinchi.common.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;

@RestController
@RequestMapping(value = "dateConllection")
@Api(tags = "火法质量-检验管理-数据采集")
public class FireMageDateCollectionController {

    @Autowired
    FireMageCheckManageService service;
    @Autowired
    FireMageDetectInfoMapper mageDetectInfoMapper;

    @GetMapping(value = "page")
    @ApiOperation(value = "分页查询")
    public Result page(@RequestParam(required = false,defaultValue = "") String time,
                       @RequestParam(required = false,defaultValue = "1") Integer page,
                       @RequestParam(required = false,defaultValue = "10") Integer size){
        return ResultUtil.success(service.collectionPage(time,page,size));
    }

    @GetMapping(value = "getByProcessByProduct")
    @ApiOperation(value = "导出-数据")
    public Result exportDatas(@RequestParam(defaultValue = "", required = false) Integer processCode,
                              @RequestParam(defaultValue = "", required = false) Integer productCode,
                              @RequestParam(defaultValue = "", required = false) String startTime) {
        return ResultUtil.success(service.getByProcessByProduct(processCode,productCode,startTime));
    }

    @PostMapping(value = "export")
    @ApiOperation(value= "导出按钮")
    public Result export(@RequestBody FireMagePost info){
        return ResultUtil.success(service.export(info.getBatches(), info.getItemNames()));
    }

    @PostMapping(value = "import")
    @ApiOperation(value = "导入按钮")
    public Result importMethod(@RequestBody MultipartFile excelFile,@RequestParam Long[] items) throws IOException {
        return ResultUtil.success(service.importMethod(excelFile,Arrays.asList(items)));
    }

}
