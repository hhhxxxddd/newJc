package com.jinchi.common.service;


import com.jinchi.common.domain.EquipmentBaseManufacturer;

import java.util.List;

/**
 * @author：Fangle
 * @className:EquipmentBaseManufacturerServiceImp
 * @description:  设备基本厂商表
 * @date:3:27 PM 2019/1/11
 */
public interface EquipmentBaseManufacturerService {

    /**
     * 新增
     * @param equipmentBaseManufacturer 设备基本厂商
     * @return
     */
    EquipmentBaseManufacturer add(EquipmentBaseManufacturer equipmentBaseManufacturer);

    /**
     * 更新
     * @param equipmentBaseManufacturer 设备基本厂商
     * @return
     */
    EquipmentBaseManufacturer update(EquipmentBaseManufacturer equipmentBaseManufacturer);

    /**
     * 详情
     * @param id
     * @return
     */
    EquipmentBaseManufacturer byId(Integer id);

    /**
     * 查询所有
     * @return
     */
    List<EquipmentBaseManufacturer> all();

    /**
     * 删除
     * @param id
     */
    void delete(Integer id);

    /**
     * 批量删除
     * @param ids
     */
    void batchDelete(List<Integer> ids);

    /**
     * 根据厂商类型查询所有
     * @param type
     * @return
     */
    List<EquipmentBaseManufacturer> byType(Integer type);
}
