package com.help.server.model;

import java.io.Serializable;
import java.util.UUID;

/**
 * 微信用户信息
 */
public class WxUserInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nickName;
    private int gender;
    private String avatarUrl;
    private String country;
    private String province;
    private String city;

    public String getNickName() {
        return nickName;
    }

    public int getGender() {
        return gender;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public String getCountry() {
        return country;
    }

    public String getProvince() {
        return province;
    }

    public String getCity() {
        return city;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Tuser wxUserToTuser(){
        Tuser user = new Tuser();
        user.setId( UUID.randomUUID().toString().replaceAll("-",""));
        user.setNickName(this.getNickName());
        user.setSex(this.getGender());
        user.setHeadImgUrl(this.getAvatarUrl());
        user.setCountry(this.getCountry());
        user.setProvince(this.getProvince());
        user.setCity(this.getCity());
        return user;
    }
}
