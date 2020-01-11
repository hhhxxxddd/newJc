package com.jinchi.common.controller;

import com.jinchi.common.dto.RawmaterialLineWeightDTO;
import com.jinchi.common.dto.Result;
import com.jinchi.common.service.PrecursorRawmaterialLineWeightService;
import com.jinchi.common.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: LiuTaoYi
 * @create_time: 2019-10-31 22:08
 * @description:
 **/
@RestController
@RequestMapping(value = "/precursorRawmaterialLineWeight")
@Api(tags = "成本核算-基础数据-原材料产线权重分配")
public class PrecursorRawmaterialLineWeightController {

    @Autowired
    PrecursorRawmaterialLineWeightService weightService;

    @GetMapping(value = "getRecordById")
    @ApiOperation(value = "根据id查询一条记录")
    public Result getOne(@RequestParam Integer id) {
        return ResultUtil.success(weightService.getRecordById(id));
    }

    @PostMapping(value = "add")
    @ApiOperation(value = "新增")
    public Result add(@RequestBody RawmaterialLineWeightDTO lineWeightDTO) {
        RawmaterialLineWeightDTO ans = weightService.add(lineWeightDTO);
        if (ans != null) {
            return ResultUtil.success(ans);
        } else {
            return ResultUtil.error("已经存在相关信息");
        }
    }

    @GetMapping(value = "page")
    @ApiOperation(value = "根据名称分页查询")
    public Result page(@RequestParam(defaultValue = "", required = false) String condition,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer size) {
        return ResultUtil.success(weightService.page(condition, page, size));
    }

    @DeleteMapping(value = "delete")
    @ApiOperation(value = "删除")
    public Result delete(@RequestParam Integer id) {
        weightService.delete(id);
        return ResultUtil.success();
    }

    @DeleteMapping(value = "ids")
    @ApiOperation(value = "批量删除")
    public Result deleteByIds(@RequestBody Integer[] ids) {
        weightService.deleteByIds(ids);
        return ResultUtil.success();
    }

    @PutMapping(value = "update")
    @ApiOperation(value = "编辑")
    public Result update(@RequestBody RawmaterialLineWeightDTO lineWeightDTO) {
        return ResultUtil.success(weightService.update(lineWeightDTO));
    }
}
