package com.jinchi.common.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 说明:产品线
 * <br>
 *
 * @author ZSCDumin
 * <br>
 * 邮箱: 2712220318@qq.com
 * <br>
 * 日期: 2018/11/15
 * <br>
 * 版本: 1.0
 */
@ApiModel(description = "产品线实体")
public class ProductLine {

    @ApiModelProperty("产品线主键,自增长")
    private Integer id;

    @ApiModelProperty("产品线名称")
    private String name;

    public ProductLine() {
    }

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

    @Override
    public String toString() {
        return "ProductLine{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }



}
