package com.jinchi.common.model;

/**
 * @author：XudongHu
 * @className:TaskMessage 服务器发送消息的格式
 * @description:
 * @date:15:03 2019/2/27
 * @Modifer:
 */
public class TaskMessage {
    private Integer batchNumberId;
    private Integer userId;
    private Integer dataType;

    public Integer getBatchNumberId() {
        return batchNumberId;
    }

    public TaskMessage setBatchNumberId(Integer batchNumberId) {
        this.batchNumberId = batchNumberId;
        return this;
    }

    public Integer getUserId() {
        return userId;
    }

    public TaskMessage setUserId(Integer userId) {
        this.userId = userId;
        return this;
    }

    public Integer getDataType() {
        return dataType;
    }

    public TaskMessage setDataType(Integer dataType) {
        this.dataType = dataType;
        return this;
    }
}
