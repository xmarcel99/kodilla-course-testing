package com.kodilla.patterns2.aop.calculator;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Aspect
@Component
public class Watcher {

    public static Logger LOGGER = LoggerFactory.getLogger(Watcher.class);

    @Before("execution(* com.kodilla.patterns2.aop.calculator.Calculator.factorial(..))" +
    "&& args(theNumber,theNumber2) && target(object)")
    public void logEvent(BigDecimal theNumber,BigDecimal theNumber2, Object object) {
        System.out.println(theNumber +"   " + theNumber2);
        LOGGER.info("Class:" + object.getClass().getName() + " , Args: " + theNumber );
    }

    @Around("execution(* com.kodilla.patterns2.aop.calculator.Calculator.factorial(..))")
    public Object measureTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object result;
        try {
            double start = System.currentTimeMillis();
            result = proceedingJoinPoint.proceed();
            double end = System.currentTimeMillis();
            LOGGER.info("Time consumed: " + (end-start) + " [ms]");
        } catch (Throwable throwable) {
            LOGGER.error(throwable.getMessage());
            throw throwable;
        }
        return result;
    }
}
