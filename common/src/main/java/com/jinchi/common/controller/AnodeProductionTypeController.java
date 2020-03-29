package com.jinchi.common.controller;


import com.jinchi.common.domain.BasicInfoAnodeProductionType;
import com.jinchi.common.dto.Result;
import com.jinchi.common.service.AnodeProductionTypeService;
import com.jinchi.common.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: LiuTaoYi
 * @create_time: 2019-12-04 10:50
 * @description:
 **/
@RestController
@RequestMapping(value = "/anodeProductionType")
@Api(tags = "正极成本-基础数据-产品型号")
public class AnodeProductionTypeController {

    @Autowired
    AnodeProductionTypeService typeService;

    @PostMapping(value = "add")
    @ApiOperation(value = "新增")
    public Result add(@RequestBody BasicInfoAnodeProductionType type) {
        return ResultUtil.success(typeService.add(type));
    }

    @DeleteMapping(value = "delete")
    @ApiOperation(value = "删除")
    public Result delete(@ApiParam(name = "id", value = "主键") @RequestParam Integer id) {
        typeService.delete(id);
        return ResultUtil.success();
    }

    @PutMapping(value = "update")
    @ApiOperation(value = "编辑")
    public Result update(@RequestBody BasicInfoAnodeProductionType type) {
        return ResultUtil.success(typeService.update(type));
    }

    @GetMapping(value = "all")
    @ApiOperation(value = "查询所有")
    public Result getAll() {
        return ResultUtil.success(typeService.getAll());
    }

}
