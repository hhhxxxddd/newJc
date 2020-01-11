package com.jc.api.exception.custom;

import com.jc.api.exception.RepoErrorTypeEnum;
import com.jinchi.common.core.exception.BaseException;

/**
 * @author XudongHu
 * @apiNote 状态错误异常
 * @className StatusRefuseException
 * @modifier
 * @since 19.11.7日21:00
 */
public class StatusRefuseException extends BaseException {
    public StatusRefuseException() {
        super(RepoErrorTypeEnum.STATUS_REFUSE_);
    }

    public StatusRefuseException(String message) {
        super(RepoErrorTypeEnum.STATUS_REFUSE_, message);
    }

}
