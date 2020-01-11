package com.jinchi.common.controller;

import com.jinchi.common.domain.BasicInfoAnodeProductionLine;
import com.jinchi.common.dto.Result;
import com.jinchi.common.service.AnodeProductionLineService;
import com.jinchi.common.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: LiuTaoYi
 * @create_time: 2019-11-25 15:50
 * @description:
 **/
@RestController
@RequestMapping(value = "/anodeProductionLine")
@Api(tags = "正极成本-基础数据-生产线")
public class AnodeProductionLineController {

    @Autowired
    AnodeProductionLineService lineService;

    @PostMapping(value = "add")
    @ApiOperation(value = "新增")
    public Result add(@RequestBody BasicInfoAnodeProductionLine line) {
        return ResultUtil.success(lineService.add(line));
    }

    @DeleteMapping(value = "delete")
    @ApiOperation(value = "删除")
    public Result delete(@ApiParam(name = "id", value = "主键") @RequestParam Integer id) {
        lineService.delete(id);
        return ResultUtil.success();
    }

    @PutMapping(value = "update")
    @ApiOperation(value = "编辑")
    public Result update(@RequestBody BasicInfoAnodeProductionLine line) {
        return ResultUtil.success(lineService.update(line));
    }

    @GetMapping(value = "all")
    @ApiOperation(value = "查询所有")
    public Result getAll() {
        return ResultUtil.success(lineService.getAll());
    }

    @GetMapping(value = "byFlag")
    @ApiOperation(value = "按产线是否启用查询")
    public Result getByFlag(@RequestParam Boolean flag) {
        return ResultUtil.success(lineService.getByFlag(flag));
    }
}
