package com.jc.api.exception;


import com.jc.api.exception.custom.*;
import com.jinchi.common.core.vo.Result;
import com.jinchi.common.web.exception.DefaultGlobalExceptionHandlerAdvice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandlerAdvice extends DefaultGlobalExceptionHandlerAdvice {

    //参数传值异常
    @ExceptionHandler(value = {ParamVerifyException.class})
    public Result paramVerify(ParamVerifyException ex) {
        log.error(ex.getMessage());
        return Result.fail(ex.getErrorType(),ex.getMessage());
    }

    //连接失败异常
    @ExceptionHandler(value = {ConnectFailException.class})
    public Result connectFail(ConnectFailException ex) {
        log.error(ex.getMessage());
        return Result.fail(ex.getErrorType(),ex.getMessage());
    }
    //数据关联异常  (外键约束等)
    @ExceptionHandler(value = {DataAssociationException.class})
    public Result dataAssociation(DataAssociationException ex) {
        log.error(ex.getMessage());
        return Result.fail(ex.getErrorType(),ex.getMessage());
    }
    //数据重复异常  (唯一键约束等)
    @ExceptionHandler(value = {DataDuplicateException.class})
    public Result dataDuplicate(DataDuplicateException ex) {
        log.error(ex.getMessage());
        return Result.fail(ex.getErrorType(),ex.getMessage());
    }
    //数据未找到异常
    @ExceptionHandler(value = {DataNotFindException.class})
    public Result dataNotFind(DataNotFindException ex) {
        log.error(ex.getMessage());
        return Result.fail(ex.getErrorType(),ex.getMessage());
    }
    //请求状态错误异常
    @ExceptionHandler(value = {StatusRefuseException.class})
    public Result statusRefuse(StatusRefuseException ex) {
        log.error(ex.getMessage());
        return Result.fail(ex.getErrorType(),ex.getMessage());
    }
}