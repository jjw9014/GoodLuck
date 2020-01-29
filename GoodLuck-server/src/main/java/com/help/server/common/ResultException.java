package com.help.server.common;


public class ResultException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private ResultCodeEnum resultCodeEnum;

    public ResultException(ResultCodeEnum resultCodeEnum, Throwable throwable) {
        super(throwable);
        this.resultCodeEnum = resultCodeEnum;
    }


    public ResultException(ResultCodeEnum resultCodeEnum) {
        super();
        this.resultCodeEnum = resultCodeEnum;
    }

    public String getCode() {
        return resultCodeEnum.getCode();
    }

    public String getMessage() {
        return resultCodeEnum.getMessage();
    }

    public ResultCodeEnum getResultCodeEnum() {
        return resultCodeEnum;
    }
}
