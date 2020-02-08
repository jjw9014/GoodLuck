package com.help.server.common;

public enum AuditStateEnum {
    AUDITING("auditing", "正在审核"),
    AUIDTED("audited", "️审核通过"),
    AUDITED_FAILURE("audited_failure", "审核驳回"),
    VERIFIED("verified", "验证通过"),
    VERIFIED_FAILURE("verified_failure", "验证驳回"),
    ;

    private String code;
    private String desc;

    AuditStateEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
