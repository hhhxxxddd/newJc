package com.jinchi.common.mapper;

import com.jinchi.common.domain.TaskHandlingRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author：XudongHu
 * @className:TaskHandlingRecordMapper
 * @description: 审核流程 审核记录
 * @date:15:24 2018/12/9
 */
@Mapper
@Component
public interface TaskHandlingRecordMapper {

    /**
     * 批量新增审核记录
     * <p>
     * 所有新增的审核记录
     * 都不新增
     * dateTime  审核日期
     * reply     审核反馈
     *
     * @param taskHandlingRecords
     */
    void insertAll(List<TaskHandlingRecord> taskHandlingRecords);

    /**
     * 更新一条审核记录
     * <p>
     * 所有更新的审核记录
     * 都只更新
     * dateTime 审核日期
     * reply    审核反馈
     * visible  是否可见
     *
     * @param taskHandlingRecord
     */
    void update(TaskHandlingRecord taskHandlingRecord);

    /**
     * 查询审核记录
     *
     * @param userId  用户id
     * @param visible 是否可见
     * @return
     */
    List<TaskHandlingRecord> toDoList(@Param("userId") Integer userId, @Param("visible") Integer visible);

    /**
     * 查询单条审核记录
     *
     * @param userId  用户id
     * @param visible 是否可见
     * @param keyId   任务流程对应表id
     * @return
     */
    TaskHandlingRecord toDoItem(@Param("userId") Integer userId, @Param("visible") Integer visible, @Param("keyId") Integer keyId);

    /**
     * 查找当前审核记录的下一条id
     *
     * @param id               主键
     * @param dataTaskRecordId 数据流程对应表
     * @return
     */
    TaskHandlingRecord next(@Param("id") Integer id, @Param("dataTaskRecordId") Integer dataTaskRecordId);

    /**
     * 找到用户所有完成的审核记录
     * 即用户id
     * 且审核日期不为空的值
     *
     * @param userId 用户id
     * @return
     */
    List<TaskHandlingRecord> finTaskRecords(@Param("userId") Integer userId);

    /**
     * 根据数据流程对应表id查询
     * 查询日期不为空的值
     *
     * @param dataTaskId
     * @return
     */
    List<TaskHandlingRecord> findByDataTaskId(@Param("dataTaskId") Integer dataTaskId);

    /**
     * 根据数据流程对应表id查询
     *
     * @param dataTaskId
     * @return
     */
    List<TaskHandlingRecord> findAllByDataTaskId(@Param("dataTaskId") Integer dataTaskId);

    /**
     * 根据数据流程对应表id 删除
     * @param dataTaskId 数据流程对应表id
     */
    void deleteByDataTaskId(Integer dataTaskId);
}
