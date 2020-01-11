package com.jc.api.controller;

import com.jc.api.entity.form.MaterialInfoForm;
import com.jc.api.entity.form.MaterialInfoQueryForm;
import com.jc.api.entity.form.MaterialInfoUpdateForm;
import com.jc.api.entity.param.MaterialInfoQueryParam;
import com.jc.api.entity.po.MaterialInfo;
import com.jc.api.service.restservice.IMaterialInfoService;
import com.jinchi.common.core.vo.Result;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author XudongHu
 * @className MaterialInfoController
 * @apiNote 物料详情controller
 * @modifer
 * @since 2019/10/31日16:32
 */
@RestController
@Api(tags = "智能仓库-物料信息管理界面")
@RequestMapping(value = "/materialInfo")
@Slf4j
public class MaterialInfoController {
    @Autowired
    private IMaterialInfoService iMaterialInfoService;


    @ApiOperation(value = "新增物料信息", notes = "新增")
    @ApiImplicitParam(name = "materialInfoForm", value = "新增参数", required = true, dataType = "MaterialInfoForm")
    @ApiResponses(@ApiResponse(code = 200, message = "处理成功", response = Result.class))
    @PostMapping(value = "/add")
    public Result add(@Valid @RequestBody MaterialInfoForm materialInfoForm) {
        MaterialInfo materialInfo = materialInfoForm.toPo(MaterialInfo.class);
        return Result.success(iMaterialInfoService.add(materialInfo));
    }

    @ApiOperation(value = "仅供测试使用-自动新增物料信息", notes = "自动新增")
    @ApiImplicitParam(name = "materialInfo", value = "新增参数", required = true, dataType = "MaterialInfo")
    @ApiResponses(@ApiResponse(code = 200, message = "处理成功", response = Result.class))
    @PostMapping(value = "/autoAdd")
    public Result autoAdd(@RequestBody MaterialInfo materialInfo) {
        return Result.success(iMaterialInfoService.autoAdd(materialInfo));
    }

    @ApiOperation(value = "更新物料信息", notes = "更新")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "物料信息id", required = true, dataType = "long"),
            @ApiImplicitParam(name = "materialInfoUpdateForm", value = "更新参数", required = true, dataType = "MaterialInfoUpdateForm")})
    @ApiResponses(@ApiResponse(code = 200, message = "处理成功", response = Result.class))
    @PutMapping(value = "/{id}")
    public Result update(@PathVariable String id, @RequestBody MaterialInfoUpdateForm materialInfoUpdateForm) {
        MaterialInfo materialInfo = materialInfoUpdateForm.toPo(MaterialInfo.class);
        materialInfo.setId(id);
        return Result.success(iMaterialInfoService.update(materialInfo));
    }

    @ApiOperation(value = "获取所有物料信息", notes = "获取所有记录")
    @ApiResponses(@ApiResponse(code = 200, message = "处理成功", response = Result.class))
    @GetMapping(value = "/all")
    public Result all() {
        return Result.success(iMaterialInfoService.getAllVo(new MaterialInfoQueryParam()));
    }

    @ApiOperation(value = "获取物料信息-条件", notes = "条件获取物料信息记录")
    @ApiImplicitParam(name = "materialInfoQueryForm", value = "查询条件", required = true, dataType = "MaterialInfoQueryForm")
    @ApiResponses(@ApiResponse(code = 200, message = "处理成功", response = Result.class))
    @PostMapping(value = "/conditions")
    public Result all(@Valid @RequestBody MaterialInfoQueryForm materialInfoQueryForm) {
        log.debug("条件获取物料记录:{}", materialInfoQueryForm);
        return Result.success(iMaterialInfoService.getAllVo(materialInfoQueryForm.toParam(MaterialInfoQueryParam.class)));
    }

    @ApiOperation(value = "获取物料信息-条件/分页", notes = "条件获取物料信息记录-分页")
    @ApiImplicitParam(name = "materialInfoQueryForm", value = "查询条件", required = true, dataType = "MaterialInfoQueryForm")
    @ApiResponses(@ApiResponse(code = 200, message = "处理成功", response = Result.class))
    @PostMapping(value = "/pages")
    public Result queryPages(@Valid @RequestBody MaterialInfoQueryForm materialInfoQueryForm) {
        log.debug("条件获取物料记录分页:{}", materialInfoQueryForm);
        return Result.success(iMaterialInfoService.getAllVoByPage(materialInfoQueryForm.getPage(), materialInfoQueryForm.toParam(MaterialInfoQueryParam.class)));
    }

    @ApiOperation(value = "删除物料信息", notes = "根据id删除物料信息")
    @ApiImplicitParam(name = "id", value = "物料信息id", required = true, dataType = "long")
    @ApiResponses(@ApiResponse(code = 200, message = "处理成功", response = Result.class))
    @DeleteMapping(value = "/{id}")
    public Result delete(@PathVariable Integer id) {
        log.debug("根据id删除物料记录:{}", id);
        return Result.success(iMaterialInfoService.delete(id));
    }

}
