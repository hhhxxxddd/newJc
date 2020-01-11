package com.jinchi.common.controller;

import com.jinchi.common.domain.AuxiliaryMaterialsStatisticHead;
import com.jinchi.common.dto.AuxiliaryAddDTO;
import com.jinchi.common.dto.Result;
import com.jinchi.common.service.AuxiliaryService;
import com.jinchi.common.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.annotation.Repeatable;

@RestController
@RequestMapping(value = "auxiliary")
@Api(tags = "前驱体-辅料统计")
public class AuxiliaryController {

    @Autowired
    AuxiliaryService auxiliaryService;

    @PostMapping(value = "addComfirm")
    @ApiOperation(value = "新增确认")
    public Result  addComfirm(@RequestBody AuxiliaryMaterialsStatisticHead head){
        return ResultUtil.success(auxiliaryService.addComfirm(head));
    }

    @GetMapping(value = "afterComfirm")
    @ApiOperation(value = "新增头成功之后的表格")
    public Result afterComfirm(@RequestParam(required = false) Long id){
        return ResultUtil.success(auxiliaryService.afterComfirm(id));
    }

    @GetMapping(value = "nextPeroidNumber")
    @ApiOperation(value = "获取下一期数")
    public Result nextPeriodNumber(@RequestParam Integer periodId){
        return ResultUtil.success(auxiliaryService.nextPeroidNumber(periodId));
    }

    @PostMapping(value = "saveOrCommit")
    @ApiOperation(value = "保存或提交")
    public Result saveOrCommit(@RequestBody AuxiliaryAddDTO auxiliaryAddDTO,
                               @ApiParam(value = "保存：0，提交：1") @RequestParam Integer flag){
        return ResultUtil.success(auxiliaryService.saveOrCommit(auxiliaryAddDTO,flag));
    }

    @PostMapping(value = "getPageUnCommit")
    @ApiOperation(value = "待提交分页查询")
    public Result getPageUnCommit(@RequestBody AuxiliaryMaterialsStatisticHead head,
                                  @RequestParam(required = false,defaultValue = "1") Integer page,
                                  @RequestParam(required = false,defaultValue = "10") Integer size){
        return ResultUtil.success(auxiliaryService.getPageUnCommit(head,page,size));
    }

    @PostMapping(value = "getPageCommit")
    @ApiOperation(value = "已统计分页查询")
    public Result getPageCommit(@RequestBody AuxiliaryMaterialsStatisticHead head,
                                @RequestParam(required = false,defaultValue = "1") Integer page,
                                @RequestParam(required = false,defaultValue = "10") Integer size){
        return ResultUtil.success(auxiliaryService.getPageCommit(head,page,size));
    }

    @PostMapping(value = "stasticByProcess")
    @ApiOperation(value = "按工序统计")
    public Result stasticByProcess(@RequestBody AuxiliaryMaterialsStatisticHead head){
        return ResultUtil.success(auxiliaryService.stasticByProcess(head));
    }

    @PostMapping(value = "stasticByLine")
    @ApiOperation(value = "按产线统计")
    public Result stasticByLine(@RequestBody AuxiliaryMaterialsStatisticHead head){
        return ResultUtil.success(auxiliaryService.stasticByLine(head));
    }

    @PostMapping(value = "processCur")
    @ApiOperation(value = "工序对比曲线")
    public Result processCur(@RequestBody AuxiliaryMaterialsStatisticHead head,
                                @RequestParam Integer processId){
        return ResultUtil.success(auxiliaryService.processCur(head,processId));
    }

    @PostMapping(value = "lineCur")
    @ApiOperation(value = "产线对比曲线")
    public Result lineCur(@RequestBody AuxiliaryMaterialsStatisticHead head,
                             @RequestParam Integer lineId){
        return ResultUtil.success(auxiliaryService.lineCur(head,lineId));
    }

    @GetMapping(value = "detail")
    @ApiOperation(value = "已提交详情")
    public Result detail(@RequestParam Long id){
        return ResultUtil.success(auxiliaryService.detail(id));
    }

    @DeleteMapping
    @ApiOperation(value = "删除")
    public Result delete(@RequestParam Long id){
        auxiliaryService.delete(id);
        return ResultUtil.success();
    }

    @DeleteMapping(value = "deleteByIds")
    @ApiOperation(value = "删除")
    public Result deleteByIds(@RequestBody Long[] ids){
        auxiliaryService.deleteByIds(ids);
        return ResultUtil.success();
    }

    @GetMapping(value = "getDate")
    @ApiOperation(value= "获取时间")
    public Result getDate(){
        return ResultUtil.success(auxiliaryService.getAllDate());
    }

    @GetMapping(value = "getVolumeWeight")
    @ApiOperation(value = "获取体积/重量值")
    public Result getVolumeWeight(@RequestParam Integer processId){
        return ResultUtil.success(auxiliaryService.getVolumeWeight(processId));
    }

    @GetMapping(value  = "defaultPage")
    @ApiOperation(value = "已统计默认")
    public Result defaultPage(){
        return ResultUtil.success(auxiliaryService.defaultPage());
    }
}
