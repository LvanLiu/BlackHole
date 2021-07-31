package com.lvan.blackholecore.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * jsr 枚举校验注解。
 *
 * @author Lvan
 * @since 2021/7/31
 */
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = {EnumValidator.class})
public @interface Enum {

    /**
     * 枚举类型
     */
    Class<?> target();

    /**
     * 获取枚举值方法
     */
    String method();

    /**
     * 错误信息
     */
    String message() default "invalid value";

    /**
     * 分组
     */
    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
