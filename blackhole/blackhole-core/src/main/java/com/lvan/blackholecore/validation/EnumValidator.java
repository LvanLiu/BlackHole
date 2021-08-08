package com.lvan.blackholecore.validation;

import cn.hutool.core.util.ObjectUtil;
import com.lvan.blackholecore.util.EnumPlusUtil;
import com.lvan.blackholecore.validation.constraints.Enum;
import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

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

    @Override
    public void initialize(Enum constraintAnnotation) {

        enumClass = constraintAnnotation.target();
        enumConstants = enumClass.getEnumConstants();
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

        return EnumPlusUtil.likeContains(enumClass, value);
    }

}
