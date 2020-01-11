package com.jc.api.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jc.api.entity.po.MaterialInfoSupplier;
import com.jc.api.entity.po.MaterialInfoWorkshop;
import com.jc.api.entity.po.MaterialType;
import com.jc.api.entity.po.StockInRecordAccount;
import com.jinchi.common.web.entity.vo.BaseVo;
import io.swagger.annotations.ApiModel;
import lombok.*;
import org.springframework.beans.BeanUtils;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author XudongHu
 * @apiNote 入库台账Vo
 * @className StockAccountJointVo
 * @modifier
 * @since 19.12.10日3:47
 */
@ToString(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(description = "物料台账VO")
public class StockAccountJointVo extends BaseVo<StockInRecordAccount> {
    private String id;
    private String materialName; //

    private String materialCode;
    private String materialBatch;
    private String materialNameCode;
    private String bagNo;
    private Double weight;

    private MaterialType rootType;
    private MaterialType leafType;
    private MaterialInfoSupplier supplier;
    private MaterialInfoWorkshop workshop;
    private Integer materialStatus;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createdTime;
    private String createdPerson;

    public StockAccountJointVo(StockInRecordAccount t){
        BeanUtils.copyProperties(t,this);
    }
}
