package com.jinchi.common.controller;

import com.jinchi.common.dto.PowerCheckRecordDTO;
import com.jinchi.common.dto.Result;
import com.jinchi.common.service.PowerCheckRecordService;
import com.jinchi.common.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: LiuTaoYi
 * @create_time: 2019-12-22 14:01
 * @description:
 **/
@RestController
@Api(tags = "动力点检-动力点检")
@RequestMapping(value = "/checkRecord")
public class PowerCheckRecordController {

    @Autowired
    PowerCheckRecordService recordService;

    @PostMapping(value = "add")
    @ApiOperation(value = "暂存或提交")
    public Result add(@RequestBody PowerCheckRecordDTO dto) {
        return ResultUtil.success(recordService.add(dto));
    }

    @DeleteMapping(value = "delete")
    @ApiOperation(value = "删除")
    public Result delete(@RequestParam Long id) {
        recordService.delete(id);
        return ResultUtil.success();
    }

    @DeleteMapping(value = "deletes")
    @ApiOperation(value = "批量删除")
    public Result delete(@RequestBody Long[] ids) {
        recordService.deletes(ids);
        return ResultUtil.success();
    }

    @GetMapping(value = "detail")
    @ApiOperation(value = "详情")
    public Result detail(@RequestParam Long id) {
        return ResultUtil.success(recordService.detail(id));
    }

    @PostMapping(value = "update")
    @ApiOperation(value = "编辑")
    public Result edit(@RequestBody PowerCheckRecordDTO dto) {
        return ResultUtil.success(recordService.update(dto));
    }

    @GetMapping(value = "page")
    @ApiOperation(value = "分页")
    public Result page(@ApiParam(name = "condition", value = "模板名称") @RequestParam(name = "condition", defaultValue = "", required = false) String condition,
                       @ApiParam(name = "page", value = "页码") @RequestParam(name = "page", defaultValue = "1") Integer page,
                       @ApiParam(name = "size", value = "条目数") @RequestParam(name = "size", defaultValue = "10") Integer size) {
        return ResultUtil.success(recordService.page(condition, page, size));
    }

    @GetMapping(value = "byDate")
    @ApiOperation(value = "按时间查询")
    public Result page(@ApiParam(name = "start", value = "开始时间") @RequestParam(name = "start", defaultValue = "", required = false) String start,
                       @ApiParam(name = "end", value = "结束时间") @RequestParam(name = "end", defaultValue = "", required = false) String end,
                       @ApiParam(name = "page", value = "页码") @RequestParam(name = "page", defaultValue = "1") Integer page,
                       @ApiParam(name = "size", value = "条目数") @RequestParam(name = "size", defaultValue = "10") Integer size) {
        return ResultUtil.success(recordService.pageByDate(start, end, page, size));
    }

    @GetMapping(value = "getOperator")
    @ApiOperation(value = "获取录检人")
    public Result getOperator() {
        return ResultUtil.success(recordService.getOperator());
    }
}
