package com.jinchi.common.dto.processParamer;

import com.jinchi.common.domain.ProcessParametersHcExceptionDispose;

import java.util.List;

public class Hc {

    private Gy gy;

    private List<ProcessParametersHcExceptionDispose> exceptionDisposes;

    private Zjp zjp;

    public Gy getGy() {
        return gy;
    }

    public void setGy(Gy gy) {
        this.gy = gy;
    }

    public List<ProcessParametersHcExceptionDispose> getExceptionDisposes() {
        return exceptionDisposes;
    }

    public void setExceptionDisposes(List<ProcessParametersHcExceptionDispose> exceptionDisposes) {
        this.exceptionDisposes = exceptionDisposes;
    }

    public Zjp getZjp() {
        return zjp;
    }

    public void setZjp(Zjp zjp) {
        this.zjp = zjp;
    }
}
