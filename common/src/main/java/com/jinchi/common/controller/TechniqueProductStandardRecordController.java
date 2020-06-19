package com.jinchi.common.controller;

import com.jinchi.common.domain.TechniqueBaseProductClass;
import com.jinchi.common.dto.CommonBatchNumberDTO;
import com.jinchi.common.dto.Result;
import com.jinchi.common.dto.technique.TechniqueBaseProductClassDTO;
import com.jinchi.common.dto.technique.TechniqueProductStandardRecordDTO;
import com.jinchi.common.service.TechniqueProductStandardRecordService;
import com.jinchi.common.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author：XudongHu
 * @className:TechniqueProductStandardRecordController
 * @description:
 * @date:22:56 2018/12/28
 */
@RestController
@RequestMapping(value = "/techProductStandard")
@Api(tags = "技术中心-产品标准")
public class TechniqueProductStandardRecordController {
    @Autowired
    private TechniqueProductStandardRecordService techniqueProductStandardRecordService;

    /**
     * 新增型号
     */
    @PostMapping(value = "/newClass")
    @ApiOperation(value = "新增型号")
    public Result<TechniqueBaseProductClass> addClass(@Valid @RequestBody TechniqueBaseProductClassDTO techniqueBaseProductClass) {
        return ResultUtil.success(techniqueProductStandardRecordService.newClass(techniqueBaseProductClass));
    }


    /**
     * 编辑型号
     */
    @PostMapping(value = "/editClass")
    @ApiOperation(value = "新增型号")
    public Result<TechniqueBaseProductClass> addClass(@Valid @RequestBody TechniqueBaseProductClass techniqueBaseProductClass) {
        return ResultUtil.success(techniqueProductStandardRecordService.editClass(techniqueBaseProductClass));
    }

    /**
     * 删除型号
     */
    @DeleteMapping(value = "/deleteClass")
    @ApiOperation(value = "删除型号")
    public Result<TechniqueBaseProductClass> deleteClass(@Valid @RequestParam Integer classId) {
        Integer value = techniqueProductStandardRecordService.deleteClass(classId);
        if (value == -1) {
            return ResultUtil.error("存在成品标准，不可删除");
        }
        return ResultUtil.success();
    }

    /**
     * 查询所有型号
     */
    @GetMapping(value = "/allClasses")
    @ApiOperation(value = "查询所有型号", notes = "根据名称模糊,需要输入父型号")
    public Result<List<TechniqueBaseProductClass>> allClasses(@ApiParam(name = "name", value = "型号名称") @RequestParam(required = false) String name,
                                                              @ApiParam(name = "parentId", value = "父型号id") @RequestParam(required = false) Integer parentId) {
        return ResultUtil.success(techniqueProductStandardRecordService.findAllClass(name, parentId));
    }

    @GetMapping(value = "/getClassesById")
    @ApiOperation(value = "根据成品 ID 查询对应的型号")
    public Result<List<TechniqueBaseProductClass>> getClassesById(@ApiParam(name = "productId", value = "成品id") @RequestParam Integer productId) {
        return ResultUtil.success(techniqueProductStandardRecordService.byProductId(productId));
    }

    /**
     * 新增/迭代 成品标准
     */
    @PostMapping
    @ApiOperation(value = "新增标准")
    public Result<CommonBatchNumberDTO> newProductStandard(@Valid @RequestBody CommonBatchNumberDTO<TechniqueProductStandardRecordDTO> commonBatchNumberDTO) {
        return ResultUtil.success(techniqueProductStandardRecordService.newProductStandard(commonBatchNumberDTO));
    }

    /**
     * 更新 标准
     */
    @PutMapping
    @ApiOperation(value = "更新标准")
    public Result<CommonBatchNumberDTO> updateProductStandard(@Valid @RequestBody CommonBatchNumberDTO<TechniqueProductStandardRecordDTO> commonBatchNumberDTO){
        return ResultUtil.success(techniqueProductStandardRecordService.updateProductStandard(commonBatchNumberDTO));
    }

    /**
     * 标准 详情
     */
    @GetMapping(value = "/{batchNumberId}")
    @ApiOperation(value = "标准详情")
    public Result<CommonBatchNumberDTO> detail(@ApiParam(name = "batchNumberId",value = "批号id") @PathVariable Integer batchNumberId){
        return ResultUtil.success(techniqueProductStandardRecordService.byBatchNumberId(batchNumberId));
    }

    /**
     * 查询所有
     */
    @GetMapping
    @ApiOperation(value = "查询所有标准",notes = "根据创建人名称模糊,并需要传入成品id和型号id")
    public Result<List<CommonBatchNumberDTO>> allStandards(@ApiParam(name = "name",value = "人名") @RequestParam(required = false) String name,
                                                           @ApiParam(name = "productId",value = "成品id") @RequestParam Integer productId,
                                                           @ApiParam(name = "classId",value = "型号id") @RequestParam Integer classId){
        return ResultUtil.success(techniqueProductStandardRecordService.byNameLikeAndMaterialIdAndClassId(name,productId,classId));
    }

}
