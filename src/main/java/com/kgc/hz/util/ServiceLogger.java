package com.kgc.hz.util;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import java.util.Arrays;

/**
 * AOP
 * 增强处理工具类
 */
@Aspect
public class ServiceLogger {

    /*日志打印*/
    private static Logger logger=Logger.getLogger(ServiceLogger.class);
    /**
     * 环绕增强
     * @param joinPoint
     * @return
     */
    @Around("execution(* com.kgc.hz.service..*.*(..))")
    private Object round(ProceedingJoinPoint joinPoint){
        logger.info("r:前置增强调用:" + joinPoint.getTarget() + "的"
                + joinPoint.getSignature().getName() + "方法，方法入参:"
                + Arrays.toString(joinPoint.getArgs()));
        try {
            Object result=joinPoint.proceed();
            logger.info("r:后置增强调用:" + joinPoint.getTarget() + "的"
                    +joinPoint.getSignature().getName() + "方法，方法返回值:"
                    + result);
            return result;
        }catch (Throwable throwable){
            logger.info("r:异常抛出增强调用:" + joinPoint.getTarget() + "的"
                    +joinPoint.getSignature().getName() + "方法，方法抛出的异常:"
                    + throwable);
            throw new RuntimeException();
        }finally {
            logger.info("r:最终增强调用:" + joinPoint.getTarget() + "的"
                    +joinPoint.getSignature().getName() + "方法");
        }
    }


}
