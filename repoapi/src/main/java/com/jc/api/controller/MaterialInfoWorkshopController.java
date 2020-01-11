package com.jc.api.controller;

import com.jc.api.entity.form.MaterialInfoWorkshopForm;
import com.jc.api.entity.form.MaterialInfoWorkshopQueryForm;
import com.jc.api.entity.param.MaterialInfoWorkshopQueryParam;
import com.jc.api.entity.po.MaterialInfoWorkshop;
import com.jc.api.service.restservice.IMaterialInfoWorkshopService;
import com.jinchi.common.core.vo.Result;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

/**
 * @author XudongHu
 * @apiNote 物料供货车间controller
 * @className MaterialInfoWorkshopController
 * @modifier
 * @since 19.12.8日1:46
 */
@RestController
@Api(tags = "(废弃)智能仓库-物料供应车间管理界面")
@RequestMapping(value = "/materialInfoWorkshop")
@Slf4j
public class MaterialInfoWorkshopController {
    @Autowired
    private IMaterialInfoWorkshopService iMaterialInfoWorkshopService;


    @ApiOperation(value = "获取所有供应车间", notes = "获取所有供应车间")
    @ApiResponses(@ApiResponse(code = 200, message = "处理成功", response = Result.class))
    @GetMapping(value = "/all")
    public Result all() {
        return Result.success(iMaterialInfoWorkshopService.getAll(new MaterialInfoWorkshopQueryParam()));
    }

    @ApiOperation(value = "获取供应车间-条件", notes = "条件获取所有供应车间")
    @ApiResponses(@ApiResponse(code = 200, message = "处理成功", response = Result.class))
    @ApiImplicitParam(name = "materialInfoWorkshopQueryForm", value = "查询条件", required = true, dataType = "MaterialInfoWorkshopQueryForm")
    @PostMapping(value = "/conditions")
    public Result query(@Valid @RequestBody MaterialInfoWorkshopQueryForm materialInfoWorkshopQueryForm) {
        log.debug("条件获取供应车间:{}",materialInfoWorkshopQueryForm);
        return Result.success(iMaterialInfoWorkshopService.getAll(materialInfoWorkshopQueryForm.toParam(MaterialInfoWorkshopQueryParam.class)));
    }

    @ApiOperation(value = "获取供应车间-条件/分页", notes = "条件获取供应车间记录-分页")
    @ApiImplicitParam(name = "materialInfoWorkshopQueryForm", value = "查询条件", required = true, dataType = "MaterialInfoWorkshopQueryForm")
    @ApiResponses(@ApiResponse(code = 200, message = "处理成功", response = Result.class))
    @PostMapping(value = "/pages")
    public Result queryPages(@Valid @RequestBody MaterialInfoWorkshopQueryForm materialInfoWorkshopQueryForm) {
        log.debug("条件获取供应车间分页:{}", materialInfoWorkshopQueryForm);
        return Result.success(iMaterialInfoWorkshopService.getAllByPage(materialInfoWorkshopQueryForm.getPage(),materialInfoWorkshopQueryForm.toParam(MaterialInfoWorkshopQueryParam.class)));
    }

    @ApiOperation(value = "新增供应车间信息", notes = "新增")
    @ApiImplicitParam(name = "materialInfoWorkshopForm", value = "新增参数", required = true, dataType = "MaterialInfoWorkshopForm")
    @ApiResponses(@ApiResponse(code = 200, message = "处理成功", response = Result.class))
    @PostMapping(value = "/add")
    public Result add(@Valid @RequestBody MaterialInfoWorkshopForm materialInfoWorkshopForm) {
        MaterialInfoWorkshop materialInfoWorkshop = materialInfoWorkshopForm.toPo(MaterialInfoWorkshop.class);
        return Result.success(iMaterialInfoWorkshopService.add(materialInfoWorkshop));
    }

    @ApiOperation(value = "仅供测试使用-自动新增供应车间信息", notes = "自动新增,存在则无动作,不存在则新增")
    @ApiImplicitParam(name = "materialWorkshopCode", value = "供货车间代号", required = true, dataType = "String")
    @ApiResponses(@ApiResponse(code = 200, message = "处理成功", response = Result.class))
    @PostMapping(value = "/autoAdd")
    public Result autoAdd(@RequestParam @NotBlank(message = "供应车间代号不能为空白") String materialWorkshopCode) {
        MaterialInfoWorkshop workshop = MaterialInfoWorkshop.builder().materialWorkshopCode(materialWorkshopCode).build();
        return Result.success(iMaterialInfoWorkshopService.autoAdd(workshop));
    }

    @ApiOperation(value = "更新供货商信息", notes = "更新")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "供货商信息id", required = true, dataType = "long"),
            @ApiImplicitParam(name = "materialInfoWorkshopForm", value = "更新参数", required = true, dataType = "MaterialInfoWorkshopForm")})
    @ApiResponses(@ApiResponse(code = 200, message = "处理成功", response = Result.class))
    @PutMapping(value = "/{id}")
    public Result update(@PathVariable String id,@RequestBody MaterialInfoWorkshopForm materialInfoWorkshopForm) {
        MaterialInfoWorkshop materialInfoWorkshop = materialInfoWorkshopForm.toPo(MaterialInfoWorkshop.class);
        materialInfoWorkshop.setId(id);
        return Result.success(iMaterialInfoWorkshopService.update(materialInfoWorkshop));
    }

    @ApiOperation(value = "删除供应车间信息", notes = "根据id删除供应车间信息")
    @ApiImplicitParam(name = "id", value = "供应车间信息id", required = true, dataType = "long")
    @ApiResponses(@ApiResponse(code = 200, message = "处理成功", response = Result.class))
    @DeleteMapping(value = "/{id}")
    public Result delete(@PathVariable Integer id) {
        log.debug("根据id删除供应车间记录:{}", id);
        return Result.success(iMaterialInfoWorkshopService.delete(id));
    }
}
