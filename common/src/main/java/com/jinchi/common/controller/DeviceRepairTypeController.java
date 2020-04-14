package com.jinchi.common.controller;

import com.jinchi.common.domain.DeviceRepairType;
import com.jinchi.common.dto.Result;
import com.jinchi.common.service.DeviceRepairTypeService;
import com.jinchi.common.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: LiuTaoYi
 * @time: 2020/3/30 13:23
 * @description:
 */
@RestController
@RequestMapping(value = "/deviceRepairType")
@Api(tags = "设备维修-维修类型")
public class DeviceRepairTypeController {

    @Autowired
    DeviceRepairTypeService repairTypeService;

    @PostMapping(value = "add")
    @ApiOperation(value = "新增")
    public Result add(@RequestBody DeviceRepairType type) {
        return ResultUtil.success(repairTypeService.add(type));
    }

    @PutMapping(value = "update")
    @ApiOperation(value = "更新")
    public Result update(@RequestBody DeviceRepairType type) {
        return ResultUtil.success(repairTypeService.update(type));
    }

    @GetMapping(value = "all")
    @ApiOperation(value = "查询所有")
    public Result getAll() {
        return ResultUtil.success(repairTypeService.getAll());
    }

    @GetMapping(value = "page")
    @ApiOperation(value = "分页查询")
    public Result getPage(@RequestParam(value = "condition", defaultValue = "", required = false) String condition,
                          @RequestParam(value = "page", defaultValue = "1") Integer page,
                          @RequestParam(value = "size", defaultValue = "10") Integer size) {
        return ResultUtil.success(repairTypeService.page(condition, page, size));
    }

    @DeleteMapping(value = "byIds")
    @ApiOperation(value = "删除")
    public Result delete(@RequestBody Integer[] ids) {
        repairTypeService.deleteByIds(ids);
        return ResultUtil.success();
    }
}
