package com.jinchi.app.dto;

/**
 * @author: LiuTaoYi
 * @time: 2020/3/27 20:55
 * @description:
 */
public class CheckDetailDTO {

    String itemCode;

    String dataType;

    String checkValue;

    String checkResult;

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getCheckValue() {
        return checkValue;
    }

    public void setCheckValue(String checkValue) {
        this.checkValue = checkValue;
    }

    public String getCheckResult() {
        return checkResult;
    }

    public void setCheckResult(String checkResult) {
        this.checkResult = checkResult;
    }
}
