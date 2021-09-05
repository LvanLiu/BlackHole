package com.lvan.blackholeweb.log.aspect;

import com.lvan.blackholeaop.AbstractAspect;
import com.lvan.blackholeaop.strategy.AspectHandleStrategy;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * {@link com.lvan.blackholeweb.log.LogAop}注解的切面处理实现。
 *
 * @author Lvan
 * @since 2021/9/5
 */
@Aspect
public class LogAspect extends AbstractAspect {

    public LogAspect(AspectHandleStrategy aspectHandleStrategy) {
        super(aspectHandleStrategy);
    }

    @Pointcut("@annotation(com.lvan.blackholeweb.log.LogAop) || @within(com.lvan.blackholeweb.log.LogAop)")
    @Override
    public void pointCut() {
    }
}
