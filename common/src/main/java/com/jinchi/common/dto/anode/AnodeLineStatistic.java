package com.jinchi.common.dto.anode;

import com.jinchi.common.domain.AnodeGoodsInProcessStatisticByLineDetails;
import com.jinchi.common.domain.AnodeGoodsInProcessStatisticHead;

import java.util.Map;

public class AnodeLineStatistic {

    AnodeGoodsInProcessStatisticHead head;

    String periodName;

    String lineName;

    Map rawW;

    Map rawB;

    AnodeGoodsInProcessStatisticByLineDetails details;

    public AnodeGoodsInProcessStatisticHead getHead() {
        return head;
    }

    public void setHead(AnodeGoodsInProcessStatisticHead head) {
        this.head = head;
    }

    public String getPeriodName() {
        return periodName;
    }

    public void setPeriodName(String periodName) {
        this.periodName = periodName;
    }

    public String getLineName() {
        return lineName;
    }

    public void setLineName(String lineName) {
        this.lineName = lineName;
    }

    public AnodeGoodsInProcessStatisticByLineDetails getDetails() {
        return details;
    }

    public void setDetails(AnodeGoodsInProcessStatisticByLineDetails details) {
        this.details = details;
    }

    public Map getRawW() {
        return rawW;
    }

    public void setRawW(Map rawW) {
        this.rawW = rawW;
    }

    public Map getRawB() {
        return rawB;
    }

    public void setRawB(Map rawB) {
        this.rawB = rawB;
    }
}
