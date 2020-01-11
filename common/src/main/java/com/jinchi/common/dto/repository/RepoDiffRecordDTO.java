package com.jinchi.common.dto.repository;

import com.jinchi.common.domain.RepoDiffRecord;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by WangZhihao on 2018/12/17.
 */
public class RepoDiffRecordDTO {
    @ApiModelProperty("盘库记录")
    private RepoDiffRecord repoDiffRecord;
    @ApiModelProperty("产品名称")
    private String materialName;
    @ApiModelProperty("批号")
    private String serialNumber;
    @ApiModelProperty("创建人")
    private String creator;
    @ApiModelProperty("记录主键")
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }


    public RepoDiffRecord getRepoDiffRecord() {
        return repoDiffRecord;
    }

    public void setRepoDiffRecord(RepoDiffRecord repoDiffRecord) {
        this.repoDiffRecord = repoDiffRecord;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }
}
