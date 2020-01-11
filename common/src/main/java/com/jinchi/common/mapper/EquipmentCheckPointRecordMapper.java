package com.jinchi.common.mapper;

import com.jinchi.common.domain.EquipmentCheckPointRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author：XudongHu
 * @className:EquipmentCheckPointRecordMapper
 * @description: 设备指导采样点
 * @date:12:56 2019/3/5
 * @Modifer:
 */
@Mapper
@Component
public interface EquipmentCheckPointRecordMapper {
    /**
     * 新增
     * @param recordList 采样点集合
     */
    void batchInsert(List<EquipmentCheckPointRecord> recordList);

    /**
     * 更新
     * @param recordList 采样点集合
     */
    void update(List<EquipmentCheckPointRecord> recordList);

    /**
     * 根据外键 instructorId查询
     * @param instructorId 外键
     * @return
     */
    List<EquipmentCheckPointRecord> byInstructorId(@Param("instructorId") Integer instructorId);

    /**
     * 删除
     * @param instructorId 外键
     */
    Integer deleteByInstructorId(@Param("instructorId") Integer instructorId);
}
