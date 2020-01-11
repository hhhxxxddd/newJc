package com.jinchi.common.dto;

import com.jinchi.common.domain.GoodsInProcessStatisticByProcessTotal;

import java.util.List;

public class GoodInStatisticTotalDTO<T> {

    List<T> details;//工序detail和产线detail

    GoodsInProcessStatisticByProcessTotal processTotal;

    public List<T> getDetails() {
        return details;
    }

    public void setDetails(List<T> details) {
        this.details = details;
    }

    public GoodsInProcessStatisticByProcessTotal getProcessTotal() {
        return processTotal;
    }

    public void setProcessTotal(GoodsInProcessStatisticByProcessTotal processTotal) {
        this.processTotal = processTotal;
    }
}
