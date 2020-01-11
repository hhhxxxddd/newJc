package com.jinchi.auth.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 说明: 操作实体类
 * <br>
 *
 * @author ZSCDumin
 * <br>
 * 邮箱: 2712220318@qq.com
 * <br>
 * 日期: 2018/11/12
 * <br>
 * 版本: 1.0
 */
@ApiModel(description = "操作权限实体")
public class Operation {

    @ApiModelProperty("操作主键,自增长")
    private Integer id;

    @ApiModelProperty("操作名称")
    private String operationName;

    @ApiModelProperty("操作名称代号")
    private String operationCode;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOperationName() {
        return operationName;
    }

    public void setOperationName(String operationName) {
        this.operationName = operationName;
    }

    public String getOperationCode() {
        return operationCode;
    }

    public void setOperationCode(String operationCode) {
        this.operationCode = operationCode;
    }

    public Operation(){

    }

    public Operation(String operationName, String operationCode) {
        this.operationName = operationName;
        this.operationCode = operationCode;
    }

    @Override
    public String toString() {
        return "Operation{" +
                "id=" + id +
                ", operationName='" + operationName + '\'' +
                ", operationCode='" + operationCode + '\'' +
                '}';
    }
}
