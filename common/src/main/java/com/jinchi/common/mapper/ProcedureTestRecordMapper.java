package com.jinchi.common.mapper;

import com.jinchi.common.domain.ProcedureTestItemRecord;
import com.jinchi.common.domain.ProcedureTestRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author：XudongHu
 * @className:ProcedureTestRecordMapper
 * @description: 制程检测
 * @date:12:34 2018/11/23
 */
@Component
@Mapper
public interface ProcedureTestRecordMapper {

    /**
     * 新增
     */
    void insert(ProcedureTestRecord procedureTestRecord);

    /**
     * 更新迭代历史数据
     *
     * @param procedureTestRecord
     */
    void updateIteration(List<ProcedureTestRecord> procedureTestRecord);

    /**
     * 根据批号id查询
     */
    List<ProcedureTestRecord> findByBatchNumberId(@Param("batchNumberId") Integer batchNumberId);


    /**
     * 根据批号id删除
     */
    void deleteByBatchNumberId(@Param("batchNumberId") Integer batchNumberId);

    /**
     * 根据批号ids删除
     */
    void deleteByBatchNumberIds(List<Integer> batchNumberIds);

    /**
     * 检测项目相关----------------------------------------------------------------------
     * 与制程检测为一对多
     * @See ProcedureTestItemRecord
     */
    /**
     * 根据制程检测id查询其检测项目
     *
     * @param procedureTestId
     * @return
     */
    List<ProcedureTestItemRecord> testItemsOfProcedureTest(@Param("procedureTestId") Integer procedureTestId);

    /**
     * 批量新增检测项目
     *
     * @param testItemRecords
     */
    void insertAllTestItems(List<ProcedureTestItemRecord> testItemRecords);

    /**
     * 根据制程检测ids删除检测项目
     *
     * @param procedureTestIds
     */
    void deleteTestItemRecord(List<Integer> procedureTestIds);

    void deleteOneTestItemRecord(@Param("procedureTestId") Integer procedureTestId);

    /**
     * 排除当前id后多条件查询
     * 确保审核通过的迭代的最新数据
     * 和你新增的 制程检测没有相同的 工厂-工序-取样点-编号
     *
     * @param factoryId      送样工厂id
     * @param procedureId    工序
     * @param samplePoint    取样点
     * @param serialNumberId 编号id
     * @param isIteration    是否迭代
     * @param status         状态 @See commonBatchNumber
     * @param id             主键
     * @return
     */
    List<ProcedureTestRecord> isUnique(@Param("factoryId") Integer factoryId,
                                       @Param("procedureId") Integer procedureId,
                                       @Param("samplePoint") String samplePoint,
                                       @Param("serialNumberId") Integer serialNumberId,
                                       @Param("isIteration") Integer isIteration,
                                       @Param("status") Integer status,
                                       @Param("id") Integer id);


}
