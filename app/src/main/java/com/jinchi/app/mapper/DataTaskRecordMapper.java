package com.jinchi.app.mapper;

import com.jinchi.app.domain.DataTaskRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author：XudongHu
 * @className:DataTaskRecordMapper
 * @description:
 * @date:16:10 2018/11/22
 */
@Mapper
@Component
public interface DataTaskRecordMapper {

    /**
     * 存储 数据和流程key表
     */
    void insert(DataTaskRecord dataTaskRecord);

    /**
     * 批量存储 数据和流程key表
     */
    void insertAll(List<DataTaskRecord> dataTaskRecordList);

    /**
     * 多条件批量删除 数据和流程key表
     */
    void deleteByBatch(@Param("id") Integer id, @Param("dataId") Integer dataBatchNumberId, @Param("taskId") Integer taskBatchNumberId);

    /**
     * 根据dataBatchNumberIds批量删除
     */
    void deleteByDataBatchNumberIds(List<Integer> dataBatchNumberIds);

    /**
     * 根据taskBatchNumberIds批量删除
     */
    void deleteByTaskBatchNumberIds(List<Integer> taskBatchNumberIds);

    /**
     * 根据id查询 任务流程对应表
     *
     * @param id
     * @return
     */
    DataTaskRecord findById(@Param("id") Integer id);

    /**
     * 根据数据批号id查询 任务流程对应表
     */
    DataTaskRecord findByDataBatchNumberId(@Param("dataBatchNumberId") Integer dataBatchNumberId);

    /**
     * 根据数据批号ids查询 任务流程对应表
     */
    List<DataTaskRecord> findByDataBatchNumberIds(List<Integer> dataBatchNumberIds);

    /**
     * 根据id删除
     * @param id
     */
    void deleteById(Integer id);
}
