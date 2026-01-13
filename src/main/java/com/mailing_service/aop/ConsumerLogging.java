package com.mailing_service.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class ConsumerLogging {

    @Around("@annotation(org.springframework.kafka.annotation.KafkaListener)")
    public Object logKafkaMethods(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("Start method: {}", joinPoint.getSignature());
        try {
            Object result = joinPoint.proceed();
            log.info("Method finished: {}", joinPoint.getSignature());
            return result;
        } catch (Exception e) {
            log.error("Error in method: {}", joinPoint.getSignature(), e);
            throw e;
        }
    }
}
