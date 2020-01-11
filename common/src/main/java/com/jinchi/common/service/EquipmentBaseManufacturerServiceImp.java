package com.jinchi.common.service;

import com.jinchi.common.domain.EquipmentBaseManufacturer;
import com.jinchi.common.mapper.EquipmentBaseManufacturerMapper;
import com.jinchi.common.utils.NumberGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author：Fangle
 * @className:EquipmentBaseManufacturerServiceImp
 * @description: 设备基本厂商
 * @date:3:27 PM 2019/1/11
 */
@Service
public class EquipmentBaseManufacturerServiceImp implements EquipmentBaseManufacturerService{
    @Autowired
    private EquipmentBaseManufacturerMapper equipmentBaseManufacturerMapper;

    /**
     * 新增基本厂商
     * @param equipmentBaseManufacturer 设备基本厂商
     * @return
     */
    @Override
    public EquipmentBaseManufacturer add(EquipmentBaseManufacturer equipmentBaseManufacturer) {
        equipmentBaseManufacturer.setCode(NumberGenerator.manufacturerCode());
        equipmentBaseManufacturerMapper.add(equipmentBaseManufacturer);
        return equipmentBaseManufacturer;
    }

    /**
     * 更新基本厂商
     * @param equipmentBaseManufacturer 设备基本厂商
     * @return
     */
    @Override
    public EquipmentBaseManufacturer update(EquipmentBaseManufacturer equipmentBaseManufacturer) {
        equipmentBaseManufacturerMapper.update(equipmentBaseManufacturer);
        return equipmentBaseManufacturer;
    }

    /**
     * 根据id查询
     * @param id
     * @return
     */
    @Override
    public EquipmentBaseManufacturer byId(Integer id) {
        return equipmentBaseManufacturerMapper.byId(id);
    }

    /**
     * 查询所有
     * @return
     */
    @Override
    public List<EquipmentBaseManufacturer> all() {
        return equipmentBaseManufacturerMapper.all();
    }

    /**
     * 根据id删除
     * @param id
     */
    @Override
    public void delete(Integer id) {
        equipmentBaseManufacturerMapper.deleteById(id);
    }

    /**
     * 根据ids批量删除
     * @param ids
     */
    @Override
    public void batchDelete(List<Integer> ids) {
        equipmentBaseManufacturerMapper.batchDelete(ids);
    }

    @Override
    public List<EquipmentBaseManufacturer> byType(Integer type) {
        return equipmentBaseManufacturerMapper.byType(type);
    }

}
