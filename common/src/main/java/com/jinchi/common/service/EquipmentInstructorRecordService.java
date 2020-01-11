package com.jinchi.common.service;

import com.jinchi.common.dto.PageBean;
import com.jinchi.common.dto.equipment.EquipmentInstructorDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author：XudongHu
 * @className:EquipmentInstructorRecordService
 * @description: 设备指导service
 * @date:23:21 2019/3/2
 */

public interface EquipmentInstructorRecordService {
    //上传图片并回显
    String uploadPic(MultipartFile file) throws IOException;
    //取消上传
    String cancelUpload(List<String> fileName) throws IOException;
    //新增-迭代
    EquipmentInstructorDTO insert(EquipmentInstructorDTO equipmentInstructorDTO);
    //更新
    EquipmentInstructorDTO update(EquipmentInstructorDTO equipmentInstructorDTO);
    //查询
    Map<Object,Object> byBatchNumberId(Integer batchNumberId);
    //查询所有-分页 - 根据指导书名称
    PageBean byNameLikeByPage(String instructorName,PageBean pageBean);
    //删除
    String delete(Integer batchNumberId) ;
    //批量删除
    String batchDelete(List<Integer> ids);
}
