package com.lvan.blackholeweb.log;

import java.lang.annotation.*;

/**
 * 使用此注解来为方法开启日志记录。
 *
 * @author Lvan
 * @since 2021/9/5
 */
@Documented
@Target(value = {ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface LogAop {
}
