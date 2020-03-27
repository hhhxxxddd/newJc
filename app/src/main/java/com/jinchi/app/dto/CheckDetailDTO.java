package com.jinchi.app.dto;

/**
 * @author: LiuTaoYi
 * @time: 2020/3/27 20:55
 * @description:
 */
public class CheckDetailDTO {

    String itemCode;

    String dateType;

    String checkValue;

    String CheckResult;

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getDateType() {
        return dateType;
    }

    public void setDateType(String dateType) {
        this.dateType = dateType;
    }

    public String getCheckValue() {
        return checkValue;
    }

    public void setCheckValue(String checkValue) {
        this.checkValue = checkValue;
    }

    public String getCheckResult() {
        return CheckResult;
    }

    public void setCheckResult(String checkResult) {
        CheckResult = checkResult;
    }
}
