package com.jinchi.common.service;

import com.jinchi.common.dto.Page;
import com.jinchi.common.dto.PageBean;
import com.jinchi.common.dto.SampleDeliveringBatchDTO;
import com.jinchi.common.dto.SampleDeliveringRecordDTO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2018/11/20.
 * 样品检测
 */

@Service
public interface SampleDeliveringRecordService {

    /**
     * 新增样品检测
     *
     * @param sampleDeliveringRecordDTO
     * @return
     */
    SampleDeliveringRecordDTO add(SampleDeliveringRecordDTO sampleDeliveringRecordDTO);

    String customSample(List<Integer> testItemIds,Integer userId,String batch);


    /**
     * 更新样品检测
     *
     * @param sampleDeliveringRecordDTO
     * @return
     */
    SampleDeliveringRecordDTO update(SampleDeliveringRecordDTO sampleDeliveringRecordDTO);

    /**
     * id查询样品检测
     *
     * @param id
     * @return
     */
    SampleDeliveringRecordDTO findById(Integer id);

    /**
     * 根据id删除
     *
     * @param id
     */
    void deleteById(Integer id);

    /**
     * 根据ids删除
     *
     * @param ids
     */
    void deleteByIds(List<Integer> ids);

    /**
     * 根据接收状态和物料类型查询样品送检
     *
     * @param acceptStatus
     * @param materialType
     * @return
     */
    List<SampleDeliveringRecordDTO> findAllByAcceptAndType(Integer acceptStatus, Integer materialType);


    /**
     * 查询所有-分页
     *
     * @param factoryName
     * @param pageBean
     * @return
     */
    PageBean findByFactoryNameByPage(String factoryName, PageBean pageBean);

    /**
     * 接受或者拒绝一个样品送检
     *
     * @param id
     * @param isAccept
     * @param handleComment
     */
    String isAccept(Integer id, Integer isAccept, String handleComment);

    /**
     * 根据编号id 查询标准
     *
     * @param serialNumberId 编号id
     * @return
     */
    List<Integer> rawStandards(Integer serialNumberId);

    Integer count(String batch);

    Page getPageByBatch(String batch, Integer page, Integer size, Integer type);

    SampleDeliveringBatchDTO newAdd(SampleDeliveringBatchDTO batchDTO);
}
