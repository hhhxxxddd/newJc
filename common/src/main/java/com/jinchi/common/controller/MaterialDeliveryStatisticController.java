package com.jinchi.common.controller;

import com.jinchi.common.domain.MaterialDeliveryStatisticHead;
import com.jinchi.common.dto.MaterialDeliveryCommitDTO;
import com.jinchi.common.dto.Result;
import com.jinchi.common.service.MaterialDeliveryStatisticService;
import com.jinchi.common.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: LiuTaoYi
 * @create_time: 2019-11-04 12:46
 * @description:
 **/
@RestController
@RequestMapping(value = "/materialDeliveryStatistic")
@Api(tags = "前驱体成本核算管理-原料领用")
public class MaterialDeliveryStatisticController {
    @Autowired
    MaterialDeliveryStatisticService statisticService;

    @GetMapping(value = "period")
    @ApiOperation(value = "获取期数")
    public Result getPeriod(@RequestParam Integer periodCode) {
        return ResultUtil.success(statisticService.getPeriod(periodCode));
    }

    @GetMapping(value = "uncommitted")
    @ApiOperation(value = "获取待提交数据")
    public Result getUncommitted(@RequestParam(required = false) Integer periodCode, @RequestParam(required = false) String startTime,
                                 @RequestParam(required = false) String endTime,@RequestParam(required = false,defaultValue = "1") Integer page, @RequestParam(required = false,defaultValue = "10") Integer size) {
        return ResultUtil.success(statisticService.queryUncommitted(startTime, endTime, periodCode, page, size));
    }

    //String startTime, String endTime, Integer periodCode, Integer page, Integer size
    @GetMapping(value = "statistic")
    @ApiOperation(value = "获取已统计数据")
    public Result getStatistic(@RequestParam(required = false) Integer periodCode, @RequestParam(required = false) String startTime,
                               @RequestParam(required = false) String endTime, @RequestParam(required = false,defaultValue = "1") Integer page, @RequestParam(required = false,defaultValue = "10") Integer size) {
        return ResultUtil.success(statisticService.queryStatistics(startTime, endTime, periodCode, page, size));
    }

    @PostMapping(value = "add")
    @ApiOperation(value = "新增")
    public Result add(@RequestBody MaterialDeliveryStatisticHead head) {
        return ResultUtil.success(statisticService.add(head));
    }

    @GetMapping(value = "stockOutData")
    @ApiOperation(value = "获取出库数据")
    public Result getStockOutData(@RequestParam String startTime, @RequestParam String endTime) {
        return ResultUtil.success(statisticService.getStockOutData(startTime, endTime));
    }

    @GetMapping(value = "lastPeriodConcentrations")
    @ApiOperation(value = "获取上期浓度")
    public Result getStockOutData(@RequestParam Integer periodCode) {
        return ResultUtil.success(statisticService.getConcentrations(periodCode));
    }

    @GetMapping(value = "supplementary")
    @ApiOperation(value = "获取补料")
    public Result getSupplementary(@RequestParam Integer periodCode) {
        return ResultUtil.success(statisticService.getSupplementary(periodCode));
    }

    @GetMapping(value = "detail")
    @ApiOperation(value = "获取待提交详情")
    public Result getDetailByCode(@RequestParam Long statisticCode) {
        return ResultUtil.success(statisticService.getDetailByCode(statisticCode));
    }

    @GetMapping(value = "statDetail")
    @ApiOperation(value = "获取已统计详情")
    public Result getStatDetailByCode(@RequestParam Long statisticCode, @RequestParam Integer materialTypeCode) {
        return ResultUtil.success(statisticService.getStatisticDetail(statisticCode, materialTypeCode));
    }

    @PostMapping(value = "save")
    @ApiOperation(value = "保存或提交")
    public Result save(@RequestBody MaterialDeliveryCommitDTO commitDTO) {
        statisticService.commit(commitDTO);
        return ResultUtil.success();
    }

    @DeleteMapping(value = "deleteByStatCode")
    @ApiOperation(value = "删除待提交记录")
    public Result deleteById(@RequestParam Long statisticCode) {
        statisticService.deleteUnsubmit(statisticCode);
        return ResultUtil.success();
    }


    @GetMapping(value = "byLineStat")
    @ApiOperation(value = "按产线统计")
    public Result getStatLineInfo(@RequestParam(required = false) Integer periodCode, @RequestParam(required = false) String startTime) {
        return ResultUtil.success(statisticService.listByProductionLine(periodCode, startTime));
    }

    //Integer periodCode, Integer lineCode, String startTime, String endTime
    @GetMapping(value = "periodCompare")
    @ApiOperation(value = "周期对比曲线")
    public Result periodCompareCurve(@RequestParam Integer periodCode, @RequestParam Integer lineCode, @RequestParam String startTime, @RequestParam String endTime) {
        return ResultUtil.success(statisticService.periodCompareCurve(periodCode, lineCode, startTime, endTime));
    }

    //Integer periodCode,Integer periodNum, Integer[] lineCodes
    @PostMapping(value = "lineCompare")
    @ApiOperation(value = "产线对比曲线")
    public Result lineCompareCurve(@RequestParam Integer periodCode, @RequestParam Integer periodNum, @RequestBody Integer[] lineCodes) {
        return ResultUtil.success(statisticService.lineCompareCurve(periodCode, periodNum, lineCodes));
    }

    @GetMapping(value = "getDate")
    @ApiOperation(value = "根据周期类型查找开始时间")
    public Result getDate(@RequestParam Integer periodId){
        return ResultUtil.success(statisticService.getDate(periodId));
    }

    @GetMapping(value = "getPeriodAndTime")
    @ApiOperation(value = "期数和时间")
    public Result getPeriodAndTime(@RequestParam Integer periodId) {
        return ResultUtil.success(statisticService.getPeriodAndTime(periodId));
    }
}
