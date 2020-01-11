package com.jinchi.common.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class RealTimeData {
    /**{
        "ID": 10981,
            "TagName": "qianqu_4.202_tt_cd205a_monanal",
            "Time": "2019-10-28 17:20:00.000",
            "Value": "54.98046875",
            "Quality": 0,
            "Error": 0,
            "ErrorMsg": "错误码为：0;错误信息为：操作成功完成"
    }*/
    Long ID;

    String TageName;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.000")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.000",timezone = "GMT+8")
    Date Time;

    Float Value;

    Integer Quality;

    Long Error;

    String ErrorMsg;

    public Long getError() {
        return Error;
    }

    public void setError(Long error) {
        Error = error;
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getTageName() {
        return TageName;
    }

    public void setTageName(String tageName) {
        TageName = tageName;
    }

    public Date getTime() {
        return Time;
    }

    public void setTime(Date time) {
        Time = time;
    }

    public Float getValue() {
        return Value;
    }

    public void setValue(Float value) {
        Value = value;
    }

    public Integer getQuality() {
        return Quality;
    }

    public void setQuality(Integer quality) {
        Quality = quality;
    }

    public String getErrorMsg() {
        return ErrorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        ErrorMsg = errorMsg;
    }
}
