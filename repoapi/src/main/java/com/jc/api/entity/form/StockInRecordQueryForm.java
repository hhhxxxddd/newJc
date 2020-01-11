package com.jc.api.entity.form;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jc.api.entity.param.StockInRecordQueryParam;
import com.jinchi.common.web.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Past;
import java.util.Date;

/**
 * @author XudongHu
 * @apiNote 入库查询表单 -前端
 * @className StockInRecordQueryForm
 * @modifier
 * @since 2019/11/1日13:46
 */
@ApiModel
@Data
public class StockInRecordQueryForm extends BaseQueryForm<StockInRecordQueryParam> {
    @ApiModelProperty(value = "物料编码",example = "MC/BN180808-WS001-RAW(TS)-Fe-1-QDBX-60KG")
    private String materialCode;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @Past(message = "查询开始时间必须小于当前日期")
    @ApiModelProperty(value = "查询开始时间",example = "2019-10-10 00:00:00")
    private Date createdTimeStart;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @ApiModelProperty(value = "查询结束时间",example = "2019-12-10 00:00:00")
    private Date createdTimeEnd;

    @ApiModelProperty(value = "入库人",example = "胡旭东")
    private String createdPerson;
}
