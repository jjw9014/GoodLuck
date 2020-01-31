package com.help.server.common;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class ExceptionHander {

    @Pointcut("execution(com.help.api.ResultDTO com.help.server.facade..*(..))")
    public void hander() {

    }

    @Around("hander()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        Object result;
        try {
            result = pjp.proceed();
            return result;
        } catch (ResultException e) {
            log.info("{}:{}", e.getCode(), e.getMessage());
            return ResultHandler.handleFailure(e);
        } catch (Throwable t) {
            //如果没有统一日志拦截，打印堆栈信息
            log.error("an uncaught exception occur, e:", t);
            return ResultHandler.handleException(t);
        }
    }
}
