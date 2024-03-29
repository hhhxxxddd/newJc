package com.jinchi.common.domain;

public class BasicInfoDeviceStatus {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column basic_info_device_status.code
     *
     * @mbggenerated
     */
    private Integer code;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column basic_info_device_status.name
     *
     * @mbggenerated
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column basic_info_device_status.color
     *
     * @mbggenerated
     */
    private String color;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table basic_info_device_status
     *
     * @mbggenerated
     */
    public BasicInfoDeviceStatus(Integer code, String name, String color) {
        this.code = code;
        this.name = name;
        this.color = color;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table basic_info_device_status
     *
     * @mbggenerated
     */
    public BasicInfoDeviceStatus() {
        super();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column basic_info_device_status.code
     *
     * @return the value of basic_info_device_status.code
     *
     * @mbggenerated
     */
    public Integer getCode() {
        return code;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column basic_info_device_status.code
     *
     * @param code the value for basic_info_device_status.code
     *
     * @mbggenerated
     */
    public void setCode(Integer code) {
        this.code = code;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column basic_info_device_status.name
     *
     * @return the value of basic_info_device_status.name
     *
     * @mbggenerated
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column basic_info_device_status.name
     *
     * @param name the value for basic_info_device_status.name
     *
     * @mbggenerated
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column basic_info_device_status.color
     *
     * @return the value of basic_info_device_status.color
     *
     * @mbggenerated
     */
    public String getColor() {
        return color;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column basic_info_device_status.color
     *
     * @param color the value for basic_info_device_status.color
     *
     * @mbggenerated
     */
    public void setColor(String color) {
        this.color = color == null ? null : color.trim();
    }
}