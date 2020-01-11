package com.jinchi.common.controller;

import com.github.pagehelper.PageInfo;
import com.jinchi.common.domain.ProductionProcess;
import com.jinchi.common.dto.Result;
import com.jinchi.common.service.ProductionProcessService;
import com.jinchi.common.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = "/productionProcess")
@Api(tags = "质量流程-基础数据-产品工序")
public class ProductionProcessController {
    @Autowired
    private ProductionProcessService productionProcessService;

    @PostMapping
    @ApiOperation(value = "新增")
    public Result<ProductionProcess> add(@RequestBody @Valid ProductionProcess productionProcess, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
        }
        return ResultUtil.success(productionProcessService.addOne(productionProcess));
    }

    @PutMapping
    @ApiOperation(value = "更新")
    public Result<ProductionProcess> update(@RequestBody @Valid ProductionProcess productionProcess, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
        }
        return ResultUtil.success(productionProcessService.updateOne(productionProcess));
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation(value = "删除")
    public Result<Object> deleteById(@ApiParam(name = "id", value = "产品工序主键") @PathVariable Integer id) {
        productionProcessService.deleteOne(id);
        return ResultUtil.success();
    }

    @GetMapping
    @ApiOperation(value = "查询所有")
    public Result<List<ProductionProcess>> findAll() {
        return ResultUtil.success(productionProcessService.findAll());
    }

    @GetMapping(value = "/pages")
    @ApiOperation(value = "查询所有-分页")
    public Result<PageInfo<ProductionProcess>> findProductionProcessesByPage(@ApiParam(name = "page", value = "页码") @RequestParam(name = "page", defaultValue = "0") Integer page,
                                                                             @ApiParam(name = "size", value = "条目数") @RequestParam(name = "size", defaultValue = "10") Integer size,
                                                                             @ApiParam(name = "orderField", value = "排序属性") @RequestParam(name = "orderField", defaultValue = "id") String orderField,
                                                                             @ApiParam(name = "orderType", value = "排序方法,只能是asc升序或者desc降序") @RequestParam(name = "orderType", defaultValue = "desc") String orderType) {
        return ResultUtil.success(productionProcessService.findAllByPage(page, size, orderField, orderType));
    }

    @GetMapping(value = "/pagesNameLike")
    @ApiOperation(value = "查询所有-名称模糊-分页")
    public Result<PageInfo<ProductionProcess>> findProductionProcessesByNameLikeByPage(@ApiParam(name = "name", value = "产品工序名称") @RequestParam String name,
                                                                                       @ApiParam(name = "page", value = "页码") @RequestParam(name = "page", defaultValue = "0") Integer page,
                                                                                       @ApiParam(name = "size", value = "条目数") @RequestParam(name = "size", defaultValue = "10") Integer size,
                                                                                       @ApiParam(name = "orderField", value = "排序属性") @RequestParam(name = "orderField", defaultValue = "id") String orderField,
                                                                                       @ApiParam(name = "orderType", value = "排序方法,只能是asc升序或者desc降序") @RequestParam(name = "orderType", defaultValue = "desc") String orderType) {
        return ResultUtil.success(productionProcessService.findAllByName(name, page, size, orderField, orderType));
    }

    @DeleteMapping
    @ApiOperation(value = "批量删除")
    public Result<Object> deleteByIds(@ApiParam(name = "ids", value = "主键数组") @RequestParam Integer[] ids) {
        productionProcessService.deleteMore(Arrays.asList(ids));
        return ResultUtil.success();
    }

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "查找")
    public Result<Object> getById(@ApiParam(name = "id", value = "产品工序主键") @PathVariable Integer id) {
        if (productionProcessService.getById(id) == null) {
            return ResultUtil.error("此id=" + id + "不存在,查找失败");
        } else {
            return ResultUtil.success(productionProcessService.getById(id));
        }
    }
}
