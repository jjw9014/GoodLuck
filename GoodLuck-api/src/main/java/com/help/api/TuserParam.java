package com.help.api;

import java.io.Serializable;
import java.util.Date;

public class TuserParam implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String name;

    private String nickName;

    private Integer sex;

    private String sexValue;

    private String birthday;

    private String province;

    private String city;

    private String country;

    private String wxNumber;

    private String mobile;

    private String headImgUrl;

    private String school;

    private String profession;

    private String unionId;

    private Integer identityType;

    private String identity;

    private Date createTime;

    private Date lastUpdateTime;

    private Date lastLoginTime;

    public String getName() {
        return name;
    }

    public String getNickName() {
        return nickName;
    }

    public Integer getSex() {
        return sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getProvince() {
        return province;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getWxNumber() {
        return wxNumber;
    }

    public String getMobile() {
        return mobile;
    }

    public String getHeadImgUrl() {
        return headImgUrl;
    }

    public String getSchool() {
        return school;
    }

    public String getProfession() {
        return profession;
    }

    public String getUnionId() {
        return unionId;
    }

    public Integer getIdentityType() {
        return identityType;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setWxNumber(String wxNumber) {
        this.wxNumber = wxNumber;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setHeadImgUrl(String headImgUrl) {
        this.headImgUrl = headImgUrl;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }

    public void setIdentityType(Integer identityType) {
        this.identityType = identityType;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getSexValue() {
        return sexValue;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setSexValue(String sexValue) {
        this.sexValue = sexValue;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
