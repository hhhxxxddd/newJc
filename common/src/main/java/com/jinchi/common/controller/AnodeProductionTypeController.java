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

    @GetMapping(value = "page")
    @ApiOperation(value = "分页")
    public Result page(@ApiParam(name = "condition", value = "条件") @RequestParam(defaultValue = "", required = false) String condition, @ApiParam(name = "page", value = "页数", defaultValue = "1") @RequestParam Integer page, @ApiParam(name = "size", value = "每页数量", defaultValue = "10") @RequestParam Integer size) {
        return ResultUtil.success(typeService.page(condition, page, size));
    }

}
