package com.jinchi.common.mapper;

import com.jinchi.common.domain.PurchaseReportRecord;
import com.jinchi.common.dto.PageBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 说明:
 * <br>
 *
 * @author ZSCDumin
 * <br>
 * 邮箱: 2712220318@qq.com
 * <br>
 * 日期: 2018/11/19
 * <br>
 * 版本: 1.0
 */
@Component
@Mapper
public interface PurchaseReportRecordMapper {
    /**
     * 添加一个原料进货检验报告单记录
     *
     * @param purchaseReportRecord 原料进货检验报告单实体
     */
    void insert(PurchaseReportRecord purchaseReportRecord);

    /**
     * 更新一个原料进货检验报告单记录
     *
     * @param purchaseReportRecord 原料进货检验报告单实体
     */
    void update(PurchaseReportRecord purchaseReportRecord);

    /**
     * 根据ID删除原料进货检验报告单记录
     *
     * @param id 记录ID
     */
    void deleteById(Integer id);

    /**
     * 根据IDS删除原料进货检验报告单记录
     *
     * @param ids 记录ID数组
     */
    void deleteByIds(Integer[] ids);

    /**
     * 根据ID查找原料进货检验报告单记录
     *
     * @param id 记录ID
     * @return PurchaseReportRecordDTO
     */
    PurchaseReportRecord getById(Integer id);

    /**
     * 根据批号id查询
     *
     * @param batchNumberId
     * @return
     * @modifier: 胡旭东
     */
    PurchaseReportRecord getByBatchNumberId(@Param("batchNumberId") Integer batchNumberId);

    /**
     * 根据物料ids查询
     * @param materialIds 物料ids
     * @param pageBean 分页实体
     * @return
     */
    List<PurchaseReportRecord> getByMaterialIds(@Param("materialIds") List<Integer> materialIds,@Param("pageBean") PageBean pageBean);

    /**
     * 查询所有
     * @param pageBean 分页实体
     * @return
     */
    List<PurchaseReportRecord> getAll(@Param("pageBean")PageBean pageBean);

    /**
     * 计算总条目
     * @return
     */
    Integer countSum(@Param("materialIds") List<Integer> materialIds);
}
