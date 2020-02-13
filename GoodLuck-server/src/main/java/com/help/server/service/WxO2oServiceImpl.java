package com.help.server.service;

import com.alibaba.fastjson.JSONObject;
import com.help.server.common.AuthUtil;
import com.help.server.common.HttpClientUtil;
import com.help.server.common.JedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 微信登录校验相关接口实现
 */
@Service
@Slf4j
public class WxO2oServiceImpl implements WxO2oService {

    @Override
    public String getAccessToken() {
        String token = JedisUtils.get("MINI_PRO_TOKEN");
        if(token == null){
            String url = AuthUtil.MINI_PRO_TOKEN_URL + "?grant_type=client_credential&appid=" + AuthUtil.MINI_PRO_APP_ID + "&secret=" + AuthUtil.MINI_PRO_APP_SECRET;
            JSONObject jsonObject = HttpClientUtil.doGetJson(url);
            if(jsonObject != null){
                token =  jsonObject.getString("access_token");
                if(token != null ){
                    int expiresIn = jsonObject.getIntValue("expires_in") - 300;
                    JedisUtils.set("MINI_PRO_TOKEN",token,expiresIn);
                }else{
                    String errMsg = jsonObject.getString("errmsg");
                    log.error(errMsg);
                }
            }
        }
        return token;
    }
}
