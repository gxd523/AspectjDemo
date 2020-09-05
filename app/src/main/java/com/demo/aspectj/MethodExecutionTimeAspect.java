package com.demo.aspectj;

import android.util.Log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

@Aspect
public class MethodExecutionTimeAspect {
    /**
     * 定义切面的规则
     * 1、就再原来的应用中那些注解的地方放到当前切面进行处理
     * execution(注解名 注解用的地方)
     */
    @Pointcut("execution(@com.demo.aspectj.MethodExecutionTime * *(..))")
    public void annotationWithMethodExecutionTime() {
    }

    /**
     * 2、对进入切面的内容如何处理
     *
     * @Before 在切入点之前运行
     * @After 在切入点之后运行
     * @Around 在切入点前后都运行
     */
    @Around("annotationWithMethodExecutionTime()")
    public Object weaveJoinPoint(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String className = methodSignature.getDeclaringType().getSimpleName();
        String methodName = methodSignature.getName();
        String value = methodSignature.getMethod().getAnnotation(MethodExecutionTime.class).value();
        Log.d("gxd", "value = " + value);
        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long duration = System.currentTimeMillis() - startTime;
        Log.d("gxd", String.format("%s.%s()...耗时%dms", className, methodName, duration));
        return result;
    }
}
