package com.jinchi.common.domain;

public class DeviceRepairType {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column device_repair_type.code
     *
     * @mbggenerated Mon Mar 30 13:04:33 CST 2020
     */
    private Integer code;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column device_repair_type.type_name
     *
     * @mbggenerated Mon Mar 30 13:04:33 CST 2020
     */
    private String typeName;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table device_repair_type
     *
     * @mbggenerated Mon Mar 30 13:04:33 CST 2020
     */
    public DeviceRepairType(Integer code, String typeName) {
        this.code = code;
        this.typeName = typeName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table device_repair_type
     *
     * @mbggenerated Mon Mar 30 13:04:33 CST 2020
     */
    public DeviceRepairType() {
        super();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column device_repair_type.code
     *
     * @return the value of device_repair_type.code
     * @mbggenerated Mon Mar 30 13:04:33 CST 2020
     */
    public Integer getCode() {
        return code;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column device_repair_type.code
     *
     * @param code the value for device_repair_type.code
     * @mbggenerated Mon Mar 30 13:04:33 CST 2020
     */
    public void setCode(Integer code) {
        this.code = code;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column device_repair_type.type_name
     *
     * @return the value of device_repair_type.type_name
     * @mbggenerated Mon Mar 30 13:04:33 CST 2020
     */
    public String getTypeName() {
        return typeName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column device_repair_type.type_name
     *
     * @param typeName the value for device_repair_type.type_name
     * @mbggenerated Mon Mar 30 13:04:33 CST 2020
     */
    public void setTypeName(String typeName) {
        this.typeName = typeName == null ? null : typeName.trim();
    }
}