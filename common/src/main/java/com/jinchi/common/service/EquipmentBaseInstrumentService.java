package com.jinchi.common.service;

import com.jinchi.common.domain.EquipmentBaseInstrument;

import java.util.List;

/**
 * @author：XudongHu
 * @className:EquipmentBaseInstrumentService
 * @description: 设备基本设备表
 * @date:13:59 2019/1/11
 */
public interface EquipmentBaseInstrumentService {
    /**
     * 新增
     */
    EquipmentBaseInstrument add(EquipmentBaseInstrument equipmentBaseInstrument);


    /**
     * 更新
     * @param equipmentBaseInstrument 设备基本设备
     * @return
     */
    EquipmentBaseInstrument update(EquipmentBaseInstrument equipmentBaseInstrument);

    /**
     * 详情
     */
    EquipmentBaseInstrument byId(Integer id);

    /**
     * 查询所有
     */
    List<EquipmentBaseInstrument> all();

    /**
     * 删除
     */
    void delete(Integer id);

    /**
     * 批量删除
     */
    void batchDelete(List<Integer> ids);
}
