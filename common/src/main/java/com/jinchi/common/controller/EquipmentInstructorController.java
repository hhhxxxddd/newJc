package com.jinchi.common.controller;

import com.jinchi.common.dto.PageBean;
import com.jinchi.common.dto.Result;
import com.jinchi.common.dto.equipment.EquipmentInstructorDTO;
import com.jinchi.common.service.EquipmentInstructorRecordService;
import com.jinchi.common.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author：XudongHu
 * @className:EquipmentInstructorController
 * @description: 设备指导书
 * @date:17:13 2019/3/5
 * @Modifer:
 */
@RestController
@RequestMapping(value = "/instructor")
@Api(tags = "设备管理-设备指导")
public class EquipmentInstructorController {
    @Autowired
    private EquipmentInstructorRecordService equipmentInstructorRecordService;

    @PostMapping
    @ApiOperation(value = "新增指导")
    public Result<EquipmentInstructorDTO> insert(@ApiParam(value = "设备指导DTO") @Valid @RequestBody EquipmentInstructorDTO equipmentInstructorDTO) {
        return ResultUtil.success(equipmentInstructorRecordService.insert(equipmentInstructorDTO));
    }

    @PutMapping
    @ApiOperation(value = "更新指导")
    public Result<EquipmentInstructorDTO> update(@ApiParam(value = "设备指导DTO") @Valid @RequestBody EquipmentInstructorDTO equipmentInstructorDTO){
        return ResultUtil.success(equipmentInstructorRecordService.update(equipmentInstructorDTO));
    }

    @PostMapping(value = "/uploadPic")
    @ApiOperation(value = "上传图片",notes = "在新增界面应该多次调用这个接口")
    public Result<String> uploadPic(@ApiParam(value = "图片") @RequestParam MultipartFile file) throws IOException {
        return ResultUtil.success(equipmentInstructorRecordService.uploadPic(file));
    }

    @DeleteMapping(value = "/deletePic")
    @ApiOperation(value = "取消上传",notes = "取消上传应该调用这个接口")
    public Result<String> uploadPic(@ApiParam(value = "文件名称") @RequestBody List<String> fileNames) throws IOException {
        return ResultUtil.success(equipmentInstructorRecordService.cancelUpload(fileNames));
    }

    @GetMapping
    @ApiOperation(value = "查询所有",notes = "分页")
    public Result<PageBean> byNameLike(@ApiParam(value = "指导书名称",name = "instructorName") @RequestParam(required = false) String instructorName,
                                       @ApiParam(value = "分页类") PageBean pageBean){
        return ResultUtil.success(equipmentInstructorRecordService.byNameLikeByPage(instructorName, pageBean));
    }

    @GetMapping(value = "/{batchNumberId}")
    @ApiOperation(value = "查询",notes = "根据批号id")
    public Result<Map<Object,Object>> byBatchNumberId(@ApiParam(value = "批号id",name = "batchNumberId") @PathVariable Integer batchNumberId){
        return ResultUtil.success(equipmentInstructorRecordService.byBatchNumberId(batchNumberId));
    }

    @DeleteMapping(value = "/{batchNumberId}")
    @ApiOperation(value = "删除",notes = "根据批号id")
    public Result<String> deleteByBatchNumberId(@ApiParam(value = "批号id",name = "batchNumberId") @PathVariable Integer batchNumberId){
        return ResultUtil.success(equipmentInstructorRecordService.delete(batchNumberId));
    }

//    @DeleteMapping
//    @ApiOperation(value = "批量删除",notes = "主键数组")
//    public Result<String> batchDelete(@ApiParam(value = "主键数组") @RequestParam Integer[] ids){
//        return ResultUtil.success(equipmentInstructorRecordService.batchDelete(Arrays.asList(ids)));
//    }


}
