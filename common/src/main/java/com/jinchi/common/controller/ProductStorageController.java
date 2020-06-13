package com.jinchi.common.controller;

import com.jinchi.common.domain.ProductStorageStatisticDataDetails;
import com.jinchi.common.domain.ProductStorageStatisticHead;
import com.jinchi.common.dto.Result;
import com.jinchi.common.service.ProductStorageService;
import com.jinchi.common.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "前驱体-成品入库")
@RequestMapping(value = "productStorage")
public class ProductStorageController {

    @Autowired
    ProductStorageService storageService;

    @GetMapping(value = "getAllBatch")
    @ApiOperation(value = "获取所有批次信息")
    public Result getAllBatch() {
        return ResultUtil.success(storageService.getAllBatch());
    }

    @PostMapping(value = "addComfirm")
    @ApiOperation(value = "新增确认")
    public Result addComfirm(@RequestBody ProductStorageStatisticHead head) {
        return ResultUtil.success(storageService.addConfirm(head));
    }

    @PostMapping(value = "update")
    @ApiOperation(value = "更新")
    public Result update(@RequestBody ProductStorageStatisticHead head) {
        return ResultUtil.success(storageService.update(head));
    }

    @PostMapping(value = "saveOrCommit")
    @ApiOperation(value = "保存或提交")
    public Result saveOrCommit(@RequestBody List<ProductStorageStatisticDataDetails> details,
                               @RequestParam Long id,
                               @ApiParam(value = "0:保存 1:提交") @RequestParam Integer flag) {
        return ResultUtil.success(storageService.saveOrCommit(id, details, flag));
    }

    @PostMapping(value = "pageUnCommit")
    @ApiOperation(value = "未提交分页")
    public Result pageUnCommit(@RequestBody ProductStorageStatisticHead head,
                               @RequestParam(required = false,defaultValue = "1") Integer page,
                               @RequestParam(required = false,defaultValue = "10") Integer size){
        return  ResultUtil.success(storageService.pageUmCommit(head,page,size));
    }

    @PostMapping(value = "pageCommit")
    @ApiOperation(value = "提交分页")
    public Result pageCommit(@RequestBody ProductStorageStatisticHead head,
                             @RequestParam(required = false,defaultValue = "1") Integer page,
                             @RequestParam(required = false,defaultValue = "10") Integer size){
        return  ResultUtil.success(storageService.pageCommit(head,page,size));
    }

    @GetMapping(value = "detail")
    @ApiOperation(value = "提交详情")
    public Result detail(@RequestParam Integer id){
        return  ResultUtil.success(storageService.detail(id));
    }

    @DeleteMapping(value = "delete")
    @ApiOperation(value = "删除")
    public Result delete(@RequestParam Long id){
        storageService.delete(id);
        return  ResultUtil.success();
    }

    @PostMapping(value = "deleteByIds")
    @ApiOperation(value = "批量删除")
    public Result deleteByIds(@RequestBody Long[] ids){
        storageService.deleteByIds(ids);
        return  ResultUtil.success();
    }

    @PostMapping(value = "stasticByLine")
    @ApiOperation(value = "按产线统计")
    public Result stasticByLine(@RequestBody ProductStorageStatisticHead head){
        return  ResultUtil.success(storageService.stastaicByLine(head));
    }

    @PostMapping(value = "periodCur")
    @ApiOperation(value = "周期曲线")
    public Result periodCur(@RequestParam Integer lineId,@RequestBody ProductStorageStatisticHead head){
        return  ResultUtil.success(storageService.periodCur(lineId,head));
    }

    @PostMapping(value = "lineCur")
    @ApiOperation(value = "产线曲线")
    public Result lineCur(@RequestBody ProductStorageStatisticHead head,
                          @RequestParam Integer[] lines){
        return  ResultUtil.success(storageService.lineCur(head,lines));
    }

    @GetMapping(value = "nextPeriod")
    @ApiOperation(value = "获取下一期数")
    public Result nextPeriod(@RequestParam Integer periodId){
        return ResultUtil.success(storageService.nextPeriod(periodId));
    }

    @GetMapping(value = "editDetail")
    @ApiOperation(value= "编辑详情")
    public Result editDetail(@RequestParam Long id){
        return ResultUtil.success(storageService.edit(id));
    }

    @GetMapping(value = "inPage")
    @ApiOperation(value= "新增里的分页")
    public Result inPage(@RequestParam Long id,
                         @RequestParam(required = false,defaultValue = "1") Integer page,
                         @RequestParam(required = false,defaultValue = "10") Integer size){
        return ResultUtil.success(storageService.getInData(id,page,size));
    }

    @GetMapping(value = "getDate")
    @ApiOperation(value = "通过周期id获取开始时间")
    public Result getDate(@RequestParam Integer periodId){
        return ResultUtil.success(storageService.getDate(periodId));
    }

    @GetMapping(value = "getPeriodAndTime")
    @ApiOperation(value = "期数和时间")
    public Result getPeroidAndTime(@RequestParam Integer peroidId){
        return ResultUtil.success(storageService.getPeriodAndTime(peroidId));
    }

    @GetMapping(value  = "defaultPage")
    @ApiOperation(value = "已统计默认")
    public Result defaultPage(){
        return ResultUtil.success(storageService.defaultPage());
    }
}
