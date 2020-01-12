package com.jc.api.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jc.api.entity.SwmsBasicMaterialType;
import com.jc.api.service.restservice.ISwmsBasicMaterialTypeService;
import com.jinchi.common.core.vo.Result;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.Set;

/**
 * @Auther: River
 * @Date: 2020/1/11 17:43
 * @Description:
 */
@RestController
@Api(tags = "智能仓库-物料类型信息管理界面")
@RequestMapping(value = "/swmsBasicMaterialType")
@Slf4j
public class SwmsBasicMaterialTypeController {

    @Autowired
    private ISwmsBasicMaterialTypeService swmsBasicMaterialTypeService;

    @ApiOperation(value = "获取所有物料类型", notes = "获取所有物料类型")
    @ApiResponses(@ApiResponse(code = 200, message = "处理成功", response = Result.class))
    @GetMapping(value = "/getAll")
    public Result getAll(){
        return Result.success(swmsBasicMaterialTypeService.getAll(""));
    }

    @ApiOperation(value = "获取物料类型-条件", notes = "条件获取所有物料类型")
    @ApiResponses(@ApiResponse(code = 200, message = "处理成功", response = Result.class))
    @ApiImplicitParam(name = "swmsBasicMaterialType", value = "查询条件", required = true, dataType = "SwmsBasicMaterialType")
    @PostMapping(value = "/conditions")
    public Result query(@RequestParam(required = false)String typeName){
        return Result.success(swmsBasicMaterialTypeService.getAll(typeName));
    }

    @ApiOperation(value = "获取物料类型-条件/分页", notes = "条件获取物料类型记录-分页")
    @ApiImplicitParam(name = "swmsBasicMaterialType", value = "查询条件", required = true, dataType = "SwmsBasicMaterialType")
    @ApiResponses(@ApiResponse(code = 200, message = "处理成功", response = Result.class))
    @PostMapping(value = "/pages")
    public Result queryPages(@RequestBody Page page, @RequestParam(required = false) String typeName) {
        log.debug("条件获取供应车间分页:{}", typeName);
        return Result.success(swmsBasicMaterialTypeService.getAllByPage(page, typeName));
    }

    @ApiOperation(value = "新增物料类型信息", notes = "新增")
    @ApiImplicitParam(name = "swmsBasicMaterialType", value = "新增参数", required = true, dataType = "SwmsBasicMaterialType")
    @ApiResponses(@ApiResponse(code = 200, message = "处理成功", response = Result.class))
    @PostMapping(value = "/add")
    public Result add(@Valid @RequestBody SwmsBasicMaterialType swmsBasicMaterialType) {
        swmsBasicMaterialType.setAutoFlag(false);
        return Result.success(swmsBasicMaterialTypeService.add(swmsBasicMaterialType));
    }

    @ApiOperation(value = "仅供测试使用-自动新增物料类型信息", notes = "自动新增,存在则无动作,不存在则新增")
    @ApiImplicitParam(name = "typeCode", value = "物料类型代号", required = true, dataType = "String")
    @ApiResponses(@ApiResponse(code = 200, message = "处理成功", response = Result.class))
    @PostMapping(value = "/autoAdd")
    public Result autoAdd(@RequestParam @NotBlank(message = "物料类型代号不能为空白") String typeCode) {
        return Result.success(swmsBasicMaterialTypeService.autoAdd(new SwmsBasicMaterialType().setTypeCode(typeCode)));
    }

    @ApiOperation(value = "更新物料类型信息", notes = "更新")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "swmsBasicMaterialType", value = "更新参数", required = true, dataType = "SwmsBasicMaterialType")})
    @ApiResponses(@ApiResponse(code = 200, message = "处理成功", response = Result.class))
    @PutMapping(value = "/{id}")
    public Result update(@RequestBody SwmsBasicMaterialType swmsBasicMaterialType) {
        return Result.success(swmsBasicMaterialTypeService.update(swmsBasicMaterialType));
    }

    @ApiOperation(value = "删除物料类型信息", notes = "根据id删除物料类型信息")
    @ApiImplicitParam(name = "id", value = "物料类型信息id", required = true, dataType = "long")
    @ApiResponses(@ApiResponse(code = 200, message = "处理成功", response = Result.class))
    @DeleteMapping(value = "/{id}")
    public Result delete(@PathVariable Integer id) {
        log.debug("根据id删除物料类型记录:{}", id);
        return Result.success(swmsBasicMaterialTypeService.delete(id));
    }

    @ApiOperation(value = "批量删除物料类型信息",notes = "根据id集合删除")
    @DeleteMapping(value = "/batchDelete")
    public Result batchDelete(@RequestParam Set<String> ids){
        return Result.success(swmsBasicMaterialTypeService.batchDelete(ids));
    }
}
