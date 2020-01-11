package com.jinchi.common.service;


/**
 * @author：XudongHu
 * @className:CommonBatchNumberMapper
 * @description:
 * @date:11:00 2018/11/20
 */
public interface CommonBatchNumberService {
    /**
     * 根据批号找到任意数据
     * @param batchNumber
     * @return
     */
    Object byBatchNumber(String batchNumber);
}
