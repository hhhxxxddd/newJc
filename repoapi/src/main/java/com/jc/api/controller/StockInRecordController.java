package com.jc.api.controller;

import com.jc.api.entity.form.StockInRecordQueryForm;
import com.jc.api.entity.param.StockInRecordQueryParam;
import com.jc.api.service.restservice.IStockInRecordService;
import com.jinchi.common.core.vo.Result;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

/**
 * @author XudongHu
 * @apiNote 入库界面controller
 * @className StockInRecordController
 * @modifier
 * @since 2019/11/1日18:35
 */
@RestController
@RequestMapping(value = "/inRecord")
@Api(tags = "(废弃)智能仓库-入库记录界面")
@Slf4j
public class StockInRecordController {
    @Autowired
    private IStockInRecordService IStockInRecordService;

    @ApiOperation(value = "仅供测试使用-入库(模仿新松入库)", notes = "前端不能入库,此接口做测试用")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "materialCode", value = "物料编码", example = "MC/BN180808-0-RAW(TS)-Fe-1-QDBX-60KG", required = true, dataType = "String"),
            @ApiImplicitParam(name = "createdPerson", value = "入库人", example = "胡旭东", required = true, dataType = "String")}
    )
    @ApiResponses(@ApiResponse(code = 200, message = "处理成功", response = Result.class))
    @PostMapping(value = "/in")
    public Result in(@RequestParam String materialCode, @RequestParam String createdPerson) {
        return Result.success(IStockInRecordService.insert(materialCode, createdPerson));
    }


    @ApiOperation(value = "获取入库记录", notes = "获取所有入库记录")
    @ApiResponses(@ApiResponse(code = 200, message = "处理成功", response = Result.class))
    @GetMapping(value = "/all")
    public Result all() {
        return Result.success(IStockInRecordService.getAll(new StockInRecordQueryParam()));
    }

    @ApiOperation(value = "获取入库记录-条件", notes = "条件获取入库记录")
    @ApiImplicitParam(name = "stockInRecordQueryForm", value = "查询条件", required = true, dataType = "StockInRecordQueryForm")
    @ApiResponses(@ApiResponse(code = 200, message = "处理成功", response = Result.class))
    @PostMapping(value = "/conditions")
    public Result query(@Valid @RequestBody StockInRecordQueryForm stockInRecordQueryForm) {
        log.debug("条件获取入库记录:{}", stockInRecordQueryForm);
        return Result.success(IStockInRecordService.getAll(stockInRecordQueryForm.toParam(StockInRecordQueryParam.class)));
    }

    @ApiOperation(value = "获取入库记录-条件/分页", notes = "条件获取入库记录")
    @ApiImplicitParam(name = "stockInRecordQueryForm", value = "查询条件", required = true, dataType = "StockInRecordQueryForm")
    @ApiResponses(@ApiResponse(code = 200, message = "处理成功", response = Result.class))
    @PostMapping(value = "/pages")
    public Result queryPages(@Valid @RequestBody StockInRecordQueryForm stockInRecordQueryForm) {
        log.debug("条件获取入库记录:{}", stockInRecordQueryForm);
        return Result.success(IStockInRecordService.getAllByPage(stockInRecordQueryForm.getPage(), stockInRecordQueryForm.toParam(StockInRecordQueryParam.class)));
    }

    @ApiOperation(value = "仅供测试使用-删除入库记录", notes = "根据id删除入库记录")
    @ApiImplicitParam(paramType = "path", name = "id", value = "入库记录id", required = true, dataType = "long")
    @ApiResponses(@ApiResponse(code = 200, message = "处理成功", response = Result.class))
    @DeleteMapping(value = "/{id}")
    public Result delete(@PathVariable String id) {
        log.debug("根据id删除入库记录:{}", id);
        return Result.success(IStockInRecordService.delete(id));
    }

    @ApiOperation(value = "仅供测试使用-批量删除入库记录", notes = "根据主键数组批量删除入库记录")
    @ApiImplicitParam(paramType = "path", name = "ids", value = "主键集合", required = true, allowMultiple = true, dataType = "String")
    @ApiResponses(@ApiResponse(code = 200, message = "处理成功", response = Result.class))
    @DeleteMapping(value = "/batch/{ids}")
    public Result deleteBatch(@PathVariable Set<String> ids) {
        log.debug("根据ids批量删除入库记录:{}", ids);
        return Result.success(IStockInRecordService.deleteBatch(ids));
    }
}
