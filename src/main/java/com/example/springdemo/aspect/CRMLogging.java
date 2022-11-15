package com.example.springdemo.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class CRMLogging {
    // setup pointcut declaration
    // controller
    @Pointcut("execution(* com.example.springdemo..controller.*.*(..))")
    private void forControllerPackage(){}

    // service
    @Pointcut("execution(* com.example.springdemo..service.*.*(..))")
    private void forServicePackage(){}

    // dao
    @Pointcut("execution(* com.example.springdemo..dao.*.*(..))")
    private void forDaoPackage(){}

    // for whole app
    @Pointcut("forControllerPackage() || forServicePackage() || forDaoPackage()")
    private void forAppFlow(){}

    // add @Before advice
    @Before("forAppFlow()")
    public void before(JoinPoint joinPoint){

        // print out which method we are advising on
        String method = joinPoint.getSignature().toShortString();
        log.info("\n=====>>> In @Before: calling method: " + method + "\n");

        // get and display args
        Object[] args = joinPoint.getArgs();

        for (Object tempArg : args){
            log.info("\n=====>>> Argument: " + tempArg);
        }
    }

    // add @AfterReturning advice
    @AfterReturning(
            pointcut = "forAppFlow()",
            returning = "result"
    )
    public void afterReturning(JoinPoint joinPoint, Object result){

        // print out which method we are advising on
        String method = joinPoint.getSignature().toShortString();
        log.info("\n=====>>> In @AfterReturning: calling method: " + method + "\n");

        // display the data returned
        log.info("\n=====>>> Result : " + result + "\n");

    }
}
