package com.lvan.blackholeaop.log.aspect;

import com.lvan.blackholeaop.log.support.DefaultLogRecord;
import com.lvan.blackholeaop.log.support.LogRecord;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * 日志切面。
 *
 * @author Lvan
 * @since 2021/8/16
 */
@Aspect
public class DefaultLogAspect extends AbstractLogAspect {

    public DefaultLogAspect() {
        super(new DefaultLogRecord());
    }

    public DefaultLogAspect(LogRecord controllerLogRecord) {
        super(controllerLogRecord);
    }

    @Pointcut("@annotation(com.lvan.blackholeaop.log.LogAop) || @within(com.lvan.blackholeaop.log.LogAop)")
    @Override
    public void pointCut() {
    }
}
