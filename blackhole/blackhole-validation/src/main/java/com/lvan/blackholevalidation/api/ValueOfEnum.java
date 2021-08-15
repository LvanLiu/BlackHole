package com.lvan.blackholevalidation.api;

import com.lvan.blackholevalidation.ValueOfEnumValidator;
import lombok.Getter;
import lombok.experimental.UtilityClass;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 可用此注解，校验传入的常量是否在枚举中定义。
 *
 * @author Lvan
 * @since 2021/8/15
 */
@Documented
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
@Repeatable(ValueOfEnum.List.class)
@Constraint(validatedBy = {ValueOfEnumValidator.class})
public @interface ValueOfEnum {

    Class<? extends Enum<?>> enumClass();

    /**
     * the method's name ,which used to validate the enum's value
     */
    String method() default "";

    String message() default "must be any of enum {enumClass}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    @Documented
    @Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
    @Retention(RUNTIME)
    @interface List {
        ValueOfEnum[] value();
    }

    @Getter
    @UtilityClass
    class EnumMethodConstants {
        public static final String METHOD_GET_VALUE = "getValue";
        public static final String METHOD_GET_CODE = "getCode";
    }
}
