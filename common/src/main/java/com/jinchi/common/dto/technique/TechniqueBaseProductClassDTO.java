package com.jinchi.common.dto.technique;

import com.jinchi.common.domain.TechniqueBaseProductClass;

/**
 * @author: LiuTaoYi
 * @time: 2020/6/19 11:54
 * @description:
 */
public class TechniqueBaseProductClassDTO {

    private TechniqueBaseProductClass techniqueBaseProductClass;
    private Integer productId;

    public TechniqueBaseProductClass getTechniqueBaseProductClass() {
        return techniqueBaseProductClass;
    }

    public void setTechniqueBaseProductClass(TechniqueBaseProductClass techniqueBaseProductClass) {
        this.techniqueBaseProductClass = techniqueBaseProductClass;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }
}
