package com.help.api;

public class TuserGroupDTO {

    private Integer identityType;

    private String identity;

    private int count;

    public Integer getIdentityType() {
        return identityType;
    }

    public String getIdentity() {
        return identity;
    }

    public int getCount() {
        return count;
    }

    public void setIdentityType(Integer identityType) {
        this.identityType = identityType;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
