package com.jc.api.exception;


import com.jinchi.common.core.exception.ErrorType;
import lombok.Getter;

@Getter
public enum RepoErrorTypeEnum implements ErrorType {

    DATA_DUPLICATE_("030100", "数据重复"),
    DATA_NOT_FIND_("000404","需要的数据未找到"),
    DATA_ASSOCIATION_("000555", "存在关联数据,操作被拒绝"),
    CONNECT_FAIL_("111111", "连接失败,请求服务未响应"),
    STATUS_REFUSE_("000333", "操作失败,数据状态不允许此种操作"),
    PARAM_ERROR("333000","操作失败,存在非法参数");
    /**
     * 错误类型码
     */
    private String code;
    /**
     * 错误类型描述信息
     */
    private String mesg;

    RepoErrorTypeEnum(String code, String mesg) {
        this.code = code;
        this.mesg = mesg;
    }
}
