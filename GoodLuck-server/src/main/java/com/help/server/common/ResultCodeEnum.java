package com.help.server.common;

public enum ResultCodeEnum {
    SUCCESS("200", "成功"),
    ERROR("500", "系统错误"),
    SUGGUEST_USER_ID_IS_NULL("101", "提交反馈未绑定用户"),
    SUGGUEST_REMARK_IS_NULL("101", "提交反馈填写为空"),
    ;
    private String code;
    private String message;

    ResultCodeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
