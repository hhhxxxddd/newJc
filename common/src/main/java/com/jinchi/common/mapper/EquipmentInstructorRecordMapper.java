package com.jinchi.common.mapper;

import com.jinchi.common.domain.EquipmentInstructorRecord;
import com.jinchi.common.dto.PageBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author：XudongHu
 * @className:EquipmentInstructorRecordMapper
 * @description:  设备指导Mapper
 * @date:11:03 2019/3/5
 * @Modifer:
 */
@Mapper
@Component
public interface EquipmentInstructorRecordMapper {
    /**
     * 新增
     */
    void insert(EquipmentInstructorRecord equipmentInstructorRecord);

    /**
     * 更新
     */
    void update(EquipmentInstructorRecord equipmentInstructorRecord);

    /**
     * 查询
     * @param id 主键
     * @return
     */
    EquipmentInstructorRecord byId(@Param("id") Integer id);

    /**
     * 根据批号id查询
     * @param batchNumberId
     * @return
     */
    EquipmentInstructorRecord byBatchNumberId(@Param("batchNumberId") Integer batchNumberId);

    /**
     * 删除
     */
    Integer delete(@Param("id") Integer id);

    /**
     * 分页本体
     * @param instructorName
     * @param pageBean
     * @return
     */
    List<EquipmentInstructorRecord> byNameLikeByPage(@Param("instructorName") String instructorName, @Param("pageBean") PageBean pageBean);

    /**
     * 分页总数
     * @param instructorName
     * @param pageBean
     * @return
     */
    Integer countSum(@Param("instructorName") String instructorName, @Param("pageBean") PageBean pageBean);
}
