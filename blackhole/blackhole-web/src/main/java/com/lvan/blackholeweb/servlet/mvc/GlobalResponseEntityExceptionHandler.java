package com.lvan.blackholeweb.servlet.mvc;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Set;

/**
 * 全局异常处理器。
 *
 * @author Lvan
 * @since 2021/7/28
 */
@Slf4j
@RestControllerAdvice
public class GlobalResponseEntityExceptionHandler {

    /**
     * 处理不支持此Http请求方法异常。
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    protected ResponseEntity<ErrorDetail> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex) {

        HttpHeaders headers = new HttpHeaders();
        Set<HttpMethod> supportedHttpMethods = ex.getSupportedHttpMethods();
        if (CollUtil.isNotEmpty(supportedHttpMethods)) {
            headers.setAllow(supportedHttpMethods);
        }

        ErrorDetail errorDetail = ErrorDetail.error(ApiCheckResultCode.HTTP_REQUEST_NOT_SUPPORTED);
        return handleExceptionInternal(ex, errorDetail, headers, HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    protected ResponseEntity<ErrorDetail> handleMissingServletRequestParameter(MissingServletRequestParameterException ex) {

        String errorMessage = StrUtil.format("参数{}必填", ex.getParameterName());
        ErrorDetail errorDetail = ErrorDetail.error(ApiCheckResultCode.HTTP_PARAMS_ERROR.getCode(), errorMessage);
        return handleExceptionInternal(ex, errorDetail, null, HttpStatus.BAD_REQUEST);
    }

    /**
     * 统一自定义全局异常响应报文。
     *
     * @param ex      异常
     * @param body    响应正文
     * @param headers 响应http header
     * @param status  http状态
     */
    protected ResponseEntity<ErrorDetail> handleExceptionInternal(
            Exception ex, ErrorDetail body, HttpHeaders headers, HttpStatus status) {

        log.error("global exception handler", ex);
        body.setType(ex.getClass().getSimpleName());
        return new ResponseEntity<>(body, headers, status);
    }
}