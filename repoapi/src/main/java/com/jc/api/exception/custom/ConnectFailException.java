package com.jc.api.exception.custom;

import com.jc.api.exception.RepoErrorTypeEnum;
import com.jinchi.common.core.exception.BaseException;

/**
 * @author XudongHu
 * @apiNote 服务间调用失败异常
 * @className ConnectFailException
 * @modifier
 * @since 19.11.7日17:27
 */
public class ConnectFailException extends BaseException {
    public ConnectFailException() {
        super(RepoErrorTypeEnum.CONNECT_FAIL_);
    }

    public ConnectFailException(String message) {
        super(RepoErrorTypeEnum.CONNECT_FAIL_, message);
    }
}
