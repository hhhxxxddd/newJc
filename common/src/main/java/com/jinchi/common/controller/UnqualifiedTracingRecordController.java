package com.jinchi.common.controller;

import com.jinchi.common.domain.UnqualifiedTracingRecord;
import com.jinchi.common.dto.CommonBatchNumberDTO;
import com.jinchi.common.dto.PageBean;
import com.jinchi.common.dto.Result;
import com.jinchi.common.service.UnqualifiedTracingRecordService;
import com.jinchi.common.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


/**
 * 说明:不合格品跟踪记录控制器
 * <br>
 *
 * @author huxudong
 * <br>
 * 日期: 2018/11/16
 * <br>
 * 版本: 1.0
 */
@RestController
@RequestMapping(value = "/unqualifiedTracingRecord")
@Api(tags = "质量流程-数据录入-不合格品跟踪")
public class UnqualifiedTracingRecordController {
    @Autowired
    private UnqualifiedTracingRecordService unqualifiedTracingRecordService;

    /**
     * 更新
     */
    @PutMapping
    @ApiOperation(value = "更新")
    public Result<CommonBatchNumberDTO> update(@RequestBody @Valid CommonBatchNumberDTO<UnqualifiedTracingRecord>  commonBatchNumberDTO) {
        return ResultUtil.success(unqualifiedTracingRecordService.update(commonBatchNumberDTO));
    }

    /**
     * 查询所有-分页
     */
    @GetMapping(value = "/pages")
    @ApiOperation(value = "查询所有-分页",notes = "分页")
    public Result<PageBean> getAllByPage(@ApiParam(name = "personName", value = "处理人名称") @RequestParam(required = false) String personName, PageBean pageBean) {
        return ResultUtil.success(unqualifiedTracingRecordService.byPage(personName, pageBean));
    }


    /**
     * 根据批号id查询
     */
    @GetMapping(value = "/{batchNumberId}")
    @ApiOperation(value = "详情",notes = "根据批号id")
    public Result<CommonBatchNumberDTO> getByBatchNumberId(@ApiParam(name = "batchNumberId", value = "批号id") @PathVariable Integer batchNumberId) {
        return ResultUtil.success(unqualifiedTracingRecordService.byBatchNumberId(batchNumberId));
    }

}
