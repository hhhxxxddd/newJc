package com.jinchi.common.domain;

public class BasicInfoAnodeProductionType {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column basic_info_anode_production_type.code
     *
     * @mbggenerated Sat Jun 20 16:51:12 CST 2020
     */
    private Integer code;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column basic_info_anode_production_type.name
     *
     * @mbggenerated Sat Jun 20 16:51:12 CST 2020
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column basic_info_anode_production_type.description
     *
     * @mbggenerated Sat Jun 20 16:51:12 CST 2020
     */
    private String description;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table basic_info_anode_production_type
     *
     * @mbggenerated Sat Jun 20 16:51:12 CST 2020
     */
    public BasicInfoAnodeProductionType(Integer code, String name, String description) {
        this.code = code;
        this.name = name;
        this.description = description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table basic_info_anode_production_type
     *
     * @mbggenerated Sat Jun 20 16:51:12 CST 2020
     */
    public BasicInfoAnodeProductionType() {
        super();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column basic_info_anode_production_type.code
     *
     * @return the value of basic_info_anode_production_type.code
     * @mbggenerated Sat Jun 20 16:51:12 CST 2020
     */
    public Integer getCode() {
        return code;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column basic_info_anode_production_type.code
     *
     * @param code the value for basic_info_anode_production_type.code
     *
     * @mbggenerated Sat Jun 20 16:51:12 CST 2020
     */
    public void setCode(Integer code) {
        this.code = code;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column basic_info_anode_production_type.name
     *
     * @return the value of basic_info_anode_production_type.name
     *
     * @mbggenerated Sat Jun 20 16:51:12 CST 2020
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column basic_info_anode_production_type.name
     *
     * @param name the value for basic_info_anode_production_type.name
     *
     * @mbggenerated Sat Jun 20 16:51:12 CST 2020
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column basic_info_anode_production_type.description
     *
     * @return the value of basic_info_anode_production_type.description
     *
     * @mbggenerated Sat Jun 20 16:51:12 CST 2020
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column basic_info_anode_production_type.description
     *
     * @param description the value for basic_info_anode_production_type.description
     *
     * @mbggenerated Sat Jun 20 16:51:12 CST 2020
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
}