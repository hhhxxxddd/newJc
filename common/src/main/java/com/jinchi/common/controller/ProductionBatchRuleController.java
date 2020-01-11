package com.jinchi.common.controller;

import com.jinchi.common.domain.BatchRuleDTO;
import com.jinchi.common.domain.ProductionBatchInfo;
import com.jinchi.common.domain.ProductionBatchRuleDetail;
import com.jinchi.common.domain.ProductionBatchRuleHead;
import com.jinchi.common.dto.ProductionBatchRuleDetailDTO;
import com.jinchi.common.dto.Result;
import com.jinchi.common.service.ProductionBatchRuleService;
import com.jinchi.common.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/productionBatchRule")
@Api(tags = "生产管理-批次规则")
public class ProductionBatchRuleController {
    @Autowired
    private ProductionBatchRuleService productionBatchRuleService;

    @GetMapping
    @ApiOperation(value = "获取批次规则，通过code，没有状态目前")
    public Result<ProductionBatchInfo> getInfoByCode(@ApiParam(value = "详情的编码") @RequestParam Short code) {
        return ResultUtil.success(productionBatchRuleService.getInfoByCode(code));
    }

    @GetMapping("/getDetail")
    @ApiOperation(value = "获取批次规则的详情，通过rule_code")
    public Result<List<ProductionBatchRuleDetail>>  getDetailByRuleCode(@ApiParam(value = "详情的编码") @RequestParam Byte code) {

         return  ResultUtil.success(productionBatchRuleService.getDetailByRuleCode(code));
    }

    @DeleteMapping("/delDetail")
    @ApiOperation(value = "通过code删除一个规则详情", notes = "根据code")
    public Result<Object> deleteByBatchNumberIds(@ApiParam(value = "详情的编码") @RequestParam Short code) {
        productionBatchRuleService.delDetailByRuleCode(code);
        return ResultUtil.success();
    }

    @PostMapping(value = "/addOneDetail")
    @ApiOperation(value = "新增一个规则详情，code不用数据，relucode是Byte型，reluvalue是两位长度string")
    public Result<Object> addOneDetail(@RequestBody @Valid ProductionBatchRuleDetail productionBatchRuleDetail , BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
        }
        System.out.println("dfdf");
        productionBatchRuleService.addOneDetail(productionBatchRuleDetail);
        return ResultUtil.success();
    }

    @PutMapping(value = "/updateAll")
    @ApiOperation(value = "更新所有规则详情，通过code，ruleCode不能动，因为有外键")
    public Result<Object> updateAll(@RequestBody @Valid ProductionBatchRuleDetailDTO productionBatchRuleDetailDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
        }
        return ResultUtil.success(productionBatchRuleService.updateAll(productionBatchRuleDetailDTO.getProductionBatchRuleDetails(), productionBatchRuleDetailDTO.getRuleCode()));
    }

    //firelfy   renew
    @GetMapping(value = "/getAll")
    @ApiOperation(value = "获取所有规则的属性")
    public Result<List<ProductionBatchRuleHead>> getAll(){
        return ResultUtil.success(productionBatchRuleService.getAll());
    }





    @PutMapping("/UpdateState")
    @ApiOperation(value = "更新状态， 主意code没有的，也会成功")
    public Result<Object>  UpdateState(@ApiParam(value = "详情的编码") @RequestParam Byte code,
                                       @ApiParam(value = "详情的编码") @RequestParam Boolean flag ) {
        productionBatchRuleService.UpdateState(code,flag);
        return  ResultUtil.success();
    }

    @GetMapping(value = "/getAllInfos")
    @ApiOperation(value = "获取所有规则的属性")
    public Result<List<BatchRuleDTO>> getAllInfos(){
        return ResultUtil.success(productionBatchRuleService.getAllInfos());
    }

    @PostMapping(value = "genBatchRecord")
    @ApiOperation(value = "根据批次信息，生成批次追溯记录")
    public Result genBatchRecord(@ApiParam(value = "当前用户id") @RequestParam Integer userId,
                               @ApiParam(value = "批次信息") @RequestParam String batch){
        return ResultUtil.success(productionBatchRuleService);
    }
}
