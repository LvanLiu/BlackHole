package com.lvan.blackholeaop;

import com.lvan.blackholeaop.strategy.AspectHandleStrategy;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;

/**
 * @author Lvan
 * @since 2021/9/5
 */
public abstract class AbstractAspect {

    private AspectHandleStrategy aspectHandleStrategy;

    protected AbstractAspect(AspectHandleStrategy aspectHandleStrategy) {
        this.aspectHandleStrategy = aspectHandleStrategy;
    }

    /**
     * 定义切点，由子类负责实现，这里的作用，仅仅作为占位符。
     */
    protected void pointCut() {
        // do nothing
    }

    /**
     * 环绕处理，利用模板方法设计模式。
     * <p>
     * 该方法不实现具体逻辑，仅调用钩子方法，由指定的策略负责实现。
     */
    @Around("pointCut()")
    public Object recordLogAroundMethod(ProceedingJoinPoint joinPoint) throws Throwable {

        aspectHandleStrategy.setJoinPoint(joinPoint);
        aspectHandleStrategy.before();
        try {
            Object responseBody = joinPoint.proceed();
            aspectHandleStrategy.afterReturning(responseBody);
            return responseBody;
        } catch (Throwable ex) {
            aspectHandleStrategy.afterExceptionThrow(ex);
            throw ex;
        }
    }
}
