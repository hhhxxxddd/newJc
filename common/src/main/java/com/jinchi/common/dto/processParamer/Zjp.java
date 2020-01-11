package com.jinchi.common.dto.processParamer;

import com.jinchi.common.domain.ProcessParametersHcIntermediateGoodsStd;

import java.util.List;

public class Zjp {

    private List<ProcessParametersHcIntermediateGoodsStd> mediate;

    private String memo;

    public List<ProcessParametersHcIntermediateGoodsStd> getMediate() {
        return mediate;
    }

    public void setMediate(List<ProcessParametersHcIntermediateGoodsStd> mediate) {
        this.mediate = mediate;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}
