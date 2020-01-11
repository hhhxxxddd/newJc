package com.jinchi.common.web.exception;


import com.jinchi.common.core.exception.BaseException;
import com.jinchi.common.core.exception.SystemErrorEnum;
import com.jinchi.common.core.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartException;

/**
 * @className DefaultGlobalExceptionHandlerAdvice
 * @author XudongHu
 * @apiNote 定义全局异常
 * @since 2019/10/29日21:12
 * @modifer
 */
@Slf4j
public class DefaultGlobalExceptionHandlerAdvice {
    @ExceptionHandler(value = {MissingServletRequestParameterException.class})
    public Result missingServletRequestParameterException(MissingServletRequestParameterException ex) {
        log.error("全局捕获->缺少必要参数:{}", ex.getMessage());
        return Result.fail(SystemErrorEnum.ARGUMENT_NOT_VALID);
    }

    @ExceptionHandler(value = {MultipartException.class})
    public Result uploadFileLimitException(MultipartException ex) {
        log.error("全局捕获->上传超过限制:{}", ex.getMessage());
        return Result.fail(SystemErrorEnum.UPLOAD_FILE_SIZE_LIMIT);
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public Result serviceException(MethodArgumentNotValidException ex) {
        log.error("全局捕获->请求参数校验不通过:{}", ex.getMessage());
        return Result.fail(SystemErrorEnum.ARGUMENT_NOT_VALID, ex.getBindingResult().getFieldError().getDefaultMessage());
    }

    @ExceptionHandler(value = {DuplicateKeyException.class})
    public Result duplicateKeyException(DuplicateKeyException ex) {
        log.error("全局捕获->唯一键冲突:{}", ex.getMessage());
        return Result.fail(SystemErrorEnum.DUPLICATE_PRIMARY_KEY);
    }

    @ExceptionHandler(value = {BaseException.class})
    public Result baseException(BaseException ex) {
        log.error("全局捕获->基本异常:{}", ex.getMessage());
        return Result.fail(ex.getErrorType());
    }

    // 未定义的异常都默认系统异常
    @ExceptionHandler(value = {Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result exception() {
        return Result.fail();
    }

    // 发生错误直接返回系统异常
    @ExceptionHandler(value = {Throwable.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result throwable() {
        return Result.fail();
    }
}