package com.jinchi.common.controller;

import com.jinchi.common.dto.Result;
import com.jinchi.common.dto.TechLineCellMapDTO;
import com.jinchi.common.service.PrecursorTechLineCellMapService;
import com.jinchi.common.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: LiuTaoYi
 * @create_time: 2019-10-15 19:25
 * @description:
 **/
@RestController
@RequestMapping(value = "/techLineCellMap")
@Api(tags = "成本核算-基础数据-生产线合成槽对照")
public class PrecursorTechLineCellMapController {


    @Autowired
    PrecursorTechLineCellMapService mapService;

    @GetMapping(value = "getRecordById")
    @ApiOperation(value = "根据id查询一条记录")
    public Result getOne(@RequestParam Integer id) {
        return ResultUtil.success(mapService.getByLineCode(id));
    }

    @PostMapping(value = "add")
    @ApiOperation(value = "新增")
    public Result add(@RequestBody TechLineCellMapDTO mapDTO) {
        TechLineCellMapDTO ans = mapService.add(mapDTO);
        if (ans.getLineCode() != null) {
            return ResultUtil.success(ans);
        } else {
            return ResultUtil.error("已经存在这个生产线的相关信息或者合成槽已分配给其他产线");
        }
    }

    @GetMapping(value = "page")
    @ApiOperation(value = "根据名称,分页查询")
    public Result page(@RequestParam(defaultValue = "", required = false) String condition,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer size) {
        return ResultUtil.success(mapService.page(condition, page, size));
    }

    @DeleteMapping(value = "delete")
    @ApiOperation(value = "删除，根据生产线编码")
    public Result delete(@RequestParam Integer id) {
        mapService.deleteById(id);
        return ResultUtil.success();
    }

    @DeleteMapping(value = "ids")
    @ApiOperation(value = "批量删除，根据生产线编码")
    public Result deleteByIds(@RequestBody Integer[] ids) {
        mapService.deleteByIds(ids);
        return ResultUtil.success();
    }

    @PutMapping(value = "update")
    @ApiOperation(value = "编辑")
    public Result update(@RequestBody TechLineCellMapDTO mapDTO) {
        return ResultUtil.success(mapService.update(mapDTO));
    }

    @PostMapping(value = "byIds")
    @ApiOperation(value = "根据生产线编码数组获取所有合成槽")
    public Result getByIds(@RequestBody Integer[] ids) {
        return ResultUtil.success(mapService.getByIds(ids));
    }
}
