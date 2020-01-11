package com.jinchi.common.controller;

import com.jinchi.common.domain.BasicInfoPrecursorMaterialDetails;
import com.jinchi.common.dto.Result;
import com.jinchi.common.service.PrecursorMaterialDetailsService;
import com.jinchi.common.service.PrecursorProcessTypeService;
import com.jinchi.common.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: LiuTaoYi
 * @create_time: 2019-09-26 19:26
 * @description:
 **/
@RestController
@RequestMapping(value = "/precursorMaterialDetails")
@Api(tags = "成本核算-基础数据-物料点名称")
public class PrecursorMaterialDetailsController {

    @Autowired
    PrecursorMaterialDetailsService materialDetailsService;

    @Autowired
    PrecursorProcessTypeService precursorProcessTypeService;

    @PostMapping(value = "add")
    @ApiOperation(value = "新增 数据类型 输入1 读取0 ni co mn勾选为1 不选为0")
    public Result add(@RequestBody BasicInfoPrecursorMaterialDetails materialDetails){
        return ResultUtil.success(materialDetailsService.add(materialDetails));
    }

    @GetMapping(value = "page")
    @ApiOperation(value = "根据名称分页查询")
    public Result page(@RequestParam(defaultValue = "",required = false) String condition,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer size){
        return ResultUtil.success(materialDetailsService.page(condition,page,size));
    }

    @GetMapping(value = "getProcess")
    @ApiOperation(value = "根据所属类别 获取工序名称")
    public Result query(@RequestParam(value = "types") Byte types){
        return ResultUtil.success(precursorProcessTypeService.getDataByTypes(types));
    }

    @GetMapping(value = "getMaterialByProcessType")
    @ApiOperation(value = "根据工序id获取物料")
    public Result getMaterialByProcessType(@RequestParam Integer processCode){
        return ResultUtil.success(materialDetailsService.getMaterialByProcessType(processCode));
    }


    @DeleteMapping(value = "delete")
    @ApiOperation(value = "删除")
    public Result delete(@RequestParam Integer id){
        materialDetailsService.delete(id);
        return ResultUtil.success();
    }

    @DeleteMapping(value = "ids")
    @ApiOperation(value = "批量删除")
    public Result deleteByIds(@RequestBody Integer[] ids){
        materialDetailsService.deleteByIds(ids);
        return ResultUtil.success();
    }

    @PutMapping(value = "update")
    @ApiOperation(value = "编辑")
    public Result update(@RequestBody BasicInfoPrecursorMaterialDetails materialDetails){
        return ResultUtil.success(materialDetailsService.update(materialDetails));
    }

    @GetMapping(value = "getRecordById")
    @ApiOperation(value = "根据id查询一条记录")
    public Result getDataById(@RequestParam Integer id){
        return ResultUtil.success(materialDetailsService.getOne(id));
    }

    @GetMapping(value = "all")
    @ApiOperation(value = "查询所有")
    public Result getAll(@RequestParam(defaultValue = "",required = false) String condition) {
        return ResultUtil.success(materialDetailsService.getAll(condition));
    }

    @GetMapping(value = "byTypes")
    @ApiOperation(value = "按主材辅材查询")
    public Result getByType(@RequestParam Byte type,
                            @RequestParam(defaultValue = "1") Integer page,
                            @RequestParam(defaultValue = "10") Integer size,
                            @RequestParam(defaultValue = "", required = false) String condition) {
        return ResultUtil.success(materialDetailsService.getByType(type, page, size, condition));
    }

    @GetMapping(value = "getHC")
    @ApiOperation(value = "查询合成工序物料")
    public Result getHC() {
        return ResultUtil.success(materialDetailsService.getHCMaterial());
    }
}
