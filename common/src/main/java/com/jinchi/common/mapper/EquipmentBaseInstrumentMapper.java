package com.jinchi.common.mapper;

import com.jinchi.common.domain.EquipmentBaseInstrument;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author：XudongHu
 * @className:EquipmentBaseInstrumentMapper
 * @description: 设备 基本设备
 * @date:14:10 2019/1/11
 */
@Mapper
@Component
public interface EquipmentBaseInstrumentMapper {
    /**
     * 根据code全称查询
     * @param code 设备编号
     * @return
     */
    EquipmentBaseInstrument byFullCode(@Param("code") String code);

    /**
     * 根据id查询
     * @param id 主键
     * @return
     */
    EquipmentBaseInstrument byId(Integer id);

    /**
     * 新增
     * @param equipmentBaseInstrument 设备实体
     */
    void add(EquipmentBaseInstrument equipmentBaseInstrument);

    /**
     * 更新
     * @param equipmentBaseInstrument 设备实体
     */
    void update(EquipmentBaseInstrument equipmentBaseInstrument);

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
    List<EquipmentBaseInstrument> all();
}
