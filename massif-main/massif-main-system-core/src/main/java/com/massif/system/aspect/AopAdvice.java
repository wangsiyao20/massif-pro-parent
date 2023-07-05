package com.massif.system.aspect;

import com.massif.system.exception.UnauthorizedException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @author wangsiyao
 * @create 2023-06-12 9:38
 */

@Aspect
@Component
public class AopAdvice {

//    @Pointcut("execution(* com.massif.system.controller.AopController.*(..))")
//    public void test(){
//    }

    @Pointcut("@annotation(com.massif.system.annotation.XAnnotation)")
    public void testAno(){
    }

    @Pointcut("@annotation(com.massif.system.annotation.XException)")
    public void testExc(){
    }

    @Pointcut("@annotation(com.massif.system.annotation.XPermissions)")
    public void aspectPermissions(){
    }

//    @Around("test()")
//    public void aroundAdvice(ProceedingJoinPoint proceedingJoinPoint) {
//        System.out.println("before");
//        try {
//            proceedingJoinPoint.proceed();
//        } catch (Throwable t) {
//            t.printStackTrace();
//        }
//        System.out.println("after");
//    }

    @Around("testAno()")
    public void aroundAno(ProceedingJoinPoint proceedingJoinPoint){

        System.out.println("annotation before...");
        try {
            proceedingJoinPoint.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        System.out.println("annotation after...");

    }

    @Before("testExc()")
    public void throwExc(){
        throw new UnauthorizedException("权限错误");
    }

}
