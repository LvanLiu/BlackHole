package com.lvan.blackholecore.util;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.ReflectUtil;

import java.lang.reflect.Field;

/**
 * 枚举工具类, 更多用法请查看{@link cn.hutool.core.util.EnumUtil}
 *
 * @author lvan
 * @date 2021/8/2
 */
public class EnumPlusUtil extends cn.hutool.core.util.EnumUtil {

    /**
     * 模糊匹配转换为枚举，给定一个值，匹配枚举中定义的所有字段名（包括name属性），一旦匹配到则返回true，否则返回false
     *
     * @param enumClass 枚举的Class对象
     * @param value     需要匹配的值
     * @return true - 匹配成功 false - 匹配失败
     */
    public static boolean likeContains(Class<?> enumClass, Object value) {

        if (value instanceof CharSequence) {
            value = value.toString().trim();
        }

        final Field[] fields = ReflectUtil.getFields(enumClass);
        Object[] enumConstants = enumClass.getEnumConstants();
        String fieldName;
        for (Field field : fields) {
            fieldName = field.getName();
            if (field.getType().isEnum() || "ENUM$VALUES".equals(fieldName) || "ordinal".equals(fieldName)) {
                // 跳过一些特殊字段
                continue;
            }
            for (Object item : enumConstants) {
                if (ObjectUtil.equal(value, ReflectUtil.getFieldValue(item, field))) {
                    return true;
                }
            }
        }
        return false;
    }
}
