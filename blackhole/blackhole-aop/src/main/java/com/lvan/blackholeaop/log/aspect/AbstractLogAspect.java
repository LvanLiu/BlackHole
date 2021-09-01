package com.lvan.blackholeaop.log.aspect;

import com.lvan.blackholeaop.log.support.LogRecord;
import lombok.Getter;
import lombok.Setter;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;

/**
 * @author lvan
 * @date 2021/8/31
 */
public abstract class AbstractLogAspect {

    @Getter
    @Setter
    private LogRecord logRecord;

    protected AbstractLogAspect(LogRecord logRecord) {
        this.logRecord = logRecord;
    }

    protected void pointCut() {
    }

    @Around("pointCut()")
    public Object recordLogAroundMethod(ProceedingJoinPoint joinPoint) throws Throwable {

        LogAspectContext logAspectContext = new LogAspectContext(joinPoint);
        logRecord.recordBeforeAdvice(logAspectContext);

        try {
            Object responseBody = joinPoint.proceed();
            logRecord.recordAfterReturnAdvice(logAspectContext, responseBody);
            return responseBody;
        } catch (Throwable e) {
            logRecord.recordAfterThrowAdvice(logAspectContext, e);
            throw e;
        }
    }
}
