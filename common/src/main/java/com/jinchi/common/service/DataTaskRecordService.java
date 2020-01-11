package com.jinchi.common.service;

import com.jinchi.common.dto.CommonBatchNumberDTO;
import com.jinchi.common.dto.CommonNameDTO;

import java.util.List;

/**
 * @author：XudongHu
 * @className:DataTaskRecordService
 * @description:
 * @date:16:39 2018/12/7
 */
public interface DataTaskRecordService {
    /**
     * 送审
     *
     * @param dataId   数据批号id
     * @param taskId   流程批号id
     * @param isUrgent 是否紧急
     * @return 返回数据批号id和流程批号id信息
     */
    String send2audit(Integer dataId, Integer taskId, Integer isUrgent);

    /**
     * 当前待办事项
     *
     * @param userId 用户id
     * @return
     */
    List<CommonBatchNumberDTO> findToDoList(Integer userId);

    /**
     * 历史待办事项
     *
     * @param userId 用户id
     * @param date   日期   可为空
     * @return
     */
    List<CommonBatchNumberDTO> findFinToDoList(Integer userId, String date);

    /**
     * 审核
     *
     * @param batchNumberId 审核的数据批号id
     * @param userId        用户id
     * @param reply         回复信息
     */
    String audit(Integer batchNumberId, Integer userId, String reply, Integer isAccept);

    /**
     * 查看数据的反馈信息
     *
     * @param batchNumberId
     * @return
     */
    List<CommonNameDTO> replies(Integer batchNumberId);

}
