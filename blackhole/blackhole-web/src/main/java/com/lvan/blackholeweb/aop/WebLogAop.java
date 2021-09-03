package com.lvan.blackholeweb.aop;

import com.lvan.blackholeaop.log.LogAop;
import com.lvan.blackholeaop.log.RecordLogSwitch;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * 主要用来记录controller层的日志。
 *
 * @author Lvan
 * @since 2021/8/16
 */
@Documented
@Target(value = {ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface WebLogAop {

    @AliasFor(annotation = LogAop.class, attribute = "recordSwitch")
    RecordLogSwitch[] recordSwitch() default RecordLogSwitch.ALL;
}