package com.jinchi.common.mapper;

import com.jinchi.common.domain.RepoRedTable;
import com.jinchi.common.dto.PageBean;
import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author ZSCDumin
 * @version 1.0
 * @description: 红单管理数据库层接口类
 * @date 2018/11/27 15:30
 */
@Mapper
@Component
public interface RepoRedTableMapper {
    /**
     * 添加一个新红单记录
     *
     * @param repoRedTable 红单记录
     */
    void insert(RepoRedTable repoRedTable);

    /**
     * 更新一个红单记录
     *
     * @param repoRedTable 红单记录
     */
    void update(RepoRedTable repoRedTable);

    /**
     * 根据ID删除红单记录
     *
     * @param id 红单记录ID
     */
    void deleteById(Integer id);

    /**
     * 根据IDS删除红单记录
     *
     * @param ids 红单记录ID数组
     */
    void deleteByIds(Integer[] ids);

    /**
     * 根据ID查找红单记录
     *
     * @param id 红单记录ID
     * @return RepoRedTable
     */
    RepoRedTable getById(Integer id);

    RepoRedTable getByBatchNumberId(Integer id);

    /**
     * 查询所有的红单记录
     *
     * @param pageBean
     * @param materialType 材料类别
     * @return List<RepoRedTable>
     */
    List<RepoRedTable> getAll(@Param("materialType") Integer materialType, @Param("pageBean") PageBean pageBean);

    /**
     * 查询所有的红单记录
     *
     * @param pageBean
     * @param materialType 材料类别
     */
    Integer getAllCount(@Param("materialType") Integer materialType, @Param("pageBean") PageBean pageBean);

    /**
     * 通过材料编号字段进行模糊查询
     *
     * @param pageBean
     * @param materialClass 材料类型
     * @param serialNumber  材料编号
     * @return List<RepoRedTable>
     */
    List<RepoRedTable> getBySerialNumberLike(@Param("materialClass") Integer materialClass, @Param("serialNumber") String serialNumber, @Param("pageBean") PageBean pageBean);

    /**
     * 通过材料编号字段进行模糊查询
     *
     * @param pageBean
     * @param materialClass 材料类型
     * @param serialNumber  材料编号
     * @return List<RepoRedTable>
     */
    Integer getBySerialNumberLikeCount(@Param("materialClass") Integer materialClass, @Param("serialNumber") String serialNumber, @Param("pageBean") PageBean pageBean);
}
