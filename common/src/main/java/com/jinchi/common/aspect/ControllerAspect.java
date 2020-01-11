package com.jinchi.common.aspect;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ControllerAspect {

    private static final Logger logger = LoggerFactory.getLogger(ControllerAspect.class);

    @Pointcut("execution(public * com.jinchi.common.controller.*.*(..))")
    public void controllerInterceptor(){

    }

    @Before("controllerInterceptor()")
    public void printStartInfo(JoinPoint joinPoint){
        logger.info(joinPoint.getSignature().toLongString() + "===================STRARTED");
    }

    @After("controllerInterceptor()")
    public void printEndInfo(JoinPoint joinPoint){
        logger.info(joinPoint.getSignature().toLongString() + "===================FINISHED");
    }
}
