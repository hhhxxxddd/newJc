package com.jinchi.common.dto;

import com.jinchi.common.domain.TechniqueBaseRawManufacturer;
import com.jinchi.common.domain.TechniqueBaseRawMaterial;

public class RawStandardDTO {

    TechniqueBaseRawManufacturer manufacturer;

    TechniqueBaseRawMaterial material;

    Integer standandId;

    public TechniqueBaseRawManufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(TechniqueBaseRawManufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public TechniqueBaseRawMaterial getMaterial() {
        return material;
    }

    public void setMaterial(TechniqueBaseRawMaterial material) {
        this.material = material;
    }

    public Integer getStandandId() {
        return standandId;
    }

    public void setStandandId(Integer standandId) {
        this.standandId = standandId;
    }
}
