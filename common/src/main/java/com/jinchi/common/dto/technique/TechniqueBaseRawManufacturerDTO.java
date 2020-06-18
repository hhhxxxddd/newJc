package com.jinchi.common.dto.technique;

import com.jinchi.common.domain.TechniqueBaseRawManufacturer;

/**
 * @author: LiuTaoYi
 * @time: 2020/6/18 20:16
 * @description:
 */
public class TechniqueBaseRawManufacturerDTO {

    private Integer rawMaterialId;
    private TechniqueBaseRawManufacturer techniqueBaseRawManufacturer;

    public Integer getRawMaterialId() {
        return rawMaterialId;
    }

    public void setRawMaterialId(Integer rawMaterialId) {
        this.rawMaterialId = rawMaterialId;
    }

    public TechniqueBaseRawManufacturer getTechniqueBaseRawManufacturer() {
        return techniqueBaseRawManufacturer;
    }

    public void setTechniqueBaseRawManufacturer(TechniqueBaseRawManufacturer techniqueBaseRawManufacturer) {
        this.techniqueBaseRawManufacturer = techniqueBaseRawManufacturer;
    }
}
