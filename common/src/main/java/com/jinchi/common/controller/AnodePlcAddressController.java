package com.jinchi.common.controller;

import com.jinchi.common.domain.BasicInfoAnodePlcAddress;
import com.jinchi.common.dto.Result;
import com.jinchi.common.service.AnodePlcAddressService;
import com.jinchi.common.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: LiuTaoYi
 * @create_time: 2019-11-25 21:45
 * @description:
 **/
@RestController
@RequestMapping(value = "/anodePlcAddress")
@Api(tags = "正极成本-基础数据-PLC地址")
public class AnodePlcAddressController {

    @Autowired
    AnodePlcAddressService addressService;

    @PostMapping(value = "add")
    @ApiOperation(value = "新增")
    public Result add(@RequestBody BasicInfoAnodePlcAddress anodePlcAddress) {
        return ResultUtil.success(addressService.add(anodePlcAddress));
    }

    @DeleteMapping(value = "delete")
    @ApiOperation(value = "删除")
    public Result delete(@RequestParam Integer id) {
        addressService.deleteById(id);
        return ResultUtil.success();
    }

    @DeleteMapping(value = "ids")
    @ApiOperation(value = "批量删除")
    public Result deleteByIds(@RequestBody Integer[] ids) {
        addressService.deleteByIds(ids);
        return ResultUtil.success();
    }

    @GetMapping(value = "page")
    @ApiOperation(value = "分页模糊查询")
    public Result page(@RequestParam(defaultValue = "", required = false) String condition,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer size) {
        return ResultUtil.success(addressService.page(condition, page, size));
    }

    @GetMapping(value = "all")
    @ApiOperation(value = "查询所有")
    public Result getAll(@RequestParam(defaultValue = "", required = false) String condition) {
        return ResultUtil.success(addressService.getAll(condition));
    }

    @PutMapping(value = "update")
    @ApiOperation(value = "更新")
    public Result update(@RequestBody BasicInfoAnodePlcAddress anodePlcAddress) {
        return ResultUtil.success(addressService.update(anodePlcAddress));
    }

    @GetMapping(value = "getRecordById")
    @ApiOperation(value = "根据id查询一条记录")
    public Result getOne(@RequestParam Integer id) {
        return ResultUtil.success(addressService.getById(id));
    }
}
