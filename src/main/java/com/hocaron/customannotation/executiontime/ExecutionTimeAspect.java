package com.hocaron.customannotation.executiontime;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class ExecutionTimeAspect {

    @Around("execution(@com.hocaron.customannotation.executiontime.ExecutionTime * *(..))")
    public Object measureExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long endTime = System.currentTimeMillis();
        long executionTime = (endTime - startTime) / 1_000; // 변환된 단위: 초

        log.info("[{}] {} 초", joinPoint.getSignature().getName(), executionTime);
        return result;
    }
}
