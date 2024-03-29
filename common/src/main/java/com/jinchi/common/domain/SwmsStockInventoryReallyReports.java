package com.jinchi.common.domain;

import java.util.Date;

public class SwmsStockInventoryReallyReports {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SWMS_stock_inventory_really_reports.id
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SWMS_stock_inventory_really_reports.material_type_id
     *
     * @mbggenerated
     */
    private Integer materialTypeId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SWMS_stock_inventory_really_reports.material_sub_type_id
     *
     * @mbggenerated
     */
    private Integer materialSubTypeId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SWMS_stock_inventory_really_reports.material_name_code
     *
     * @mbggenerated
     */
    private Integer materialNameCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SWMS_stock_inventory_really_reports.material_supplier_code
     *
     * @mbggenerated
     */
    private Integer materialSupplierCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SWMS_stock_inventory_really_reports.material_name
     *
     * @mbggenerated
     */
    private String materialName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SWMS_stock_inventory_really_reports.material_batch
     *
     * @mbggenerated
     */
    private String materialBatch;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SWMS_stock_inventory_really_reports.measure_unit
     *
     * @mbggenerated
     */
    private String measureUnit;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SWMS_stock_inventory_really_reports.check_status
     *
     * @mbggenerated
     */
    private Byte checkStatus;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SWMS_stock_inventory_really_reports.real_weight
     *
     * @mbggenerated
     */
    private Float realWeight;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SWMS_stock_inventory_really_reports.useful_weight
     *
     * @mbggenerated
     */
    private Float usefulWeight;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SWMS_stock_inventory_really_reports.stock_date
     *
     * @mbggenerated
     */
    private Date stockDate;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SWMS_stock_inventory_really_reports
     *
     * @mbggenerated
     */
    public SwmsStockInventoryReallyReports(Long id, Integer materialTypeId, Integer materialSubTypeId, Integer materialNameCode, Integer materialSupplierCode, String materialName, String materialBatch, String measureUnit, Byte checkStatus, Float realWeight, Float usefulWeight, Date stockDate) {
        this.id = id;
        this.materialTypeId = materialTypeId;
        this.materialSubTypeId = materialSubTypeId;
        this.materialNameCode = materialNameCode;
        this.materialSupplierCode = materialSupplierCode;
        this.materialName = materialName;
        this.materialBatch = materialBatch;
        this.measureUnit = measureUnit;
        this.checkStatus = checkStatus;
        this.realWeight = realWeight;
        this.usefulWeight = usefulWeight;
        this.stockDate = stockDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SWMS_stock_inventory_really_reports
     *
     * @mbggenerated
     */
    public SwmsStockInventoryReallyReports() {
        super();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SWMS_stock_inventory_really_reports.id
     *
     * @return the value of SWMS_stock_inventory_really_reports.id
     *
     * @mbggenerated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SWMS_stock_inventory_really_reports.id
     *
     * @param id the value for SWMS_stock_inventory_really_reports.id
     *
     * @mbggenerated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SWMS_stock_inventory_really_reports.material_type_id
     *
     * @return the value of SWMS_stock_inventory_really_reports.material_type_id
     *
     * @mbggenerated
     */
    public Integer getMaterialTypeId() {
        return materialTypeId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SWMS_stock_inventory_really_reports.material_type_id
     *
     * @param materialTypeId the value for SWMS_stock_inventory_really_reports.material_type_id
     *
     * @mbggenerated
     */
    public void setMaterialTypeId(Integer materialTypeId) {
        this.materialTypeId = materialTypeId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SWMS_stock_inventory_really_reports.material_sub_type_id
     *
     * @return the value of SWMS_stock_inventory_really_reports.material_sub_type_id
     *
     * @mbggenerated
     */
    public Integer getMaterialSubTypeId() {
        return materialSubTypeId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SWMS_stock_inventory_really_reports.material_sub_type_id
     *
     * @param materialSubTypeId the value for SWMS_stock_inventory_really_reports.material_sub_type_id
     *
     * @mbggenerated
     */
    public void setMaterialSubTypeId(Integer materialSubTypeId) {
        this.materialSubTypeId = materialSubTypeId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SWMS_stock_inventory_really_reports.material_name_code
     *
     * @return the value of SWMS_stock_inventory_really_reports.material_name_code
     *
     * @mbggenerated
     */
    public Integer getMaterialNameCode() {
        return materialNameCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SWMS_stock_inventory_really_reports.material_name_code
     *
     * @param materialNameCode the value for SWMS_stock_inventory_really_reports.material_name_code
     *
     * @mbggenerated
     */
    public void setMaterialNameCode(Integer materialNameCode) {
        this.materialNameCode = materialNameCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SWMS_stock_inventory_really_reports.material_supplier_code
     *
     * @return the value of SWMS_stock_inventory_really_reports.material_supplier_code
     *
     * @mbggenerated
     */
    public Integer getMaterialSupplierCode() {
        return materialSupplierCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SWMS_stock_inventory_really_reports.material_supplier_code
     *
     * @param materialSupplierCode the value for SWMS_stock_inventory_really_reports.material_supplier_code
     *
     * @mbggenerated
     */
    public void setMaterialSupplierCode(Integer materialSupplierCode) {
        this.materialSupplierCode = materialSupplierCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SWMS_stock_inventory_really_reports.material_name
     *
     * @return the value of SWMS_stock_inventory_really_reports.material_name
     *
     * @mbggenerated
     */
    public String getMaterialName() {
        return materialName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SWMS_stock_inventory_really_reports.material_name
     *
     * @param materialName the value for SWMS_stock_inventory_really_reports.material_name
     *
     * @mbggenerated
     */
    public void setMaterialName(String materialName) {
        this.materialName = materialName == null ? null : materialName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SWMS_stock_inventory_really_reports.material_batch
     *
     * @return the value of SWMS_stock_inventory_really_reports.material_batch
     *
     * @mbggenerated
     */
    public String getMaterialBatch() {
        return materialBatch;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SWMS_stock_inventory_really_reports.material_batch
     *
     * @param materialBatch the value for SWMS_stock_inventory_really_reports.material_batch
     *
     * @mbggenerated
     */
    public void setMaterialBatch(String materialBatch) {
        this.materialBatch = materialBatch == null ? null : materialBatch.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SWMS_stock_inventory_really_reports.measure_unit
     *
     * @return the value of SWMS_stock_inventory_really_reports.measure_unit
     *
     * @mbggenerated
     */
    public String getMeasureUnit() {
        return measureUnit;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SWMS_stock_inventory_really_reports.measure_unit
     *
     * @param measureUnit the value for SWMS_stock_inventory_really_reports.measure_unit
     *
     * @mbggenerated
     */
    public void setMeasureUnit(String measureUnit) {
        this.measureUnit = measureUnit == null ? null : measureUnit.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SWMS_stock_inventory_really_reports.check_status
     *
     * @return the value of SWMS_stock_inventory_really_reports.check_status
     *
     * @mbggenerated
     */
    public Byte getCheckStatus() {
        return checkStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SWMS_stock_inventory_really_reports.check_status
     *
     * @param checkStatus the value for SWMS_stock_inventory_really_reports.check_status
     *
     * @mbggenerated
     */
    public void setCheckStatus(Byte checkStatus) {
        this.checkStatus = checkStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SWMS_stock_inventory_really_reports.real_weight
     *
     * @return the value of SWMS_stock_inventory_really_reports.real_weight
     *
     * @mbggenerated
     */
    public Float getRealWeight() {
        return realWeight;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SWMS_stock_inventory_really_reports.real_weight
     *
     * @param realWeight the value for SWMS_stock_inventory_really_reports.real_weight
     *
     * @mbggenerated
     */
    public void setRealWeight(Float realWeight) {
        this.realWeight = realWeight;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SWMS_stock_inventory_really_reports.useful_weight
     *
     * @return the value of SWMS_stock_inventory_really_reports.useful_weight
     *
     * @mbggenerated
     */
    public Float getUsefulWeight() {
        return usefulWeight;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SWMS_stock_inventory_really_reports.useful_weight
     *
     * @param usefulWeight the value for SWMS_stock_inventory_really_reports.useful_weight
     *
     * @mbggenerated
     */
    public void setUsefulWeight(Float usefulWeight) {
        this.usefulWeight = usefulWeight;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SWMS_stock_inventory_really_reports.stock_date
     *
     * @return the value of SWMS_stock_inventory_really_reports.stock_date
     *
     * @mbggenerated
     */
    public Date getStockDate() {
        return stockDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SWMS_stock_inventory_really_reports.stock_date
     *
     * @param stockDate the value for SWMS_stock_inventory_really_reports.stock_date
     *
     * @mbggenerated
     */
    public void setStockDate(Date stockDate) {
        this.stockDate = stockDate;
    }
}