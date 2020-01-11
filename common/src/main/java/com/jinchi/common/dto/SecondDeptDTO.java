package com.jinchi.common.dto;

public class SecondDeptDTO {
    String name;
    Integer code;

    @Override
    public String toString() {
        return " SecondDeptDTO{" +
                "name=" + name +
                ",code=" + code +
                '}';
    }

    public String getName() {
        return name;
    }

    public Integer getCode() {
        return code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
