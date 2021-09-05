package com.lvan.blackholeweb.utils;

import lombok.experimental.UtilityClass;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Lvan
 * @since 2021/9/5
 */
@UtilityClass
public class RequestContextHolderUtil {

    public static HttpServletRequest acquireHttpServletRequest() {

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return attributes.getRequest();
    }
}
