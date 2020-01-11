package com.jinchi.common.dto;


/**
 * Created by Administrator on 2018/11/20.
 */
public class SampleItemDTO {
    private String itemName;
    private String result;
    private String unit;

    public SampleItemDTO(String itemName, String result, String unit) {
        this.itemName = itemName;
        this.result = result;
        this.unit = unit;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Override
    public String toString() {
        return "Items{" +
                "itemName='" + itemName + '\'' +
                ", result='" + result + '\'' +
                ", unit='" + unit + '\'' +
                '}';
    }

}
