package com.help.server.model;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.UUID;

/**
 * 微信用户信息
 */
public class WxUserInfo implements Serializable {

    private static final long serialVersionUID = 1L;
    private String id;
    private String code;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Tuser wxUserToTuser(){
        Tuser user = new Tuser();
        if(StringUtils.isNotBlank(this.getId())){
            user.setId(this.getId());
        }else{
            user.setId( UUID.randomUUID().toString().replaceAll("-",""));
        }
        user.setNickName(this.getNickName());
        user.setSex(this.getGender());
        user.setHeadImgUrl(this.getAvatarUrl());
        user.setCountry(this.getCountry());
        user.setProvince(this.getProvince());
        user.setCity(this.getCity());
        return user;
    }
}
