package com.jinchi.common.domain;

public class AnodeCostAccountingStatisticByLineDetail {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column anode_cost_accounting_statistic_by_line_detail.code
     *
     * @mbggenerated
     */
    private Long code;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column anode_cost_accounting_statistic_by_line_detail.statistic_code
     *
     * @mbggenerated
     */
    private Long statisticCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column anode_cost_accounting_statistic_by_line_detail.periods
     *
     * @mbggenerated
     */
    private Integer periods;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column anode_cost_accounting_statistic_by_line_detail.line_coed
     *
     * @mbggenerated
     */
    private Integer lineCoed;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column anode_cost_accounting_statistic_by_line_detail.type_code
     *
     * @mbggenerated
     */
    private Integer typeCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column anode_cost_accounting_statistic_by_line_detail.material_requisitions
     *
     * @mbggenerated
     */
    private Float materialRequisitions;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column anode_cost_accounting_statistic_by_line_detail.material_balance
     *
     * @mbggenerated
     */
    private Float materialBalance;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column anode_cost_accounting_statistic_by_line_detail.last_material_in_process_first
     *
     * @mbggenerated
     */
    private Float lastMaterialInProcessFirst;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column anode_cost_accounting_statistic_by_line_detail.current_goods_in_process_first
     *
     * @mbggenerated
     */
    private Float currentGoodsInProcessFirst;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column anode_cost_accounting_statistic_by_line_detail.last_goods_in_process_second
     *
     * @mbggenerated
     */
    private Float lastGoodsInProcessSecond;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column anode_cost_accounting_statistic_by_line_detail.current_goods_in_process_second
     *
     * @mbggenerated
     */
    private Float currentGoodsInProcessSecond;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column anode_cost_accounting_statistic_by_line_detail.unit_consumption_precursor
     *
     * @mbggenerated
     */
    private Float unitConsumptionPrecursor;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column anode_cost_accounting_statistic_by_line_detail.unit_consumption_neurolithium
     *
     * @mbggenerated
     */
    private Float unitConsumptionNeurolithium;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column anode_cost_accounting_statistic_by_line_detail.product_storage
     *
     * @mbggenerated
     */
    private Float productStorage;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table anode_cost_accounting_statistic_by_line_detail
     *
     * @mbggenerated
     */
    public AnodeCostAccountingStatisticByLineDetail(Long code, Long statisticCode, Integer periods, Integer lineCoed, Integer typeCode, Float materialRequisitions, Float materialBalance, Float lastMaterialInProcessFirst, Float currentGoodsInProcessFirst, Float lastGoodsInProcessSecond, Float currentGoodsInProcessSecond, Float unitConsumptionPrecursor, Float unitConsumptionNeurolithium, Float productStorage) {
        this.code = code;
        this.statisticCode = statisticCode;
        this.periods = periods;
        this.lineCoed = lineCoed;
        this.typeCode = typeCode;
        this.materialRequisitions = materialRequisitions;
        this.materialBalance = materialBalance;
        this.lastMaterialInProcessFirst = lastMaterialInProcessFirst;
        this.currentGoodsInProcessFirst = currentGoodsInProcessFirst;
        this.lastGoodsInProcessSecond = lastGoodsInProcessSecond;
        this.currentGoodsInProcessSecond = currentGoodsInProcessSecond;
        this.unitConsumptionPrecursor = unitConsumptionPrecursor;
        this.unitConsumptionNeurolithium = unitConsumptionNeurolithium;
        this.productStorage = productStorage;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table anode_cost_accounting_statistic_by_line_detail
     *
     * @mbggenerated
     */
    public AnodeCostAccountingStatisticByLineDetail() {
        super();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column anode_cost_accounting_statistic_by_line_detail.code
     *
     * @return the value of anode_cost_accounting_statistic_by_line_detail.code
     *
     * @mbggenerated
     */
    public Long getCode() {
        return code;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column anode_cost_accounting_statistic_by_line_detail.code
     *
     * @param code the value for anode_cost_accounting_statistic_by_line_detail.code
     *
     * @mbggenerated
     */
    public void setCode(Long code) {
        this.code = code;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column anode_cost_accounting_statistic_by_line_detail.statistic_code
     *
     * @return the value of anode_cost_accounting_statistic_by_line_detail.statistic_code
     *
     * @mbggenerated
     */
    public Long getStatisticCode() {
        return statisticCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column anode_cost_accounting_statistic_by_line_detail.statistic_code
     *
     * @param statisticCode the value for anode_cost_accounting_statistic_by_line_detail.statistic_code
     *
     * @mbggenerated
     */
    public void setStatisticCode(Long statisticCode) {
        this.statisticCode = statisticCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column anode_cost_accounting_statistic_by_line_detail.periods
     *
     * @return the value of anode_cost_accounting_statistic_by_line_detail.periods
     *
     * @mbggenerated
     */
    public Integer getPeriods() {
        return periods;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column anode_cost_accounting_statistic_by_line_detail.periods
     *
     * @param periods the value for anode_cost_accounting_statistic_by_line_detail.periods
     *
     * @mbggenerated
     */
    public void setPeriods(Integer periods) {
        this.periods = periods;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column anode_cost_accounting_statistic_by_line_detail.line_coed
     *
     * @return the value of anode_cost_accounting_statistic_by_line_detail.line_coed
     *
     * @mbggenerated
     */
    public Integer getLineCoed() {
        return lineCoed;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column anode_cost_accounting_statistic_by_line_detail.line_coed
     *
     * @param lineCoed the value for anode_cost_accounting_statistic_by_line_detail.line_coed
     *
     * @mbggenerated
     */
    public void setLineCoed(Integer lineCoed) {
        this.lineCoed = lineCoed;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column anode_cost_accounting_statistic_by_line_detail.type_code
     *
     * @return the value of anode_cost_accounting_statistic_by_line_detail.type_code
     *
     * @mbggenerated
     */
    public Integer getTypeCode() {
        return typeCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column anode_cost_accounting_statistic_by_line_detail.type_code
     *
     * @param typeCode the value for anode_cost_accounting_statistic_by_line_detail.type_code
     *
     * @mbggenerated
     */
    public void setTypeCode(Integer typeCode) {
        this.typeCode = typeCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column anode_cost_accounting_statistic_by_line_detail.material_requisitions
     *
     * @return the value of anode_cost_accounting_statistic_by_line_detail.material_requisitions
     *
     * @mbggenerated
     */
    public Float getMaterialRequisitions() {
        return materialRequisitions;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column anode_cost_accounting_statistic_by_line_detail.material_requisitions
     *
     * @param materialRequisitions the value for anode_cost_accounting_statistic_by_line_detail.material_requisitions
     *
     * @mbggenerated
     */
    public void setMaterialRequisitions(Float materialRequisitions) {
        this.materialRequisitions = materialRequisitions;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column anode_cost_accounting_statistic_by_line_detail.material_balance
     *
     * @return the value of anode_cost_accounting_statistic_by_line_detail.material_balance
     *
     * @mbggenerated
     */
    public Float getMaterialBalance() {
        return materialBalance;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column anode_cost_accounting_statistic_by_line_detail.material_balance
     *
     * @param materialBalance the value for anode_cost_accounting_statistic_by_line_detail.material_balance
     *
     * @mbggenerated
     */
    public void setMaterialBalance(Float materialBalance) {
        this.materialBalance = materialBalance;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column anode_cost_accounting_statistic_by_line_detail.last_material_in_process_first
     *
     * @return the value of anode_cost_accounting_statistic_by_line_detail.last_material_in_process_first
     *
     * @mbggenerated
     */
    public Float getLastMaterialInProcessFirst() {
        return lastMaterialInProcessFirst;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column anode_cost_accounting_statistic_by_line_detail.last_material_in_process_first
     *
     * @param lastMaterialInProcessFirst the value for anode_cost_accounting_statistic_by_line_detail.last_material_in_process_first
     *
     * @mbggenerated
     */
    public void setLastMaterialInProcessFirst(Float lastMaterialInProcessFirst) {
        this.lastMaterialInProcessFirst = lastMaterialInProcessFirst;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column anode_cost_accounting_statistic_by_line_detail.current_goods_in_process_first
     *
     * @return the value of anode_cost_accounting_statistic_by_line_detail.current_goods_in_process_first
     *
     * @mbggenerated
     */
    public Float getCurrentGoodsInProcessFirst() {
        return currentGoodsInProcessFirst;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column anode_cost_accounting_statistic_by_line_detail.current_goods_in_process_first
     *
     * @param currentGoodsInProcessFirst the value for anode_cost_accounting_statistic_by_line_detail.current_goods_in_process_first
     *
     * @mbggenerated
     */
    public void setCurrentGoodsInProcessFirst(Float currentGoodsInProcessFirst) {
        this.currentGoodsInProcessFirst = currentGoodsInProcessFirst;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column anode_cost_accounting_statistic_by_line_detail.last_goods_in_process_second
     *
     * @return the value of anode_cost_accounting_statistic_by_line_detail.last_goods_in_process_second
     *
     * @mbggenerated
     */
    public Float getLastGoodsInProcessSecond() {
        return lastGoodsInProcessSecond;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column anode_cost_accounting_statistic_by_line_detail.last_goods_in_process_second
     *
     * @param lastGoodsInProcessSecond the value for anode_cost_accounting_statistic_by_line_detail.last_goods_in_process_second
     *
     * @mbggenerated
     */
    public void setLastGoodsInProcessSecond(Float lastGoodsInProcessSecond) {
        this.lastGoodsInProcessSecond = lastGoodsInProcessSecond;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column anode_cost_accounting_statistic_by_line_detail.current_goods_in_process_second
     *
     * @return the value of anode_cost_accounting_statistic_by_line_detail.current_goods_in_process_second
     *
     * @mbggenerated
     */
    public Float getCurrentGoodsInProcessSecond() {
        return currentGoodsInProcessSecond;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column anode_cost_accounting_statistic_by_line_detail.current_goods_in_process_second
     *
     * @param currentGoodsInProcessSecond the value for anode_cost_accounting_statistic_by_line_detail.current_goods_in_process_second
     *
     * @mbggenerated
     */
    public void setCurrentGoodsInProcessSecond(Float currentGoodsInProcessSecond) {
        this.currentGoodsInProcessSecond = currentGoodsInProcessSecond;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column anode_cost_accounting_statistic_by_line_detail.unit_consumption_precursor
     *
     * @return the value of anode_cost_accounting_statistic_by_line_detail.unit_consumption_precursor
     *
     * @mbggenerated
     */
    public Float getUnitConsumptionPrecursor() {
        return unitConsumptionPrecursor;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column anode_cost_accounting_statistic_by_line_detail.unit_consumption_precursor
     *
     * @param unitConsumptionPrecursor the value for anode_cost_accounting_statistic_by_line_detail.unit_consumption_precursor
     *
     * @mbggenerated
     */
    public void setUnitConsumptionPrecursor(Float unitConsumptionPrecursor) {
        this.unitConsumptionPrecursor = unitConsumptionPrecursor;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column anode_cost_accounting_statistic_by_line_detail.unit_consumption_neurolithium
     *
     * @return the value of anode_cost_accounting_statistic_by_line_detail.unit_consumption_neurolithium
     *
     * @mbggenerated
     */
    public Float getUnitConsumptionNeurolithium() {
        return unitConsumptionNeurolithium;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column anode_cost_accounting_statistic_by_line_detail.unit_consumption_neurolithium
     *
     * @param unitConsumptionNeurolithium the value for anode_cost_accounting_statistic_by_line_detail.unit_consumption_neurolithium
     *
     * @mbggenerated
     */
    public void setUnitConsumptionNeurolithium(Float unitConsumptionNeurolithium) {
        this.unitConsumptionNeurolithium = unitConsumptionNeurolithium;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column anode_cost_accounting_statistic_by_line_detail.product_storage
     *
     * @return the value of anode_cost_accounting_statistic_by_line_detail.product_storage
     *
     * @mbggenerated
     */
    public Float getProductStorage() {
        return productStorage;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column anode_cost_accounting_statistic_by_line_detail.product_storage
     *
     * @param productStorage the value for anode_cost_accounting_statistic_by_line_detail.product_storage
     *
     * @mbggenerated
     */
    public void setProductStorage(Float productStorage) {
        this.productStorage = productStorage;
    }
}