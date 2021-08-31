package com.lvan.blackholeaop.log.support;

import com.lvan.blackholeaop.log.aspect.LogAspectContext;

/**
 * record the method log api。
 *
 * @author Lvan
 * @since 2021/8/29
 */
public interface LogRecord {

    void recordBeforeAdvice(LogAspectContext context);

    void recordAfterReturnAdvice(LogAspectContext context, Object response);

    void recordAfterThrowAdvice(LogAspectContext context, Throwable ex);
}
