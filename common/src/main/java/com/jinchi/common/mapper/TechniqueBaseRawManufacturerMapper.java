package com.jinchi.common.mapper;

import com.jinchi.common.domain.TechniqueBaseRawManufacturer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author：XudongHu
 * @className:TechniqueBaseRawManufactuereMapper
 * @description:
 * @date:10:41 2018/12/22
 */
@Mapper
@Component
public interface TechniqueBaseRawManufacturerMapper {

    /**
     * 新增一个原材料厂商
     *
     * @param techniqueBaseRawManufacturer
     */
    void insert(TechniqueBaseRawManufacturer techniqueBaseRawManufacturer);

    /**
     * 更新一个原材料厂商
     *
     * @param techniqueBaseRawManufacturer
     */
    void update(TechniqueBaseRawManufacturer techniqueBaseRawManufacturer);

    /**
     * 根据id删除
     *
     * @param id
     */
    void deleteById(@Param("id") Integer id);

    /**
     * 根据ids删除
     *
     * @param ids
     */
    void deleteByIds(List<Integer> ids);


    /**
     * 根据id查询
     */
    TechniqueBaseRawManufacturer getById(@Param("id") Integer id);

    /**
     * 查询所有
     */
    List<TechniqueBaseRawManufacturer> getAll();


    /**
     * 根据全名查询
     * @param name
     * @return
     */
    TechniqueBaseRawManufacturer getByName(@Param("name") String name);

    /**
     * 模糊查询
     * @param name 工厂名称
     * @return
     */
    List<TechniqueBaseRawManufacturer> getByNameLike(@Param("name") String name);

    /**
     * 根据代号全称查询
     * @param code 代号
     * @return
     */
    TechniqueBaseRawManufacturer byCode(@Param("code") String code);
}
