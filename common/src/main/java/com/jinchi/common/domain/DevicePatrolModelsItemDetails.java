package com.jinchi.common.domain;

public class DevicePatrolModelsItemDetails {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column device_patrol_models_item_details.code
     *
     * @mbggenerated
     */
    private Long code;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column device_patrol_models_item_details.model_code
     *
     * @mbggenerated
     */
    private Long modelCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column device_patrol_models_item_details.patrol_item
     *
     * @mbggenerated
     */
    private String patrolItem;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column device_patrol_models_item_details.patrol_content
     *
     * @mbggenerated
     */
    private String patrolContent;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table device_patrol_models_item_details
     *
     * @mbggenerated
     */
    public DevicePatrolModelsItemDetails(Long code, Long modelCode, String patrolItem, String patrolContent) {
        this.code = code;
        this.modelCode = modelCode;
        this.patrolItem = patrolItem;
        this.patrolContent = patrolContent;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table device_patrol_models_item_details
     *
     * @mbggenerated
     */
    public DevicePatrolModelsItemDetails() {
        super();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column device_patrol_models_item_details.code
     *
     * @return the value of device_patrol_models_item_details.code
     *
     * @mbggenerated
     */
    public Long getCode() {
        return code;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column device_patrol_models_item_details.code
     *
     * @param code the value for device_patrol_models_item_details.code
     *
     * @mbggenerated
     */
    public void setCode(Long code) {
        this.code = code;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column device_patrol_models_item_details.model_code
     *
     * @return the value of device_patrol_models_item_details.model_code
     *
     * @mbggenerated
     */
    public Long getModelCode() {
        return modelCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column device_patrol_models_item_details.model_code
     *
     * @param modelCode the value for device_patrol_models_item_details.model_code
     *
     * @mbggenerated
     */
    public void setModelCode(Long modelCode) {
        this.modelCode = modelCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column device_patrol_models_item_details.patrol_item
     *
     * @return the value of device_patrol_models_item_details.patrol_item
     *
     * @mbggenerated
     */
    public String getPatrolItem() {
        return patrolItem;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column device_patrol_models_item_details.patrol_item
     *
     * @param patrolItem the value for device_patrol_models_item_details.patrol_item
     *
     * @mbggenerated
     */
    public void setPatrolItem(String patrolItem) {
        this.patrolItem = patrolItem == null ? null : patrolItem.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column device_patrol_models_item_details.patrol_content
     *
     * @return the value of device_patrol_models_item_details.patrol_content
     *
     * @mbggenerated
     */
    public String getPatrolContent() {
        return patrolContent;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column device_patrol_models_item_details.patrol_content
     *
     * @param patrolContent the value for device_patrol_models_item_details.patrol_content
     *
     * @mbggenerated
     */
    public void setPatrolContent(String patrolContent) {
        this.patrolContent = patrolContent == null ? null : patrolContent.trim();
    }
}