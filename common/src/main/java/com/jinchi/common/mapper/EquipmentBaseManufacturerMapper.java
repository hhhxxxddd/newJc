package com.jinchi.common.mapper;

import com.jinchi.common.domain.EquipmentBaseManufacturer;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author：Fangle
 * @className:EquipmentBaseManufacturerMapper
 * @description: 设备基本厂商表
 * @date:4:08 PM 2019/1/11
 */
@Mapper
@Component
public interface EquipmentBaseManufacturerMapper {
    /**
     * 根据id查询
     * @param id 主键
     * @return
     */
    EquipmentBaseManufacturer byId(Integer id);

    /**
     * 新增
     * @param equipmentBaseManufacturer 设备实体
     */
    void add(EquipmentBaseManufacturer equipmentBaseManufacturer);

    /**
     * 更新
     * @param equipmentBaseManufacturer 设备实体
     */
    void update(EquipmentBaseManufacturer equipmentBaseManufacturer);

    /**
     * 根据id删除
     * @param id
     */
    void deleteById(Integer id);

    /**
     * 根据ids批量删除
     * @param ids
     */
    void batchDelete(List<Integer> ids);

    /**
     * 查询所有
     * @return
     */
    List<EquipmentBaseManufacturer> all();

    /**
     * 根据厂商类型查询所有
     * @return type 厂商类型id
     */
    List<EquipmentBaseManufacturer> byType(Integer type);
}
