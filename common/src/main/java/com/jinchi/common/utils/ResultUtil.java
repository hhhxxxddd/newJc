package com.jinchi.common.utils;


import com.jinchi.common.dto.Result;
import com.jinchi.common.exceptions.EnumExceptions;
import com.jinchi.common.exceptions.JcExceptions;

/**
 * 统一返回工具类
 *
 * @Author：huxudong
 */
public class ResultUtil {
    /**
     * 成功
     *
     * @param data
     * @return
     */
    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<T>();
        result.setCode(EnumExceptions.SUCCESS.getCode());
        result.setMessage(EnumExceptions.SUCCESS.getMessage());
        result.setData(data);
        return result;
    }

    /**
     * 成功
     *
     * @return
     */
    public static <T> Result<T> success() {
        return success(null);
    }

    public static <T> Result<T> success(int i){
        Result<T> result = new Result<T>();
        result.setCode(i);
        result.setMessage(EnumExceptions.SUCCESS.getMessage());
        return result;
    }

    /**
     * 异常
     *
     * @param securityException
     * @return
     */
    public static <T> Result<T> error(JcExceptions securityException) {
        Result<T> result = new Result<T>();
        result.setCode(securityException.getCode());
        result.setMessage(securityException.getMessage());
        return result;
    }

    /**
     * 异常
     *
     * @param message
     * @return
     */
    public static <T> Result<T> error(String message) {
        Result<T> result = new Result<T>();
        result.setCode(-2);
        result.setMessage(message);
        return result;
    }
}
