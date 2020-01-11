package com.jinchi.common.mapper;

import com.jinchi.common.domain.Task;
import com.jinchi.common.dto.TaskDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author：XudongHu
 * @description: 流程管理
 * @date:21:25 2018/11/19
 */
@Mapper
@Component
public interface TaskMapper {
    /**
     * 批量新增审核流程
     */
    void insertAll(List<Task> tasks);


    /**
     * 使用批号删除
     */
    void deleteByBatchNumberId(@Param("batchNumberId") Integer batchNumberId);

    /**
     * 根据批号批量删除
     */
    void deleteByBatchNumberIds(List<Integer> batchNumbers);

    /**
     * 根据批号查询
     */
    List<Task> findByBatchNumberId(@Param("batchNumberId") Integer batchNumberId);

    /**
     * 查询所有
     */
    List<Task> findAll();

    /**
     * 根据用户id和查询用户当前的待办事项
     *
     * @param userId
     * @return
     */
    List<Task> toDoList(@Param("userId") Integer userId);

    /**
     * 根据batchNumberId 查询待办事项的细节
     *
     * @param batchNumberId
     * @return
     */
    List<TaskDTO> toDoListDetails(@Param("batchNumberId") Integer batchNumberId);

}
