package com.jc.api.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jc.api.entity.SwmsBasicInactionStockDeadline;
import com.jc.api.entity.SwmsBasicMaterialSubType;
import com.jc.api.service.restservice.ISwmsBasicInactionStockDeadlineService;
import com.jc.api.service.restservice.imp.SwmsBasicInactionStockDeadlineService;
import com.jinchi.common.core.vo.Result;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.Set;

/**
 * @Auther: River
 * @Date: 2020/1/12 18:42
 * @Description:
 */
@RestController
@Api(tags = "智能仓库-呆滞期限信息管理界面")
@RequestMapping(value = "/swmsBasicInactionStockDeadline")
@Slf4j
public class SwmsBasicInactionStockDeadlineController {

    @Autowired
    private ISwmsBasicInactionStockDeadlineService swmsBasicInactionStockDeadlineService;

    @ApiOperation(value = "获取所有呆滞期限", notes = "获取所有呆滞期限")
    @ApiResponses(@ApiResponse(code = 200, message = "处理成功", response = Result.class))
    @GetMapping(value = "/getAll")
    public Result getAll(){
        return Result.success(swmsBasicInactionStockDeadlineService.getAll(""));
    }

    @ApiOperation(value = "获取呆滞期限-条件/分页", notes = "条件获取呆滞期限记录-分页")
    @ApiResponses(@ApiResponse(code = 200, message = "处理成功", response = Result.class))
    @PostMapping(value = "/pages")
    public Result queryPages(@RequestBody Page page, @RequestParam(required = false) String subTypeName) {
        return Result.success(swmsBasicInactionStockDeadlineService.getAllByPage(page,subTypeName));
    }

    @ApiOperation(value = "新增呆滞期限信息", notes = "新增")
    @ApiResponses(@ApiResponse(code = 200, message = "处理成功", response = Result.class))
    @PostMapping(value = "/add")
    public Result add(@Valid @RequestBody SwmsBasicInactionStockDeadline swmsBasicInactionStockDeadline) {
        return Result.success(swmsBasicInactionStockDeadlineService.add(swmsBasicInactionStockDeadline));
    }

    @ApiOperation(value = "更新呆滞期限信息", notes = "更新")
    @ApiResponses(@ApiResponse(code = 200, message = "处理成功", response = Result.class))
    @PutMapping(value = "/{id}")
    public Result update(@RequestBody SwmsBasicInactionStockDeadline swmsBasicInactionStockDeadline) {
        return Result.success(swmsBasicInactionStockDeadlineService.update(swmsBasicInactionStockDeadline));
    }

    @ApiOperation(value = "删除呆滞期限信息", notes = "根据id删除呆滞期限信息")
    @ApiImplicitParam(name = "id", value = "物料子类型信息id", required = true, dataType = "long")
    @ApiResponses(@ApiResponse(code = 200, message = "处理成功", response = Result.class))
    @DeleteMapping(value = "/{id}")
    public Result delete(@PathVariable Integer id) {
        log.debug("根据id删除呆滞期限记录:{}", id);
        return Result.success(swmsBasicInactionStockDeadlineService.delete(id));
    }

    @ApiOperation(value = "批量删除呆滞期限信息",notes = "根据id集合删除")
    @DeleteMapping(value = "/batchDelete")
    public Result batchDelete(@RequestParam Set<String> ids){
        return Result.success(swmsBasicInactionStockDeadlineService.batchDelete(ids));
    }
}
