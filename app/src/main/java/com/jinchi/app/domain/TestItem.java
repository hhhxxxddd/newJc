package com.jinchi.app.domain;

import io.swagger.annotations.ApiModel;


/**
 * Created by Administrator on 2018/11/19.
 */
@ApiModel(description = "检测项目实体")
public class TestItem {

    private Integer id;     //PK
    private String name;    //NN
    private String unit;    //NN    计量单位

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Override
    public String toString() {
        return "TestItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", unit='" + unit + '\'' +
                '}';
    }
}
