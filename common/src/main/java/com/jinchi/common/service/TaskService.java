package com.jinchi.common.service;


import com.github.pagehelper.PageInfo;
import com.jinchi.common.dto.CommonBatchNumberDTO;
import com.jinchi.common.dto.TaskDTO;

import java.util.List;

/**
 * @author：XudongHu
 * @description: 流程管理 接口
 * @date:14:12 2018/11/19
 */
public interface TaskService {
    /**
     * 新增审核任务
     */
    CommonBatchNumberDTO<List<TaskDTO>> add(CommonBatchNumberDTO<List<TaskDTO>> batchTaskPersonDTO);

    /**
     * 删除审核任务
     */
    void deleteByBatchNumberId(Integer batchNumberId);

    /**
     * 批量删除审核任务
     */
    void deleteByBatch(List<Integer> batchNumberIds);

    /**
     * 更新审核任务
     */
    CommonBatchNumberDTO<List<TaskDTO>> update(CommonBatchNumberDTO<List<TaskDTO>> batchTaskPersonDTO);

    /**
     * 根据批号id查询审核任务
     */
    CommonBatchNumberDTO<List<TaskDTO>> findByBatchNumberId(Integer batchNumberId);

    /**
     * 查询所有
     */
    List<CommonBatchNumberDTO> findAll();

    /**
     * 查询所有已提交的审核流程
     */
    List<CommonBatchNumberDTO> validTasks();

    /**
     * 查询所有-分页
     */
    PageInfo findAllByNameLikeByPage(String taskName, Integer page, Integer size, String fieldName, String orderType);

}
