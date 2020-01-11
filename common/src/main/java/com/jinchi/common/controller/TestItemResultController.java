package com.jinchi.common.controller;

import com.jinchi.common.domain.TestItemResultRecord;
import com.jinchi.common.dto.Result;
import com.jinchi.common.service.TestItemResultRecordService;
import com.jinchi.common.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author ZSCDumin
 * @version 1.0
 * @description: TODO
 * @date 2018/12/18 14:56
 */
@RestController
@Api(tags = "质量流程-基础数据-检测项目结果")
public class TestItemResultController {
    @Autowired
    private TestItemResultRecordService testItemResultRecordService;

    @PostMapping(value = "/testItemResultRecord")
    @ApiOperation(value = "新增")
    public Result<TestItemResultRecord> add(@RequestBody @Valid TestItemResultRecord testItemResultRecord) {
        return ResultUtil.success(testItemResultRecordService.insert(testItemResultRecord));
    }

    @PostMapping("/testItemResultRecords")
    @ApiOperation(value = "批量删除")
    public Result<Object> deleteByIds(@RequestBody @Valid List<TestItemResultRecord> testItemResultRecords) {
        testItemResultRecordService.batchInsert(testItemResultRecords);
        return ResultUtil.success();
    }

    @PutMapping("/testItemResultRecords")
    @ApiOperation(value = "批量更新")
    public Result<Object> batchUpdate(@RequestBody @Valid List<TestItemResultRecord> testItemResultRecords) {
        testItemResultRecordService.batchUpdate(testItemResultRecords);
        return ResultUtil.success();
    }

    @PutMapping("/testItemResultRecord")
    @ApiOperation(value = "更新")
    public Result<Object> update(@RequestBody @Valid TestItemResultRecord testItemResultRecord) {
        testItemResultRecordService.update(testItemResultRecord);
        return ResultUtil.success();
    }

    @DeleteMapping("/testItemResultRecord/{id}")
    @ApiOperation(value = "删除")
    public Result<Object> deleteById(@RequestParam @PathVariable Integer id) {
        testItemResultRecordService.deleteById(id);
        return ResultUtil.success();
    }

    @DeleteMapping("/testItemResultRecords")
    @ApiOperation(value = "批量删除")
    public Result<Object> deleteByIds(@RequestBody @Valid Integer[] ids) {
        testItemResultRecordService.deleteByIds(ids);
        return ResultUtil.success();
    }

    @GetMapping(value = "/testItemResultRecord/{id}")
    @ApiOperation(value = "查找")
    public Result<TestItemResultRecord> getById(@RequestParam @PathVariable Integer id) {
        return ResultUtil.success(testItemResultRecordService.findById(id));
    }
}
