package com.lvan.blackholeweb.servlet.mvc;

import lombok.Data;

/**
 * 错误详情。
 *
 * @author Lvan
 * @since 2021/7/29
 */
@Data
public class ErrorDetail {

    /**
     * 异常类型，用于快速定位问题
     */
    private String type;

    /**
     * 错误码
     */
    private String code;

    /**
     * 错误信息
     */
    private String message;
}
