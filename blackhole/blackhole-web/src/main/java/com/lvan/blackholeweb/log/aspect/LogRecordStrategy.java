package com.lvan.blackholeweb.log.aspect;

import cn.hutool.core.lang.UUID;
import cn.hutool.json.JSONUtil;
import com.lvan.blackholeaop.strategy.AbstractAspectStrategy;
import com.lvan.blackholeaop.util.JointPointUtil;
import com.lvan.blackholeweb.utils.RequestContextHolderUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Lvan
 * @since 2021/9/5
 */
@Slf4j
public class LogRecordStrategy extends AbstractAspectStrategy {

    private static final String LOG_TRACE_ID = "traceId";

    @Override
    public void before() {

        MDC.put(LOG_TRACE_ID, UUID.fastUUID().toString(true));

        if (isControllerMethod()) {
            HttpServletRequest httpServletRequest = RequestContextHolderUtil.acquireHttpServletRequest();
            log.info("uri: {}", httpServletRequest.getRequestURI());
        }

        Object[] methodArgs = JointPointUtil.of(getJoinPoint()).acquireMethodArgs();
        log.info("method args:{}", methodArgs);
    }

    @Override
    public void afterReturning(Object response) {

        log.info("response body: {}", JSONUtil.toJsonStr(response));
    }

    @Override
    public void afterExceptionThrow(Throwable ex) {
    }

    private boolean isControllerMethod() {
        return JointPointUtil.of(getJoinPoint()).hasAnnotation(RequestMapping.class);
    }
}
