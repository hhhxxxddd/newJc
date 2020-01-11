package com.jinchi.common.dto.processParamer;

import com.jinchi.common.domain.ProcessParametersHcDetail;

import java.util.List;

public class Gy {

    private List<ProAndLine> proAndLines;

    private List<ProcessParametersHcDetail> details;

    private String memo;

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public List<ProAndLine> getProAndLines() {
        return proAndLines;
    }

    public void setProAndLines(List<ProAndLine> proAndLines) {
        this.proAndLines = proAndLines;
    }

    public List<ProcessParametersHcDetail> getDetails() {
        return details;
    }

    public void setDetails(List<ProcessParametersHcDetail> details) {
        this.details = details;
    }
}
