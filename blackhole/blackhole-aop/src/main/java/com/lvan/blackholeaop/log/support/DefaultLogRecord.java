package com.lvan.blackholeaop.log.support;

import com.lvan.blackholeaop.log.LogAop;
import com.lvan.blackholeaop.log.aspect.LogAspectContext;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Lvan
 * @since 2021/8/29
 */
@Slf4j
public class DefaultLogRecord extends AbstractLogRecord {

    @Override
    public void recordBeforeAdvice(LogAspectContext context) {
        if (!context.isEnableRecordSwitch(LogAop.RecordLogSwitch.BEFORE)) {
            return;
        }

        log.info("[before] args:{}", context.getArgs());
    }

    @Override
    public void recordAfterReturnAdvice(LogAspectContext context, Object response) {

    }

    @Override
    public void recordAfterThrowAdvice(LogAspectContext context, Throwable ex) {

    }
}
