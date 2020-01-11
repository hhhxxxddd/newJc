package com.jinchi.common.dto;

import com.jinchi.common.domain.BasicInfoPrecursorProductionLine;
import com.jinchi.common.domain.BasicInfoVgaPoint;

import java.util.List;

public class VgaWeightDTO {

    BasicInfoVgaPoint vgaPoint;

    List<BasicInfoPrecursorProductionLineDTO> lines;

    public BasicInfoVgaPoint getVgaPoint() {
        return vgaPoint;
    }

    public void setVgaPoint(BasicInfoVgaPoint vgaPoint) {
        this.vgaPoint = vgaPoint;
    }

    public List<BasicInfoPrecursorProductionLineDTO> getLines() {
        return lines;
    }

    public void setLines(List<BasicInfoPrecursorProductionLineDTO> lines) {
        this.lines = lines;
    }
}
