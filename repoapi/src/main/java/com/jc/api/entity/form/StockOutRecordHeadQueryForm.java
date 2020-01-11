package com.jc.api.entity.form;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jc.api.entity.param.StockOutRecordHeadQueryParam;
import com.jinchi.common.web.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author XudongHu
 * @apiNote 出库表头查询表单
 * @className StockOutHeadQueryForm
 * @modifier
 * @since 19.11.6日21:37
 */
@ApiModel
@Data
public class StockOutRecordHeadQueryForm extends BaseQueryForm<StockOutRecordHeadQueryParam> {
    @ApiModelProperty(value = "出库单号", example = "就是批号")
    private String stockOutRecordHeadCode;
    @ApiModelProperty(value = "创建人id", example = "1")
    private Integer createdPersonId; //创建人id FK
    @ApiModelProperty(value = "出库点id", example = "1")
    private Integer endPositionId; //出库点id FK
    @ApiModelProperty(value = "产线id", example = "1")
    private Integer productionLineId;//产线id FK
    @ApiModelProperty(value = "出库一级类型id", example = "1")
    private Integer outMaterialType;// 出库一级类型
    @ApiModelProperty(value = "出库用途 1前驱体,2正极", example = "1")
    private Integer outUsage;
    @ApiModelProperty(value = "是否完成", example = "1")
    private Integer completionFlag;
    @ApiModelProperty(value = "起始日期", example = "2019-10-10 00:00:00")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createdTimeStart;
    @ApiModelProperty(value = "终止日期", example = "2019-12-10 00:00:00")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createdTimeEnd;
}
