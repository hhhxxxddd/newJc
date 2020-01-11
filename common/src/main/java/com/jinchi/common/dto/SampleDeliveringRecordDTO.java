package com.jinchi.common.dto;

import com.jinchi.common.domain.DeliveryFactory;
import com.jinchi.common.domain.RepoBaseSerialNumber;
import com.jinchi.common.domain.SampleDeliveringRecord;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;


/**
 * Created by Administrator on 2018/11/20.
 * modified:huxudong
 */
public class SampleDeliveringRecordDTO implements Serializable {

    //外键
    @ApiModelProperty("送检工厂")
    private DeliveryFactory deliveryFactory;

    //外键
    @ApiModelProperty("送检人")
    private AuthUserDTO deliverer;

    //外键
    @ApiModelProperty("物料基础编号")
    private String serialNumberName;

    //外键
    @ApiModelProperty("检测项目")
    private List<Integer> testItemIds;

    //VO显示检测项目
    @ApiModelProperty("检测项目字符串")
    private String testItemString;

    @ApiModelProperty("详细数据")
    @NotNull(message = "样品检测详细数据不能为空")
    private SampleDeliveringRecord sampleDeliveringRecord;

    @ApiModelProperty("材料实体")
    private RepoBaseSerialNumber repoBaseSerialNumber;

    private String batch;

    private Integer standradId;

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public DeliveryFactory getDeliveryFactory() {
        return deliveryFactory;
    }

    public SampleDeliveringRecordDTO setDeliveryFactory(DeliveryFactory deliveryFactory) {
        this.deliveryFactory = deliveryFactory;
        return this;
    }

    public AuthUserDTO getDeliverer() {
        return deliverer;
    }

    public SampleDeliveringRecordDTO setDeliverer(AuthUserDTO deliverer) {
        this.deliverer = deliverer;
        return this;
    }

    public String getSerialNumberName() {
        return serialNumberName;
    }

    public SampleDeliveringRecordDTO setSerialNumberName(String serialNumberName) {
        this.serialNumberName = serialNumberName;
        return this;
    }

    public List<Integer> getTestItemIds() {
        return testItemIds;
    }

    public SampleDeliveringRecordDTO setTestItemIds(List<Integer> testItemIds) {
        this.testItemIds = testItemIds;
        return this;
    }

    public String getTestItemString() {
        return testItemString;
    }

    public SampleDeliveringRecordDTO setTestItemString(String testItemString) {
        this.testItemString = testItemString;
        return this;
    }

    public SampleDeliveringRecord getSampleDeliveringRecord() {
        return sampleDeliveringRecord;
    }

    public SampleDeliveringRecordDTO setSampleDeliveringRecord(SampleDeliveringRecord sampleDeliveringRecord) {
        this.sampleDeliveringRecord = sampleDeliveringRecord;
        return this;
    }

    public RepoBaseSerialNumber getRepoBaseSerialNumber() {
        return repoBaseSerialNumber;
    }

    public SampleDeliveringRecordDTO setRepoBaseSerialNumber(RepoBaseSerialNumber repoBaseSerialNumber) {
        this.repoBaseSerialNumber = repoBaseSerialNumber;
        return this;
    }

    public Integer getStandradId() {
        return standradId;
    }

    public void setStandradId(Integer standradId) {
        this.standradId = standradId;
    }
}
