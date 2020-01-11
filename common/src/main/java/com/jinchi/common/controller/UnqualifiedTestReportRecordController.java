package com.jinchi.common.controller;

import com.jinchi.common.dto.PageBean;
import com.jinchi.common.dto.Result;
import com.jinchi.common.dto.quality.unqualified.UnqualifiedDetailDTO;
import com.jinchi.common.service.UnqualifiedTestReportRecordService;
import com.jinchi.common.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

/**
 * @author：XudongHu
 * @className:UnqualifiedTestReportRecordController
 * @description: 不合格品评审
 * @date:15:44 2018/12/24
 */
@RestController
@RequestMapping(value = "/unqualifiedTestReportRecord")
@Api(tags = "质量流程-数据录入-不合格品评审")
public class UnqualifiedTestReportRecordController {
    @Autowired
    private UnqualifiedTestReportRecordService unqualifiedTestReportRecordService;

    /**
     * 查看详情
     *
     * @param batchNumberId 批号id
     * @return
     */
    @GetMapping(value = "/{batchNumberId}")
    @ApiOperation(value = "详情", notes = "根据批号id")
    public Result<Map<Object,Object>> getByBatchNumberId(@ApiParam(name = "batchNumberId", value = "批号id") @PathVariable Integer batchNumberId) {
        return ResultUtil.success(unqualifiedTestReportRecordService.findByBatchNumberId(batchNumberId));
    }

    /**
     * 查询所有
     *
     * @param createPerson 创建人名
     * @param pageBean   分页信息
     * @return
     */
    @GetMapping(value = "/pages")
    @ApiOperation(value = "查询所有-分页", notes = "根据创建人名字")
    public Result<PageBean> getAllByPage(@ApiParam(name = "createPerson", value = "创建人名") @RequestParam(required = false) String createPerson, PageBean pageBean) {
        return ResultUtil.success(unqualifiedTestReportRecordService.getAllByPage(createPerson, pageBean));
    }


    /**
     * 更新合格内容
     *
     * @return
     */
    @PutMapping
    @ApiOperation(value = "更新", notes = "只能更新是否合格")
    public Result<Object> updateIsValid(@ApiParam(value = "更新内容") @RequestBody @Valid UnqualifiedDetailDTO unqualifiedDetailDTO) {
        unqualifiedTestReportRecordService.update(unqualifiedDetailDTO);
        return ResultUtil.success();
    }
}
