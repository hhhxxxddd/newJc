package com.jinchi.common.mapper;

import com.jinchi.common.domain.TestReportRecord;
import com.jinchi.common.dto.PageBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Administrator on 2018/11/20.
 */
@Mapper
@Component
public interface TestReportRecordMapper {
    /**
     * 新增一个检测结果记录
     *
     * @param testReportRecord
     * @return
     */
    void saveOne(TestReportRecord testReportRecord);

    void update(TestReportRecord testReportRecord);

    TestReportRecord getBySampleDeliveringRecordId(@Param("id") Integer id);

    TestReportRecord getById(Integer id);

    /**
     * 根据进货单编号查询-杜敏
     *
     * @param purchaseReportRecordId
     * @return
     */
    List<TestReportRecord> getByPurchaseReportRecordId(Integer purchaseReportRecordId);

    void updatePurchaseReportRecordId(@Param("purchaseReportRecordId") Integer purchaseReportRecordId, @Param("ids") Integer[] ids);

    /**
     * 根据批号id查询
     *
     * @param batchNumberId
     * @return
     */
    TestReportRecord getByBatchNumberId(@Param("batchNumberId") Integer batchNumberId);


    /**
     * 根据批号状态和 是否生成查询
     * @param status 批号状态 默认为审核通过的
     * @param isGenerate 是否生成 根据是否存在进货报告单判断
     * @param pageBean
     * @return
     */
    List<TestReportRecord> byIsGenerate(@Param("status") Integer status, @Param("isGenerate") Integer isGenerate, @Param("pageBean")PageBean pageBean);

    /**
     * 查询总数  条件同上
     * @return
     */
    Integer countSum(@Param("status") Integer status,@Param("isGenerate") Integer isGenerate);

    List<TestReportRecord> selectBySQL(@Param("sql") String sql);
}
