package com.jinchi.common.core.exception;

import lombok.Getter;

/**
 * @className BaseException
 * @author XudongHu
 * @apiNote 基本异常类
 *          继承运行时异常
 *
 *          errorType是我们抽象出来的所有异常的接口
 *          对于系统异常数量较少,我们直接用枚举实现此接口即可
 *          对于业务异常,会不停增加
 *          因此
 *          我们定义ServiceException继承此类去完成更复杂的实现
 *
 * @since 2019/10/29日20:51
 * @modifer
 */
@Getter
public class BaseException extends RuntimeException {
    /**
     * 异常对应的错误类型
     */
    private final ErrorType errorType;

    /**
     * 默认是系统异常
     */
    public BaseException() {
        this.errorType = SystemErrorEnum.SYSTEM_ERROR;
    }

    public BaseException(ErrorType errorType) {
        this.errorType = errorType;
    }

    public BaseException(ErrorType errorType, String message) {
        super(message);
        this.errorType = errorType;
    }

    public BaseException(ErrorType errorType, String message, Throwable cause) {
        super(message, cause);
        this.errorType = errorType;
    }
}
