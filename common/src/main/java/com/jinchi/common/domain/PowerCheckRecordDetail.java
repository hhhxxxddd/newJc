package com.jinchi.common.domain;

public class PowerCheckRecordDetail {
    private Long code;

    private Long recordCode;

    private String place;

    private String checkItem;

    private String checkContent;

    private Byte checkValue;

    private String checkResult;

    private Byte dataType;

    public PowerCheckRecordDetail(Long code, Long recordCode, String place, String checkItem, String checkContent, Byte checkValue, String checkResult, Byte dataType) {
        this.code = code;
        this.recordCode = recordCode;
        this.place = place;
        this.checkItem = checkItem;
        this.checkContent = checkContent;
        this.checkValue = checkValue;
        this.checkResult = checkResult;
        this.dataType = dataType;
    }

    public PowerCheckRecordDetail() {
        super();
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public Long getRecordCode() {
        return recordCode;
    }

    public void setRecordCode(Long recordCode) {
        this.recordCode = recordCode;
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

    public Byte getCheckValue() {
        return checkValue;
    }

    public void setCheckValue(Byte checkValue) {
        this.checkValue = checkValue;
    }

    public String getCheckResult() {
        return checkResult;
    }

    public void setCheckResult(String checkResult) {
        this.checkResult = checkResult == null ? null : checkResult.trim();
    }

    public Byte getDataType() {
        return dataType;
    }

    public void setDataType(Byte dataType) {
        this.dataType = dataType;
    }
}