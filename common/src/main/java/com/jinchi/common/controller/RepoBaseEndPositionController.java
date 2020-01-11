package com.jinchi.common.controller;

import com.jinchi.common.domain.RepoBaseEndPosition;
import com.jinchi.common.dto.Result;
import com.jinchi.common.service.RepoBaseEndPositionService;
import com.jinchi.common.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author XudongHu
 * @className RepoBaseEndPositionController
 * @apiNote 原材料出口点
 * @modifer
 * @since 2019/10/28 19:50
 */
@Api(tags = "智能仓库-基本数据-原材料出库点")
@RestController
@RequestMapping(value = "/endPosition")
public class RepoBaseEndPositionController {
    @Autowired
    private RepoBaseEndPositionService repoBaseEndPositionService;

    @ApiOperation("根据例子查询出库点")
    @PostMapping
    public Result<List<RepoBaseEndPosition>> findAllByExample(@RequestBody RepoBaseEndPosition repoBaseEndPosition){
        return ResultUtil.success(repoBaseEndPositionService.findAllByExample(repoBaseEndPosition));
    }
}
