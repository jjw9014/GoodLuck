package com.help.server.common;

public enum AuditStateEnum {
    AUDITING("auditing", "正在审核"),
    AUIDTED("audited", "已️审核"),
    VERIFIED("verified", "已验证"),
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
