package com.jinchi.common.controller;

import com.jinchi.common.dto.PageBean;
import com.jinchi.common.dto.Result;
import com.jinchi.common.dto.quality.purchase.PurchaseRecordHeadDTO;
import com.jinchi.common.service.PurchaseReportRecordService;
import com.jinchi.common.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.Map;

/**
 * @author：XudongHu
 * @className:PurchaseReportRecordController
 * @description: 进货检测
 * @date:16:23 2018/12/29
 */
@RestController
@RequestMapping(value = "/purchaseReportRecord")
@Api(tags = "质量流程-数据录入-进货检验")
public class PurchaseReportRecordController {
    @Autowired
    private PurchaseReportRecordService purchaseReportRecordService;


    /**
     * 查询待发布数据-分页
     * @param personName 创建人名称
     * @param isGenerate 是否为已生成数据
     * @param pageBean   分页实体
     * @return
     */
    @ApiOperation(value = "待生成数据-分页",notes = "根据创建人名称模糊,0为未生成 不传为所有")
    @GetMapping(value = "/rawPages")
    public Result<PageBean> rawPages(@ApiParam(name = "personName",value = "送样人名称") @RequestParam(required = false) String personName,
                                     @ApiParam(name = "isGenerate",value = "0为未生成的 不传为所有") @RequestParam(required = false) Integer isGenerate,PageBean pageBean){
        return ResultUtil.success(purchaseReportRecordService.allPurchaseRaw(personName,isGenerate,pageBean));
    }


    /**
     * 生成进货检验报告单 预览
     * 一次确认
     * @param batchNumberIds
     * @return
     */
    @ApiOperation(value = "生成预览",notes = "根据多个批号")
    @PostMapping(value = "/preview")
    public Result<Map<Object,Object>> previewPurchase(@ApiParam(value = "批号ids",name = "batchNumberIds") @RequestParam Integer[] batchNumberIds){
        return ResultUtil.success(purchaseReportRecordService.previewPurchase(Arrays.asList(batchNumberIds)));
    }

    /**
     * 生成进货检测报告单
     * 二次确认
     * @param batchNumberIds 批号ids
     * @param createPersonId 创建人id
     * @return
     */
    @ApiOperation(value = "生成",notes = "根据多个批号和创建人Id")
    @PostMapping(value = "/generate")
    public Result<String> generatePurchase(@ApiParam(value = "批号ids",name = "batchNumberIds") @RequestParam Integer[] batchNumberIds,
                                           @ApiParam(value = "创建人id",name = "createPersonId") @RequestParam Integer createPersonId){
        return ResultUtil.success(purchaseReportRecordService.generatePurchase(Arrays.asList(batchNumberIds),createPersonId));
    }

    /**
     * 进货数据-分页
     * @param materialName
     * @param pageBean
     * @return
     */
    @ApiOperation(value = "进货数据-分页",notes = "材料名称模糊")
    @GetMapping(value = "/purchasePages")
    public Result<PageBean> allPurchase(@ApiParam(value = "材料名称",name = "materialName") @RequestParam(required = false) String materialName, PageBean pageBean){
        return ResultUtil.success(purchaseReportRecordService.allPurchase(materialName,pageBean));
    }

    /**
     * 进货详情
     * @param batchNumberId 批号id
     * @return
     */
    @ApiOperation(value = "进货详情",notes = "根据批号id")
    @GetMapping
    public Result<PurchaseRecordHeadDTO> purchaseDetail(@ApiParam(value = "批号id",name = "batchNumberId") @RequestParam Integer batchNumberId){
        return ResultUtil.success(purchaseReportRecordService.byBatchNumberId(batchNumberId));
    }

    /**
     * 编辑进货
     * @param purchaseRecordHeadDTO
     * @return
     */
    @ApiOperation(value = "编辑")
    @PutMapping
    public Result<Integer> purchaseUpdate(@Valid @RequestBody PurchaseRecordHeadDTO purchaseRecordHeadDTO){
        return ResultUtil.success(purchaseReportRecordService.updatePurchase(purchaseRecordHeadDTO));
    }

    /**
     * 待发布数据-分页
     * @param personName 创建人名称
     * @param pageBean 分页实体
     * @return
     */
    @ApiOperation(value = "待发布数据-分页")
    @GetMapping(value = "/releasePages")
    public Result<PageBean> releasePages(@ApiParam(value = "创建人名称",name = "personName") @RequestParam(required = false) String personName, PageBean pageBean){
        return ResultUtil.success(purchaseReportRecordService.allRelease(personName,pageBean));
    }

    /**
     * 发布
     * @param batchNumberId 批号id
     * @return
     */
    @ApiOperation(value = "发布")
    @PutMapping(value = "/{batchNumberId}")
    public Result<String> release(@ApiParam(value = "批号id",name = "batchNumberId") @PathVariable Integer batchNumberId){
        return ResultUtil.success(purchaseReportRecordService.release(batchNumberId));
    }

    /**
     * 根据批号id删除
     * @param batchNumberId
     * @return
     */
    @ApiOperation(value = "删除",notes = "根据批号id")
    @DeleteMapping(value = "/{batchNumberId}")
    public Result<Object> deleteByBatchNumberId(@ApiParam(value = "批号id",name = "batchNumberId") @PathVariable Integer batchNumberId){
        purchaseReportRecordService.deleteByBatchNumberId(batchNumberId);
        return ResultUtil.success();
    }

}
