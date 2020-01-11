package com.jinchi.common.core.exception;
/**
 * @className ErrorType
 * @author XudongHu
 * @apiNote 异常类型抽象类
 * @since 2019/10/29日18:48
 * @modifer
 */
public interface ErrorType {
    /**
     * 返回code
     *
     * @return
     */
    String getCode();

    /**
     * 返回mesg
     *
     * @return
     */
    String getMesg();
}
