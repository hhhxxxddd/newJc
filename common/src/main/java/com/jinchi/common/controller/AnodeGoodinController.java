package com.jinchi.common.controller;

import com.jinchi.common.domain.AnodeGoodsInProcessStatisticHead;
import com.jinchi.common.dto.Result;
import com.jinchi.common.dto.anode.AnodeGoodinDTO;
import com.jinchi.common.service.AnodeGoodinService;
import com.jinchi.common.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "anodeGoodin")
@Api(tags = "正极成本-在制品管理")
public class AnodeGoodinController {

    @Autowired
    AnodeGoodinService anodeGoodinService;

    @PostMapping(value = "addComfirm")
    @ApiOperation(value = "新增确认")
    public Result addComfirm(@RequestBody AnodeGoodsInProcessStatisticHead head) {
        return ResultUtil.success(anodeGoodinService.addComfirm(head));
    }

    @PostMapping(value = "afterComfirm")
    @ApiOperation(value = "新增确认后，编辑")
    public Result afterComfirm(@RequestParam Long id) {
        return ResultUtil.success(anodeGoodinService.afterComfirm(id));
    }

    @GetMapping(value = "getNextPeriods")
    @ApiOperation(value = "获取下一期数")
    public Result getNextPeriods(@RequestParam Integer periodId, @RequestParam Integer lineCode) {
        return ResultUtil.success(anodeGoodinService.getNextPeriods(periodId, lineCode));
    }

    @PostMapping(value = "saveOrCommit")
    @ApiOperation(value = "保存或提交")
    public Result saveOrCommit(@RequestBody AnodeGoodinDTO data, @RequestParam Long id, @RequestParam Integer flag) {
        return ResultUtil.success(anodeGoodinService.saveOrCommit(data, id, flag));
    }

    @PostMapping(value = "unCommitPage")
    @ApiOperation(value = "待提交分页")
    public Result unCommitPage(@RequestBody AnodeGoodsInProcessStatisticHead head,
                               @RequestParam(required = false, defaultValue = "1") Integer page,
                               @RequestParam(required = false, defaultValue = "10") Integer size) {
        return ResultUtil.success(anodeGoodinService.pageUnCommit(head, page, size));
    }

    @PostMapping(value = "commitPage")
    @ApiOperation(value = "已统计分页")
    public Result commitPage(@RequestBody AnodeGoodsInProcessStatisticHead head,
                             @RequestParam(required = false, defaultValue = "1") Integer page,
                             @RequestParam(required = false, defaultValue = "10") Integer size) {
        return ResultUtil.success(anodeGoodinService.pageCommit(head, page, size));
    }

    @GetMapping(value = "commitDetail")
    @ApiOperation(value = "提交详情")
    public Result commitDetail(@RequestParam Long totalsId) {
        return ResultUtil.success(anodeGoodinService.commitDetail(totalsId));
    }

    @DeleteMapping
    @ApiOperation(value = "删除")
    public Result delete(@RequestParam Long id) {
        anodeGoodinService.delete(id);
        return ResultUtil.success();
    }

    @GetMapping(value = "statisticLine")
    @ApiOperation(value = "按产线统计")
    public Result statisticLine(@RequestParam Integer periodId, @RequestParam Integer periods) {
        String str = anodeGoodinService.judgeLine(periodId, periods);
        if (str.length() != 0) {
            return ResultUtil.error("产线" + str + "未统计");
        }
        return ResultUtil.success(anodeGoodinService.statisticLine(periodId, periods));
    }

    @GetMapping(value = "statisticProcess")
    @ApiOperation(value = "按工序统计")
    public Result statisticProcess(@RequestParam Integer periodId,
                                   @RequestParam Integer periods,
                                   @RequestParam Integer lineId) {
        return ResultUtil.success(anodeGoodinService.statisticProcess(periodId, periods, lineId));
    }

    @GetMapping(value = "lineCompare")
    @ApiOperation(value = "产线对比分析")
    public Result lineCompare(@RequestParam Integer periodId,
                              @RequestParam Integer dataFlag,
                              @RequestParam(required = false) Integer materialFlag,
                              @RequestParam String startTime,
                              @RequestParam String endTime) {
        return ResultUtil.success(anodeGoodinService.lineCompare(periodId, startTime, endTime, dataFlag, materialFlag));
    }

    @GetMapping(value = "processCompare")
    @ApiOperation(value = "工序对比分析")
    public Result processCompare(@RequestParam Integer periodId,
                                 @RequestParam Integer flag,
                                 @RequestParam Integer lineCode, @RequestParam String startTime, @RequestParam String endTime) {
        return ResultUtil.success(anodeGoodinService.processCompare(periodId, lineCode, startTime, endTime, flag));
    }

    @GetMapping(value = "getDateByPeriodId")
    @ApiOperation(value= "根据周期类型获取期数和时间")
    public Result getDateByPeriodId(@RequestParam Integer periodId){
        return ResultUtil.success(anodeGoodinService.getDateByPeriodId(periodId));
    }
}
