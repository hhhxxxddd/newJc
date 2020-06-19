package com.jinchi.common.controller;

import com.jinchi.common.domain.TechniqueBaseRawManufacturer;
import com.jinchi.common.domain.TechniqueBaseRawMaterial;
import com.jinchi.common.domain.TestItem;
import com.jinchi.common.dto.CommonBatchNumberDTO;
import com.jinchi.common.dto.Result;
import com.jinchi.common.dto.technique.TechniqueBaseRawManufacturerDTO;
import com.jinchi.common.dto.technique.TechniqueRawStandardRecordDTO;
import com.jinchi.common.service.TechniqueRawStandardRecordService;
import com.jinchi.common.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

/**
 * @author：XudongHu
 * @className:TechniqueRawStandardRecordController
 * @description: 原材料标准接口
 * @date:14:27 2018/12/28
 */
@RestController
@RequestMapping(value = "/techRawStandard")
@Api(tags = "技术中心-原材料标准")
public class TechniqueRawStandardRecordController {
    @Autowired
    private TechniqueRawStandardRecordService techniqueRawStandardRecordService;

    /**
     * 新增原料
     */
    @PostMapping(value = "/newRaw")
    @ApiOperation(value = "新增原料")
    public Result<TechniqueBaseRawMaterial> addRaw(@RequestParam Integer serialNumberId, @RequestParam Integer[] testItemIds) {
        return ResultUtil.success(techniqueRawStandardRecordService.addNewRawMaterial(serialNumberId, Arrays.asList(testItemIds)));
    }

    @PostMapping(value = "/updateRaw")
    @ApiOperation(value = "原材料更新名称")
    public Result<TechniqueBaseRawMaterial> updateRaw(@RequestBody TechniqueBaseRawMaterial rawMaterial) {
        return ResultUtil.success(techniqueRawStandardRecordService.updateRaw(rawMaterial));
    }

    @PostMapping(value = "/addRaw")
    @ApiOperation(value = "新增原料")
    public Result<TechniqueBaseRawMaterial> addRawExtra(@RequestParam String materialName, @RequestParam Integer[] testItemIds) {
        return ResultUtil.success(techniqueRawStandardRecordService.addRowExtra(materialName, testItemIds));
    }

    @DeleteMapping(value = "/deleteRaw")
    @ApiOperation(value = "删除原料")
    public Result deleteRawExtra(@RequestParam Integer materialId) {
        int value = techniqueRawStandardRecordService.deleteRawExtra(materialId);
        if (value == -1) {
            return ResultUtil.error("存在生产厂家，请先删除该原材料下所有生产厂家");
        }
        return ResultUtil.success();
    }

    @DeleteMapping(value = "/deleteManufacturer")
    @ApiOperation(value = "删除工厂")
    public Result deleteManufacturer(@RequestParam Integer manufacturerId) {
        int value = techniqueRawStandardRecordService.deleteManufacturer(manufacturerId);
        if (value == -1) {
            return ResultUtil.error("存在原材料标准，不可删除");
        }
        return ResultUtil.success();
    }

    /**
     * 新增原料工厂
     */
    @PostMapping(value = "/newManufacturer")
    @ApiOperation(value = "新增工厂")
    public Result<TechniqueBaseRawManufacturer> addManufacturer(@Valid @RequestBody TechniqueBaseRawManufacturerDTO techniqueBaseRawManufacturerDTO) {
        return ResultUtil.success(techniqueRawStandardRecordService.addNewRawManufacturer(techniqueBaseRawManufacturerDTO));
    }


    @PostMapping(value = "/editManufacturer")
    @ApiOperation(value = "编辑工厂名称")
    public Result<TechniqueBaseRawManufacturer> editManufacturer(@Valid @RequestBody TechniqueBaseRawManufacturer techniqueBaseRawManufacturer) {
        return ResultUtil.success(techniqueRawStandardRecordService.editManufacturer(techniqueBaseRawManufacturer));
    }

    /**
     * 查询所有原料
     *
     * @param name 名称模糊
     * @return
     */
    @GetMapping(value = "/raw")
    @ApiOperation(value = "查询所有原料")
    public Result<List<TechniqueBaseRawMaterial>> allRaw(@ApiParam(name = "name", value = "原料名称") @RequestParam(required = false) String name) {
        return ResultUtil.success(techniqueRawStandardRecordService.baseRawMaterialNameLike(name));
    }

    /**
     * 查询所有工厂
     * @param name 名称模糊
     * @return
     */
    @GetMapping(value = "/manufacturers")
    @ApiOperation(value = "查询所有原料")
    public Result<List<TechniqueBaseRawManufacturer>> allRawManufacturer(@ApiParam(name = "name", value = "工厂名称") @RequestParam(required = false) String name) {
        return ResultUtil.success(techniqueRawStandardRecordService.baseRawManufacturerNameLike(name));
    }

    @GetMapping(value = "/manufacturerByRawId")
    @ApiOperation(value = "根据原材料ID查询对应的生产厂家")
    public Result<List<TechniqueBaseRawManufacturer>> getManufacturersByRawId(@ApiParam(name = "rawMaterialId", value = "原材料id") @RequestParam Integer rawMaterialId) {
        return ResultUtil.success(techniqueRawStandardRecordService.baseRawManufacturerById(rawMaterialId));
    }


    /**
     * 查询所有标准
     *
     * @param name 人名称模糊
     * @return
     */
    @GetMapping
    @ApiOperation(value = "查询所有标准",notes = "创建人名称模糊,并且需要传入工厂和原料")
    public Result<List<CommonBatchNumberDTO>> allStandards(@ApiParam(name = "name",value = "人名") @RequestParam(required = false) String name,
                                                           @ApiParam(name = "materialId",value = "原材料id") @RequestParam Integer materialId,
                                                           @ApiParam(name = "factoryId",value = "工厂id") @RequestParam Integer factoryId){
        return ResultUtil.success(techniqueRawStandardRecordService.allRawStandardPersonNameLike(name,materialId,factoryId));
    }

    /**
     * 标准详情
     * @param batchNumberId 批号id
     * @return
     */
    @GetMapping(value = "/{batchNumberId}")
    @ApiOperation(value = "标准详情")
    public Result<CommonBatchNumberDTO> details(@ApiParam(name = "batchNumberId",value = "批号id") @PathVariable Integer batchNumberId){
        return ResultUtil.success(techniqueRawStandardRecordService.rawStandardDetail(batchNumberId));
    }

    /**
     * 新增/迭代 标准
     * @param commonBatchNumberDTO
     * @return
     */
    @PostMapping
    @ApiOperation(value = "新增/迭代标准")
    public Result<CommonBatchNumberDTO> newStandard(@ApiParam(value = "批号DTO") @Valid @RequestBody CommonBatchNumberDTO<TechniqueRawStandardRecordDTO> commonBatchNumberDTO){
        return ResultUtil.success(techniqueRawStandardRecordService.addRawStandard(commonBatchNumberDTO));
    }

    /**
     * 更新标准
     * @param commonBatchNumberDTO
     * @return
     */
    @PutMapping
    @ApiOperation(value = "更新标准")
    public Result<CommonBatchNumberDTO> updateStandard(@ApiParam(value = "批号DTO") @Valid @RequestBody CommonBatchNumberDTO<TechniqueRawStandardRecordDTO> commonBatchNumberDTO){
        return ResultUtil.success(techniqueRawStandardRecordService.updateRawStandard(commonBatchNumberDTO));
    }

    /**
     * 查看原材料主成分
     * @param rawId
     * @return
     */
    @GetMapping(value = "/rawItems")
    @ApiOperation(value = "原料主成分")
    public Result<List<TestItem>> rawIngredient(@ApiParam(value = "原材料id",name = "rawId") @RequestParam Integer rawId){
        return ResultUtil.success(techniqueRawStandardRecordService.rawIngredient(rawId));
    }

    @GetMapping(value = "/getCurrentRawStandard")
    @ApiOperation(value = "获取当前原材料标准")
    public Result getCurrentRawStandard(){
        return ResultUtil.success(techniqueRawStandardRecordService.getCurrentRawStandard());
    }

    @GetMapping(value = "/getItemsByRawId")
    @ApiOperation(value = "根据原材料ID查询检测项目")
    public Result getItemsByRawId(@RequestParam Integer rawId) {
        return ResultUtil.success(techniqueRawStandardRecordService.getItemsByRawId(rawId));
    }

}
