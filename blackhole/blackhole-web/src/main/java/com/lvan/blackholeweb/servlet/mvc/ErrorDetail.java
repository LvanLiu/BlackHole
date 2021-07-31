package com.lvan.blackholeweb.servlet.mvc;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.lvan.blackholecore.api.ResponseCode;
import lombok.Data;
import lombok.NonNull;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 错误详情。
 *
 * @author Lvan
 * @since 2021/7/29
 */
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class ErrorDetail implements Serializable {

    private static final long serialVersionUID = 5133369361095043230L;
    /**
     * 异常类型，用于快速定位问题
     */
    private String type;

    /**
     * 错误码
     */
    private Integer code;

    /**
     * 错误信息
     */
    private String message;

    public static ErrorDetail error(@NonNull ResponseCode responseCode) {
        return error("", responseCode);
    }

    public static ErrorDetail error(@NonNull Integer code, @NonNull String message) {
        return error("", code, message);
    }

    public static ErrorDetail error(String type, @NonNull ResponseCode responseCode) {

        return error(type, responseCode.getCode(), responseCode.getMessage());
    }

    /**
     * 根据异常类型、错误码、错误信息来构建错误模型。
     */
    public static ErrorDetail error(String type, @NonNull Integer code, @NonNull String message) {

        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setType(type)
                .setCode(code)
                .setMessage(message);
        return errorDetail;
    }
}
