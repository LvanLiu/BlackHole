package com.lvan.blackholeaop.log.aspect;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ObjectUtil;
import com.lvan.blackholeaop.log.LogAop;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

/**
 * @author Lvan
 * @since 2021/8/30
 */
public class LogAspectContext {

    private final LogAop.RecordLogSwitch[] recordLogSwitches;
    private Boolean recordAllLog;
    private Boolean recordNothing;

    private JoinPoint logJoinPoint;

    public LogAspectContext(final JoinPoint logJoinPoint) {

        this.logJoinPoint = logJoinPoint;
        LogAop logAop = acquireEnableLogAopAnnotation(logJoinPoint);
        this.recordLogSwitches = logAop.recordSwitch();
    }

    private LogAop acquireEnableLogAopAnnotation(final JoinPoint joinPoint) {

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        return method.getAnnotation(LogAop.class);
    }

    public boolean isRecordAllLog() {

        if (ObjectUtil.isNotNull(recordAllLog)) {
            return recordAllLog;
        }

        recordAllLog = ArrayUtil.contains(recordLogSwitches, LogAop.RecordLogSwitch.ALL);
        return recordAllLog;
    }

    public boolean isRecordNothing() {

        if (ObjectUtil.isNotNull(recordNothing)) {
            return recordNothing;
        }

        recordNothing = ArrayUtil.contains(recordLogSwitches, LogAop.RecordLogSwitch.NONE);
        return recordNothing;
    }

    public boolean isEnableRecordSwitch(final LogAop.RecordLogSwitch expectRecordSwitch) {

        if (isRecordAllLog()) {
            return true;
        }

        if (isRecordNothing()) {
            return false;
        }

        return ArrayUtil.contains(recordLogSwitches, expectRecordSwitch);
    }

    public Object[] getArgs() {
        return logJoinPoint.getArgs();
    }
}
