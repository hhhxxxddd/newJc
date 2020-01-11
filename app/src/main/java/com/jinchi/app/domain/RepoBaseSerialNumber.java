package com.jinchi.app.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 批号映射表，这和Common batch number有区别
 *
 * @author lllyyyggg
 */
@ApiModel(description = "物料编号")
public class RepoBaseSerialNumber {

    @ApiModelProperty("主键")
    private Integer id;             //PK

    @ApiModelProperty("编号")
    private String serialNumber;    //NN 编号

    @ApiModelProperty("物料名称")
    @NotBlank(message = "物料名称不能为空")
    private String materialName;    //NN 物料名称

    @ApiModelProperty("物料型号")
    @NotNull(message = "物料型号不能为空")
    private Integer materialClass;  //NN 物料型号

    @ApiModelProperty("生产厂家名称")
    private String manufacturerName; //NN 生产厂家名称

    public Integer getId() {
        return id;
    }

    public RepoBaseSerialNumber setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public RepoBaseSerialNumber setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
        return this;
    }

    public String getMaterialName() {
        return materialName;
    }

    public RepoBaseSerialNumber setMaterialName(String materialName) {
        this.materialName = materialName;
        return this;
    }

    public Integer getMaterialClass() {
        return materialClass;
    }

    public RepoBaseSerialNumber setMaterialClass(Integer materialClass) {
        this.materialClass = materialClass;
        return this;
    }

    public String getManufacturerName() {
        return manufacturerName;
    }

    public RepoBaseSerialNumber setManufacturerName(String manufacturerName) {
        this.manufacturerName = manufacturerName;
        return this;
    }

    @Override
    public String toString() {
        return "RepoBaseSerialNumber{" +
                "id=" + id +
                ", serialNumber='" + serialNumber + '\'' +
                ", materialName='" + materialName + '\'' +
                ", materialClass=" + materialClass +
                ", manufacturerName='" + manufacturerName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RepoBaseSerialNumber)) return false;

        RepoBaseSerialNumber that = (RepoBaseSerialNumber) o;

        if (getId() != null ? !getId().equals(that.getId()) : that.getId() != null) return false;
        if (getSerialNumber() != null ? !getSerialNumber().equals(that.getSerialNumber()) : that.getSerialNumber() != null)
            return false;
        if (getMaterialName() != null ? !getMaterialName().equals(that.getMaterialName()) : that.getMaterialName() != null)
            return false;
        if (getMaterialClass() != null ? !getMaterialClass().equals(that.getMaterialClass()) : that.getMaterialClass() != null)
            return false;
        return getManufacturerName() != null ? getManufacturerName().equals(that.getManufacturerName()) : that.getManufacturerName() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getSerialNumber() != null ? getSerialNumber().hashCode() : 0);
        result = 31 * result + (getMaterialName() != null ? getMaterialName().hashCode() : 0);
        result = 31 * result + (getMaterialClass() != null ? getMaterialClass().hashCode() : 0);
        result = 31 * result + (getManufacturerName() != null ? getManufacturerName().hashCode() : 0);
        return result;
    }
}
