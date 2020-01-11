package com.jinchi.common.dto.technique;

import com.jinchi.common.domain.TechniqueRawStandardRecord;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author：XudongHu
 * @className:TechniqueRawStandardRecordDTO
 * @description:
 * @date:23:55 2018/12/27
 */
public class TechniqueRawStandardRecordDTO {

    @ApiModelProperty("原材料名")
    private String rawMaterialName;

    @ApiModelProperty("原材料厂商名")
    private String rawManufacturerName;

    @ApiModelProperty("原材料标准记录表")
    private TechniqueRawStandardRecord techniqueRawStandardRecord;

    @ApiModelProperty("原材料标准值")
    private List<TechniqueRawTestItemDTO> rawStandards;

    public TechniqueRawStandardRecord getTechniqueRawStandardRecord() {
        return techniqueRawStandardRecord;
    }

    public TechniqueRawStandardRecordDTO setTechniqueRawStandardRecord(TechniqueRawStandardRecord techniqueRawStandardRecord) {
        this.techniqueRawStandardRecord = techniqueRawStandardRecord;
        return this;
    }

    public List<TechniqueRawTestItemDTO> getRawStandards() {
        return rawStandards;
    }

    public TechniqueRawStandardRecordDTO setRawStandards(List<TechniqueRawTestItemDTO> rawStandards) {
        this.rawStandards = rawStandards;
        return this;
    }

    public String getRawMaterialName() {
        return rawMaterialName;
    }

    public TechniqueRawStandardRecordDTO setRawMaterialName(String rawMaterialName) {
        this.rawMaterialName = rawMaterialName;
        return this;
    }

    public String getRawManufacturerName() {
        return rawManufacturerName;
    }

    public TechniqueRawStandardRecordDTO setRawManufacturerName(String rawManufacturerName) {
        this.rawManufacturerName = rawManufacturerName;
        return this;
    }

    @Override
    public String toString() {
        return "TechniqueRawStandardRecordDTO{" +
                "techniqueRawStandardRecord=" + techniqueRawStandardRecord +
                ", rawStandards=" + rawStandards +
                '}';
    }
}
