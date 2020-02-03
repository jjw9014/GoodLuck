package com.help.server.model;

import java.io.Serializable;

/**
 * 微信token封装类
 */
public class WxToken implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 授权用户唯一标识
     */
    private String openId;

    /**
     * 接口调用凭证
     */
    private String accessToken;

    /**
     * access_token接口调用凭证超时时间，单位（秒）
     */
    private Integer expiresIn;

    /**
     * 用户刷新access_token
     */
    private String refreshToken;

    /**
     * 用户授权的作用域，使用逗号（,）分隔
     */
    private String scope;

    public WxToken(String openId, String accessToken, Integer expiresIn, String refreshToken, String scope) {
        this.openId = openId;
        this.accessToken = accessToken;
        this.expiresIn = expiresIn;
        this.refreshToken = refreshToken;
        this.scope = scope;
    }

    public String getOpenId() {
        return openId;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public Integer getExpiresIn() {
        return expiresIn;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public String getScope() {
        return scope;
    }
}
