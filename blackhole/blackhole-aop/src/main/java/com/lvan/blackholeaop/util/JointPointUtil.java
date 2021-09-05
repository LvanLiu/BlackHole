package com.lvan.blackholeaop.util;

import cn.hutool.core.util.ObjectUtil;
import com.lvan.blackholeaop.exception.AopException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.AnnotationUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @author Lvan
 * @since 2021/9/5
 */
public class JointPointUtil {

    private JoinPoint joinPoint;

    private JointPointUtil(JoinPoint joinPoint) {
        this.joinPoint = joinPoint;
    }

    public static JointPointUtil of(JoinPoint joinPoint) {
        return new JointPointUtil(joinPoint);
    }

    public Object[] acquireMethodArgs() {
        return joinPoint.getArgs();
    }

    public <T extends Annotation> T acquireMethodAnnotation(Class<T> annotationClass) {

        Method method = acquireMethodSignature();
        return method.getAnnotation(annotationClass);
    }

    public <T extends Annotation> boolean hasAnnotation(Class<T> annotationClass) {

        Method method = acquireMethodSignature();
        T annotation = AnnotationUtils.findAnnotation(method, annotationClass);
        return ObjectUtil.isNotNull(annotation);
    }

    private Method acquireMethodSignature() {

        Signature signature = joinPoint.getSignature();
        if (!(signature instanceof MethodSignature)) {
            throw new AopException("signature is not MethodSignature, the signature is [{}]", signature.getName());
        }

        MethodSignature methodSignature = (MethodSignature) signature;
        return methodSignature.getMethod();
    }
}
