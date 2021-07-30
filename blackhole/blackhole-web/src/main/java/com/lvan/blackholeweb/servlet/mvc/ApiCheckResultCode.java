package com.lvan.blackholeweb.servlet.mvc;

import com.lvan.blackholecore.code.ResponseCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Http请求校验码。
 * <p>
 * 响应码得定义主要分为以下几类：
 * 1. 参数校验不通过类
 * 2. 不支持Http请求类
 * 3. 请求信息转换失败类
 * 4. 系统无此资源类
 *
 * @author Lvan
 * @since 2021/7/29
 */
@Getter
@AllArgsConstructor
public enum ApiCheckResultCode implements ResponseCode {

    HTTP_PARAMS_ERROR(1001, "参数校验失败"),
    HTTP_REQUEST_NOT_SUPPORTED(1002, "不支持此Http请求"),
    HTTP_CONVERT_ERROR(1003, "请求报文转换失败"),
    HTTP_NOT_FOUND(1004, "系统无此资源");

    private final int code;
    private final String message;
}
