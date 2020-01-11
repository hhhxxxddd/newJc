package com.jinchi.common.mapper;

import com.jinchi.common.domain.RepoStock;
import com.jinchi.common.dto.repository.RepoStockDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by WangZhihao on 2018/11/30.
 */
@Mapper
@Component
public interface RepoStockMapper {

    /**
     * 根据物料类型查询库存
     *
     * @param materialClass
     * @return
     */
    List<RepoStockDTO> findAllByMaterialClass(@Param(value = "materialClass") Integer materialClass);

    /**
     * 根据类型和名称查询库存
     *
     * @param materialClass
     * @param materialName
     * @return
     */

    List<RepoStockDTO> findAllByName(@Param(value = "materialClass") Integer materialClass,
                                     @Param(value = "materialName") String materialName);


    /**
     * 查询总数
     * @param materialClass
     * @param materialName
     * @return
     */
    Integer countSum(@Param(value = "materialClass") Integer materialClass,
                     @Param(value = "materialName") String materialName);

    /**
     * 根据编号id查询库存
     *
     * @param serialNumberId 基本编号id
     * @return RepoStock
     * @Author huxudong
     */
    RepoStock findBySerialNumberId(@Param("serialNumberId") Integer serialNumberId);

    /**
     * 根据主键id查询
     *
     * @param id 主键
     * @return RepoStock 库存
     * @Author huxudong
     */
    RepoStock findById(@Param("id") Integer id);


    /**
     * 根据基本编号新增一种仓库物料
     *
     * @return
     * @Author huxudong
     * @Param repoStock
     * @Description 这里的新增是指已存在基本编号, 但是库存表中还没有数据的新增
     */
    void addNewStock(RepoStock repoStock);

    /**
     * 更新库存
     *
     * @return repoStock
     * @Author huxudong
     * @Description 增加或者减少库存  故只更新数量和重量
     */
    void updateStock(RepoStock repoStock);
}
