package com.jc.api.exception.custom;

import com.jc.api.exception.RepoErrorTypeEnum;
import com.jinchi.common.core.exception.BaseException;

/**
 * @author XudongHu
 * @apiNote 参数校验异常
 * @className ParamVerifyException
 * @modifier
 * @since 20.1.11日13:25
 */
public class ParamVerifyException  extends BaseException {
    public ParamVerifyException() {
        super(RepoErrorTypeEnum.PARAM_ERROR);
    }

    public ParamVerifyException(String message) {
        super(RepoErrorTypeEnum.PARAM_ERROR, message);
    }
}
