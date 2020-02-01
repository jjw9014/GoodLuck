package com.help.server.common;

public enum TagEnum {
    Material_Shortage("A", "物资匮乏"),
    SHORT_HANDED("B", "人手不足"),
    WORK_DIFFICULT("C", "上班困难"),
    HOME_DIFFICULT("D", "回家困难"),
    MEDICAL_DIFFICULT("E", "就医困难"),
    SHORT_MASK("F", "个人缺口罩"),
    OTHER("Z", "其他"),
    ;

    private String code;
    private String desc;

    TagEnum(String code, String desc) {
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
