package com.jinchi.common.controller;

import com.jinchi.common.dto.LineWeightDTO;
import com.jinchi.common.dto.Result;
import com.jinchi.common.service.PrecursorMaterialDetailsService;
import com.jinchi.common.service.PrecursorMaterialService;
import com.jinchi.common.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: LiuTaoYi
 * @create_time: 2019-09-26 21:03
 * @description:
 **/
@RestController
@RequestMapping(value = "/precursorMaterialLineWeight")
@Api(tags = "成本核算-基础数据-物料产线权重分配")
public class PrecursorMaterialLineWeightController {

    @Autowired
    PrecursorMaterialService precursorMaterialService;

    @Autowired
    PrecursorMaterialDetailsService precursorMaterialDetailsService;

    @GetMapping(value = "getRecordById")
    @ApiOperation(value = "根据id查询一条记录")
    public Result getOne(@RequestParam Integer id){
        return ResultUtil.success(precursorMaterialService.getRecordById(id));
    }

    @PostMapping(value = "add")
    @ApiOperation(value = "新增")
    public Result add(@RequestBody LineWeightDTO lineWeightDTO){
        LineWeightDTO ans = precursorMaterialService.add(lineWeightDTO);
        if (ans != null){
            return ResultUtil.success(ans);
        }else{
            return ResultUtil.error("已经存在这个物料点的相关信息");
        }
    }

    @PostMapping(value = "getMaterialName")
    @ApiOperation(value = "查询物料点名称")
    public Result getDataByCondition(Integer processCode, Byte types){
        return ResultUtil.success(precursorMaterialDetailsService.getDataByProcessAndType(processCode,types));
    }

    @GetMapping(value = "page")
    @ApiOperation(value = "根据名称分页查询")
    public Result page(@RequestParam(defaultValue = "",required = false) String condition,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer size){
        return ResultUtil.success(precursorMaterialService.page(condition,page,size));
    }

    @DeleteMapping(value = "delete")
    @ApiOperation(value = "删除")
    public Result delete(@RequestParam Integer id){
        precursorMaterialService.delete(id);
        return ResultUtil.success();
    }

    @DeleteMapping(value = "ids")
    @ApiOperation(value = "批量删除")
    public Result deleteByIds(@RequestBody Integer[] ids){
        precursorMaterialService.deleteByIds(ids);
        return ResultUtil.success();
    }

    @PutMapping(value = "update")
    @ApiOperation(value = "编辑")
    public Result update(@RequestBody LineWeightDTO lineWeightDTO){
        return ResultUtil.success(precursorMaterialService.update(lineWeightDTO));
    }
}
