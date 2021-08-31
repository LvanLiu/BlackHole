package com.lvan.blackholeaop.log.support;

import com.lvan.blackholeaop.log.LogAop;
import com.lvan.blackholeaop.log.aspect.LogAspectContext;

/**
 * @author Lvan
 * @since 2021/8/29
 */
public abstract class AbstractLogRecord implements LogRecord {

    @Override
    public void recordBeforeAdvice(LogAspectContext context) {

        if (!context.isEnableRecordSwitch(LogAop.RecordLogSwitch.BEFORE)) {
            return;
        }
        outPutLogBeforeAdvice(context);
    }

    @Override
    public void recordAfterReturnAdvice(LogAspectContext context, Object response) {

        if (!context.isEnableRecordSwitch(LogAop.RecordLogSwitch.AFTER_RETURNING)) {
            return;
        }

        outPutLogAfterReturnAdvice(context, response);
    }

    @Override
    public void recordAfterThrowAdvice(LogAspectContext context, Throwable ex) {

        if (!context.isEnableRecordSwitch(LogAop.RecordLogSwitch.AFTER_THROWING)) {
            return;
        }

        outPutLogAfterThrowAdvice(context, ex);
    }

    protected abstract void outPutLogBeforeAdvice(LogAspectContext context);

    protected abstract void outPutLogAfterReturnAdvice(LogAspectContext context, Object response);

    protected abstract void outPutLogAfterThrowAdvice(LogAspectContext context, Throwable ex);
}
