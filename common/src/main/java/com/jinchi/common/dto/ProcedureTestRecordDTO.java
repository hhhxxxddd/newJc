package com.jinchi.common.dto;

import com.jinchi.common.domain.DeliveryFactory;
import com.jinchi.common.domain.ProcedureTestRecord;
import com.jinchi.common.domain.ProductionProcess;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;


/**
 * @author：XudongHu
 * @className:ProcedureTestRecordDetailsDTO
 * @description: 制程检测 详情
 * @date:11:38 2018/11/23
 */
@ApiModel(description = "制程检测详情DTO")
public class ProcedureTestRecordDTO {

    //外键
    @ApiModelProperty("送检工厂")
    private DeliveryFactory deliveryFactory;

    //外键
    @ApiModelProperty("工序")
    private ProductionProcess productionProcess;

    //根据检测项目生成的内容
    @ApiModelProperty("检测项目内容")
    private String testItemString;

    //外键
    @ApiModelProperty("受检物料名称")
    private String testMaterialName;

    //外键 角色
    @ApiModelProperty("取样人")
    private String sampler;

    //外键 角色
    @ApiModelProperty("检测人")
    private String tester;

    //外键
    @ApiModelProperty("检测项目")
    private List<Integer> testItemIds;

    //本体
    @ApiModelProperty("详情")
    private ProcedureTestRecord procedureTestRecord;


    public String getTestMaterialName() {
        return testMaterialName;
    }

    public ProcedureTestRecordDTO setTestMaterialName(String testMaterialName) {
        this.testMaterialName = testMaterialName;
        return this;
    }

    public ProductionProcess getProductionProcess() {
        return productionProcess;
    }

    public ProcedureTestRecordDTO setProductionProcess(ProductionProcess productionProcess) {
        this.productionProcess = productionProcess;
        return this;
    }

    public List<Integer> getTestItemIds() {
        return testItemIds;
    }

    public ProcedureTestRecordDTO setTestItemIds(List<Integer> testItemIds) {
        this.testItemIds = testItemIds;
        return this;
    }

    public String getSampler() {
        return sampler;
    }

    public ProcedureTestRecordDTO setSampler(String sampler) {
        this.sampler = sampler;
        return this;
    }

    public String getTester() {
        return tester;
    }

    public ProcedureTestRecordDTO setTester(String tester) {
        this.tester = tester;
        return this;
    }

    public ProcedureTestRecord getProcedureTestRecord() {
        return procedureTestRecord;
    }

    public ProcedureTestRecordDTO setProcedureTestRecord(ProcedureTestRecord procedureTestRecord) {
        this.procedureTestRecord = procedureTestRecord;
        return this;
    }

    public String getTestItemString() {
        return testItemString;
    }

    public ProcedureTestRecordDTO setTestItemString(String testItemString) {
        this.testItemString = testItemString;
        return this;
    }

    public DeliveryFactory getDeliveryFactory() {
        return deliveryFactory;
    }

    public ProcedureTestRecordDTO setDeliveryFactory(DeliveryFactory deliveryFactory) {
        this.deliveryFactory = deliveryFactory;
        return this;
    }

    @Override
    public String toString() {
        return "ProcedureTestRecordDTO{" +
                "deliveryFactory=" + deliveryFactory +
                ", productionProcess=" + productionProcess +
                ", testItemString='" + testItemString + '\'' +
                ", testMaterialName='" + testMaterialName + '\'' +
                ", sampler='" + sampler + '\'' +
                ", tester='" + tester + '\'' +
                ", testItemIds=" + testItemIds +
                ", procedureTestRecord=" + procedureTestRecord +
                '}';
    }
}
