package com.help.api;

import java.io.Serializable;
import java.util.List;

public class QuestionPageParam implements Serializable {
    private static final long serialVersionUID = 1L;

    private String tag;

    private String pubTimeStart;

    private String pubTimeEnd;

    private int starsMin;

    private int starsMax;

    private String state;

    private String auditState;

    private List<String> auditStates;

    private String number;

    private String pubUserId;

    private String province;

    private String city;

    private String district;

    private String street;

    private String mobile;

    private int pageSize;

    private int pageNo;

    private String orderBy;

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getPubTimeStart() {
        return pubTimeStart;
    }

    public void setPubTimeStart(String pubTimeStart) {
        this.pubTimeStart = pubTimeStart;
    }

    public String getPubTimeEnd() {
        return pubTimeEnd;
    }

    public void setPubTimeEnd(String pubTimeEnd) {
        this.pubTimeEnd = pubTimeEnd;
    }

    public int getStarsMin() {
        return starsMin;
    }

    public void setStarsMin(int starsMin) {
        this.starsMin = starsMin;
    }

    public int getStarsMax() {
        return starsMax;
    }

    public void setStarsMax(int starsMax) {
        this.starsMax = starsMax;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getAuditState() {
        return auditState;
    }

    public void setAuditState(String auditState) {
        this.auditState = auditState;
    }

    public List<String> getAuditStates() {
        return auditStates;
    }

    public void setAuditStates(List<String> auditStates) {
        this.auditStates = auditStates;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPubUserId() {
        return pubUserId;
    }

    public void setPubUserId(String pubUserId) {
        this.pubUserId = pubUserId;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }
}
