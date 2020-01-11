package com.jinchi.common.controller;

import com.jinchi.common.domain.PowerCheckItem;
import com.jinchi.common.dto.Result;
import com.jinchi.common.service.PowerCheckItemService;
import com.jinchi.common.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: LiuTaoYi
 * @create_time: 2019-12-21 14:58
 * @description:
 **/
@RestController
@Api(tags = "动力点检-点检项目")
@RequestMapping(value = "/checkItem")
public class PowerCheckItemController {

    @Autowired
    PowerCheckItemService itemService;

    @PostMapping(value = "add")
    @ApiOperation(value = "新增")
    public Result add(@RequestBody PowerCheckItem item) {
        return ResultUtil.success(itemService.add(item));
    }

    @GetMapping(value = "getPlace")
    @ApiOperation(value = "获取地点")
    public Result getPlace(@RequestParam Long siteCode) {
        return ResultUtil.success(itemService.listPlaceBySite(siteCode));
    }

    @GetMapping(value = "byId")
    @ApiOperation(value = "根据id获取一条记录")
    public Result byId(@RequestParam Long id) {
        return ResultUtil.success(itemService.getOne(id));
    }

    @PostMapping(value = "byIds")
    @ApiOperation(value = "根据id数组获取一组记录")
    public Result byIds(@RequestBody Long[] ids) {
        return ResultUtil.success(itemService.getByIds(ids));
    }

    @PutMapping(value = "update")
    @ApiOperation(value = "编辑")
    public Result edit(@RequestBody PowerCheckItem item) {
        return ResultUtil.success(itemService.update(item));
    }

    @GetMapping(value = "page")
    @ApiOperation(value = "分页")
    public Result page(@ApiParam(name = "condition", value = "点检项目") @RequestParam(name = "condition", defaultValue = "", required = false) String condition,
                       @ApiParam(name = "page", value = "页码") @RequestParam(name = "page", defaultValue = "1") Integer page,
                       @ApiParam(name = "size", value = "条目数") @RequestParam(name = "size", defaultValue = "10") Integer size) {
        return ResultUtil.success(itemService.listByPage(condition, page, size));
    }

    @GetMapping(value = "getItems")
    @ApiOperation(value = "根据站点id和地点获取点检项目")
    public Result getItems(@RequestParam Long siteCode, @RequestParam String place) {
        return ResultUtil.success(itemService.getItemByConditions(siteCode, place));
    }


    @DeleteMapping(value = "delete")
    @ApiOperation(value = "删除")
    public Result delete(@RequestParam Long id) {
        itemService.deleteById(id);
        return ResultUtil.success();
    }

    @DeleteMapping(value = "deletes")
    @ApiOperation(value = "批量删除")
    public Result delete(@RequestBody Long[] ids) {
        itemService.deleteByIds(ids);
        return ResultUtil.success();
    }
}
