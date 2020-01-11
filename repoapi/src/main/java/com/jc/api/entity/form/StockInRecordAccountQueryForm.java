package com.jc.api.entity.form;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jc.api.entity.param.StockInRecordAccountQueryParam;
import com.jinchi.common.web.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author XudongHu
 * @apiNote 物料台账查询表单
 * @className StockInRecordAccountQueryForm
 * @modifier
 * @since 19.12.10日4:41
 */
@ApiModel
@Data
public class StockInRecordAccountQueryForm extends BaseQueryForm<StockInRecordAccountQueryParam> {
    @ApiModelProperty(value = "物料信息id",example = "1")
    private Long materialInfoId;
    @ApiModelProperty(value = "物料编码",example = "MC/BN180808-WS001-RAW(TS)-Fe-1-QDBX-60KG")
    private String materialCode; //NN 物料编码 冗余
    @ApiModelProperty(value = "物料批号",example = "MC/BN180808")
    private String materialBatch;//NN 物料批号 冗余
    @ApiModelProperty(value = "袋号",example = "1")
    private String bagNo; //NN 物料袋号 冗余
    @ApiModelProperty(value = "物料子类型id",example = "1")
    private Integer materialLeafTypeId; //Fk 物料子类型id 冗余
    @ApiModelProperty(value = "物料父类型id",example = "1")
    private Integer materialRootTypeId; //Fk 物料根类型id 冗余
    @ApiModelProperty(value = "物料供应车间id",example = "1")
    private Integer materialWorkshopId; //Fk 物料供应车间id 冗余
    @ApiModelProperty(value = "物料供应商id",example = "1")
    private Integer materialSupplierId; //Fk 物料供应商id 冗余
    @ApiModelProperty(value = "物料名称代号",example = "Fe")
    private String materialNameCode;// NN 物料名称代号 冗余
    @ApiModelProperty(value = "最小重量",example = "0.0")
    private Double minWeight; //NN 此袋重量 冗余 保留两位小数
    @ApiModelProperty(value = "最大重量",example = "10000.0")
    private Double maxWeight;  //
    @ApiModelProperty(value = "物料状态",example = "1")
    private Integer materialStatus; //NN 物料状态 0已出库 1在库中 2待出库 默认1
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @ApiModelProperty(value = "查询开始时间",example = "2019-10-10 00:00:00")
    private Date createdTimeStart;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @ApiModelProperty(value = "查询结束时间",example = "2019-12-10 00:00:00")
    private Date createdTimeEnd;
    @ApiModelProperty(value = "入库人",example = "胡旭东")
    private String createdPerson;
}
