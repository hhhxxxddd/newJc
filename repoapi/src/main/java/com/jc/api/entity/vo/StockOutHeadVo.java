package com.jc.api.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jc.api.entity.po.StockOutRecordHead;
import com.jinchi.common.web.entity.vo.BaseVo;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author XudongHu
 * @apiNote 出库页面表头VO
 * @className StockOutHeadVo
 * @modifier
 * @since 19.11.6日21:06
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(description = "出库页面表头vo")
public class StockOutHeadVo extends BaseVo<StockOutRecordHead> {
    private String id;
    private String stockOutRecordHeadCode;
    private String endPosition;
    private String productionLine;
    private String createdPerson;
    private String bagsSum;
    private Double weightSum;
    private Integer outUsage;
    private Integer outMaterialType;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date completionTime;
    private Integer completionFlag;
    private Map<String, List<StockOutRecordVo>> records;

    public StockOutHeadVo(StockOutRecordHead stockOutRecordHead) {
        BeanUtils.copyProperties(stockOutRecordHead, this);
    }
}
