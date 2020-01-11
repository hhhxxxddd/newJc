package com.jinchi.app.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author: LiuTaoYi
 * @create_time: 2019-08-26 15:57
 * @description:
 **/
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class BasicInfoDeptDto {
    @JsonProperty(value = "deptCode")
    private Integer code;
    private String name;

    public BasicInfoDeptDto(Integer code, String name) {
        this.code = code;
        this.name = name;
    }
    public BasicInfoDeptDto(){

    }

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "BasicInfoDeptDto{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
