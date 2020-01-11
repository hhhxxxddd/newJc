package com.jinchi.common.domain;

public class PowerCheckModelDetail {
    private Long code;

    private Long modelCode;

    private String place;

    private String checkItem;

    private String checkContent;

    private Byte dataType;

    private String frequency;

    public PowerCheckModelDetail(Long code, Long modelCode, String place, String checkItem, String checkContent, Byte dataType, String frequency) {
        this.code = code;
        this.modelCode = modelCode;
        this.place = place;
        this.checkItem = checkItem;
        this.checkContent = checkContent;
        this.dataType = dataType;
        this.frequency = frequency;
    }

    public PowerCheckModelDetail() {
        super();
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public Long getModelCode() {
        return modelCode;
    }

    public void setModelCode(Long modelCode) {
        this.modelCode = modelCode;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place == null ? null : place.trim();
    }

    public String getCheckItem() {
        return checkItem;
    }

    public void setCheckItem(String checkItem) {
        this.checkItem = checkItem == null ? null : checkItem.trim();
    }

    public String getCheckContent() {
        return checkContent;
    }

    public void setCheckContent(String checkContent) {
        this.checkContent = checkContent == null ? null : checkContent.trim();
    }

    public Byte getDataType() {
        return dataType;
    }

    public void setDataType(Byte dataType) {
        this.dataType = dataType;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency == null ? null : frequency.trim();
    }
}