package com.voda.springbootapicaching.model.common;

import com.voda.springbootapicaching.enumeration.ResponseMessage;
import com.voda.springbootapicaching.enumeration.StatusCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class DefaultRes<T>
{
    private int status;
    private String message;
    private T data;

    public DefaultRes(final StatusCode status, final ResponseMessage message)
    {
        this(status.getCode(), message.getMessage());
    }

    public DefaultRes(final int status, final String message) {
        this.status = status;
        this.message = message;
        this.data = null;
    }

    public static<T> DefaultRes<T> res(StatusCode status, ResponseMessage message)
    {
        return res(status.getCode(), message.getMessage(), null);
    }

    public static<T> DefaultRes<T> res(int status, String message)
    {
        return res(status, message, null);
    }

    public static<T> DefaultRes<T> res(final StatusCode status, final ResponseMessage message, final T t)
    {
        return res(status.getCode(), message.getMessage(), t);
    }

    public static<T> DefaultRes<T> res(final int status, final String message, final T t)
    {
        return DefaultRes.<T>builder()
                .data(t)
                .status(status)
                .message(message)
                .build();
    }

    public static final DefaultRes HIT = new DefaultRes(StatusCode.BAD_REQUEST, ResponseMessage.BAD_REQUEST);

    public static final DefaultRes FAIL_DEFAULT_RES = new DefaultRes(StatusCode.INTERNAL_SERVER_ERROR, ResponseMessage.INTERNAL_SERVER_ERROR);
}
