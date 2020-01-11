package com.jinchi.common.mapper;

import com.jinchi.common.domain.TechniqueBaseRawMaterial;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 说明:原材料名称接口映射类
 * <br>
 *
 * @author ZSCDumin
 * <br>
 * 邮箱: 2712220318@qq.com
 * <br>
 * 日期: 2018/11/16
 * <br>
 * 版本: 1.0
 */
@Mapper
@Component
public interface TechniqueBaseRawMaterialMapper {
    /**
     * 添加一个新原材料名称
     *
     * @param techniqueBaseRawMaterial 原材料名称
     */
    void insert(TechniqueBaseRawMaterial techniqueBaseRawMaterial);

    /**
     * 更新一个原材料名称
     *
     * @param techniqueBaseRawMaterial 原材料名称
     */
    void update(TechniqueBaseRawMaterial techniqueBaseRawMaterial);

    /**
     * 根据ID删除原材料名称
     *
     * @param id 原材料名称ID
     */
    void deleteById(Integer id);

    /**
     * 根据IDS删除原材料名称
     *
     * @param ids 原材料名称ID数组
     */
    void deleteByIds(Integer[] ids);

    /**
     * 根据ID查找原材料名称
     *
     * @param id 原材料名称ID
     * @return TechniqueBaseRawMaterial
     */
    TechniqueBaseRawMaterial getById(Integer id);

    /**
     * 查询所有的原材料名称
     *
     * @return List<TechniqueBaseRawMaterial>
     */
    List<TechniqueBaseRawMaterial> getAll();

    /**
     * 通过全名查询
     *
     * @param name 原材料名称
     * @return List<TechniqueBaseRawMaterial>
     */
    TechniqueBaseRawMaterial getByName(@Param("name") String name);

    /**
     * 模糊查询
     * @param name 原材料名称
     * @return
     */
    List<TechniqueBaseRawMaterial> getByNameLike(@Param("name") String name);

}
