package com.jinchi.common.controller;

import com.jinchi.common.domain.TechniqueBaseRawManufacturer;
import com.jinchi.common.domain.TechniqueBaseRawMaterial;
import com.jinchi.common.domain.TestItem;
import com.jinchi.common.dto.CommonBatchNumberDTO;
import com.jinchi.common.dto.Result;
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
    public Result<TechniqueBaseRawMaterial> addRaw( @RequestParam Integer serialNumberId,@RequestParam Integer[] testItemIds){
        return ResultUtil.success(techniqueRawStandardRecordService.addNewRawMaterial(serialNumberId, Arrays.asList(testItemIds)));
    }

    @PostMapping(value = "/addRaw")
    @ApiOperation(value = "新增原料")
    public Result<TechniqueBaseRawMaterial> addRawExtra( @RequestParam String materialName,@RequestParam Integer[] testItemIds){
        return ResultUtil.success(techniqueRawStandardRecordService.addRowExtra(materialName,testItemIds));
    }

    /**
     * 新增原料工厂
     */
    @PostMapping(value = "/newManufacturer")
    @ApiOperation(value = "新增工厂")
    public Result<TechniqueBaseRawManufacturer> addManufacturer(@Valid @RequestBody TechniqueBaseRawManufacturer techniqueBaseRawManufacturer){
        return ResultUtil.success(techniqueRawStandardRecordService.addNewRawManufacturer(techniqueBaseRawManufacturer));
    }

    /**
     * 查询所有原料
     * @param name 名称模糊
     * @return
     */
    @GetMapping(value = "/raw")
    @ApiOperation(value = "查询所有原料")
    public Result<List<TechniqueBaseRawMaterial>> allRaw(@ApiParam(name = "name",value = "原料名称") @RequestParam(required = false) String name){
        return ResultUtil.success(techniqueRawStandardRecordService.baseRawMaterialNameLike(name));
    }

    /**
     * 查询所有工厂
     * @param name 名称模糊
     * @return
     */
    @GetMapping(value = "/manufacturers")
    @ApiOperation(value = "查询所有原料")
    public Result<List<TechniqueBaseRawManufacturer>> allRawManufacturer(@ApiParam(name = "name",value = "工厂名称") @RequestParam(required = false) String name){
        return ResultUtil.success(techniqueRawStandardRecordService.baseRawManufacturerNameLike(name));
    }







    /**
     * 查询所有标准
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
