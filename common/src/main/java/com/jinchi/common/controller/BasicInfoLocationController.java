package com.jinchi.common.controller;

import com.jinchi.common.domain.BasicInfoLocation;
import com.jinchi.common.dto.Page;
import com.jinchi.common.dto.Result;
import com.jinchi.common.service.BasicInfoLocationService;
import com.jinchi.common.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/basicInfoLocation")
@Api(tags = "设备管理-基础数据-位置基础数据")
public class BasicInfoLocationController {
    @Autowired
    BasicInfoLocationService basicInfoLocationService;

    @PostMapping(value = "/addBasicInfo")
    @ApiOperation(value = "基础信息-新增")
    public Result<BasicInfoLocation> addBasicInfo(@RequestBody @Valid BasicInfoLocation basicInfoLocation, BindingResult bindingResult) {
        return ResultUtil.success(basicInfoLocationService.add(basicInfoLocation));
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation(value = "基础信息-删除")
    public Result deleteById(@ApiParam(name = "id", value = "点检模板主键") @PathVariable Integer id ){
        basicInfoLocationService.deleteById(id);
        return ResultUtil.success();
    }


    @DeleteMapping(value = "/deleteByIds")
    @ApiOperation(value = "基础信息-批量删除")
    public Result deleteByIds(@RequestBody @Valid Integer[] ids, BindingResult bindingResultLong){
       basicInfoLocationService.deleteByIds(ids);
        return ResultUtil.success();
    }

    @PutMapping(value = "/updateById")
    @ApiOperation(value = "基础信息-编辑")
    public Result<BasicInfoLocation> updateById(@RequestBody @Valid BasicInfoLocation basicInfoLocation, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
        }
        return ResultUtil.success(basicInfoLocationService.updataById(basicInfoLocation));
    }

    @GetMapping(value = "/getPage")
    @ApiOperation(value = "基础信息-分页")
    public Result<Page> getPage(@ApiParam(name = "deptId", value = "设备Id") @RequestParam Integer deptId,
                                 @ApiParam(name = "page", value = "页码") @RequestParam(name = "page", defaultValue = "1") Integer page,
                                 @ApiParam(name = "size", value = "条目数") @RequestParam(name = "size", defaultValue = "10") Integer size){

        return ResultUtil.success(basicInfoLocationService.getByPage(deptId,page,size));

    }

    @GetMapping(value = "/maintenanceDetailById")
    @ApiOperation("部门Code获取该部门下所有位置信息")
    public Result<List<BasicInfoLocation>> getLocationById(@ApiParam( name = "id",value = "部门主键") @RequestParam Integer id) {
        return  ResultUtil.success(basicInfoLocationService.getLocationById(id));
    }



}
