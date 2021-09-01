package com.lvan.blackholeweb.servlet.mvc;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 全局异常处理器。
 *
 * @author Lvan
 * @since 2021/7/28
 */
@Slf4j
@RestControllerAdvice
public class GlobalResponseEntityExceptionHandler {

    @ExceptionHandler({
            MissingServletRequestParameterException.class,
            MissingServletRequestPartException.class,
            ServletRequestBindingException.class,
            TypeMismatchException.class,
            HttpMessageNotReadableException.class,
            HttpMessageNotWritableException.class,
            MethodArgumentNotValidException.class,
            BindException.class})
    protected ResponseEntity<ErrorDetail> handleMissingServletRequestParameter(Exception ex) {

        String errorMessage;
        if (ex instanceof MissingServletRequestParameterException) {
            errorMessage = StrUtil.format("参数{}必填", ((MissingServletRequestParameterException) ex).getParameterName());
        } else if (ex instanceof MissingServletRequestPartException) {
            errorMessage = StrUtil.format("参数{}必填", ((MissingServletRequestPartException) ex).getRequestPartName());
        } else if (ex instanceof HttpMessageNotReadableException) {
            errorMessage = "Http请求信息不可读或者格式错误";
        } else if (ex instanceof BindException) {
            BindException bindException = (BindException) ex;
            errorMessage = bindException.getFieldErrors().stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(","));
        } else {
            errorMessage = ex.getMessage();
        }

        ErrorDetail errorDetail = ErrorDetail.error(ApiCode.ApiCheckResultCode.HTTP_PARAMS_ERROR.getCode(), errorMessage);
        return handleExceptionInternal(ex, errorDetail, null, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    protected ResponseEntity<ErrorDetail> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex) {

        HttpHeaders headers = new HttpHeaders();
        Set<HttpMethod> supportedHttpMethods = ex.getSupportedHttpMethods();
        if (CollUtil.isNotEmpty(supportedHttpMethods)) {
            headers.setAllow(supportedHttpMethods);
        }

        ErrorDetail errorDetail = ErrorDetail.error(ApiCode.ApiCheckResultCode.HTTP_REQUEST_NOT_SUPPORTED);
        return handleExceptionInternal(ex, errorDetail, headers, HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    protected ResponseEntity<ErrorDetail> handleHttpMediaTypeNotSupported(
            HttpMediaTypeNotSupportedException ex, WebRequest request) {

        HttpHeaders headers = new HttpHeaders();
        List<MediaType> mediaTypes = ex.getSupportedMediaTypes();
        if (!CollectionUtils.isEmpty(mediaTypes)) {
            headers.setAccept(mediaTypes);
            if (request instanceof ServletWebRequest) {
                ServletWebRequest servletWebRequest = (ServletWebRequest) request;
                if (HttpMethod.PATCH.equals(servletWebRequest.getHttpMethod())) {
                    headers.setAcceptPatch(mediaTypes);
                }
            }
        }

        ErrorDetail errorDetail = ErrorDetail.error(ApiCode.ApiCheckResultCode.HTTP_REQUEST_NOT_SUPPORTED);
        return handleExceptionInternal(ex, errorDetail, headers, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }

    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
    protected ResponseEntity<ErrorDetail> handleHttpMediaTypeNotAcceptable(HttpMediaTypeNotAcceptableException ex) {

        return handleExceptionInternal(ex, null, null, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(RuntimeException.class)
    protected ResponseEntity<ErrorDetail> handleRuntimeException(RuntimeException ex) {

        ErrorDetail errorDetail = ErrorDetail.error(ApiCode.SystemErrorCode.SYSTEM_ERROR);
        return handleExceptionInternal(ex, errorDetail, null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    protected ResponseEntity<ErrorDetail> handleExceptionInternal(
            Exception ex, ErrorDetail body, HttpHeaders headers, HttpStatus status) {

        log.error("global exception handler", ex);
        if (ObjectUtil.isNotNull(body)) {
            body.setType(ex.getClass().getSimpleName());
        }
        return new ResponseEntity<>(body, headers, status);
    }
}