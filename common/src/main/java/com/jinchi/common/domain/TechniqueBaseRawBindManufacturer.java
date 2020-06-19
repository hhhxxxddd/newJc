package com.jinchi.common.domain;

public class TechniqueBaseRawBindManufacturer {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column technique_base_raw_bind_manufacturer.id
     *
     * @mbggenerated Thu Jun 18 20:43:51 CST 2020
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column technique_base_raw_bind_manufacturer.raw_id
     *
     * @mbggenerated Thu Jun 18 20:43:51 CST 2020
     */
    private Integer rawId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column technique_base_raw_bind_manufacturer.manufacturer_id
     *
     * @mbggenerated Thu Jun 18 20:43:51 CST 2020
     */
    private Integer manufacturerId;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table technique_base_raw_bind_manufacturer
     *
     * @mbggenerated Thu Jun 18 20:43:51 CST 2020
     */
    public TechniqueBaseRawBindManufacturer(Integer id, Integer rawId, Integer manufacturerId) {
        this.id = id;
        this.rawId = rawId;
        this.manufacturerId = manufacturerId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table technique_base_raw_bind_manufacturer
     *
     * @mbggenerated Thu Jun 18 20:43:51 CST 2020
     */
    public TechniqueBaseRawBindManufacturer() {
        super();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column technique_base_raw_bind_manufacturer.id
     *
     * @return the value of technique_base_raw_bind_manufacturer.id
     * @mbggenerated Thu Jun 18 20:43:51 CST 2020
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column technique_base_raw_bind_manufacturer.id
     *
     * @param id the value for technique_base_raw_bind_manufacturer.id
     * @mbggenerated Thu Jun 18 20:43:51 CST 2020
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column technique_base_raw_bind_manufacturer.raw_id
     *
     * @return the value of technique_base_raw_bind_manufacturer.raw_id
     * @mbggenerated Thu Jun 18 20:43:51 CST 2020
     */
    public Integer getRawId() {
        return rawId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column technique_base_raw_bind_manufacturer.raw_id
     *
     * @param rawId the value for technique_base_raw_bind_manufacturer.raw_id
     * @mbggenerated Thu Jun 18 20:43:51 CST 2020
     */
    public void setRawId(Integer rawId) {
        this.rawId = rawId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column technique_base_raw_bind_manufacturer.manufacturer_id
     *
     * @return the value of technique_base_raw_bind_manufacturer.manufacturer_id
     * @mbggenerated Thu Jun 18 20:43:51 CST 2020
     */
    public Integer getManufacturerId() {
        return manufacturerId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column technique_base_raw_bind_manufacturer.manufacturer_id
     *
     * @param manufacturerId the value for technique_base_raw_bind_manufacturer.manufacturer_id
     * @mbggenerated Thu Jun 18 20:43:51 CST 2020
     */
    public void setManufacturerId(Integer manufacturerId) {
        this.manufacturerId = manufacturerId;
    }
}