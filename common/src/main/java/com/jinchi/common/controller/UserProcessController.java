package com.jinchi.common.controller;

import com.jinchi.common.domain.*;
import com.jinchi.common.dto.RealTimeData;
import com.jinchi.common.dto.Result;
import com.jinchi.common.mapper.BasicInfoPrecursorMaterialDetailsMapper;
import com.jinchi.common.mapper.BasicInfoPrecursorMaterialPlcMapMapper;
import com.jinchi.common.mapper.BasicInfoPrecursorPlcAddressMapper;
import com.jinchi.common.service.UserProcessService;
import com.jinchi.common.utils.RealTimeUtil;
import com.jinchi.common.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "userProcess")
@Api(tags = "设备管理-基础数据-用户工序分配")
public class UserProcessController {

    @Autowired
    UserProcessService userProcessService;

    @PutMapping
    @ApiOperation(value = "修改")
    public Result update(@RequestParam Integer userId,@RequestBody Integer[] processDeptIds){
        userProcessService.update(userId,processDeptIds);
        return ResultUtil.success();
    }

    @GetMapping
    @ApiOperation(value = "分页模糊查询")
    public Result page(@RequestParam Integer deptId,
                       @RequestParam(defaultValue = "",required = false) String condition,
                       @RequestParam(defaultValue = "1",required = false) Integer page,
                       @RequestParam(defaultValue = "10",required = false) Integer size){
        return ResultUtil.success(userProcessService.page(deptId,condition,page,size));
    }

}
