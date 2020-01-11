package com.jinchi.common.dto.technique;

import com.jinchi.common.domain.TechniqueProductStandardRecord;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author：XudongHu
 * @className:TechniqueProductStandardRecordDTO
 * @description:
 * @date:15:12 2018/12/28
 */
public class TechniqueProductStandardRecordDTO {
    @ApiModelProperty("产品名称")
    private String productName;

    @ApiModelProperty("型号名称")
    private String className;

    @ApiModelProperty("成品标准记录表")
    private TechniqueProductStandardRecord techniqueProductStandardRecord;

    @ApiModelProperty("成品标准值")
    private List<TechniqueProductTestItemDTO> techniqueProductTestItemDTOs;

    public TechniqueProductStandardRecord getTechniqueProductStandardRecord() {
        return techniqueProductStandardRecord;
    }

    public TechniqueProductStandardRecordDTO setTechniqueProductStandardRecord(TechniqueProductStandardRecord techniqueProductStandardRecord) {
        this.techniqueProductStandardRecord = techniqueProductStandardRecord;
        return this;
    }

    public List<TechniqueProductTestItemDTO> getTechniqueProductTestItemDTOs() {
        return techniqueProductTestItemDTOs;
    }

    public TechniqueProductStandardRecordDTO setTechniqueProductTestItemDTOs(List<TechniqueProductTestItemDTO> techniqueProductTestItemDTOs) {
        this.techniqueProductTestItemDTOs = techniqueProductTestItemDTOs;
        return this;
    }

    public String getProductName() {
        return productName;
    }

    public TechniqueProductStandardRecordDTO setProductName(String productName) {
        this.productName = productName;
        return this;
    }

    public String getClassName() {
        return className;
    }

    public TechniqueProductStandardRecordDTO setClassName(String className) {
        this.className = className;
        return this;
    }

    @Override
    public String toString() {
        return "TechniqueProductStandardRecordDTO{" +
                "productName='" + productName + '\'' +
                ", className='" + className + '\'' +
                ", techniqueProductStandardRecord=" + techniqueProductStandardRecord +
                ", techniqueProductTestItemDTOs=" + techniqueProductTestItemDTOs +
                '}';
    }
}
