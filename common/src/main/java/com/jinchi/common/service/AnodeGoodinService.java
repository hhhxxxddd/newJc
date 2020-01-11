package com.jinchi.common.service;

import com.jinchi.common.domain.AnodeGoodsInProcessStatisticHead;
import com.jinchi.common.dto.Page;
import com.jinchi.common.dto.anode.AnodeGoodinDTO;
import com.jinchi.common.dto.anode.AnodeStatisticDTO;

import java.util.List;
import java.util.Map;

public interface AnodeGoodinService {

    Map<String,Object> addComfirm(AnodeGoodsInProcessStatisticHead head);

    AnodeGoodinDTO afterComfirm(Long id);

    Map getNextPeriods(Integer periodId,Integer lineCode);

    String saveOrCommit(AnodeGoodinDTO data,Long id,Integer flag);

    Page pageUnCommit(AnodeGoodsInProcessStatisticHead head, Integer page, Integer size);

    Page pageCommit(AnodeGoodsInProcessStatisticHead head,Integer page,Integer size);

    void delete(Long id);

    AnodeStatisticDTO commitDetail(Long totalId);

    List getDateByPeriodId(Integer periodId);

    Object statisticLine(Integer periodId,Integer periods);

    String judgeLine(Integer periodId,Integer periods);

    List statisticProcess(Integer periodId, Integer periods, Integer lineId);

    Map getEnableLineAndStatisticRecord(Integer periodId, Integer periods);

    List lineCompare(Integer periodId, String startTime, String endTime, Integer dataFlag, Integer materialFlag);

    List processCompare(Integer periodId, Integer lineCode, String startTime, String endTime, Integer flag);
}
