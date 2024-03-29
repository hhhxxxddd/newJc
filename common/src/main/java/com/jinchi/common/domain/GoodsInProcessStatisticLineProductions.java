package com.jinchi.common.domain;

public class GoodsInProcessStatisticLineProductions {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column goods_in_process_statistic_line_productions.code
     *
     * @mbggenerated
     */
    private Long code;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column goods_in_process_statistic_line_productions.statistic_code
     *
     * @mbggenerated
     */
    private Long statisticCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column goods_in_process_statistic_line_productions.process_code
     *
     * @mbggenerated
     */
    private Integer processCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column goods_in_process_statistic_line_productions.line_code
     *
     * @mbggenerated
     */
    private Integer lineCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column goods_in_process_statistic_line_productions.production_type
     *
     * @mbggenerated
     */
    private String productionType;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table goods_in_process_statistic_line_productions
     *
     * @mbggenerated
     */
    public GoodsInProcessStatisticLineProductions(Long code, Long statisticCode, Integer processCode, Integer lineCode, String productionType) {
        this.code = code;
        this.statisticCode = statisticCode;
        this.processCode = processCode;
        this.lineCode = lineCode;
        this.productionType = productionType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table goods_in_process_statistic_line_productions
     *
     * @mbggenerated
     */
    public GoodsInProcessStatisticLineProductions() {
        super();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column goods_in_process_statistic_line_productions.code
     *
     * @return the value of goods_in_process_statistic_line_productions.code
     *
     * @mbggenerated
     */
    public Long getCode() {
        return code;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column goods_in_process_statistic_line_productions.code
     *
     * @param code the value for goods_in_process_statistic_line_productions.code
     *
     * @mbggenerated
     */
    public void setCode(Long code) {
        this.code = code;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column goods_in_process_statistic_line_productions.statistic_code
     *
     * @return the value of goods_in_process_statistic_line_productions.statistic_code
     *
     * @mbggenerated
     */
    public Long getStatisticCode() {
        return statisticCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column goods_in_process_statistic_line_productions.statistic_code
     *
     * @param statisticCode the value for goods_in_process_statistic_line_productions.statistic_code
     *
     * @mbggenerated
     */
    public void setStatisticCode(Long statisticCode) {
        this.statisticCode = statisticCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column goods_in_process_statistic_line_productions.process_code
     *
     * @return the value of goods_in_process_statistic_line_productions.process_code
     *
     * @mbggenerated
     */
    public Integer getProcessCode() {
        return processCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column goods_in_process_statistic_line_productions.process_code
     *
     * @param processCode the value for goods_in_process_statistic_line_productions.process_code
     *
     * @mbggenerated
     */
    public void setProcessCode(Integer processCode) {
        this.processCode = processCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column goods_in_process_statistic_line_productions.line_code
     *
     * @return the value of goods_in_process_statistic_line_productions.line_code
     *
     * @mbggenerated
     */
    public Integer getLineCode() {
        return lineCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column goods_in_process_statistic_line_productions.line_code
     *
     * @param lineCode the value for goods_in_process_statistic_line_productions.line_code
     *
     * @mbggenerated
     */
    public void setLineCode(Integer lineCode) {
        this.lineCode = lineCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column goods_in_process_statistic_line_productions.production_type
     *
     * @return the value of goods_in_process_statistic_line_productions.production_type
     *
     * @mbggenerated
     */
    public String getProductionType() {
        return productionType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column goods_in_process_statistic_line_productions.production_type
     *
     * @param productionType the value for goods_in_process_statistic_line_productions.production_type
     *
     * @mbggenerated
     */
    public void setProductionType(String productionType) {
        this.productionType = productionType == null ? null : productionType.trim();
    }
}