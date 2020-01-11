package com.jc.api.controller;

import com.jc.api.entity.dto.MatDeliDTO;
import com.jc.api.entity.form.StockOutRecordHeadForm;
import com.jc.api.entity.form.StockOutRecordHeadQueryForm;
import com.jc.api.entity.form.StockOutRecordHeadUpdateForm;
import com.jc.api.entity.param.StockOutRecordHeadQueryParam;
import com.jc.api.entity.po.StockOutRecordHead;
import com.jc.api.service.restservice.IStockOutRecordHeadService;
import com.jc.api.service.restservice.IStockOutRecordService;
import com.jinchi.common.core.vo.Result;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author：XudongHu
 * @className: StockOutRecordHeadController
 * @description: 出库管理界面
 * @date:15:38 2019/3/31
 * @Modifer:
 */
@RestController
@RequestMapping(value = "/outManage")
@Api(tags = "智能仓库-出库管理界面")
@Slf4j
public class StockOutRecordHeadController {
    @Autowired
    private IStockOutRecordHeadService iStockOutRecordHeadService;
    @Autowired
    private IStockOutRecordService iStockOutRecordService;


    @ApiOperation(value = "新增出库计划",notes = "待出库和已出库的数据无法新增")
    @ApiResponses(@ApiResponse(code = 200, message = "处理成功", response = Result.class))
    @ApiImplicitParam(name = "stockOutRecordHeadForm", value = "新增表单", required = true, dataType = "StockOutRecordHeadForm")
    @PostMapping(value = "/add")
    public Result outPlan(@Valid @RequestBody StockOutRecordHeadForm stockOutRecordHeadForm) {
        log.info("新增出库计划====================>{}",stockOutRecordHeadForm);
        return Result.success(iStockOutRecordHeadService.repoOut(stockOutRecordHeadForm.toPo(StockOutRecordHead.class),stockOutRecordHeadForm.getRecords()));
    }

    @ApiOperation(value = "更新出库计划",notes = "仅能更新保存数据")
    @ApiResponses(@ApiResponse(code = 200, message = "处理成功", response = Result.class))
    @ApiImplicitParam(name = "stockOutRecordHeadUpdateForm", value = "更新表单", required = true, dataType = "StockOutRecordHeadUpdateForm")
    @PutMapping(value = "/update")
    public Result update(@RequestBody StockOutRecordHeadUpdateForm stockOutRecordHeadUpdateForm) {
        log.info("更新出库计划====================>{}",stockOutRecordHeadUpdateForm);
        return Result.success(iStockOutRecordHeadService.update(stockOutRecordHeadUpdateForm.toPo(StockOutRecordHead.class)));
    }

    @ApiOperation(value = "查询出库计划详情",notes = "因为审核需要根据批号Id查询,所以提供两种方式查询")
    @ApiResponses(@ApiResponse(code = 200, message = "处理成功", response = Result.class))
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "出库计划主键/批号id", required = true, dataType = "Long",example = "1"),
            @ApiImplicitParam(name = "type", value = "是否根据主键查询", required = true, dataType = "String",defaultValue = "true",example = "true"),})
    @GetMapping(value = "/{id}")
    public Result detail(@PathVariable Integer id,@RequestParam boolean type) {
        return Result.success(iStockOutRecordHeadService.byId(id,type));
    }

    @ApiOperation(value = "删除出库计划",notes = "仅能删除未提交/审核不通过的数据")
    @ApiResponses(@ApiResponse(code = 200, message = "处理成功", response = Result.class))
    @ApiImplicitParam(type = "path",name = "id", value = "出库计划主键", required = true, dataType = "Long",example = "1")
    @DeleteMapping(value = "/{id}")
    public Result delete(@PathVariable Integer id) {
        return Result.success(iStockOutRecordHeadService.delete(id));
    }

    @ApiOperation(value = "查询所有出库计划-条件/分页")
    @ApiResponses(@ApiResponse(code = 200, message = "处理成功", response = Result.class))
    @ApiImplicitParam(name = "stockOutRecordHeadQueryForm", value = "查询条件", required = true, dataType = "StockOutRecordHeadQueryForm")
    @PostMapping(value = "/pages")
    public Result pages(@RequestBody StockOutRecordHeadQueryForm stockOutRecordHeadQueryForm) {
        return Result.success(iStockOutRecordHeadService.pages(stockOutRecordHeadQueryForm.getPage(), stockOutRecordHeadQueryForm.toParam(StockOutRecordHeadQueryParam.class)));
    }

    @ApiOperation(value = "仅供测试使用-向新松发送出库计划/审核响应",notes = "status改为2=>审核通过=>发送给新松")
    @PostMapping(value = "/outStart")
    public Boolean outStart(@RequestParam Integer applicationFormId,@RequestParam Integer status) {
        return iStockOutRecordHeadService.outStart(applicationFormId,status);
    }

    @ApiOperation(value = "仅供测试使用-出库上报(模仿新松上报)")
    @PostMapping(value = "/outPost")
    public Boolean outPost(@RequestParam(defaultValue = "出库单号") String stockOutHeadRecordCode, @RequestParam(defaultValue = "物料编码") String materialCode) {
        iStockOutRecordHeadService.outPost(stockOutHeadRecordCode,materialCode);
        return true;
    }

    @ApiOperation(value = "仅供测试使用-出库完成上报(模仿新松出库完成上报)")
    @PostMapping(value = "/outFinished")
    public Boolean outFinished(@RequestParam(defaultValue = "出库单号") String stockOutHeadRecordCode) {
        iStockOutRecordHeadService.outFinished(stockOutHeadRecordCode);
        return true;
    }

    @PostMapping(value = "/matOut")
    @ApiOperation(value = "获取出库信息--原料领用")
    public Result matOut(@RequestParam String startTime,@RequestParam String endTime,@RequestBody List<String> matName){
        List<MatDeliDTO> matDeliDTOS = iStockOutRecordService.dateForMaterialDelivery(startTime, endTime, matName);
        return Result.success(matDeliDTOS);
    }
}
