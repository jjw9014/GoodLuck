package com.help.server.model;

import java.io.Serializable;

public class Area implements Serializable {
    private Integer id;

    private String areaCode;

    private String areaName;

    private String areaParentCode;

    private Integer areaType;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getAreaParentCode() {
        return areaParentCode;
    }

    public void setAreaParentCode(String areaParentCode) {
        this.areaParentCode = areaParentCode;
    }

    public Integer getAreaType() {
        return areaType;
    }

    public void setAreaType(Integer areaType) {
        this.areaType = areaType;
    }
}