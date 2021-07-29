package com.lvan.blackholecore.code;

/**
 * 响应码接口。
 *
 * @author Lvan
 * @since 2021/7/29
 */
public interface ResponseCode {

    /**
     * 获取响应码。
     */
    String getCode();

    /**
     * 获取返回信息。
     */
    String getMessage();
}
