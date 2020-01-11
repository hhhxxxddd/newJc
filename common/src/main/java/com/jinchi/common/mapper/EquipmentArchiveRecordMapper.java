package com.jinchi.common.mapper;

import com.jinchi.common.domain.EquipmentArchiveRecord;
import com.jinchi.common.dto.PageBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author：XudongHu
 * @className:EquipmentArchiveRecordMapper
 * @description: 设备档案
 * @date:13:28 2019/1/15
 * @Modifer:
 */
@Mapper
@Component
public interface EquipmentArchiveRecordMapper {
    /**
     * 根据档案全名查询
     * @param name 全名
     * @param id 主键   排除该主键
     * @return
     */
    EquipmentArchiveRecord byName(@Param("name") String name,@Param("id") Integer id);

    /**
     * 新增
     * @param equipmentArchiveRecord 设备档案
     */
    void insert(EquipmentArchiveRecord equipmentArchiveRecord);

    /**
     * 查询所有
     * @param pageBean 分页实体
     * @param name 档案名称
     * @return
     */
    List<EquipmentArchiveRecord> all(@Param("pageBean") PageBean pageBean,@Param("name") String name);

    /**
     * 计算总数
     * @param name 档案名称
     * @return
     */
    Integer countSum(@Param("name") String name);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    EquipmentArchiveRecord byId(Integer id);

    /**
     * 更新
     * @param equipmentArchiveRecord 设备档案实体
     */
    void update(EquipmentArchiveRecord equipmentArchiveRecord);

    /**
     * 根据主键删除
     * @param id 主键
     */
    void deleteById(Integer id);

    /**
     * 根据主键批量删除
     * @param ids 主键数组
     */
    void batchDelete(List<Integer> ids);

}
