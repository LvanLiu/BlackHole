package com.lvan.blackholeaop.log;

import com.lvan.blackholeaop.log.api.EnableLogAop;
import com.lvan.blackholeaop.log.core.ControllerLogRecord;
import com.lvan.blackholeaop.log.core.DefaultLogRecord;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;
import java.util.Arrays;

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

    @Pointcut("@annotation(com.lvan.blackholeaop.log.api.EnableLogAop)")
    public void pointCut() {
    }

    @Around("pointCut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {

        EnableLogAop enableLogAop = acquireEnableLogAopAnnotation(joinPoint);
        EnableLogAop.RecordSwitch[] recordSwitches = enableLogAop.recordSwitch();

        controllerLogRecord.recordBeforeAdvice(joinPoint);

        try {
            Object responseBody = joinPoint.proceed();
            controllerLogRecord.recordAfterReturnAdvice(joinPoint, responseBody);
            return responseBody;
        } catch (Throwable e) {
            controllerLogRecord.recordAfterThrowAdvice(joinPoint, e);
            throw e;
        }
    }

    private EnableLogAop acquireEnableLogAopAnnotation(JoinPoint joinPoint) {

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        return method.getAnnotation(EnableLogAop.class);
    }

    private boolean shouldRecordLogBeforeAdvice(EnableLogAop.RecordSwitch[] recordSwitches) {

        return Arrays.stream(recordSwitches)
                .anyMatch(recordSwitch -> EnableLogAop.RecordSwitch.BEFORE.equals(recordSwitch));
    }
}
