package com.lvan.blackholeweb.servlet.mvc;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * Api返回结果，对于成功的情况，需要包装多一层返回信息。
 *
 * @author Lvan
 * @since 2021/7/31
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResult<T> extends ErrorDetail implements Serializable {

    private static final long serialVersionUID = -3341256509080251708L;

    private T data;

    public static <T> ApiResult<T> success(T data) {
        ApiResult<T> apiResult = new ApiResult<>();
        apiResult.setCode(ApiCode.BizHandleCode.SUCCESS.getCode())
                .setMessage(ApiCode.BizHandleCode.SUCCESS.getMessage());
        apiResult.setData(data);
        return apiResult;
    }

    public static ApiResult<Void> success() {
        ApiResult<Void> apiResult = new ApiResult<>();
        apiResult.setCode(ApiCode.BizHandleCode.SUCCESS.getCode())
                .setMessage(ApiCode.BizHandleCode.SUCCESS.getMessage());
        return apiResult;
    }
}
