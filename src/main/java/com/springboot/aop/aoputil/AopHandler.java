package com.springboot.aop.aoputil;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Component
@Aspect
public class AopHandler
{

    Logger logger= LoggerFactory.getLogger(AopHandler.class);
    /* @Around with AOP target actions
     *  We have to provide classname path or with the escape values
     * */
    @Around("execution(* com.springboot.aop.democontroller..*(..))")
    public Object logAroundExecution(ProceedingJoinPoint joinPoint) throws Throwable {
        logger.info("Entry into the {} {} of {}",joinPoint.getKind(),joinPoint.getSignature().getName(),joinPoint.getSignature().getDeclaringTypeName());
        Object response=joinPoint.proceed();
        logger.info("Exit from the {} {} of {}",joinPoint.getKind(),joinPoint.getSignature().getName(),joinPoint.getSignature().getDeclaringTypeName());
        return response;
    }

    /* execution
     *  We have to provide classname path or with the escape values
     * */
//    @Before("execution(* com.springboot.aop.democontroller..*(..))")
//    public void logBeforeExecution(JoinPoint joinPoint)
//    {
//         logger.info("Entry into the {} {} of {}",joinPoint.getKind(),joinPoint.getSignature().getName(),joinPoint.getSignature().getDeclaringTypeName());
//    }
//
//    @After("execution(* com.springboot.aop.democontroller..*(..))")
//    public void logAfterExecution(JoinPoint joinPoint)
//    {
//         logger.info("Exit from the {} {} of {}",joinPoint.getKind(),joinPoint.getSignature().getName(),joinPoint.getSignature().getDeclaringTypeName());
//    }


    /* within
    *  We have to provide classname path
    * */
    @Before("within(com.springboot.aop.demoservice.UserService)")
    public void logBeforeExecutionWithin(JoinPoint joinPoint)
    {
        logger.info("Entry into the {} {} of {}", joinPoint.getKind(), joinPoint.getSignature().getName(), joinPoint.getSignature().getDeclaringTypeName());
    }

    @After("within(com.springboot.aop.demoservice.UserService)")
    public void logAfterExecutionWithin(JoinPoint joinPoint)
    {
        logger.info("Exit from the {} {} of {}",joinPoint.getKind(),joinPoint.getSignature().getName(),joinPoint.getSignature().getDeclaringTypeName());
    }



    /* @within
     *  We have to provide annotation class path of an annotated 'class'
     * */
    @Before("@within(org.springframework.stereotype.Repository)")
    public void logBeforeExecutionWithinAnnotation(JoinPoint joinPoint)
    {
        logger.info("Entry into the {} {} of {}", joinPoint.getKind(), joinPoint.getSignature().getName(), joinPoint.getSignature().getDeclaringTypeName());
    }


    @After("@within(org.springframework.stereotype.Repository)")
    public void logAfterExecutionWithinAnnotation(JoinPoint joinPoint)
    {
        logger.info("Exit from the {} {} of {}",joinPoint.getKind(),joinPoint.getSignature().getName(),joinPoint.getSignature().getDeclaringTypeName());
    }


    /* @annotation
     *  We have to provide annotation class path of an annotated 'method'
     * */
//    @Before("@annotation(org.springframework.web.bind.annotation.DeleteMapping)")
//    public void logBeforeDeleteRequest(JoinPoint joinPoint)
//    {
//                logger.warn("DELETION ALERT : {}  {}  of {} ",joinPoint.getKind(),joinPoint.getSignature().getName(),joinPoint.getSignature().getDeclaringTypeName());
//    }



    /* @Pointcut
     *  Custom pointcut for target condition with 'AND', 'OR'
     * */
    @Pointcut("within(com.springboot.aop.democontroller.UserController) && @annotation(org.springframework.web.bind.annotation.DeleteMapping)")
    public void deleteMethodUserControllerPointCut()
    {

    }

    /*  Calling above custom pointcut */
    @Before("deleteMethodUserControllerPointCut()")
    public void logBeforeDeleteRequest(JoinPoint joinPoint)
    {
        logger.warn("DELETION ALERT : {}  {}  of {} ",joinPoint.getKind(),joinPoint.getSignature().getName(),joinPoint.getSignature().getDeclaringTypeName());
    }


}
