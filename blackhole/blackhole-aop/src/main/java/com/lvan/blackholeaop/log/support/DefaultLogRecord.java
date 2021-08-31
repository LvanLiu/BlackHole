package com.lvan.blackholeaop.log.support;

import cn.hutool.json.JSONUtil;
import com.lvan.blackholeaop.log.aspect.LogAspectContext;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Lvan
 * @since 2021/8/29
 */
@Slf4j
public class DefaultLogRecord extends AbstractLogRecord {

    @Override
    protected void outPutLogBeforeAdvice(LogAspectContext context) {
        log.info("method args:{}", context.getArgs());
    }

    @Override
    protected void outPutLogAfterReturnAdvice(LogAspectContext context, Object response) {
        log.info("method return:{}", JSONUtil.toJsonStr(response));
    }

    @Override
    protected void outPutLogAfterThrowAdvice(LogAspectContext context, Throwable ex) {
        log.info("method throws", ex);
    }
}
