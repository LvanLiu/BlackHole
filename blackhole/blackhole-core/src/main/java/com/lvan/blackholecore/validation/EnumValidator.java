package com.lvan.blackholecore.validation;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.lvan.blackholecore.validation.constraints.Enum;
import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.Method;

/**
 * @author Lvan
 * @since 2021/7/31
 */
@Slf4j
public class EnumValidator implements ConstraintValidator<Enum, Object> {

    /**
     * 枚举类
     */
    private Class<?> enumClass;
    private Object[] enumConstants;
    private String methodName;

    @Override
    public void initialize(Enum constraintAnnotation) {

        enumClass = constraintAnnotation.target();
        enumConstants = enumClass.getEnumConstants();
        methodName = constraintAnnotation.method();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {

        if (ObjectUtil.isNull(value)) {
            return true;
        }

        //枚举类没有定义常量, 不允许通过校验
        if (ObjectUtil.isAllEmpty(enumConstants)) {
            log.warn("枚举类没有定义常量，枚举类：{}", enumClass.getName());
            return false;
        }

        //方法名为空，那么就获取不到枚举值，这种情况就没法校验，直接
        if (StrUtil.isBlank(methodName)) {
            log.warn("{}中没有指定method或者method为空", enumClass.getName());
            return false;
        }

        try {
            Method method = enumClass.getMethod(methodName);
            for (Object obj : enumConstants) {
                Object tmp = method.invoke(obj);
                if (value.equals(tmp)) {
                    return true;
                }
            }
        } catch (Exception ex) {
            log.error("获取枚举值失败", ex);
        }
        return false;
    }
}
