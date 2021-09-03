package com.lvan.blackholeaop.log.context;

import com.lvan.blackholeaop.context.AspectContext;
import com.lvan.blackholeaop.log.RecordLogSwitch;

/**
 * 主要提供日志切面注解自定义属性的参数相关服务接口。
 *
 * @author lvan
 * @date 2021/9/3
 */
public interface LogAspectContext extends AspectContext {

    boolean isEnableRecordSwitch(final RecordLogSwitch expectRecordSwitch);
}
