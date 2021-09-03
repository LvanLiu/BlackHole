package com.lvan.blackholeaop.log;

import java.lang.annotation.*;

/**
 * 使用此注解来为方法开启日志记录，那么日志切面将会以环绕的形式记录方法的入口以及出口信息。
 *
 * @author Lvan
 * @since 2021/8/16
 */
@Documented
@Target(value = {ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface LogAop {

    /**
     * switch to control logging
     */
    RecordLogSwitch[] recordSwitch() default RecordLogSwitch.ALL;
}