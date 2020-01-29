package com.help.server.common;

import com.help.api.ResultDTO;

public class ResultHandler {

    public static <T> ResultDTO<T> handleSuccess(String msg, T data) {
        return handleSuccessWithCount(msg, data, null);
    }

    public static <T> ResultDTO<T> handleSuccessWithCount(String msg, T data, Integer count) {
        ResultDTO<T> ret = new ResultDTO<>();
        ret.setCode(ResultCodeEnum.SUCCESS.getCode());
        ret.setSuccess(true);
        ret.setMessage(msg);
        ret.setData(data);
        ret.setCount(count);
        return ret;
    }

    public static <T> ResultDTO<T> handleError(String msg, T data) {
        ResultDTO<T> ret = new ResultDTO<>();
        ret.setCode(ResultCodeEnum.ERROR.getCode());
        ret.setMessage(msg);
        ret.setSuccess(false);
        ret.setData(data);
        return ret;
    }

    public static <T> ResultDTO<T> handleError(ResultCodeEnum responseEnum) {
        ResultDTO<T> ret = new ResultDTO<>();
        ret.setCode(responseEnum.getCode());
        ret.setMessage(responseEnum.getMessage());
        ret.setSuccess(false);
        return ret;
    }
    public static <T> ResultDTO<T> handleException(String msg, T data, Throwable e) {
        ResultDTO<T> ret = new ResultDTO<>();
        ret.setCode(ResultCodeEnum.ERROR.getCode());
        ret.setSuccess(false);
        ret.setMessage(null == msg ? e.getMessage() : msg);
        ret.setData(data);
        return ret;

    }

    public static <T> ResultDTO<T> handleSuccess(Object data) {
        return new ResultDTO(ResultCodeEnum.SUCCESS.getCode(), ResultCodeEnum.SUCCESS.getMessage(), data,true, null);
    }

    public static <T> ResultDTO<T> handleSuccessWithCount(Object data, Integer count) {
        return new ResultDTO(ResultCodeEnum.SUCCESS.getCode(), ResultCodeEnum.SUCCESS.getMessage(), data,true, count);
    }

    public static <T> ResultDTO<T> handleFailure(ResultException e) {
        return new ResultDTO(e.getCode(), e.getMessage(), null, false);
    }

    public static <T>ResultDTO<T> handleException(Throwable t) {
        return new ResultDTO(ResultCodeEnum.ERROR.getCode(),
                (t.getCause()==null) ? t.getMessage() : t.getCause().getMessage(),
                null,
                false);
    }

    public static <T> ResultDTO<T> createErrorResult(String msg) {
        ResultDTO<T> result = new ResultDTO<>();
        result.setData(null);
        result.setMessage(msg);
        result.setSuccess(false);
        result.setCode(ResultCodeEnum.ERROR.getCode());
        return result;
    }

    public static <T> ResultDTO<T> createErrorResult(String msg, T data, Class<T> clazz) {

        ResultDTO<T> result = new ResultDTO<>();
        result.setData(data);
        result.setMessage(msg);
        result.setSuccess(false);
        result.setCode(ResultCodeEnum.ERROR.getCode());
        return result;
    }

    public static <T> ResultDTO<T> createExceptionResult(String msg, Throwable e) {
        ResultDTO<T> result = new ResultDTO<>();
        result.setMessage(msg + e.getMessage());
        result.setData(null);
        result.setSuccess(false);
        result.setCode(ResultCodeEnum.ERROR.getCode());
        // result.setException(e);
        return result;
    }

    public static <T> ResultDTO<T> createSuccessResult(String msg, T data, Class<T> clazz) {
        ResultDTO<T> result = new ResultDTO<>();
        result.setMessage(msg);
        result.setSuccess(true);
        result.setData(data);
        result.setCode(ResultCodeEnum.SUCCESS.getCode());
        return result;
    }
}
