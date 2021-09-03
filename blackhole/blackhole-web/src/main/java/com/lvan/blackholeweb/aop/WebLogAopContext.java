package com.lvan.blackholeweb.aop;

import com.lvan.blackholeaop.log.RecordLogSwitch;
import com.lvan.blackholeaop.log.context.AbstractLogAspectContext;
import org.aspectj.lang.JoinPoint;

/**
 * @author lvan
 * @date 2021/9/3
 */
public class WebLogAopContext extends AbstractLogAspectContext {

    private final RecordLogSwitch[] recordLogSwitches;

    public WebLogAopContext(final JoinPoint logJoinPoint) {

        super(logJoinPoint);
        WebLogAop logAop = acquireMethodAnnotation(WebLogAop.class);
        this.recordLogSwitches = logAop.recordSwitch();
    }

    @Override
    protected RecordLogSwitch[] acquireRecordLogSwitches() {
        return this.recordLogSwitches;
    }
}
