package com.help.server.common;

public enum StateEnum {
    RESOLVED("resolved", "已解决"), UNRESOLVED("unresolved", "未解决");

    private String code;
    private String desc;

    StateEnum(String code, String desc) {
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
