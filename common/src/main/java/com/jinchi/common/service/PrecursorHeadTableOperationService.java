package com.jinchi.common.service;


import java.util.Date;

/**
 * @author: LiuTaoYi
 * @time: 2020/6/12 17:10
 * @description:
 */
public interface PrecursorHeadTableOperationService {

    Boolean checkDate(Integer periodId, Integer periods);

    void updateAllEndTime(Integer periodId, Integer periods, Date endTime);
}
