package com.lvan.blackholeweb.servlet.mvc;

import com.lvan.blackholecore.code.ResponseCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.UtilityClass;

/**
 * @author Lvan
 * @since 2021/7/31
 */
@UtilityClass
public class ApiCode {

    /**
     * Http请求校验失败错误码
     */
    @Getter
    @AllArgsConstructor
    public enum ApiCheckResultCode implements ResponseCode {

        HTTP_PARAMS_ERROR(1000, "参数校验失败"),
        HTTP_REQUEST_NOT_SUPPORTED(1001, "不支持此Http请求");

        private final int code;
        private final String message;
    }

    /**
     * 系统级别的错误码
     */
    @Getter
    @AllArgsConstructor
    public enum SystemErrorCode implements ResponseCode {

        SYSTEM_ERROR(2000, "系统错误");

        private final int code;
        private final String message;
    }
}
