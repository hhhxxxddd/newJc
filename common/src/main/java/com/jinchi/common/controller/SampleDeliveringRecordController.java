package com.jinchi.common.controller;

import com.jinchi.common.dto.PageBean;
import com.jinchi.common.dto.Result;
import com.jinchi.common.dto.SampleDeliveringRecordDTO;
import com.jinchi.common.service.SampleDeliveringRecordService;
import com.jinchi.common.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;


/**
 * @author：XudongHu
 * @className:SampleDeliveringRecordController
 * @description: 样品送检
 * @date:17:33 2018/11/27
 */
@RestController
@RequestMapping(value = "/sampleDeliveringRecord")
@Api(tags = "质量流程-数据录入-样品送检")
public class SampleDeliveringRecordController {
    @Autowired
    private SampleDeliveringRecordService sampleDeliveringRecordService;

    /**
     * 新增
     */
    @PostMapping
    @ApiOperation(value = "新增")
    public Result<SampleDeliveringRecordDTO> add(@Valid @RequestBody SampleDeliveringRecordDTO sampleDeliveringRecordDTO) {
        return ResultUtil.success(sampleDeliveringRecordService.add(sampleDeliveringRecordDTO));
    }

    @PostMapping(value = "/custom")
    @ApiOperation(value = "自定义送检")
    public Result<String> customSample(@ApiParam(value = "检测项目集合") @RequestParam Integer[] ids,
                                       @ApiParam(value = "用户id")@RequestParam Integer userId,
                                       @ApiParam(value = "批次")@RequestParam String batch){
        return ResultUtil.success(sampleDeliveringRecordService.customSample(Arrays.asList(ids),userId,batch));
    }


    /**
     * 更新
     */
    @PutMapping
    @ApiOperation(value = "更新", notes = "只有保存数据可编辑")
    public Result<SampleDeliveringRecordDTO> update(@Valid @RequestBody SampleDeliveringRecordDTO sampleDeliveringRecordDTO) {
        return ResultUtil.success(sampleDeliveringRecordService.update(sampleDeliveringRecordDTO));
    }

    /**
     * 根据id查询
     */
    @GetMapping(value = "/{id}")
    @ApiOperation(value = "查询", notes = "条件接收状态和物料类型都可以为空")
    public Result<SampleDeliveringRecordDTO> getById(@ApiParam(name = "id", value = "主键") @PathVariable Integer id) {
        return ResultUtil.success(sampleDeliveringRecordService.findById(id));
    }


    /**
     * 根据id删除
     */
    @DeleteMapping(value = "/{id}")
    @ApiOperation(value = "删除", notes = "只有保存数据可删除")
    public Result<Object> deleteById(@ApiParam(name = "id", value = "主键") @PathVariable Integer id) {
        sampleDeliveringRecordService.deleteById(id);
        return ResultUtil.success();
    }

    /**
     * 根据ids删除
     */
    @DeleteMapping
    @ApiOperation(value = "批量删除", notes = "只有保存数据可删除")
    public Result<Object> deleteByIds(@ApiParam(value = "主键数组") @RequestBody Integer[] ids) {
        sampleDeliveringRecordService.deleteByIds(Arrays.asList(ids));
        return ResultUtil.success();
    }

    /**
     * 接收或者拒绝一个样品送检
     */
    @PostMapping(value = "/accept")
    @ApiOperation(value = "接受/拒绝", notes = "拒绝必须填写理由")
    public Result<Object> accept(@ApiParam(name = "id", value = "主键") @RequestParam Integer id,
                                 @ApiParam(name = "isAccept", value = "是否接受 2接受 3拒绝") @RequestParam Integer isAccept,
                                 @ApiParam(name = "handleComment", value = "拒绝理由") @RequestParam(required = false) String handleComment) {
        sampleDeliveringRecordService.isAccept(id, isAccept, handleComment);
        return ResultUtil.success();
    }

    /**
     * 查询所有
     *
     * @param acceptStatus 接收状态
     * @param materialType 物料类型
     * @return
     */
    @GetMapping
    @ApiOperation(value = "查询所有", notes = "条件接收状态和物料类型都可以为空")
    public Result<List<SampleDeliveringRecordDTO>> getAll(@ApiParam(name = "acceptStatus", value = "接收状态") @RequestParam(required = false) Integer acceptStatus,
                                                          @ApiParam(name = "materialType", value = "物料类型") @RequestParam(required = false) Integer materialType) {
        return ResultUtil.success(sampleDeliveringRecordService.findAllByAcceptAndType(acceptStatus, materialType));
    }

    /**
     * 查询所有-分页
     */
    @GetMapping(value = "/pages")
    @ApiOperation(value = "查询所有-分页", notes = "条件接收状态和物料类型都可以为空")
    public Result<PageBean<SampleDeliveringRecordDTO>> getAllByPages(@ApiParam(name = "factoryName", value = "送样工厂名称") @RequestParam(required = false) String factoryName, PageBean pageBean) {
        return ResultUtil.success(sampleDeliveringRecordService.findByFactoryNameByPage(factoryName, pageBean));
    }

    /**
     * 查询原材料标准
     * @param serialNumberId 编号id
     * @return
     */
    @GetMapping(value = "/rawStandard")
    @ApiOperation(value = "查询原材料标准",notes = "原材料专用")
    public Result<List<Integer>> rawStandards(@ApiParam(value = "原材料编号id",name = "serialNumberId") @RequestParam Integer serialNumberId ){
        return ResultUtil.success(sampleDeliveringRecordService.rawStandards(serialNumberId));
    }

    /**
     * 新加需求
     * @param batch
     */
    @GetMapping(value = "/count")
    @ApiOperation(value = "统计该批次号数量")
    public Result count(@ApiParam(value = "批次号",name = "batch") @RequestParam String batch ){
        return ResultUtil.success(sampleDeliveringRecordService.count(batch));
    }

    @GetMapping(value = "getPageByBatch")
    @ApiOperation(value = "根据批号模糊分页查询")
    public Result getPageByBatch(@ApiParam(name = "batch", value = "批号") @RequestParam(defaultValue = "") String batch,
                                 @ApiParam(name = "page", value = "页码") @RequestParam(name = "page", defaultValue = "1") Integer page,
                                 @ApiParam(name = "size", value = "条目数") @RequestParam(name = "size", defaultValue = "10") Integer size){
        return ResultUtil.success(sampleDeliveringRecordService.getPageByBatch(batch,page,size));
    }

}