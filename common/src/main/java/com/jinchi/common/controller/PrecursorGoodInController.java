package com.jinchi.common.controller;

import com.jinchi.common.domain.BasicInfoPrecursorMaterialDetails;
import com.jinchi.common.dto.BasicInfoPrecursorMaterialDetailsDTO;
import com.jinchi.common.dto.GoodInTableDTO;
import com.jinchi.common.dto.Result;
import com.jinchi.common.service.PrecursorGoodInService;
import com.jinchi.common.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/goodIn")
@Api(tags = "前躯体-在制品统计")
public class PrecursorGoodInController {

    @Autowired
    PrecursorGoodInService goodInService;

    @GetMapping(value = "page")
    @ApiOperation(value= "分页查询")
    public Result page(@RequestParam(required = false,defaultValue = "") String startTime,
                       @RequestParam(required = false,defaultValue = "") String endTime,
                       @RequestParam(required = false) Integer periodId,
                       @RequestParam(required = false,defaultValue = "1") Integer page,
                       @RequestParam(required = false,defaultValue = "10") Integer size){
        return ResultUtil.success(goodInService.page(startTime,endTime,periodId,page,size));
    }

    @GetMapping(value = "addComfirm")
    @ApiOperation(value = "新增-确认")
    public Result addComfirm(@RequestParam Integer periodId,
                             @RequestParam Integer lineName,
                             @RequestParam String startTime,
                             @RequestParam String endTime){
        return ResultUtil.success(goodInService.addComfirm(periodId,lineName,startTime,endTime));
    }

    @PostMapping(value = "/afterComfirm")
    @ApiOperation(value = "新增确认后")
    public Result afterComfirm(){
        return ResultUtil.success(goodInService.getTables(null));
    }

    @GetMapping(value = "commitDetail")
    @ApiOperation(value = "待提交详情")
    public Result commitDetail(@RequestParam(required = false)Long stasticId){
        return ResultUtil.success(goodInService.getTables(stasticId));
    }

    @GetMapping(value = "/getLastPotencyByProcessId")
    @ApiOperation(value = "根据工序id获取上期浓度")
    public Result getLastMonPotency(@RequestParam Integer processId){
        return ResultUtil.success(goodInService.getLastPotency(processId));
    }

    @PostMapping(value = "/saveOrCommit")
    @ApiOperation(value = "保存/提交")
    public Result saveOrCommit(@RequestParam Long statisticId,
                               @ApiParam(value = "保存：0，提交：1") @RequestParam Integer flag,
                               @RequestBody GoodInTableDTO goodInTableDTO){
        return ResultUtil.success(goodInService.saveOrCommit(statisticId,flag,goodInTableDTO));
    }

    @GetMapping(value = "statisticPage")
    @ApiOperation(value = "已统计分页查询")
    public Result statisticPage(@RequestParam(required = false,defaultValue = "") String startTime,
                                @RequestParam(required = false,defaultValue = "") String endTime,
                                @RequestParam Integer periodId,
                                @RequestParam(required = false,defaultValue = "1") Integer page,
                                @RequestParam(required = false,defaultValue = "10") Integer size){
        return ResultUtil.success(goodInService.statisticPage(startTime,endTime,periodId,page,size));
    }

    @GetMapping(value = "statisticDetail")
    @ApiOperation(value = "已统计详情")
    public Result statisticdeDetail(@RequestParam Long processDetailId){
        return ResultUtil.success(goodInService.statisticDetail(processDetailId));
    }

    @GetMapping(value = "analysisProcess")
    @ApiOperation(value = "统计分析-按工序统计")
    public Result analysisProcess(@RequestParam Integer periodId,
                                  @RequestParam String startTime){
        return ResultUtil.success(goodInService.analysisProcess(periodId,startTime));
    }

    @GetMapping(value = "analysisLine")
    @ApiOperation(value = "统计分析-按产线统计")
    public Result analysisLine(@RequestParam Integer periodId,
                                  @RequestParam String startTime){
        return ResultUtil.success(goodInService.analysisLine(periodId,startTime));
    }

    @GetMapping(value = "processCompare")
    @ApiOperation(value = "统计分析-工序对比")
    public Result processCompare(@RequestParam Integer periodId,
                                 @RequestParam Integer processId,
                                 @RequestParam String startTime,
                                 @RequestParam String endTime){
        return ResultUtil.success(goodInService.processCompare(periodId,processId,startTime,endTime));
    }

    @GetMapping(value = "lineCompare")
    @ApiOperation(value = "统计分析-产线对比")
    public Result lineCompare(@RequestParam Integer periodId,
                                 @RequestParam Integer lineId,
                                 @RequestParam String startTime,
                                 @RequestParam String endTime){
        return ResultUtil.success(goodInService.lineCompare(periodId,lineId,startTime,endTime));
    }

    @GetMapping(value = "getLineNameByPeriod")
    @ApiOperation(value = "根据周期类型id获取期数")
    public Result getLineNameByPeriod(@RequestParam Integer periodId){
        return ResultUtil.success(goodInService.getLineNameByPeriod(periodId));
    }

    @GetMapping(value = "getStartTime")
    @ApiOperation(value = "获取统计周期内存在的开始时间")
    public Result getStartTime(@RequestParam Integer periodId) {
        return ResultUtil.success(goodInService.getStartTime(periodId));
    }

    @DeleteMapping
    @ApiOperation(value = "删除")
    public Result delete(@RequestParam Long stasticId){
        goodInService.delete(stasticId);
        return ResultUtil.success();
    }

    @GetMapping(value = "getVolumeWeight")
    @ApiOperation(value = "获取体积/重量值")
    public Result getVolumeWeight(@RequestParam Integer processId){
        return ResultUtil.success(goodInService.getVolumeWeight(processId));
    }

    @GetMapping(value  = "defaultPage")
    @ApiOperation(value = "已统计默认")
    public Result defaultPage(){
        return ResultUtil.success(goodInService.defaultPage());
    }


    @GetMapping(value = "getByLineByProcess")
    @ApiOperation(value = "根据产线，工序，工艺参数获取数据")
    public Result getByLineByProcess(@RequestParam Integer lineCode,
                                     @RequestParam Integer processCode,
                                     @RequestParam Long paramId,
                                     @RequestBody List<BasicInfoPrecursorMaterialDetailsDTO> mats){
        return ResultUtil.success(goodInService.getByLineByProcess(lineCode,processCode,paramId,mats));
    }
}