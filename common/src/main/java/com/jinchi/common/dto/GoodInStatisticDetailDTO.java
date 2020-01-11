package com.jinchi.common.dto;

import com.jinchi.common.domain.GoodsInProcessStatisticByLineDetail;
import com.jinchi.common.domain.GoodsInProcessStatisticByProcessDetail;
import com.jinchi.common.domain.GoodsInProcessStatisticHead;

import java.util.List;

public class GoodInStatisticDetailDTO {

    GoodsInProcessStatisticHead head;

    GoodsInProcessStatisticByProcessDetail detail;

    GoodsInProcessStatisticByLineDetail lineDetail;

    List materials;//对应6张工序表的结构

    String periodName;

    String processName;

    String lineName;

    Float totalNi;

    Float totalCo;

    Float totalMn;

    Float total;

    public GoodsInProcessStatisticHead getHead() {
        return head;
    }

    public void setHead(GoodsInProcessStatisticHead head) {
        this.head = head;
    }

    public GoodsInProcessStatisticByProcessDetail getDetail() {
        return detail;
    }

    public void setDetail(GoodsInProcessStatisticByProcessDetail detail) {
        this.detail = detail;
    }

    public List getMaterials() {
        return materials;
    }

    public void setMaterials(List materials) {
        this.materials = materials;
    }

    public String getPeriodName() {
        return periodName;
    }

    public void setPeriodName(String periodName) {
        this.periodName = periodName;
    }

    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }

    public Float getTotalNi() {
        return totalNi;
    }

    public void setTotalNi(Float totalNi) {
        this.totalNi = totalNi;
    }

    public Float getTotalCo() {
        return totalCo;
    }

    public void setTotalCo(Float totalCo) {
        this.totalCo = totalCo;
    }

    public Float getTotalMn() {
        return totalMn;
    }

    public void setTotalMn(Float totalMn) {
        this.totalMn = totalMn;
    }

    public GoodsInProcessStatisticByLineDetail getLineDetail() {
        return lineDetail;
    }

    public void setLineDetail(GoodsInProcessStatisticByLineDetail lineDetail) {
        this.lineDetail = lineDetail;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public String getLineName() {
        return lineName;
    }

    public void setLineName(String lineName) {
        this.lineName = lineName;
    }
}
