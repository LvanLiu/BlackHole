package com.lvan.blackholeaop.context;

import lombok.Getter;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @author lvan
 * @date 2021/9/3
 */
public abstract class AbstractAspectContext implements AspectContext {

    @Getter
    private final JoinPoint joinPoint;

    protected AbstractAspectContext(JoinPoint joinPoint) {
        this.joinPoint = joinPoint;
    }

    @Override
    public Object[] acquireArgs() {
        return joinPoint.getArgs();
    }

    protected <T extends Annotation> T acquireMethodAnnotation(Class<T> annotationClass) {

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        return method.getAnnotation(annotationClass);
    }
}
