package com.jinchi.common.service;

import com.jinchi.common.domain.MaterialDeliveryStatisticHead;
import com.jinchi.common.domain.MaterialDeliveryStatisticSulfateConcentration;
import com.jinchi.common.dto.ListByProductionLineDTO;
import com.jinchi.common.dto.MaterialDeliveryCommitDTO;
import com.jinchi.common.dto.Page;

import java.util.List;

public interface MaterialDeliveryStatisticService {
    List getPeriod(Integer periodCode);//获取期数

    Object add(MaterialDeliveryStatisticHead head);//新增统计头表

    List getStockOutData(String startTime, String endTime);//获取出库数据

    MaterialDeliveryStatisticSulfateConcentration getConcentrations(Integer periodCode);//获取上期浓度

    List getSupplementary(Integer periodCode);//获取补料数据

    //查询待提交数据
    Page queryUncommitted(String startTime, String endTime, Integer periodCode, Integer page, Integer size);

    //查询已统计数据
    Page queryStatistics(String startTime, String endTime, Integer periodCode, Integer page, Integer size);

    //查询待提交数据详情
    MaterialDeliveryCommitDTO getDetailByCode(Long statisticCode);

    //保存 / 提交
    void commit(MaterialDeliveryCommitDTO commitDTO);

    //查询已统计详情
    List getStatisticDetail(Long statisticCode, Integer materialTypeCode);

    //删除待提交记录
    void deleteUnsubmit(Long statisticCode);

    //按产线统计
    ListByProductionLineDTO listByProductionLine(Integer periodCode, String startTime);

    //周期对比曲线
    List periodCompareCurve(Integer periodCode, Integer lineCode, String startTime, String endTime);

    //产线对比曲线
    List lineCompareCurve(Integer periodCode, Integer periodNum, Integer[] lineCodes);

    List getDate(Integer periodId);

    List getPeriodAndTime(Integer periodId);
}
