package com.jinchi.common.controller;

import com.github.pagehelper.PageInfo;
import com.jinchi.common.domain.TestItem;
import com.jinchi.common.dto.Result;
import com.jinchi.common.service.TestItemService;
import com.jinchi.common.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by Administrator on 2018/11/19.
 */
@RestController
@RequestMapping(value = "/testItem")
@Api(tags = "质量流程-基础数据-检测项目")
public class TestItemController {
    @Autowired
    private TestItemService testItemService;

    @PostMapping
    @ApiOperation(value = "新增")
    public Result<TestItem> add(@RequestBody @Valid TestItem testItem) {
        return ResultUtil.success(testItemService.add(testItem));
    }

    @DeleteMapping
    @ApiOperation(value = "批量删除")
    public Result<Object> deleteByIds(@RequestBody @Valid Integer[] ids) {
        testItemService.deleteByIds(ids);
        return ResultUtil.success();
    }

    @GetMapping
    @ApiOperation(value = "查询所有")
    public Result<List<TestItem>> getAll() {
        return ResultUtil.success(testItemService.getAll());
    }

    @GetMapping(value = "/pages")
    @ApiOperation(value = "查询所有-分页")
    public Result<PageInfo<TestItem>> getAllByPage(
            @ApiParam(name = "page", value = "页码") @RequestParam(name = "page", defaultValue = "0") Integer page,
            @ApiParam(name = "size", value = "条目数") @RequestParam(name = "size", defaultValue = "10") Integer size,
            @ApiParam(name = "orderField", value = "排序属性") @RequestParam(name = "orderField", defaultValue = "id") String
                    orderField,
            @ApiParam(name = "orderType", value = "排序方法,只能是asc升序或者desc降序") @RequestParam(name = "orderType", defaultValue = "desc") String
                    orderType
    ) {
        return ResultUtil.success(testItemService.getAllByPage(page, size, orderField, orderType));
    }

    @GetMapping(value = "/pagesNameLike")
    @ApiOperation(value = "查询所有-名称模糊-分页")
    public Result<PageInfo<TestItem>> getNameLikeByPage(
            @ApiParam(name = "testItemName", value = "检测项目名") @RequestParam(name = "testItemName", defaultValue = "") String
                    testItemName,
            @ApiParam(name = "page", value = "页码") @RequestParam(name = "page", defaultValue = "0") Integer page,
            @ApiParam(name = "size", value = "条目数") @RequestParam(name = "size", defaultValue = "10") Integer size,
            @ApiParam(name = "orderField", value = "排序属性") @RequestParam(name = "orderField", defaultValue = "id") String
                    orderField,
            @ApiParam(name = "orderType", value = "排序方法,只能是asc升序或者desc降序") @RequestParam(name = "orderType", defaultValue = "desc") String
                    orderType) {
        return ResultUtil.success(testItemService.getByNameLikeByPage(testItemName, page, size, orderField, orderType));
    }

    @PutMapping
    @ApiOperation(value = "更新")
    public Result<TestItem> update(@RequestBody @Valid TestItem testItem) {
        return ResultUtil.success(testItemService.update(testItem));
    }

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "查找")
    public Result<TestItem> getById(@RequestParam Integer id) {
        return ResultUtil.success(testItemService.getById(id));
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation(value = "删除")
    public Result<TestItem> deleteById(@RequestParam Integer id) {
        testItemService.deleteById(id);
        return ResultUtil.success();
    }
}
