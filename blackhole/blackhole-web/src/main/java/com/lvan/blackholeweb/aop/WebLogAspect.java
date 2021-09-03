package com.lvan.blackholeweb.aop;

import com.lvan.blackholeaop.log.aspect.AbstractLogAspect;
import com.lvan.blackholeaop.log.support.LogRecord;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * @author lvan
 * @date 2021/9/3
 */
@Aspect
public class WebLogAspect extends AbstractLogAspect {

    public WebLogAspect(LogRecord logRecord) {
        super(logRecord);
    }

    @Pointcut("@annotation(com.lvan.blackholeweb.aop.WebLogAop) || @within(com.lvan.blackholeweb.aop.WebLogAop)")
    @Override
    protected void pointCut() {
    }
}
