package com.jinchi.common.dto.processParamer;

import com.jinchi.common.domain.BasicInfoPrecursorProductionLine;

import java.util.List;

public class ProAndLine {
    private String productClassName;

    private Short productClass;

    private List<Integer> lines;

    private List<String> lineNames;

    public List<String> getLineNames() {
        return lineNames;
    }

    public void setLineNames(List<String> lineNames) {
        this.lineNames = lineNames;
    }

    public List<Integer> getLines() {
        return lines;
    }

    public void setLines(List<Integer> lines) {
        this.lines = lines;
    }

    public String getProductClassName() {
        return productClassName;
    }

    public void setProductClassName(String productClassName) {
        this.productClassName = productClassName;
    }

    public Short getProductClass() {
        return productClass;
    }

    public void setProductClass(Short productClass) {
        this.productClass = productClass;
    }
}
