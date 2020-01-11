package com.jinchi.common.controller;

import com.jinchi.common.domain.RepoBaseSerialNumber;
import com.jinchi.common.dto.Result;
import com.jinchi.common.service.RepoBaseSerialNumberService;
import com.jinchi.common.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * @author:YuboWu
 * @description:SerialNumber基本表
 * @date:12:30 2018/12/7
 */
@RestController
@RequestMapping(value = "/repoBaseSerialNumber")
@Api(tags = "智能仓库-基础编号")
public class RepoBaseSerialNumberController {
    @Autowired
    private RepoBaseSerialNumberService repoBaseSerialNumberService;


    @GetMapping
    @ApiOperation(value = "查询所有",notes = "根据类型")
    public Result<List<RepoBaseSerialNumber>> findAll(@ApiParam(name = "materialClass", value = "物料类型") @RequestParam(name = "materialClass", required = false) Integer materialClass) {
        return ResultUtil.success(repoBaseSerialNumberService.findAll(materialClass));
    }

    @PostMapping
    @ApiOperation(value = "新增基本物料")
    public Result<RepoBaseSerialNumber> add(@ApiParam(value = "基本编号") @Valid @RequestBody RepoBaseSerialNumber repoBaseSerialNumber){
        return ResultUtil.success(repoBaseSerialNumberService.add(repoBaseSerialNumber));
    }

    @PostMapping(value = "/addExtra")
    @ApiOperation(value = "新增基本物料(成品)")
    public Result<RepoBaseSerialNumber> addExtra(@ApiParam(value = "基本编号") @Valid @RequestBody RepoBaseSerialNumber repoBaseSerialNumber){
        return ResultUtil.success(repoBaseSerialNumberService.addExtra(repoBaseSerialNumber));
    }

    @GetMapping(value = "/factors")
    @ApiOperation(value = "查询所有-多条件",notes = "多条件")
    public Result<List<RepoBaseSerialNumber>> getAll(RepoBaseSerialNumber repoBaseSerialNumber){
        return ResultUtil.success(repoBaseSerialNumberService.findByFactors(repoBaseSerialNumber));
    }


    @GetMapping(value = "/allName")
    @ApiOperation(value = "查询物料/厂商",notes = "多条件")
    public Result<List<Map<Object,Object>>> all(@ApiParam(name = "materialClass", value = "物料类型") @RequestParam(name = "materialClass", required = false) Integer materialClass,
                                          @ApiParam(name = "isManufacturer", value = "是否为厂商") @RequestParam(name = "isManufacturer") Integer isManufacturer){
        return ResultUtil.success(repoBaseSerialNumberService.uniqueValue(materialClass, isManufacturer));
    }

}
