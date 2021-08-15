package com.lvan.blackholevalidation;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import com.lvan.blackholevalidation.api.ValueOfEnum;
import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.ValidationException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 枚举常量校验器。
 *
 * @author Lvan
 * @since 2021/8/15
 */
@Slf4j
public class ValueOfEnumValidator implements ConstraintValidator<ValueOfEnum, Object> {

    private List<? extends Enum<?>> enumConstants;
    private Method method;

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {

        //该校验器不负责值为空的校验，返回true，这样可以传递给其他校验器校验。
        if (ObjectUtil.isNull(value)) {
            return true;
        }

        if (ObjectUtil.isNull(method) && value instanceof CharSequence) {
            List<String> enumNames = enumConstants.stream().map(Enum::name).collect(Collectors.toList());
            return enumNames.contains(String.valueOf(value));
        }

        for (Enum<?> enumConstant : enumConstants) {
            Object enumValue = ReflectUtil.invoke(enumConstant, method);
            if (ObjectUtil.equals(value, enumValue)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void initialize(ValueOfEnum constraintAnnotation) {
        this.enumConstants = Stream.of(constraintAnnotation.enumClass().getEnumConstants())
                .collect(Collectors.toList());

        if (StrUtil.isNotBlank(constraintAnnotation.method())) {
            try {
                this.method = constraintAnnotation.enumClass().getMethod(constraintAnnotation.method());
            } catch (NoSuchMethodException e) {
                log.error("failed to getMethod", e);
                throw new ValidationException(StrUtil.format("failed to getMethod from enumClass. method:{} enumClass:{}",
                        constraintAnnotation.method(), constraintAnnotation.enumClass().getCanonicalName()));
            }
        }
    }
}
