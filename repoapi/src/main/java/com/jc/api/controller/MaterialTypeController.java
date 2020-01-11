package com.jc.api.controller;

import com.jc.api.entity.form.MaterialTypeForm;
import com.jc.api.entity.po.MaterialType;
import com.jc.api.service.restservice.IMaterialTypeService;
import com.jinchi.common.core.vo.Result;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author XudongHu
 * @apiNote 物料类型管理
 * @className MaterialTypeController
 * @modifier
 * @since 2019/11/2日22:47
 */
@RestController
@Api(tags = "(废弃)智能仓库-物料类型管理界面")
@RequestMapping(value = "/type")
@Slf4j
public class MaterialTypeController {
    @Autowired
    private IMaterialTypeService iMaterialTypeService;

    @ApiOperation(value = "新增物料类型", notes = "新增")
    @ApiImplicitParam(name = "materialTypeForm", value = "新增参数", required = true, dataType = "MaterialTypeForm")
    @ApiResponses(@ApiResponse(code = 200, message = "处理成功", response = Result.class))
    @PostMapping(value = "/add")
    public Result add(@Valid @RequestBody MaterialTypeForm materialTypeForm) {
        MaterialType materialType = materialTypeForm.toPo(MaterialType.class);
        return Result.success(iMaterialTypeService.add(materialType));
    }

    @ApiOperation(value = "更新物料类型", notes = "更新,仅能更新名称和代号,其余参数不识别")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "物料信息id", required = true, dataType = "long"),
            @ApiImplicitParam(name = "materialTypeForm", value = "更新参数", required = true, dataType = "MaterialTypeForm")})
    @ApiResponses(@ApiResponse(code = 200, message = "处理成功", response = Result.class))
    @PutMapping(value = "/{id}")
    public Result update(@PathVariable String id,@RequestBody MaterialTypeForm materialTypeForm) {
        MaterialType materialType = materialTypeForm.toPo(MaterialType.class);
        materialType.setId(id);
        return Result.success(iMaterialTypeService.update(materialType));
    }

    @ApiOperation(value = "仅供测试使用-自动新增物料类型", notes = "自动新增")
    @ApiImplicitParam(name = "typeListCode", value = "类型编码,括号内为该类型的子类型", required = true, dataType = "String",example = "RAW(TS(NN))")
    @ApiResponses(@ApiResponse(code = 200, message = "处理成功", response = Result.class))
    @PostMapping(value = "/autoAdd")
    public Result autoAdd(@RequestParam  String typeListCode) {
        return Result.success(iMaterialTypeService.autoAdd(typeListCode));
    }


    @ApiOperation(value = "查询所有根类型")
    @ApiResponses(@ApiResponse(code = 200, message = "处理成功", response = Result.class))
    @GetMapping(value = "/topLevel")
    public Result topLevel() {
        MaterialType materialType = MaterialType.builder().level(1).build();
        return Result.success(iMaterialTypeService.getAll(materialType));
    }

    @ApiOperation(value = "查询所有类型:树形")
    @ApiResponses(@ApiResponse(code = 200, message = "处理成功", response = Result.class))
    @GetMapping(value = "/tree")
    public Result allTree() {
        return Result.success(iMaterialTypeService.getAllTypeTree());
    }

    @ApiOperation(value = "查询目标类型链", notes = "根据主键")
    @ApiResponses(@ApiResponse(code = 200, message = "处理成功", response = Result.class))
    @ApiImplicitParam(type = "path", name = "id", value = "主键", required = true, dataType = "Long")
    @GetMapping(value = "/{id}")
    public Result query(@PathVariable Integer id) {
        return Result.success(iMaterialTypeService.getCompleteType(id));
    }

    @ApiOperation(value = "查询目标类型子类型:树形", notes = "根据主键")
    @ApiResponses(@ApiResponse(code = 200, message = "处理成功", response = Result.class))
    @ApiImplicitParam(type = "path", name = "id", value = "主键", required = true, dataType = "Long")
    @GetMapping(value = "/tree/{id}")
    public Result queryTree(@PathVariable Integer id) {
        return Result.success(iMaterialTypeService.getTypeTree(id));
    }


    @ApiOperation(value = "删除类型")
    @ApiResponses(@ApiResponse(code = 200, message = "处理成功", response = Result.class))
    @ApiImplicitParam(name = "id", value = "类型主键", required = true, dataType = "Long")
    @DeleteMapping(value = "/{id}")
    public Result delete(@PathVariable Integer id) {
        return Result.success(iMaterialTypeService.delete(id));
    }
}
