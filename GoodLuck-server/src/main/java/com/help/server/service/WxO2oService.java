package com.help.server.service;

/**
 * 微信登录校验相关接口
 */
public interface WxO2oService {

    /**
     * 获取微信小程序accessToken
     * @return
     */
    String getAccessToken();
}
