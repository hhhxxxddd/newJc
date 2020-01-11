package com.jinchi.common.controller;

import com.jinchi.common.domain.EquipmentArchiveRecord;
import com.jinchi.common.dto.PageBean;
import com.jinchi.common.dto.Result;
import com.jinchi.common.dto.equipment.EquipmentArchiveDTO;
import com.jinchi.common.service.EquipmentArchiveRecordService;
import com.jinchi.common.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 * @author：XudongHu
 * @className:EquipmentArchiveRecordController
 * @description: 设备指导
 * @date:17:03 2019/1/15
 * @Modifer:
 */
@RestController
@RequestMapping(value = "/equipmentArchiveRecord")
@Api(tags = "设备管理-设备档案")
public class EquipmentArchiveRecordController {
    @Autowired
    private EquipmentArchiveRecordService equipmentArchiveRecordService;

    @ApiOperation(value = "新增")
    @PostMapping
    public Result<EquipmentArchiveRecord> add(@ApiParam(value = "设备档案实体") @Valid EquipmentArchiveRecord equipmentArchiveRecord,
                                              @ApiParam(value = "上传文件") MultipartFile file) throws IOException {
        return ResultUtil.success(equipmentArchiveRecordService.add(equipmentArchiveRecord, file));
    }

    @ApiOperation(value = "更新")
    @PutMapping
    public Result<EquipmentArchiveRecord> update(@ApiParam(value = "设备档案实体") @Valid EquipmentArchiveRecord equipmentArchiveRecord,
                                                 @ApiParam(value = "上传文件", name = "file") @RequestParam(required = false) MultipartFile file) throws IOException {
        return ResultUtil.success(equipmentArchiveRecordService.update(equipmentArchiveRecord, file));
    }

    @ApiOperation(value = "查询所有")
    @GetMapping
    public Result<List<EquipmentArchiveDTO>> all() {
        return ResultUtil.success(equipmentArchiveRecordService.all(null, null));
    }

    @ApiOperation(value = "查询所有-分页")
    @GetMapping(value = "/pages")
    public Result<PageBean> pages(@ApiParam(name = "name", value = "档案名称") @RequestParam(required = false) String name, PageBean pageBean) {
        return ResultUtil.success(equipmentArchiveRecordService.pages(pageBean, name));
    }

    @ApiOperation(value = "详情")
    @GetMapping(value = "/{id}")
    public Result<EquipmentArchiveDTO> byId(@ApiParam(name = "id", value = "主键") @PathVariable Integer id) {
        return ResultUtil.success(equipmentArchiveRecordService.byId(id));
    }


    @ApiOperation(value = "删除")
    @DeleteMapping(value = "/{id}")
    public Result<EquipmentArchiveDTO> delete(@ApiParam(name = "id", value = "主键") @PathVariable Integer id) {
        equipmentArchiveRecordService.deleteById(id);
        return ResultUtil.success();
    }


    @ApiOperation(value = "批量删除")
    @DeleteMapping
    public Result<EquipmentArchiveDTO> batchDelete(@ApiParam(name = "ids", value = "主键数组") @RequestBody List<Integer> ids) {
        equipmentArchiveRecordService.batchDelete(ids);
        return ResultUtil.success();
    }

    /**
     * 查看手册
     *
     * @param id 主键
     */
    @ApiOperation(value = "访问手册")
    @GetMapping(value = "/manual")
    public ModelAndView pdf(@ApiParam(value = "主键", name = "id") @RequestParam Integer id) {
        return new ModelAndView("redirect:" + "/equipmentArchiveRecord/pdf/" + equipmentArchiveRecordService.manual(id));
//        return new ModelAndView(new RedirectView("http://www.baidu.com"));
    }

    /**
     * 下载pdf
     *
     * @param filename 文件名
     * @return
     * @throws FileNotFoundException
     */
    @ApiOperation(value = "下载")
    @GetMapping(value = "/download")
    public ResponseEntity<Object> downloadFile(String filename) throws FileNotFoundException {
        File file = new File(filename);
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
        HttpHeaders headers = new HttpHeaders();

        headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", file.getName()));
        headers.add("Cache-control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        ResponseEntity<Object> responseEntity
                = ResponseEntity.ok()
                .headers(headers)
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/txt"))
                .body(resource);

        return responseEntity;
    }
}
