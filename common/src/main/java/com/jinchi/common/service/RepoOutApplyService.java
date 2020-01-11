package com.jinchi.common.service;

import com.jinchi.common.dto.CommonBatchNumberDTO;
import com.jinchi.common.dto.PageBean;
import com.jinchi.common.dto.repository.RepoOutHeadDTO;
import com.jinchi.common.dto.repository.RepoOutReceiveMessageDTO;

import java.util.List;

/**
 * @author：XudongHu
 * @className:RepoOutApplyService
 * @description: 出库 service
 * @date:12:35 2018/12/2
 */

public interface RepoOutApplyService {
    /**
     * 出库申请
     */
    String sendToOut(RepoOutHeadDTO repoOutHeadDTO);

    /**
     * 根据批号id查询
     */
    CommonBatchNumberDTO findByBatchNumberId(Integer batchNumberId);

    /**
     * 根据批号id删除库存
     */
    void deleteByBatchNumberId(Integer batchNumberId);

    /**
     * 根据批号ids删除
     */
    void deleteByBatchNumberIds(List<Integer> batchNumberIds);

    /**
     * 根据申请人名称和类型查询所有出库申请
     */
    List<CommonBatchNumberDTO> findAllByNameLikeAndType(String personName, Integer materialType);

    /**
     * 根据申请人名称和类型查询所有出库申请-分页
     */
    PageBean findAllByNameLikeAndTypeByPage(String personName, Integer materialType, Integer page, Integer size,
                                            String fieldName, String orderType);

    /**
     * 向仓库发送修改库存命令
     * @param batchNumberId 批号id
     */
    void sendOutMessage(Integer batchNumberId);

    /**
     * 接受仓库修改库存后的值
     * @param repoOutReceiveMessageDTO 出库真实记录dto
     */
    String receiveOutMessage(RepoOutReceiveMessageDTO repoOutReceiveMessageDTO);
}
