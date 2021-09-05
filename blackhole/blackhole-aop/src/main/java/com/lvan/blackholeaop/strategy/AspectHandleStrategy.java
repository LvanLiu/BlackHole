package com.lvan.blackholeaop.strategy;

import org.aspectj.lang.JoinPoint;

/**
 * 切面处理策略。
 * <p>
 * 可以实现此接口，来完成对切面拦截前后的处理。
 *
 * @author Lvan
 * @since 2021/9/5
 */
public interface AspectHandleStrategy {

    void setJoinPoint(JoinPoint joinPoint);

    void before();

    void afterReturning(Object response);

    void afterExceptionThrow(Throwable ex);
}
