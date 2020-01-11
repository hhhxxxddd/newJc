package com.jinchi.common.dto;

import com.jinchi.common.domain.GoodsInProcessStatisticHead;

public class GoodInHeadDTO{

    GoodsInProcessStatisticHead goodsInProcessStatisticHead;

    String period;

    public GoodsInProcessStatisticHead getGoodsInProcessStatisticHead() {
        return goodsInProcessStatisticHead;
    }

    public void setGoodsInProcessStatisticHead(GoodsInProcessStatisticHead goodsInProcessStatisticHead) {
        this.goodsInProcessStatisticHead = goodsInProcessStatisticHead;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }
}
