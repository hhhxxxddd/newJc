package com.jinchi.common.controller;

import com.jinchi.common.domain.BasicInfoPrecursorVgaLineWeight;
import com.jinchi.common.dto.Result;
import com.jinchi.common.service.PrecursorVagWeightService;
import com.jinchi.common.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/vgaMap")
@Api(tags = "成本核算-基础数据-VGA权重分配")
public class PrecursorVagWeightController {

    @Autowired
    PrecursorVagWeightService precursorVagWeightService;

    @PostMapping
    @ApiOperation(value = "新增")
    public Result add(@RequestBody List<BasicInfoPrecursorVgaLineWeight> weightList){
        String ans = precursorVagWeightService.add(weightList);
        if("新增成功".equals(ans)) {
            return ResultUtil.success(ans);
        }
        else{
            return ResultUtil.error(ans);
        }
    }

    @DeleteMapping
    @ApiOperation(value = "删除")
    public Result delete(@RequestParam Integer id){
        precursorVagWeightService.delete(id);
        return ResultUtil.success();
    }

    @DeleteMapping(value = "ids")
    @ApiOperation(value = "批量删除")
    public Result deleteByIds(@RequestBody Integer[] ids){
        precursorVagWeightService.deleteByIds(ids);
        return  ResultUtil.success();
    }

    @GetMapping(value = "page")
    @ApiOperation(value = "根据物料名称模糊查询分页")
    public Result page(@RequestParam(defaultValue = "",required = false)String condition,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer size){
        return ResultUtil.success(precursorVagWeightService.getAllByPage(condition,page,size));
    }

    @PutMapping
    @ApiOperation(value = "更新")
    public Result update(@RequestBody List<BasicInfoPrecursorVgaLineWeight> weightList){
        return ResultUtil.success(precursorVagWeightService.update(weightList));
    }

    @GetMapping(value = "getInfoByVgaId")
    @ApiOperation(value = "根据vgaId获取权重")
    public Result getInfoByVgaId(@RequestParam Integer vgaId){
        return ResultUtil.success(precursorVagWeightService.getInfoByVgaId(vgaId));
    }
}
