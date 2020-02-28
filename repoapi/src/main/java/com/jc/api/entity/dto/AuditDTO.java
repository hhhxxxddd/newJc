package com.jc.api.entity.dto;

import lombok.Data;

/**
 * 用于火法，湿法出库申请右侧表单
 */
@Data
public class AuditDTO {

    private Integer matId;
    private String matName;
    private String metBatch;
    private Long ledgersId;
    private Float weight;
    private String group;

}
