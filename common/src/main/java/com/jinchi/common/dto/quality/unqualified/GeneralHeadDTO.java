package com.jinchi.common.dto.quality.unqualified;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jinchi.common.domain.PurchaseReportRecord;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author：XudongHu
 * @className:GeneralHeadDTO
 * @description: 成品和原材料具有不同的表头,使用同一个类管理
 * @date:15:51 2019/1/24
 * @Modifer:
 */
public class GeneralHeadDTO {
    @ApiModelProperty("物料名称")
    private String materialName;

    @ApiModelProperty("工厂名称")
    private String factory;

    @ApiModelProperty("检测人")
    private String tester;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date date;

    @ApiModelProperty("规格")
    private String norm;

    @ApiModelProperty("重量")
    private String weight;

    @ApiModelProperty("数量")
    private String quantity;

    public String getMaterialName() {
        return materialName;
    }

    public GeneralHeadDTO setMaterialName(String materialName) {
        this.materialName = materialName;
        return this;
    }

    public String getFactory() {
        return factory;
    }

    public GeneralHeadDTO setFactory(String factory) {
        this.factory = factory;
        return this;
    }

    public String getTester() {
        return tester;
    }

    public GeneralHeadDTO setTester(String tester) {
        this.tester = tester;
        return this;
    }

    public Date getDate() {
        return date;
    }

    public GeneralHeadDTO setDate(Date date) {
        this.date = date;
        return this;
    }

    public String getNorm() {
        return norm;
    }

    public GeneralHeadDTO setNorm(String norm) {
        this.norm = norm;
        return this;
    }

    public String getWeight() {
        return weight;
    }

    public GeneralHeadDTO setWeight(String weight) {
        this.weight = weight;
        return this;
    }

    public String getQuantity() {
        return quantity;
    }

    public GeneralHeadDTO setQuantity(String quantity) {
        this.quantity = quantity;
        return this;
    }

    public void purchaseSetting(PurchaseReportRecord reportRecord){
            this.quantity = reportRecord.getQuantity();
            this.weight = reportRecord.getWeight();
            this.norm = reportRecord.getNorm();
            this.date = reportRecord.getReceiveDate();
    }

    @Override
    public String toString() {
        return "GeneralHeadDTO{" +
                "materialName='" + materialName + '\'' +
                ", factorypattern='" + factory + '\'' +
                ", tester='" + tester + '\'' +
                ", date=" + date +
                ", norm='" + norm + '\'' +
                ", weight='" + weight + '\'' +
                ", quantity='" + quantity + '\'' +
                '}';
    }
}
