package com.jinchi.app.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author: LiuTaoYi
 * @create_time: 2019-09-16 15:12
 * @description:
 **/

public class SpotcheckNFCDTO {
    private Integer userId;
    private String icCardCode;


    public Integer getUserId() {
        return userId;
    }
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getIcCardCode() {
        return icCardCode;
    }

    public void setIcCardCode(String icCardCode) {
        this.icCardCode = icCardCode;
    }
}
