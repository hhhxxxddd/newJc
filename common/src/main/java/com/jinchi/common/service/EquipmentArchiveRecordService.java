package com.jinchi.common.service;

import com.jinchi.common.domain.EquipmentArchiveRecord;
import com.jinchi.common.dto.PageBean;
import com.jinchi.common.dto.equipment.EquipmentArchiveDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @author：XudongHu
 * @className:EquipmentArchiveRecordService
 * @description:
 * @date:16:14 2019/1/11
 * @Modifer:
 */
public interface EquipmentArchiveRecordService {
    /**
     * 新增
     * @param equipmentArchiveRecord 设备档案实体
     * @return
     */
    EquipmentArchiveRecord add(EquipmentArchiveRecord equipmentArchiveRecord, MultipartFile file) throws IOException;

    /**
     * 更新
     * @param equipmentArchiveRecord 设备档案实体
     * @return
     */
    EquipmentArchiveRecord update(EquipmentArchiveRecord equipmentArchiveRecord,MultipartFile file) throws IOException;

    /**
     * 详情
     * @param id 主键
     * @return
     */
    EquipmentArchiveDTO byId(Integer id);

    String manual(Integer id);


    /**
     * 查询所有
     * @return
     */
    List<EquipmentArchiveDTO> all(PageBean pageBean,String manualName);

    /**
     * 查询所有-分页
     * @param pageBean 分页类
     * @return
     */
    PageBean pages(PageBean pageBean,String manualName);

    /**
     * 根据id删除
     * @param id 主键
     */
    void deleteById(Integer id);

    /**
     * 批量删除
     * @param ids
     */
    void batchDelete(List<Integer> ids);


}
