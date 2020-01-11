package com.jinchi.app.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author: LiuTaoYi
 * @create_time: 2019-09-03 15:07
 * @description: 设备点检模块 点检操作员 点检计划 生成点检记录 入参dto
 **/
public class SpotcheckRecordDTO {
    private Long planCode;//所属计划单号
//    private Long deviceCode;//主设备编号
//    private String fixedassetsCode;//固定资产编码
//
//    private String deviceName;//设备名称
//    private Integer deptCode; //所属部门

    private Integer userId;

    public Integer getUserId() {
        return userId;
    }
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Long getPlanCode() {
        return planCode;
    }
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    public void setPlanCode(Long planCode) {
        this.planCode = planCode;
    }


}
