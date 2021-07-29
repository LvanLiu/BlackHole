package com.lvan.blackholeweb.servlet.mvc;

import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器。
 *
 * @author Lvan
 * @since 2021/7/28
 */
@RestControllerAdvice
public class GlobalResponseEntityExceptionHandler {

    /**
     * 处理不支持此Http请求方法异常。
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    protected ResponseEntity<ErrorDetail> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex) {
        return null;
    }
}
