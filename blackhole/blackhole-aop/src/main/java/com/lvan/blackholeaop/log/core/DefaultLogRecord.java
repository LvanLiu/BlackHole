package com.lvan.blackholeaop.log.core;

import org.aspectj.lang.JoinPoint;

/**
 * @author Lvan
 * @since 2021/8/29
 */
public class DefaultLogRecord extends AbstractControllerLogRecord {

    @Override
    public void recordBeforeAdvice(JoinPoint joinPoint) {

    }

    @Override
    public void recordAfterReturnAdvice(JoinPoint joinPoint, Object responseBody) {

    }

    @Override
    public void recordAfterThrowAdvice(JoinPoint joinPoint, Throwable ex) {

    }
}
