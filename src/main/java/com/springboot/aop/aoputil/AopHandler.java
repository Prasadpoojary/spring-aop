package com.springboot.aop.aoputil;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;


@Component
@Aspect
public class AopHandler
{


    /* @Around with AOP target actions
     *  We have to provide classname path or with the escape values
     * */
    @Around("execution(* com.springboot.aop.democontroller..*(..))")
    public Object logAroundExecution(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("Entry into the "+joinPoint.getKind()+" "+joinPoint.getSignature().getName()+" of "+joinPoint.getSignature().getDeclaringTypeName());
        Object response=joinPoint.proceed();
        System.out.println("Exit from the "+joinPoint.getKind()+" "+joinPoint.getSignature().getName()+" of "+joinPoint.getSignature().getDeclaringTypeName());
        return response;
    }

    /* execution
     *  We have to provide classname path or with the escape values
     * */
//    @Before("execution(* com.springboot.aop.democontroller..*(..))")
//    public void logBeforeExecution(JoinPoint joinPoint)
//    {
//        System.out.println("Entry into the "+joinPoint.getKind()+" "+joinPoint.getSignature().getName()+" of "+joinPoint.getSignature().getDeclaringTypeName());
//    }
//
//    @After("execution(* com.springboot.aop.democontroller..*(..))")
//    public void logAfterExecution(JoinPoint joinPoint)
//    {
//        System.out.println("Exit from the "+joinPoint.getKind()+" "+joinPoint.getSignature().getName()+" of "+joinPoint.getSignature().getDeclaringTypeName());
//    }


    /* within
    *  We have to provide classname path
    * */
    @Before("within(com.springboot.aop.demoservice.UserService)")
    public void logBeforeExecutionWithin(JoinPoint joinPoint)
    {
        System.out.println("Entry into "+joinPoint.getKind()+" "+joinPoint.getSignature().getName()+" of "+joinPoint.getSignature().getDeclaringTypeName());
    }


    @After("within(com.springboot.aop.demoservice.UserService)")
    public void logAfterExecutionWithin(JoinPoint joinPoint)
    {
        System.out.println("Exit from the "+joinPoint.getKind()+" "+joinPoint.getSignature().getName()+" of "+joinPoint.getSignature().getDeclaringTypeName());
    }



    /* @within
     *  We have to provide annotation class path of an annotated 'class'
     * */
    @Before("@within(org.springframework.stereotype.Repository)")
    public void logBeforeExecutionWithinAnnotation(JoinPoint joinPoint)
    {
        System.out.println("Entry into "+joinPoint.getKind()+" "+joinPoint.getSignature().getName()+" of "+joinPoint.getSignature().getDeclaringTypeName());
    }


    @After("@within(org.springframework.stereotype.Repository)")
    public void logAfterExecutionWithinAnnotation(JoinPoint joinPoint)
    {
        System.out.println("Exit from the "+joinPoint.getKind()+" "+joinPoint.getSignature().getName()+" of "+joinPoint.getSignature().getDeclaringTypeName());
    }


    /* @annotation
     *  We have to provide annotation class path of an annotated 'method'
     * */
//    @Before("@annotation(org.springframework.web.bind.annotation.DeleteMapping)")
//    public void logBeforeDeleteRequest(JoinPoint joinPoint)
//    {
//        System.out.println("DELETION ALERT :  "+joinPoint.getKind()+" "+joinPoint.getSignature().getName()+" of "+joinPoint.getSignature().getDeclaringTypeName());
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
        System.out.println("DELETION ALERT :  "+joinPoint.getKind()+" "+joinPoint.getSignature().getName()+" of "+joinPoint.getSignature().getDeclaringTypeName());
    }


}
