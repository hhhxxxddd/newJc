package com.jc.api.controller;

import com.jc.api.entity.SwmsBasicMaterialType;
import com.jc.api.service.restservice.SwmsBasicMaterialTypeService;
import com.jinchi.common.core.vo.Result;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

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
    private SwmsBasicMaterialTypeService swmsBasicMaterialTypeService;

    @ApiOperation(value = "获取所有物料类型", notes = "获取所有物料类型")
    @ApiResponses(@ApiResponse(code = 200, message = "处理成功", response = Result.class))
    @GetMapping(value = "/getAll")
    public Result getAll(){
        return Result.success(swmsBasicMaterialTypeService.getAll(new SwmsBasicMaterialType()));
    }

    @ApiOperation(value = "获取物料类型-条件", notes = "条件获取所有物料类型")
    @ApiResponses(@ApiResponse(code = 200, message = "处理成功", response = Result.class))
    @ApiImplicitParam(name = "swmsBasicMaterialType", value = "查询条件", required = true, dataType = "SwmsBasicMaterialType")
    @PostMapping(value = "/conditions")
    public Result query(SwmsBasicMaterialType swmsBasicMaterialType){
        return Result.success(swmsBasicMaterialTypeService.getAll(swmsBasicMaterialType));
    }

//    @ApiOperation(value = "获取供应车间-条件/分页", notes = "条件获取供应车间记录-分页")
//    @ApiImplicitParam(name = "swmsBasicMaterialType", value = "查询条件", required = true, dataType = "SwmsBasicMaterialType")
//    @ApiResponses(@ApiResponse(code = 200, message = "处理成功", response = Result.class))
//    @PostMapping(value = "/pages")
//    public Result queryPages(@Valid @RequestBody SwmsBasicMaterialType swmsBasicMaterialType) {
//        log.debug("条件获取供应车间分页:{}", swmsBasicPlantInfo);
//        return Result.success(swmsBasicMaterialTypeService.getAllByPage(swmsBasicMaterialType.getPage(),swmsBasicMaterialType));
//    }

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
}
