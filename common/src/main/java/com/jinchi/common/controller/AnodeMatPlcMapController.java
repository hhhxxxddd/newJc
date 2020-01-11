package com.jinchi.common.controller;

import com.jinchi.common.domain.BasicInfoAnodeMaterialPlcMap;
import com.jinchi.common.dto.Result;
import com.jinchi.common.service.AnodeMatPlcMapService;
import com.jinchi.common.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @author: LiuTaoYi
 * @create_time: 2019-11-25 22:28
 * @description:
 **/

@RestController
@RequestMapping(value = "/anodeMatPlcMap")
@Api(tags = "正极成本-基础数据-物料种类PLC仪表对照表")
public class AnodeMatPlcMapController {

    @Autowired
    AnodeMatPlcMapService mapService;

    @PostMapping(value = "add")
    @ApiOperation(value = "新增")
    public Result add(@RequestBody BasicInfoAnodeMaterialPlcMap map) {
        return ResultUtil.success(mapService.add(map));
    }

    @DeleteMapping(value = "delete")
    @ApiOperation(value = "删除")
    public Result delete(@RequestParam Integer id) {
        mapService.deleteById(id);
        return ResultUtil.success();
    }

    @DeleteMapping(value = "ids")
    @ApiOperation(value = "批量删除")
    public Result deleteByIds(@RequestBody Integer[] ids) {
        mapService.deleteByIds(ids);
        return ResultUtil.success();
    }

    @GetMapping(value = "page")
    @ApiOperation(value = "分页模糊查询")
    public Result page(@RequestParam(defaultValue = "", required = false) String condition,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer size) {
        return ResultUtil.success(mapService.page(condition, page, size));
    }

    @GetMapping(value = "all")
    @ApiOperation(value = "查询所有")
    public Result getAll(@RequestParam(defaultValue = "", required = false) String condition) {
        return ResultUtil.success(mapService.getAll(condition));
    }

    @PutMapping(value = "update")
    @ApiOperation(value = "更新")
    public Result update(@RequestBody BasicInfoAnodeMaterialPlcMap map) {
        return ResultUtil.success(mapService.update(map));
    }

    @GetMapping(value = "getRecordByIdV1")
    @ApiOperation(value = "根据id查询一条记录 V1")
    public Result getOne(@RequestParam Integer id) {
        return ResultUtil.success(mapService.getById(id));
    }

    @GetMapping(value = "getRecordByIdV2")
    @ApiOperation(value = "根据id查询一条记录 V2")
    public Result getOneV2(@RequestParam Integer id) {
        return ResultUtil.success(mapService.getOne(id));
    }
}

