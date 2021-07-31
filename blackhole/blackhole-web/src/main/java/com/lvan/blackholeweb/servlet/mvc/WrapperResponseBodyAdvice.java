package com.lvan.blackholeweb.servlet.mvc;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.AbstractMappingJacksonResponseBodyAdvice;

/**
 * 统一拦截controller的response body, 使用ApiResult进行包装。
 *
 * @author Lvan
 * @since 2021/7/31
 */
@RestControllerAdvice
public class WrapperResponseBodyAdvice extends AbstractMappingJacksonResponseBodyAdvice {

    @Override
    protected void beforeBodyWriteInternal(MappingJacksonValue bodyContainer, MediaType contentType,
                                           MethodParameter returnType, ServerHttpRequest request,
                                           ServerHttpResponse response) {

        Class<?> declaringClass = returnType.getDeclaringClass();
        boolean isNeedWrapper = returnType.hasMethodAnnotation(WrapperResult.class) ||
                declaringClass.isAnnotationPresent(WrapperResult.class);
        if (!isNeedWrapper) {
            return;
        }

        //若是ErrorDetail的子类，那么就不需要包装
        if (bodyContainer.getValue() instanceof ErrorDetail) {
            return;
        }

        bodyContainer.setValue(ApiResult.success(bodyContainer.getValue()));
    }
}
