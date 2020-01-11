package com.jinchi.app.constant;

/**
 * @author：XudongHu
 * @className:QualityTaskTypeEnum
 * @description:
 * @date:23:04 2018/12/15
 */
public enum QualityTaskTypeEnum {
    /**
     * 对应Task流程管理中的任务类型
     */
    TASK_TEST("检测任务", 1),
    TASK_AUDIT("审核任务", 2),
    TASK_POST("发布任务", 3),
    ;

    private String taskType;

    private Integer taskCode;

    QualityTaskTypeEnum(String taskType, Integer taskCode) {
        this.taskType = taskType;
        this.taskCode = taskCode;
    }

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    public Integer get() {
        return taskCode;
    }

    public void setCode(Integer taskCode) {
        this.taskCode = taskCode;
    }
}
