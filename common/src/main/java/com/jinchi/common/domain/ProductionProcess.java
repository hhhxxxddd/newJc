package com.jinchi.common.domain;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 说明: 产品工序
 */
@ApiModel(description = "产品工序实体")
public class ProductionProcess {

    @ApiModelProperty("产品工序主键,自增长")
    private Integer id;

    @ApiModelProperty("产品工序名称")
    private String name;

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
        return "ProductionProcess{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductionProcess)) return false;

        ProductionProcess that = (ProductionProcess) o;

        if (getId() != null ? !getId().equals(that.getId()) : that.getId() != null) return false;
        return getName() != null ? getName().equals(that.getName()) : that.getName() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        return result;
    }
}
