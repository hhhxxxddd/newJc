package com.jinchi.common.dto.anode;

import com.jinchi.common.domain.AnodeGoodsInProcessStatisticByProcessTotals;
import com.jinchi.common.domain.AnodeGoodsInProcessStatisticHead;

import java.util.List;

public class AnodeStatisticDTO {

    private AnodeGoodsInProcessStatisticHead head;

    private String periodName;

    private String lineName;

    private Object comment;

    private String processName;

    private String typeName;

    private List<AnodeMaterial> mats;

    private List<AnodeMaterial> others;

    private Float tMix;//总已混

    private Float tBal;//总结存

    private Float tFee;//总进料

    private Float tCom;//总消耗

    private Integer tIn;

    private Integer tOut;

    private Integer bags;

    private Float productWeight;

    private Float productStorage;

    public Float getProductStorage() {
        return productStorage;
    }

    public void setProductStorage(Float productStorage) {
        this.productStorage = productStorage;
    }

    public Float gettCom() {
        return tCom;
    }

    public void settCom(Float tCom) {
        this.tCom = tCom;
    }

    public Integer gettIn() {
        return tIn;
    }

    public void settIn(Integer tIn) {
        this.tIn = tIn;
    }

    public Integer gettOut() {
        return tOut;
    }

    public void settOut(Integer tOut) {
        this.tOut = tOut;
    }

    private AnodeGoodsInProcessStatisticByProcessTotals totals;

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

    public Object getComment() {
        return comment;
    }

    public void setComment(Object comment) {
        this.comment = comment;
    }

    public AnodeGoodsInProcessStatisticByProcessTotals getTotals() {
        return totals;
    }

    public void setTotals(AnodeGoodsInProcessStatisticByProcessTotals totals) {
        this.totals = totals;
    }

    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public List<AnodeMaterial> getMats() {
        return mats;
    }

    public void setMats(List<AnodeMaterial> mats) {
        this.mats = mats;
    }

    public List<AnodeMaterial> getOthers() {
        return others;
    }

    public void setOthers(List<AnodeMaterial> others) {
        this.others = others;
    }

    public Float gettMix() {
        return tMix;
    }

    public void settMix(Float tMix) {
        this.tMix = tMix;
    }

    public Float gettBal() {
        return tBal;
    }

    public void settBal(Float tBal) {
        this.tBal = tBal;
    }

    public Float gettFee() {
        return tFee;
    }

    public void settFee(Float tFee) {
        this.tFee = tFee;
    }

    public Integer getBags() {
        return bags;
    }

    public void setBags(Integer bags) {
        this.bags = bags;
    }

    public Float getProductWeight() {
        return productWeight;
    }

    public void setProductWeight(Float productWeight) {
        this.productWeight = productWeight;
    }
}
