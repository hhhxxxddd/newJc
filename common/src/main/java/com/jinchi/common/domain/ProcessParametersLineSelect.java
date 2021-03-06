package com.jinchi.common.domain;

public class ProcessParametersLineSelect {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column process_parameters_line_select.code
     *
     * @mbggenerated
     */
    private Long code;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column process_parameters_line_select.process_code
     *
     * @mbggenerated
     */
    private Long processCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column process_parameters_line_select.line_code
     *
     * @mbggenerated
     */
    private Integer lineCode;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table process_parameters_line_select
     *
     * @mbggenerated
     */
    public ProcessParametersLineSelect(Long code, Long processCode, Integer lineCode) {
        this.code = code;
        this.processCode = processCode;
        this.lineCode = lineCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table process_parameters_line_select
     *
     * @mbggenerated
     */
    public ProcessParametersLineSelect() {
        super();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column process_parameters_line_select.code
     *
     * @return the value of process_parameters_line_select.code
     *
     * @mbggenerated
     */
    public Long getCode() {
        return code;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column process_parameters_line_select.code
     *
     * @param code the value for process_parameters_line_select.code
     *
     * @mbggenerated
     */
    public void setCode(Long code) {
        this.code = code;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column process_parameters_line_select.process_code
     *
     * @return the value of process_parameters_line_select.process_code
     *
     * @mbggenerated
     */
    public Long getProcessCode() {
        return processCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column process_parameters_line_select.process_code
     *
     * @param processCode the value for process_parameters_line_select.process_code
     *
     * @mbggenerated
     */
    public void setProcessCode(Long processCode) {
        this.processCode = processCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column process_parameters_line_select.line_code
     *
     * @return the value of process_parameters_line_select.line_code
     *
     * @mbggenerated
     */
    public Integer getLineCode() {
        return lineCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column process_parameters_line_select.line_code
     *
     * @param lineCode the value for process_parameters_line_select.line_code
     *
     * @mbggenerated
     */
    public void setLineCode(Integer lineCode) {
        this.lineCode = lineCode;
    }
}