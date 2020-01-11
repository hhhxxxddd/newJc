package com.jinchi.app.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author: LiuTaoYi
 * @create_time: 2019-08-30 15:38
 * @description:
 **/
public class DeviceMaintenanceRecordHeadUpdateDto {
    private Long headId;
    private int flag; //设置一个标记为 0 代表暂存 1代表提交
    private String note;

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Long getHeadId() {
        return headId;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    public void setHeadId(Long headId) {
        this.headId = headId;
    }


    public int getFlag() {
        return flag;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    public void setFlag(int flag) {
        this.flag = flag;
    }
}
