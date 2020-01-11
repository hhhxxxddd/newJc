package com.jinchi.common.service;

import com.jinchi.common.domain.EquipmentBaseInstrument;
import com.jinchi.common.mapper.EquipmentBaseInstrumentMapper;
import com.jinchi.common.utils.NumberGenerator;
import io.jsonwebtoken.lang.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author：XudongHu
 * @className:EquipmentBaseInstrumentServiceImp
 * @description: 设备基本设备
 * @date:14:00 2019/1/11
 */
@Service
public class EquipmentBaseInstrumentServiceImp implements EquipmentBaseInstrumentService {
    @Autowired
    private EquipmentBaseInstrumentMapper equipmentBaseInstrumentMapper;
    /**
     * 新增基本设备
     * @param equipmentBaseInstrument 设备实体
     * @return
     */
    @Override
    public EquipmentBaseInstrument add(EquipmentBaseInstrument equipmentBaseInstrument) {
        equipmentBaseInstrument.setCode(NumberGenerator.equipmentCode());
        //编号 唯一值验证
        EquipmentBaseInstrument instrument = equipmentBaseInstrumentMapper.byFullCode(equipmentBaseInstrument.getCode());
        Assert.isNull(instrument,"新增失败,编号不唯一");
        equipmentBaseInstrumentMapper.add(equipmentBaseInstrument);
        return equipmentBaseInstrument;
    }

    /**
     * 更新基本设备
     * @param equipmentBaseInstrument 设备基本设备
     * @return
     */
    @Override
    public EquipmentBaseInstrument update(EquipmentBaseInstrument equipmentBaseInstrument) {
        equipmentBaseInstrumentMapper.update(equipmentBaseInstrument);
        return equipmentBaseInstrument;
    }


    /**
     * 详情
     * @param id
     * @return
     */
    @Override
    public EquipmentBaseInstrument byId(Integer id) {
        return equipmentBaseInstrumentMapper.byId(id);
    }

    /**
     * 查询所有
     * @return
     */
    @Override
    public List<EquipmentBaseInstrument> all() {
        return equipmentBaseInstrumentMapper.all();
    }

    /**
     * 根据id删除
     * @param id
     */
    @Override
    public void delete(Integer id) {
        equipmentBaseInstrumentMapper.deleteById(id);
    }

    /**
     * 根据ids批量删除
     * @param ids
     */
    @Override
    public void batchDelete(List<Integer> ids) {
        equipmentBaseInstrumentMapper.batchDelete(ids);
    }
}
