package com.jc.api.entity.dto;

/**
 * @author：XudongHu
 * @className:OutPlanCancelDTO
 * @description:
 * 出库计划取消DTO
 * 1.发送出库计划==>2.仓库接单==>3.仓库出库开始==>(4.每出一袋上报一次)==>5.出库终了==>6.返回出库总信息
 * 在发送出库计划到仓库出库开始之间,可以发送出库计划取消指令,返回是否取消的消息
 * @date:10:02 2019/3/18
 * @Modifer:
 */
@Deprecated
public class OutPlanCancelDTO {
    //出库单号
    private String PLAN_CODE;
    //出库创建人
    private String PLAN_OPERATOR;//

    public String getPLAN_CODE() {
        return PLAN_CODE;
    }

    public OutPlanCancelDTO setPLAN_CODE(String PLAN_CODE) {
        this.PLAN_CODE = PLAN_CODE;
        return this;
    }

    public String getPLAN_OPERATOR() {
        return PLAN_OPERATOR;
    }

    public OutPlanCancelDTO setPLAN_OPERATOR(String PLAN_OPERATOR) {
        this.PLAN_OPERATOR = PLAN_OPERATOR;
        return this;
    }
}
