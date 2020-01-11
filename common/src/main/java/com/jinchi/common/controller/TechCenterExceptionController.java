package com.jinchi.common.controller;

import com.jinchi.common.domain.BasicInfoExceptionModels;
import com.jinchi.common.dto.Result;
import com.jinchi.common.service.TechExceptionService;
import com.jinchi.common.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/techException")
@Api(tags = "技术中心-异常处理模板")
public class TechCenterExceptionController {
    @Autowired
    TechExceptionService techExceptionService;

    @PostMapping
    @ApiOperation(value = "新增")
    public Result add(@RequestBody BasicInfoExceptionModels basicInfoExceptionModels){
        return ResultUtil.success(techExceptionService.add(basicInfoExceptionModels));
    }

    @DeleteMapping
    @ApiOperation(value = "删除")
    public Result delete(@RequestParam Integer id){
        techExceptionService.delete(id);
        return ResultUtil.success();
    }

    @DeleteMapping(value = "ids")
    @ApiOperation(value = "批量删除")
    public Result deleteByIds(@RequestBody Integer[] ids){
        techExceptionService.deleteByIds(ids);
        return ResultUtil.success();
    }

    @GetMapping(value = "getAll")
    @ApiOperation(value = "获取所有")
    public Result getAll(){
        return ResultUtil.success(techExceptionService.getAll());
    }

    @GetMapping(value = "page")
    @ApiOperation(value = "分页查询")
    public Result page(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer size){
        return ResultUtil.success(techExceptionService.getAllPage(page,size));
    }

    @GetMapping(value = "detail")
    @ApiOperation(value = "详情")
    public Result detail(@RequestParam Integer id){
        return ResultUtil.success(techExceptionService.detail(id));
    }

    @PutMapping
    @ApiOperation(value = "更新")
    public Result update(@RequestBody BasicInfoExceptionModels basicInfoExceptionModels){
        return ResultUtil.success(techExceptionService.update(basicInfoExceptionModels));
    }

}
