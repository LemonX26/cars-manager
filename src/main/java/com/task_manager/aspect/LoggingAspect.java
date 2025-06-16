package com.task_manager.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect //guard which says what happens after execution of method
/*
 Aspekt w Javie to modułowy fragment kodu, który definiuje dodatkową funkcjonalność,
  np. logowanie czy obsługę błędów, i jest stosowany do różnych części aplikacji bez modyfikacji ich kodu źródłowego,
   najczęściej w ramach programowania aspektowego (AOP)
 */
@Component // to add this class to the Spring context (lifecycle)
public class LoggingAspect
{
    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class); // Logger instance for logging
    @Before("execution(* com.task_manager.controller.*.*(..))") // pointcut expression
    public void logBefore(JoinPoint joinPoint) // JoinPoint is a specific point during the execution of a program, such as a method call or field assignment
    {
        logger.info("Before: {}", joinPoint.getSignature().getName());
    }

    @AfterReturning(pointcut = "execution(* com.task_manager.controller.*.*(..))", returning = "result") // pointcut expression with returning value
    public void logAfterReturning(JoinPoint joinPoint, Object result) // Log after method execution, object result is returned value
    {
        logger.info("After: {} returned with value: {}", joinPoint.getSignature().getName(), result);
    }

}
