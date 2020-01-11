package com.jc.api.entity.form;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jc.api.entity.param.StockOutRecordQueryParam;
import com.jinchi.common.web.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author XudongHu
 * @apiNote 出库记录查询表单
 * @className StockOutRecordQueryForm
 * @modifier
 * @since 19.12.10日10:11
 */
@ApiModel
@Data
public class StockOutRecordQueryForm extends BaseQueryForm<StockOutRecordQueryParam> {
    @ApiModelProperty(value = "物料信息id", example = "1")
    private Integer materialInfoId;
    @ApiModelProperty(value = "出库单表头id", example = "1")
    private Integer stockOutRecordHeadId; //出库表头id FK
    @ApiModelProperty(value = "组名", example = "第一组??")
    private String groupName;
    @ApiModelProperty(value = "起始日期", example = "2019-10-10 00:00:00")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date startDateTime;
    @ApiModelProperty(value = "终止日期", example = "2019-10-10 00:00:00")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date endDateTime;
}
