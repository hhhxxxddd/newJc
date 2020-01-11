package com.jinchi.common.controller;

import com.jinchi.common.domain.BasicInfoDept;
import com.jinchi.common.dto.DeptManageDTO;
import com.jinchi.common.dto.Result;
import com.jinchi.common.service.DeptManageService;
import com.jinchi.common.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/dept")
@Api(tags = "设备管理-部门")
public class DeptManageController {

    @Autowired
    DeptManageService deptManageService;
    @PostMapping
    @ApiOperation(value = "新增")
    public Result<BasicInfoDept> add(@RequestBody @Valid BasicInfoDept basicInfoDept, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
        }
        return ResultUtil.success(deptManageService.addOne(basicInfoDept));
    }

    @PutMapping
    @ApiOperation(value = "更新")
    public Result<BasicInfoDept> update(@RequestBody @Valid BasicInfoDept basicInfoDept, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
        }
        return ResultUtil.success(deptManageService.update(basicInfoDept));
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation(value = "删除")
    public Result<BasicInfoDept> deleteByIddeleteById(@ApiParam(name = "id", value = "部门主键") @PathVariable Integer id) {
        deptManageService.deleteById(id);
        return ResultUtil.success();
    }

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "查询")
    public Result<BasicInfoDept> getDeptById(@ApiParam(name = "id", value = "部门主键") @PathVariable Integer id){
        return ResultUtil.success(deptManageService.getDeptById(id));
    }

    @GetMapping()
    @ApiOperation(value = "获取目录")
    public Result<List<DeptManageDTO>> getCata(){
        return ResultUtil.success(deptManageService.getCata());
    }
}
