package com.lvan.blackholeaop.log.context;

import com.lvan.blackholeaop.log.LogAop;
import com.lvan.blackholeaop.log.RecordLogSwitch;
import org.aspectj.lang.JoinPoint;

/**
 * @author Lvan
 * @since 2021/8/30
 */
public class LogAopContext extends AbstractLogAspectContext {

    private final RecordLogSwitch[] recordLogSwitches;

    public LogAopContext(final JoinPoint logJoinPoint) {

        super(logJoinPoint);
        LogAop logAop = acquireMethodAnnotation(LogAop.class);
        this.recordLogSwitches = logAop.recordSwitch();
    }

    @Override
    protected RecordLogSwitch[] acquireRecordLogSwitches() {
        return this.recordLogSwitches;
    }
}
