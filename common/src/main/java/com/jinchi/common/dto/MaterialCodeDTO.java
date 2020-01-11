package com.jinchi.common.dto;

/**
 * @author：XudongHu
 * @className:materialCodeDTO
 * @description:
 * @date:13:17 2019/3/15
 * @Modifer:
 */
public class MaterialCodeDTO {
    //批号,我们要加MC/
    private String batchCode;
    //材料名
    private String materialName;
    //材料类型
    private String materialType;
    //车间代号
    private String workShop;
    //厂商代号
    private String manufactureCode;
    //重量
    private String weight;

    public String getBatchCode() {
        return batchCode;
    }

    public MaterialCodeDTO setBatchCode(String batchCode) {
        this.batchCode = batchCode;
        return this;
    }

    public String getMaterialName() {
        return materialName;
    }

    public MaterialCodeDTO setMaterialName(String materialName) {
        this.materialName = materialName;
        return this;
    }

    public String getMaterialType() {
        return materialType;
    }

    public MaterialCodeDTO setMaterialType(String materialType) {
        this.materialType = materialType;
        return this;
    }

    public String getWorkShop() {
        return workShop;
    }

    public MaterialCodeDTO setWorkShop(String workShop) {
        this.workShop = workShop;
        return this;
    }


    public String getManufactureCode() {
        return manufactureCode;
    }

    public MaterialCodeDTO setManufactureCode(String manufactureCode) {
        this.manufactureCode = manufactureCode;
        return this;
    }

    public String getWeight() {
        return weight;
    }

    public MaterialCodeDTO setWeight(String weight) {
        this.weight = weight;
        return this;
    }

}
