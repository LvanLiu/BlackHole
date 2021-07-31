package com.lvan.blackholeweb.servlet.mvc;

import java.lang.annotation.*;

/**
 * 标识返回信息是否需要使用ApiResult来包装。
 *
 * @author Lvan
 * @since 2021/7/31
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface WrapperResult {
}
