package com.jinchi.common.domain;

public class FireMageTestItems {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column fire_mage_test_items.code
     *
     * @mbggenerated
     */
    private Long code;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column fire_mage_test_items.name
     *
     * @mbggenerated
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column fire_mage_test_items.unit
     *
     * @mbggenerated
     */
    private String unit;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fire_mage_test_items
     *
     * @mbggenerated
     */
    public FireMageTestItems(Long code, String name, String unit) {
        this.code = code;
        this.name = name;
        this.unit = unit;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fire_mage_test_items
     *
     * @mbggenerated
     */
    public FireMageTestItems() {
        super();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column fire_mage_test_items.code
     *
     * @return the value of fire_mage_test_items.code
     *
     * @mbggenerated
     */
    public Long getCode() {
        return code;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column fire_mage_test_items.code
     *
     * @param code the value for fire_mage_test_items.code
     *
     * @mbggenerated
     */
    public void setCode(Long code) {
        this.code = code;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column fire_mage_test_items.name
     *
     * @return the value of fire_mage_test_items.name
     *
     * @mbggenerated
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column fire_mage_test_items.name
     *
     * @param name the value for fire_mage_test_items.name
     *
     * @mbggenerated
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column fire_mage_test_items.unit
     *
     * @return the value of fire_mage_test_items.unit
     *
     * @mbggenerated
     */
    public String getUnit() {
        return unit;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column fire_mage_test_items.unit
     *
     * @param unit the value for fire_mage_test_items.unit
     *
     * @mbggenerated
     */
    public void setUnit(String unit) {
        this.unit = unit == null ? null : unit.trim();
    }
}