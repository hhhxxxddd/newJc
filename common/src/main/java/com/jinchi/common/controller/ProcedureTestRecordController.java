package com.jinchi.common.controller;

import com.github.pagehelper.PageInfo;
import com.jinchi.common.dto.CommonBatchNumberDTO;
import com.jinchi.common.dto.ProcedureTestRecordDTO;
import com.jinchi.common.dto.Result;
import com.jinchi.common.service.ProcedureTestRecordService;
import com.jinchi.common.utils.ResultUtil;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

/**
 * @author：XudongHu
 * @className:ProcedureTestRecordContoller
 * @description: 制程检验接口
 * @date:17:29 2018/11/23
 */
@RestController
@RequestMapping(value = "/procedureTestRecord")
@Api(tags = "质量流程-数据录入-制程检验")
public class ProcedureTestRecordController {
    @Autowired
    private ProcedureTestRecordService procedureTestRecordService;

    /**
     * 新增
     *
     * @param batchProcedureTestRecord
     * @return
     */
    @PostMapping
    @ApiOperation(value = "新增")
    public Result<CommonBatchNumberDTO> add(@ApiParam(examples = @Example(value = {@ExampleProperty(mediaType = "application/json", value = "test")})) @Valid @RequestBody CommonBatchNumberDTO<List<ProcedureTestRecordDTO>> batchProcedureTestRecord) {
        return ResultUtil.success(procedureTestRecordService.add(batchProcedureTestRecord));
    }

    /**
     * 更新
     *
     * @param batchProcedureTestRecord
     * @return
     */
    @PutMapping
    @ApiOperation(value = "更新")
    public Result<CommonBatchNumberDTO> update(@Valid @RequestBody CommonBatchNumberDTO<List<ProcedureTestRecordDTO>> batchProcedureTestRecord) {
        return ResultUtil.success(procedureTestRecordService.update(batchProcedureTestRecord));
    }

    /**
     * 删除
     *
     * @param batchNumberId 批号id
     * @return
     */
    @DeleteMapping(value = "/{batchNumberId}")
    @ApiOperation(value = "删除", notes = "根据批号id")
    public Result<Object> deleteByBatchNumberId(@ApiParam(value = "批号id", name = "batchNumberId") @PathVariable Integer batchNumberId) {
        procedureTestRecordService.deleteByBatchNumberId(batchNumberId);
        return ResultUtil.success();
    }

    /**
     * 批量删除
     *
     * @param batchNumberIds 批号ids
     * @return
     */
    @DeleteMapping
    @ApiOperation(value = "批量删除", notes = "根据批号ids")
    public Result<Object> deleteByBatchNumberIds(@ApiParam(value = "批号ids") @RequestParam Integer[] batchNumberIds) {
        procedureTestRecordService.deleteByBatchNumberIds(Arrays.asList(batchNumberIds));
        return ResultUtil.success();
    }

    /**
     * 根据批号id查询
     *
     * @param
     * @return
     */
    @GetMapping(value = "/{batchNumberId}")
    @ApiOperation(value = "查询", notes = "根据批号id")
    public Result<CommonBatchNumberDTO> getByBatchNumberId(@ApiParam(value = "批号id", name = "batchNumberId") @PathVariable Integer batchNumberId) {
        return ResultUtil.success(procedureTestRecordService.findByBatchNumberId(batchNumberId));
    }

    /**
     * 查询所有
     *
     * @return
     */
//    @PreAuthorize("hasRole(\"ROLE_ADMIN\") or hasRole(\"ROLE_AUTH_DEPARTMENT_QUERY\")")
    @GetMapping
    @ApiOperation(value = "查询所有")
    public Result<List<CommonBatchNumberDTO>> getAll() {
        return ResultUtil.success(procedureTestRecordService.findAll());
    }

    /**
     * 查询所有-分页
     *
     * @param personName 创建人名称
     * @param page       页码
     * @param size       条目数
     * @param orderField 排序属性
     * @param orderType  排序方法
     * @return
     */
    @GetMapping(value = "/pages")
    @ApiOperation(value = "查询所有-分页")
    public Result<PageInfo<CommonBatchNumberDTO>> getAllByPage(
            @ApiParam(value = "创建人名称", name = "personName") @RequestParam(required = false) String personName,
            @ApiParam(value = "产线（送样工厂id）" ,name = "newId") @RequestParam(required =  false) Integer newId,
            @ApiParam(value = "页码", name = "page") @RequestParam(name = "page", defaultValue = "0") Integer page,
            @ApiParam(value = "条目数", name = "size") @RequestParam(name = "size", defaultValue = "10") Integer size,
            @ApiParam(value = "排序属性", name = "orderField") @RequestParam(name = "orderField", defaultValue = "id") String orderField,
            @ApiParam(value = "排序方法,只能是asc升序或者desc降序", name = "orderType") @RequestParam(name = "orderType", defaultValue = "desc") String orderType) {
        return ResultUtil.success(procedureTestRecordService.findAllByPage(personName, newId,page, size, orderField, orderType));
    }


    /**
     * 迭代
     *
     * @param batchProcedureTestRecord
     * @return
     */
    @PostMapping(value = "/iteration")
    @ApiOperation(value = "迭代", notes = "迭代后会新增,但依旧需要传入需要迭代批号的id")
    public Result<CommonBatchNumberDTO> iteration(@Valid @RequestBody CommonBatchNumberDTO<List<ProcedureTestRecordDTO>> batchProcedureTestRecord) {
        return ResultUtil.success(procedureTestRecordService.iteration(batchProcedureTestRecord));
    }

    /**
     * 级联查询检测项目
     *
     * @param factoryId
     * @param procedureId
     * @param samplePointName
     * @return
     */
    @GetMapping(value = "/testItems")
    @ApiOperation(value = "查询中间品检测项目", notes = "全为空查询所有工厂,第一项不为空查询工厂下所有工序,前两项不为空查询工厂工序下所有取样点,以此类推,全不为空查询检测项目", tags = "质量流程-数据录入-样品送检")
    public Result<Object> queryTestItems(@ApiParam(value = "工厂id", name = "factoryId") @RequestParam(required = false) Integer factoryId,
                                              @ApiParam(value = "工序id", name = "procedureId") @RequestParam(required = false) Integer procedureId,
                                              @ApiParam(value = "取样点", name = "samplePointName") @RequestParam(required = false) String samplePointName) {
        return ResultUtil.success(procedureTestRecordService.queryTestItems(factoryId, procedureId, samplePointName));
    }

}
