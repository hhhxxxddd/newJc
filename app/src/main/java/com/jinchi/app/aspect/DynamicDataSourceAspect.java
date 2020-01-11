package com.jinchi.app.aspect;


import com.jinchi.app.dynamicds.DynamicDataSourceContextHolder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 动态的数据源的切换一般用来实现数据库的读写分离的。
 * <p>
 * 我这里使用其根据不同的业务调用不同的数据库。
 */
@Aspect
@Component
public class DynamicDataSourceAspect {

    private static final Logger logger = LoggerFactory.getLogger(DynamicDataSourceAspect.class);

    @Pointcut("execution(public * com.jinchi.app.mapper.*.*(..))")
    public void daoAspect() {
    }

    private static final String AUTH_SOURCE = "AuthMapper";

    /**
     * 在dao方法执行之前拦截
     */
    @Before("daoAspect()")
    public void beforeUserSwitchDataSource(JoinPoint joinPoint) {
        System.out.println(joinPoint.getSignature());
        boolean isFromDogMapper = isFrom(AUTH_SOURCE, joinPoint.getSignature().toString());
        if (isFromDogMapper) {
            DynamicDataSourceContextHolder.useSlaveDataSource();
            logger.info(">>>>>>Switch DataSource to [{}] in method [{}]",
                    DynamicDataSourceContextHolder.getDataSourceKey(),
                    joinPoint.getSignature().getName()
            );
        }
    }

    /**
     * 在dao方法执行之后拦截
     */

    @After("daoAspect()")
    public void restoreDataSource(JoinPoint joinPoint) {
        DynamicDataSourceContextHolder.clearDataSourceKey();
        logger.info(">>>>>>Restore DataSource to [{}] in method [{}]",
                DynamicDataSourceContextHolder.getDataSourceKey(),
                joinPoint.getSignature().getName()
        );
    }

    private boolean isFrom(String authMapper, String name) {
        return name.contains(authMapper);
    }
}