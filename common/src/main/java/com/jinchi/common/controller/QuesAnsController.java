package com.jinchi.common.controller;

import com.jinchi.common.domain.BasicInfoQuesAns;
import com.jinchi.common.dto.Result;
import com.jinchi.common.service.QuesAnsService;
import com.jinchi.common.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = "用户帮助")
@RequestMapping(value = "quesAndAns")
public class QuesAnsController {

    @Autowired
    QuesAnsService quesAnsService;

    @PostMapping
    @ApiOperation(value = "新增")
    public Result add(@RequestBody BasicInfoQuesAns basicInfoQuesAns){
        return ResultUtil.success(quesAnsService.add(basicInfoQuesAns));
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation(value = "删除")
    public Result delete(@PathVariable Long id){
        quesAnsService.delete(id);
        return ResultUtil.success();
    }

    @DeleteMapping(value = "ids")
    @ApiOperation(value = "批量删除")
    public Result deleteByIds(@RequestBody Long[] ids){
        quesAnsService.deleteByIds(ids);
        return ResultUtil.success();
    }

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "详情")
    public Result detail(@PathVariable Long id){
        return ResultUtil.success(quesAnsService.getById(id));
    }

    @GetMapping(value = "page")
    @ApiOperation(value = "分页条件查询")
    public Result page(@RequestParam(required = false,defaultValue = "") String condition,
                       @RequestParam(required = false,defaultValue = "1") Integer page,
                       @RequestParam(required = false,defaultValue = "10") Integer size){
        return ResultUtil.success(quesAnsService.page(condition,page,size));
    }

    @PutMapping
    @ApiOperation(value = "更新")
    public Result update(@RequestBody BasicInfoQuesAns basicInfoQuesAns){
        return ResultUtil.success(quesAnsService.update(basicInfoQuesAns));
    }
}
