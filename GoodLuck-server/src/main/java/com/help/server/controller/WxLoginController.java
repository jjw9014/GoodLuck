package com.help.server.controller;

import com.alibaba.fastjson.JSONObject;
import com.help.api.ResultDTO;
import com.help.server.common.AuthUtil;
import com.help.server.common.HttpClientUtil;
import com.help.server.common.JedisUtils;
import com.help.server.common.ResultHandler;
import com.help.server.model.Tuser;
import com.help.server.model.WxToken;
import com.help.server.service.TuserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * 微信授权登录
 * @Author LY
 * @Date 2020/2/2 11:00
 * @Version 1.0
 */
@RestController
@RequestMapping(path = "/o2o/")
@Slf4j
public class WxLoginController {

    @Autowired
    private TuserService tuserService;

    /**
     * 登录成功的回调函数
     *
     * @param request
     * @param response
     */
    @RequestMapping("/login")
    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String code = request.getParameter("code");
        if(null != code){
            // 通过code获取openid,access_token
            String url = AuthUtil.WX_ACCESS_TOKEN_URL + "?appid=" + AuthUtil.APP_ID + "&secret=" + AuthUtil.APP_SECRET
                    + "&code=" + code + "&grant_type=authorization_code";
            JSONObject jsonObject = HttpClientUtil.doGetJson(url);
            String openId =  jsonObject.getString("openid");
            String token = jsonObject.getString("access_token");
            String refreshToken =  jsonObject.getString("refresh_token");

            // 刷新access_token, refresh_token
            String refreshTokenUrl = AuthUtil.WX_REFRESH_ACCESS_TOKEN_URL + "?appid=" + AuthUtil.APP_ID + "&grant_type=refresh_token"
                    + "&refresh_token=" + refreshToken;
            JSONObject refreshTokenJson = HttpClientUtil.doGetJson(refreshTokenUrl);
            String  accessToken = refreshTokenJson.getString("access_token");
            int expiresIn = 30*24*60*60;

            String scope = refreshTokenJson.getString("scope");

            //将微信用户信息存入redis中
            WxToken wxToken = new WxToken(openId,accessToken,expiresIn,refreshToken,scope);
            JedisUtils.setObject(openId,wxToken,expiresIn);
            log.info("用户id：{} 登录成功，失效时长：{}",openId,expiresIn);

            //通过openid和access_token 获取用户信息
            String infoUrl = AuthUtil.WX_USER_INFO_URL + "?access_token=" + token + "&openid=" + openId + "&lang=zh_CN";
            JSONObject userInfo = HttpClientUtil.doGetJson(infoUrl);

            if( userInfo != null ){
                // 用户信息入库
                String nickName = userInfo.getString("nickname");
                int sex = userInfo.getIntValue("sex");
                String province = userInfo.getString("province");
                String city = userInfo.getString("city");
                String country = userInfo.getString("country");
                String headImgurl = userInfo.getString("headimgurl");
                String unionId = userInfo.getString("unionid");
                Date date = new Date();
                Tuser tuser = new Tuser(openId,nickName,sex,province,city,country,headImgurl,unionId,date);
                tuserService.addWxUserInfo(tuser);

                //添加cookie
                Cookie cookie = new Cookie("wx_openid",openId);
                cookie.setMaxAge(expiresIn);
                cookie.setPath("/");
                response.addCookie(cookie);

                // 这里是授权成功返回的页面
                response.sendRedirect(AuthUtil.WX_AUTH_SUCCESS_URL + "?code=1");
            }else{
                log.info("获取用户数据失败，跳转到失败页面");
                response.sendRedirect(AuthUtil.WX_AUTH_SUCCESS_URL + "?code=0");
            }
        }else{
            log.info("授权未成功，跳转到失败页面");
            response.sendRedirect(AuthUtil.WX_AUTH_SUCCESS_URL + "?code=0");
        }
    }

    /**
     * 登录成功的回调函数
     *
     * @param request
     * @param response
     */
    @RequestMapping("/getAccessToken")
    public ResultDTO getAccessToken(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String token = JedisUtils.get("MINI_PRO_TOKEN");
        if(token != null){
            return ResultHandler.handleSuccess(token);
        }else{
            String url = AuthUtil.MINI_PRO_TOKEN_URL + "?grant_type=client_credential&appid=" + AuthUtil.APP_ID + "&secret=" + AuthUtil.APP_SECRET;
            JSONObject jsonObject = HttpClientUtil.doGetJson(url);
            if(jsonObject != null){
                token =  jsonObject.getString("access_token");
                if(token != null ){
                    int expiresIn = jsonObject.getIntValue("expires_in");
                    JedisUtils.set("MINI_PRO_TOKEN",token,expiresIn);
                    return ResultHandler.handleSuccess(token);
                }else{
                    String errMsg = jsonObject.getString("errmsg");
                    return ResultHandler.createErrorResult( errMsg != null ? errMsg : "获取accessToken失败");
                }
            }else {
                return ResultHandler.createErrorResult("获取accessToken失败");
            }
        }
    }


}
