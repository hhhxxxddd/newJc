package com.jc.api.controller;

import com.jc.api.entity.form.MaterialInfoSupplierForm;
import com.jc.api.entity.form.MaterialInfoSupplierQueryForm;
import com.jc.api.entity.param.MaterialInfoSupplierQueryParam;
import com.jc.api.entity.po.MaterialInfoSupplier;
import com.jc.api.service.restservice.IMaterialInfoSupplierService;
import com.jinchi.common.core.vo.Result;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

/**
 * @author XudongHu
 * @apiNote 物料入库供货商controller
 * @className MaterialInfoSupplierController
 * @modifier
 * @since 19.12.8日1:45
 */
@RestController
@Api(tags = "(废弃)智能仓库-物料供货商管理界面")
@RequestMapping(value = "/materialInfoSupplier")
@Slf4j
public class MaterialInfoSupplierController {
    @Autowired
    private IMaterialInfoSupplierService iMaterialInfoSupplierService;


    @ApiOperation(value = "获取所有供货商", notes = "获取所有供货商")
    @ApiResponses(@ApiResponse(code = 200, message = "处理成功", response = Result.class))
    @GetMapping(value = "/all")
    public Result all() {
        return Result.success(iMaterialInfoSupplierService.getAll(new MaterialInfoSupplierQueryParam()));
    }

    @ApiOperation(value = "获取供货商-条件", notes = "条件获取所有供货商")
    @ApiResponses(@ApiResponse(code = 200, message = "处理成功", response = Result.class))
    @ApiImplicitParam(name = "materialInfoSupplierQueryForm", value = "查询条件", required = true, dataType = "MaterialInfoSupplierQueryForm")
    @PostMapping(value = "/conditions")
    public Result query(@Valid @RequestBody MaterialInfoSupplierQueryForm materialInfoSupplierQueryForm) {
        log.debug("条件获取供货商:{}",materialInfoSupplierQueryForm);
        return Result.success(iMaterialInfoSupplierService.getAll(materialInfoSupplierQueryForm.toParam(MaterialInfoSupplierQueryParam.class)));
    }

    @ApiOperation(value = "获取供货商-条件/分页", notes = "条件获取供货商记录-分页")
    @ApiImplicitParam(name = "materialInfoSupplierQueryForm", value = "查询条件", required = true, dataType = "MaterialInfoSupplierQueryForm")
    @ApiResponses(@ApiResponse(code = 200, message = "处理成功", response = Result.class))
    @PostMapping(value = "/pages")
    public Result queryPages(@Valid @RequestBody MaterialInfoSupplierQueryForm materialInfoSupplierQueryForm) {
        log.debug("条件获取供货商分页:{}", materialInfoSupplierQueryForm);
        return Result.success(iMaterialInfoSupplierService.getAllByPage(materialInfoSupplierQueryForm.getPage(),materialInfoSupplierQueryForm.toParam(MaterialInfoSupplierQueryParam.class)));
    }
    //新增供货商
    @ApiOperation(value = "新增供货商信息", notes = "新增")
    @ApiImplicitParam(name = "materialInfoSupplierForm", value = "新增参数", required = true, dataType = "MaterialInfoSupplierForm")
    @ApiResponses(@ApiResponse(code = 200, message = "处理成功", response = Result.class))
    @PostMapping(value = "/add")
    public Result add(@Valid @RequestBody MaterialInfoSupplierForm materialInfoSupplierForm) {
        MaterialInfoSupplier materialInfoSupplier = materialInfoSupplierForm.toPo(MaterialInfoSupplier.class);
        return Result.success(iMaterialInfoSupplierService.add(materialInfoSupplier));
    }

    @ApiOperation(value = "仅供测试使用-自动新增供货商信息", notes = "自动新增,存在则无动作,不存在则新增")
    @ApiImplicitParam(name = "materialSupplierCode", value = "供货商代号", required = true, dataType = "String")
    @ApiResponses(@ApiResponse(code = 200, message = "处理成功", response = Result.class))
    @PostMapping(value = "/autoAdd")
    public Result autoAdd(@RequestParam @NotBlank(message = "供货商代号不能为空白") String materialSupplierCode) {
        MaterialInfoSupplier supplier = MaterialInfoSupplier.builder().materialSupplierCode(materialSupplierCode).build();
        return Result.success(iMaterialInfoSupplierService.autoAdd(supplier));
    }

    @ApiOperation(value = "更新供货商信息", notes = "更新")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "供货商信息id", required = true, dataType = "long"),
            @ApiImplicitParam(name = "materialInfoSupplierForm", value = "更新参数", required = true, dataType = "MaterialInfoSupplierForm")})
    @ApiResponses(@ApiResponse(code = 200, message = "处理成功", response = Result.class))
    @PutMapping(value = "/{id}")
    public Result update(@PathVariable String id,@RequestBody MaterialInfoSupplierForm materialInfoSupplierForm) {
        MaterialInfoSupplier materialInfoSupplier = materialInfoSupplierForm.toPo(MaterialInfoSupplier.class);
        materialInfoSupplier.setId(id);
        return Result.success(iMaterialInfoSupplierService.update(materialInfoSupplier));
    }

    @ApiOperation(value = "删除供货商信息", notes = "根据id删除供货商信息")
    @ApiImplicitParam(name = "id", value = "供货商信息id", required = true, dataType = "long")
    @ApiResponses(@ApiResponse(code = 200, message = "处理成功", response = Result.class))
    @DeleteMapping(value = "/{id}")
    public Result delete(@PathVariable Integer id) {
        log.debug("根据id删除供货商记录:{}", id);
        return Result.success(iMaterialInfoSupplierService.delete(id));
    }

}
