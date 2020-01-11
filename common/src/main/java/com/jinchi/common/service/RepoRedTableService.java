package com.jinchi.common.service;

import com.jinchi.common.domain.RepoRedTable;
import com.jinchi.common.dto.CommonBatchNumberDTO;
import com.jinchi.common.dto.PageBean;
import com.jinchi.common.dto.repository.RedTableDTO;

import java.util.List;

/**
 * @author ZSCDumin
 * @version 1.0
 * @description: 红单管理服务类
 * @date 2018/11/27 15:37
 */
public interface RepoRedTableService {

    /**
     * 添加一个红单记录
     *
     * @param commonBatchNumberDTO 批号记录实体
     * @return RepoRedTable
     */
    CommonBatchNumberDTO<RepoRedTable> insert(CommonBatchNumberDTO<RepoRedTable> commonBatchNumberDTO);

    /**
     * 更新一个红单记录
     *
     * @param commonBatchNumberDTO 批号记录实体
     * @return PurchaseReportRecordDTO
     */
    CommonBatchNumberDTO<RepoRedTable> update(CommonBatchNumberDTO<RepoRedTable> commonBatchNumberDTO);

    /**
     * 根据ID删除红单记录
     *
     * @param id 记录ID
     */
    void deleteById(Integer id);

    /**
     * 根据IDS删除红单记录
     *
     * @param ids 记录ID数组
     */
    void deleteByIds(Integer[] ids);

    /**
     * 根据ID查找红单记录
     *
     * @param id 记录ID
     * @return RepoRedTable 红单实体
     */
    RedTableDTO getById(Integer id);


    List<RedTableDTO> getByBatchNumberId(Integer id);

    /**
     * 查询所有的红单记录
     *
     * @param pageNum   第几页
     * @param pageSize  每页个数
     * @param fieldName 字段名
     * @param orderType 排序规则
     * @return PageInfo 排序集合
     */
    PageBean<RedTableDTO> getAllByPage(Integer materialType, Integer pageNum, Integer pageSize, String fieldName, String orderType);

    /**
     * 通过红单记录材料名称进行模糊查询
     *
     * @param materialType 材料类型
     * @param serialNumber 材料编号
     * @param pageSize     每页个数
     * @param fieldName    字段名
     * @param fieldName    字段名
     * @param orderType    排序规则
     * @return PageInfo 排序集合
     */
    PageBean<RedTableDTO> getBySerialNumberLikeByPage(Integer materialType, String serialNumber, Integer pageNum, Integer pageSize, String fieldName, String orderType);

    void updateStock(Integer batchNumberId);
}
