package com.jinchi.common.mapper;

import com.jinchi.common.domain.TechniqueBaseProductClass;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author：XudongHu
 * @className:TechniqueBaseProductClassMapper
 * @description: 技术基本产品型号
 * @date:16:37 2018/12/28
 */
@Mapper
@Component
public interface TechniqueBaseProductClassMapper {
    /**
     * 新增型号
     * @param techniqueBaseProductClass 型号实体
     */
    void insert(TechniqueBaseProductClass techniqueBaseProductClass);

    /**
     * 更新型号
     *
     * @param techniqueBaseProductClass 型号实体
     */
    void update(TechniqueBaseProductClass techniqueBaseProductClass);

    /**
     * 更新型号名称
     *
     * @param techniqueBaseProductClass 型号实体
     */
    void updateName(TechniqueBaseProductClass techniqueBaseProductClass);

    /**
     * 查询所有型号
     *
     * @param name     型号名称
     * @param parentId 父id
     * @return
     */
    List<TechniqueBaseProductClass> nameLikeAndParentIs(@Param("name") String name, @Param("parentId") Integer parentId, @Param("productId") Integer productId);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    TechniqueBaseProductClass findById(@Param("id") Integer id);

    void deleteById(@Param("id") Integer id);
}
