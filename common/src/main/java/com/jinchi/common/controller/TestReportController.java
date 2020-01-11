package com.jinchi.common.controller;

import com.jinchi.common.domain.TestReportRecord;
import com.jinchi.common.dto.Result;
import com.jinchi.common.service.TestReportRecordService;
import com.jinchi.common.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author ZSCDumin
 * @version 1.0
 * @description: TODO
 * @date 2018/12/20 16:37
 */

@Api(tags = "质量流程-基础数据-检测结果记录")
@RestController
public class TestReportController {

    @Autowired
    private TestReportRecordService testReportRecordService;


    /**
     * 更新原材料进货记录
     */
//    @PreAuthorize("hasRole(\"ROLE_ADMIN\") or hasRole(\"ROLE_AUTH_USER_QUERY\")")
//    @Secured("QUALITY_PRODUCTINE_UPDATE")
    @PutMapping(value = "/testReportRecord")
    @ApiOperation(value = "更新检测记录")
    public Result<TestReportRecord> update(@RequestBody @Valid TestReportRecord testReportRecord, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
        }
        return ResultUtil.success(testReportRecordService.update(testReportRecord));
    }
}
