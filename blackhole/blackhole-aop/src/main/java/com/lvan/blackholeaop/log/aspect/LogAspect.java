package com.lvan.blackholeaop.log.aspect;

import com.lvan.blackholeaop.log.support.ControllerLogRecord;
import com.lvan.blackholeaop.log.support.DefaultLogRecord;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * 日志切面。
 *
 * @author Lvan
 * @since 2021/8/16
 */
@Slf4j
@Aspect
public class LogAspect {

    @Getter
    @Setter
    private ControllerLogRecord controllerLogRecord;

    public LogAspect() {
        this.controllerLogRecord = new DefaultLogRecord();
    }

    public LogAspect(ControllerLogRecord controllerLogRecord) {
        this.controllerLogRecord = controllerLogRecord;
    }

    @Pointcut("@annotation(com.lvan.blackholeaop.log.EnableLogAop)")
    public void pointCut() {
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
