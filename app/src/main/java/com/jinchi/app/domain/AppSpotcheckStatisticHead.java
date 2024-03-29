package com.jinchi.app.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class AppSpotcheckStatisticHead {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app_spotcheck_statistic_head.code
     *
     * @mbggenerated
     */
    private Long code;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app_spotcheck_statistic_head.dept_code
     *
     * @mbggenerated
     */
    private Integer deptCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app_spotcheck_statistic_head.dept_name
     *
     * @mbggenerated
     */
    private String deptName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app_spotcheck_statistic_head.statistical_date
     *
     * @mbggenerated
     */
    @DateTimeFormat(pattern = "yyyy-MM")
    @JsonFormat(pattern = "yyyy-MM",timezone = "GMT+8")
    private Date statisticalDate;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app_spotcheck_statistic_head
     *
     * @mbggenerated
     */
    public AppSpotcheckStatisticHead(Long code, Integer deptCode, String deptName, Date statisticalDate) {
        this.code = code;
        this.deptCode = deptCode;
        this.deptName = deptName;
        this.statisticalDate = statisticalDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app_spotcheck_statistic_head
     *
     * @mbggenerated
     */
    public AppSpotcheckStatisticHead() {
        super();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column app_spotcheck_statistic_head.code
     *
     * @return the value of app_spotcheck_statistic_head.code
     *
     * @mbggenerated
     */
    public Long getCode() {
        return code;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column app_spotcheck_statistic_head.code
     *
     * @param code the value for app_spotcheck_statistic_head.code
     *
     * @mbggenerated
     */
    public void setCode(Long code) {
        this.code = code;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column app_spotcheck_statistic_head.dept_code
     *
     * @return the value of app_spotcheck_statistic_head.dept_code
     *
     * @mbggenerated
     */
    public Integer getDeptCode() {
        return deptCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column app_spotcheck_statistic_head.dept_code
     *
     * @param deptCode the value for app_spotcheck_statistic_head.dept_code
     *
     * @mbggenerated
     */
    public void setDeptCode(Integer deptCode) {
        this.deptCode = deptCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column app_spotcheck_statistic_head.dept_name
     *
     * @return the value of app_spotcheck_statistic_head.dept_name
     *
     * @mbggenerated
     */
    public String getDeptName() {
        return deptName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column app_spotcheck_statistic_head.dept_name
     *
     * @param deptName the value for app_spotcheck_statistic_head.dept_name
     *
     * @mbggenerated
     */
    public void setDeptName(String deptName) {
        this.deptName = deptName == null ? null : deptName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column app_spotcheck_statistic_head.statistical_date
     *
     * @return the value of app_spotcheck_statistic_head.statistical_date
     *
     * @mbggenerated
     */
    public Date getStatisticalDate() {
        return statisticalDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column app_spotcheck_statistic_head.statistical_date
     *
     * @param statisticalDate the value for app_spotcheck_statistic_head.statistical_date
     *
     * @mbggenerated
     */
    public void setStatisticalDate(Date statisticalDate) {
        this.statisticalDate = statisticalDate;
    }
}