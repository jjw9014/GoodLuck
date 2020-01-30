package com.help.server.common;

public enum ResultCodeEnum {
    SUCCESS("200", "成功"),
    ERROR("500", "系统错误"),
    SUGGUEST_USER_ID_IS_NULL("101", "提交反馈未绑定用户"),
    SUGGUEST_REMARK_IS_NULL("102", "提交反馈填写为空"),

    QUESTION_TAG_IS_NULL("201", "问题标签未选择"),
    QUESTION_PUB_USER_IS_NULL("202", "发布人信息为空"),
    QUESTION_REMARK_IS_NULL("203", "问题描述为空"),
    QUESTION_NUMBER_IS_NULL("204", "问题编号为空"),
    QUESTION_NUMBER_NOT_EXIST("205", "问题编号不存在"),
    QUESTION_AUDIT_STATE_IS_NULL("206", "审核状态为空"),
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
