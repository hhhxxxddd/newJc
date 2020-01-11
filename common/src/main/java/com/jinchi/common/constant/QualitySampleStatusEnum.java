package com.jinchi.common.constant;

/**
 * @author：XudongHu
 * @className:QualitySampleStatusEnum
 * @description:
 * @date:23:02 2018/12/15
 */
public enum QualitySampleStatusEnum {

    /**
     * SampleDeliveringRecord 中的 acceptStatus
     */

    SAMPLE_SAVE("保存", -1),
    SAMPLE_WAIT("等待接收", 1),
    SAMPLE_ACCEPTED("接收", 2),
    SAMPLE_DENY("拒绝", 3);

    private String acceptStatus;

    private Integer acceptCode;

    QualitySampleStatusEnum(String acceptStatus, Integer acceptCode) {
        this.acceptStatus = acceptStatus;
        this.acceptCode = acceptCode;
    }

    public String getAcceptStatus() {
        return acceptStatus;
    }

    public void setAcceptStatus(String acceptStatus) {
        this.acceptStatus = acceptStatus;
    }

    public Integer get() {
        return acceptCode;
    }

    public void setCode(Integer acceptCode) {
        this.acceptCode = acceptCode;
    }
}
