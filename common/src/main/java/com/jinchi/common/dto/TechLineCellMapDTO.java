package com.jinchi.common.dto;

import java.util.List;

/**
 * @author: LiuTaoYi
 * @create_time: 2019-10-15 19:29
 * @description:
 **/

public class TechLineCellMapDTO {
//    Integer code;
    Integer lineCode;
    String  lineName;
    List<PrecursorMaterialDTO> materialDTOS;

//    public Integer getCode() {
//        return code;
//    }
//
//    public void setCode(Integer code) {
//        this.code = code;
//    }

    public Integer getLineCode() {
        return lineCode;
    }

    public void setLineCode(Integer lineCode) {
        this.lineCode = lineCode;
    }

    public String getLineName() {
        return lineName;
    }

    public void setLineName(String lineName) {
        this.lineName = lineName;
    }

    public List<PrecursorMaterialDTO> getMaterialDTOS() {
        return materialDTOS;
    }

    public void setMaterialDTOS(List<PrecursorMaterialDTO> materialDTOS) {
        this.materialDTOS = materialDTOS;
    }
}
