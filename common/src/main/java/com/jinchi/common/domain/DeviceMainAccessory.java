package com.jinchi.common.domain;

public class DeviceMainAccessory {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column device_main_accessory.code
     *
     * @mbggenerated
     */
    private Long code;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column device_main_accessory.name
     *
     * @mbggenerated
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column device_main_accessory.specification
     *
     * @mbggenerated
     */
    private String specification;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column device_main_accessory.counts
     *
     * @mbggenerated
     */
    private Integer counts;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column device_main_accessory.main_code
     *
     * @mbggenerated
     */
    private Long mainCode;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table device_main_accessory
     *
     * @mbggenerated
     */
    public DeviceMainAccessory(Long code, String name, String specification, Integer counts, Long mainCode) {
        this.code = code;
        this.name = name;
        this.specification = specification;
        this.counts = counts;
        this.mainCode = mainCode;
    }
    public DeviceMainAccessory(){

    }

    public void setCode(Long code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public void setCounts(Integer counts) {
        this.counts = counts;
    }

    public void setMainCode(Long mainCode) {
        this.mainCode = mainCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column device_main_accessory.code
     *
     * @return the value of device_main_accessory.code
     *
     * @mbggenerated
     */
    public Long getCode() {
        return code;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column device_main_accessory.name
     *
     * @return the value of device_main_accessory.name
     *
     * @mbggenerated
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column device_main_accessory.specification
     *
     * @return the value of device_main_accessory.specification
     *
     * @mbggenerated
     */
    public String getSpecification() {
        return specification;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column device_main_accessory.counts
     *
     * @return the value of device_main_accessory.counts
     *
     * @mbggenerated
     */
    public Integer getCounts() {
        return counts;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column device_main_accessory.main_code
     *
     * @return the value of device_main_accessory.main_code
     *
     * @mbggenerated
     */
    public Long getMainCode() {
        return mainCode;
    }
}