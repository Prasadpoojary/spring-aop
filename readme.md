# Aspect Oriented Programming : 
Aspect oriented programming in short AOP is a methodology or we can say its a feature of programming paradigm to modularise the cross-cutting concerns. Cross-cutting concerns are nothing but aspects of programming like logging, security, transaction management, alert configurations... etc

## Why AOP  ?
* Instead of writing chunk of code repeatedly in multiple methods we can create separate module to perform the same. It reduces code duplication.
* Increases code readability and maintains proper structure 
* Easy to differentiate business functionalities  and  other unrelated aspects 
* Helps to configure end to end logging to trace the abnormal flow of program


### Dependency 
``` 
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-aop</artifactId>
</dependency>
```




### Enable AspectJ auto-proxy from main method 
```
import org.springframework.context.annotation.EnableAspectJAutoProxy;


@SpringBootApplication
@EnableAspectJAutoProxy
public class AopApplication 
{

	public static void main(String[] args) 
	{
	   SpringApplication.run(AopApplication.class, args);
	}

}
```

### @Around with AOP target actions 
We have to provide class path or class path with  escape values/patterns, so all the method present under given class/pattern will be logged before and after execution

```
@Around("execution(* com.springboot.aop.democontroller..*(..))")
public Object logAroundExecution(ProceedingJoinPoint joinPoint) throws Throwable 
{
    logger.info("Entry into the {} {} of {}",joinPoint.getKind(),joinPoint.getSignature().getName(),joinPoint.getSignature().getDeclaringTypeName());
    Object response=joinPoint.proceed();
    logger.info("Exit from the {} {} of {}",joinPoint.getKind(),joinPoint.getSignature().getName(),joinPoint.getSignature().getDeclaringTypeName());
    return response;
}
```
##### Output : 
``` 
2024-08-19T22:33:21.379+05:30  INFO 17928 --- [aop] [http-nio-8080-exec-1] com.springboot.aop.aoputil.AopHandler    : Entry into the method-execution getAllUsers of com.springboot.aop.democontroller.UserController
2024-08-19T22:33:21.387+05:30  INFO 17928 --- [aop] [http-nio-8080-exec-1] com.springboot.aop.aoputil.AopHandler    : Exit from the method-execution getAllUsers of com.springboot.aop.democontroller.UserController
```


### @Before and @After 
It can be used to perform the specific task before or after execution of target method 
``` 
    @Before("execution(* com.springboot.aop.democontroller..*(..))")
    public void logBeforeExecution(JoinPoint joinPoint)
    {
         logger.info("Entry into the {} {} of {}",joinPoint.getKind(),joinPoint.getSignature().getName(),joinPoint.getSignature().getDeclaringTypeName());
    }


    @After("execution(* com.springboot.aop.democontroller..*(..))")
    public void logAfterExecution(JoinPoint joinPoint)
    {
         logger.info("Exit from the {} {} of {}",joinPoint.getKind(),joinPoint.getSignature().getName(),joinPoint.getSignature().getDeclaringTypeName());
    }
```


### within and @within 
* within - Specific task will be performed on the target methods within given class. 

* @within - Specific task will be performed on the target method  within the class which is annotated with given annotation class. 

``` 
   @Before("within(com.springboot.aop.demoservice.UserService)")
    public void logBeforeExecutionWithin(JoinPoint joinPoint)
    {
        logger.info("Entry into the {} {} of {}", joinPoint.getKind(), joinPoint.getSignature().getName(), joinPoint.getSignature().getDeclaringTypeName());
    }
    

   @Before("@within(org.springframework.stereotype.Repository)")
    public void logBeforeExecutionWithinAnnotation(JoinPoint joinPoint)
    {
        logger.info("Entry into the {} {} of {}", joinPoint.getKind(), joinPoint.getSignature().getName(), joinPoint.getSignature().getDeclaringTypeName());
    }

```


### @Annotation 
Suppose you want to perform some action on method calls which are annotated with specific annotation we can use @Annotation 

``` 
    @Before("@annotation(org.springframework.web.bind.annotation.DeleteMapping)")
    public void logBeforeDeleteRequest(JoinPoint joinPoint)
    {
          logger.warn("DELETION ALERT : {}  {}  of {} ",joinPoint.getKind(),joinPoint.getSignature().getName(),joinPoint.getSignature().getDeclaringTypeName());
    }
```



### @Pointcut
Its used to declare the pointcut expressions. We can create custom pointcuts as per our needs and use it wherever we want

``` 
    // creating custom pointcut 'deleteMethodUserControllerPointCut'
    @Pointcut("within(com.springboot.aop.democontroller.UserController) && @annotation(org.springframework.web.bind.annotation.DeleteMapping)")
    public void deleteMethodUserControllerPointCut()
    {

    }
    
    
    //  Using above custom pointcut
    @Before("deleteMethodUserControllerPointCut()")
    public void logBeforeDeleteRequest(JoinPoint joinPoint)
    {
        logger.warn("DELETION ALERT : {}  {}  of {} ",joinPoint.getKind(),joinPoint.getSignature().getName(),joinPoint.getSignature().getDeclaringTypeName());
    }
```

