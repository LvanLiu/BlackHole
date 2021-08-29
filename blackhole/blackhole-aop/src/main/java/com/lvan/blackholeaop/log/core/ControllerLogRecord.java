package com.lvan.blackholeaop.log.core;

import org.aspectj.lang.JoinPoint;

/**
 * record the controller log api。
 *
 * @author Lvan
 * @since 2021/8/29
 */
public interface ControllerLogRecord {

    /**
     * record log before advice
     */
    void recordBeforeAdvice(JoinPoint joinPoint);

    /**
     * record log after return advice
     */
    void recordAfterReturnAdvice(JoinPoint joinPoint, Object responseBody);

    /**
     * record log after throw advice
     */
    void recordAfterThrowAdvice(JoinPoint joinPoint, Throwable ex);
}
