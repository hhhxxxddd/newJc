package com.jc.api.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 用于流量统计
 */
@Data
@AllArgsConstructor
@Accessors(chain = true)
public class StreamDTO {
    private Float in;
    private Float out;
    private Float store = 0f;
}
