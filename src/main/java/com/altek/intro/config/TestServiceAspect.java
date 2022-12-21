package com.altek.intro.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
@Slf4j
public class TestServiceAspect {

    @Before("execution(* com.altek.intro.controller..*(..))")
    public void before(JoinPoint joinPoint) {
        log.info(" before called " + joinPoint.getSignature().getName());
    }

    @After("execution(* com.altek.intro.controller..*(..))")
    public void after(JoinPoint joinPoint) {
        log.info(" after called " + joinPoint.getSignature().getName());
    }

    @AfterReturning("execution(* com.altek.intro.controller..*(..))")
    public void afterReturning(JoinPoint joinPoint) {
        log.info("call " + joinPoint.getSignature().getName() + " success!");
    }

    @AfterThrowing("execution(* com.altek.intro.controller..*(..))")
    public void afterThrowing(JoinPoint joinPoint) {
        log.info("call " + joinPoint.getSignature().getName() + " has exception!");
    }
}