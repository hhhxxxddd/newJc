package com.jinchi.common.mapper;

import com.jinchi.common.domain.EquipmentRepairRecord;
import com.jinchi.common.dto.PageBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author：XudongHu
 * @className:EquipmentRepairRecordMapper
 * @description: 设备维修Mapper
 * @date:23:33 2019/3/10
 * @Modifer:
 */
@Mapper
@Component
public interface EquipmentRepairRecordMapper {
    /**
     * 新增
     * @param equipmentRepairRecord
     */
    void insert(EquipmentRepairRecord equipmentRepairRecord);

    /**
     * 更新
     * @param equipmentRepairRecord
     */
    void update(EquipmentRepairRecord equipmentRepairRecord);

    /**
     * 详情
     * @param id
     * @return
     */
    EquipmentRepairRecord byId(@Param("id") Integer id);

    /**
     * 查询所有
     * @param equipmentName 设备名称
     * @param pageBean 分页类
     * @return
     */
    List<EquipmentRepairRecord> byNameLikeByPage(@Param("equipmentName") String equipmentName,
                                                 @Param("pageBean") PageBean pageBean);

    /**
     * 计算总数
     * @param equipmentName
     * @return
     */
    Integer countSum(@Param("equipmentName") String equipmentName);

}
