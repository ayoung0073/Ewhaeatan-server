package com.mayko.ewhaplate.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class LoggingAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass()); // logger.debug 안 찍힘

    // controller 패키지에서 *Controller 클래스에 있는 메서드 중 파라미터 0개인 메서드만
    // service 패키지에서 *Service 클래스에 있는 메서드 중 파라미터 0개인 메서드만
    @Around("execution(* com.mayko.ewhaplate..api.*Controller.*(..))")
    public Object printLog(ProceedingJoinPoint joinPoint) throws Throwable {

        String type = "";
        String name = joinPoint.getSignature().getDeclaringTypeName();

        if (name.contains("Controller")) {
            type = "Controller - '";
        }

        // Controller - 'com.mayko.ewhaplate.api.FoodController.getList'
        // logger.info(type + name + "." + joinPoint.getSignature().getName() + "'");

        logger.info(joinPoint.getSignature().getName() + " -> " + Arrays.toString(joinPoint.getArgs()));
        // getName - 메서드 이름
        return joinPoint.proceed();
    }
}