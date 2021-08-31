package com.lvan.blackholeaop.log.aspect;

import com.lvan.blackholeaop.log.support.ControllerLogRecord;
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
    private ControllerLogRecord controllerLogRecord;

    public AbstractLogAspect(ControllerLogRecord controllerLogRecord) {
        this.controllerLogRecord = controllerLogRecord;
    }

    protected void pointCut() {
    }

    @Around("pointCut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        LogAspectContext logAspectContext = new LogAspectContext(joinPoint);
        controllerLogRecord.recordBeforeAdvice(logAspectContext);
        try {
            Object responseBody = joinPoint.proceed();
            controllerLogRecord.recordAfterReturnAdvice(logAspectContext, responseBody);
            return responseBody;
        } catch (Throwable e) {
            controllerLogRecord.recordAfterThrowAdvice(logAspectContext, e);
            throw e;
        }
    }
}
