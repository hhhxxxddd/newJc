package com.jc.api.entity.dto;

import lombok.Data;

/**
 * 用于火法，湿法出库申请右侧表单
 */
@Data
public class AuditDTO {

    private Long matId;
    private String matName;
    private String metBatch;
    private Long stockId;

}
