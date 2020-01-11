package com.jinchi.common.controller;

import com.jinchi.common.domain.BasicInfoCompoundCellVolumes;
import com.jinchi.common.dto.Result;
import com.jinchi.common.service.PrecursorCompoundCellVolumesService;
import com.jinchi.common.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: LiuTaoYi
 * @create_time: 2019-11-01 11:28
 * @description:
 **/
@RestController
@RequestMapping(value = "/precursorCompoundCellVolumes")
@Api(tags = "成本核算-基础数据-合成槽体积值")
public class PrecursorCompoundCellVolumesController {

    @Autowired
    PrecursorCompoundCellVolumesService volumesService;

    @PostMapping(value = "add")
    @ApiOperation(value = "新增 合成槽号通过 common/precursorMaterialDetails/getHC 这个接口获取")
    public Result add(@RequestBody BasicInfoCompoundCellVolumes volumes) {
        return ResultUtil.success(volumesService.add(volumes));
    }

    @GetMapping(value = "page")
    @ApiOperation(value = "根据名称分页查询")
    public Result page(@RequestParam(defaultValue = "", required = false) String condition,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer size) {
        return ResultUtil.success(volumesService.page(condition, page, size));
    }


    @DeleteMapping(value = "delete")
    @ApiOperation(value = "删除")
    public Result delete(@RequestParam Integer id) {
        volumesService.deleteById(id);
        return ResultUtil.success();
    }

    @DeleteMapping(value = "ids")
    @ApiOperation(value = "批量删除")
    public Result deleteByIds(@RequestBody Integer[] ids) {
        volumesService.deleteByIds(ids);
        return ResultUtil.success();
    }

    @PutMapping(value = "update")
    @ApiOperation(value = "编辑")
    public Result update(@RequestBody BasicInfoCompoundCellVolumes volumes) {
        return ResultUtil.success(volumesService.update(volumes));
    }

    @GetMapping(value = "getRecordById")
    @ApiOperation(value = "根据id查询一条记录")
    public Result getDataById(@RequestParam Integer id) {
        return ResultUtil.success(volumesService.getOne(id));
    }
}
