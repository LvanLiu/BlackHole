package com.lvan.blackholeaop.log.support;

import com.lvan.blackholeaop.log.aspect.LogAspectContext;

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
    void recordBeforeAdvice(LogAspectContext context);

    /**
     * record log after return advice
     */
    void recordAfterReturnAdvice(LogAspectContext context, Object response);

    /**
     * record log after throw advice
     */
    void recordAfterThrowAdvice(LogAspectContext context, Throwable ex);
}
