package com.jinchi.common.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author：XudongHu
 * @className:ProcedureTestItemRecord
 * @description:
 * @date:21:23 2018/12/11
 */
@ApiModel(description = "制程检测项目记录实体")
public class ProcedureTestItemRecord {

    @ApiModelProperty("主键")
    private Integer id;   //NN

    //TestItem外键
    @ApiModelProperty("检测项目")
    private Integer testItemId; //NN

    //ProcedureTestItemRecord外键
    @ApiModelProperty("制程检测id")
    private Integer procedureTestRecordId; //NN

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTestItemId() {
        return testItemId;
    }

    public void setTestItemId(Integer testItemId) {
        this.testItemId = testItemId;
    }

    public Integer getProcedureTestRecordId() {
        return procedureTestRecordId;
    }

    public void setProcedureTestRecordId(Integer procedureTestRecordId) {
        this.procedureTestRecordId = procedureTestRecordId;
    }

    @Override
    public String toString() {
        return "ProcedureTestItemRecord{" +
                "id=" + id +
                ", testItemId=" + testItemId +
                ", procedureTestRecordId=" + procedureTestRecordId +
                '}';
    }
}
