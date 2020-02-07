package com.help.server.common;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.net.URLEncoder;

/**
 * 微信授权工具类
 */
public class AuthUtil {
    /**
     * 微信授权APPID
     */
    public static final String APP_ID = CommonConfigUtil.getValue("wx_app_id");
    /**
     * 微信授权APPSECRET
     */
    public static final String APP_SECRET = CommonConfigUtil.getValue("wx_app_secret");

    /**
     * 微信授权url前缀
     */
    public static final String WX_AUTH_URL_PREFIX = "https://open.weixin.qq.com/connect/qrconnect";

    /**
     * 微信授权后返回url
     */
    public static final String BAKUP_URL = CommonConfigUtil.getValue("wx_backup_url");;


    /**
     * 微信授权url
     */
    public static final String WX_AUTH_URL =  WX_AUTH_URL_PREFIX + "?appid=" + APP_ID + "&redirect_uri=" + URLEncoder.encode(BAKUP_URL)
            + "&response_type=code" + "&scope=snsapi_login" + "&state=STATE#wechat_redirect";

    //public static final String WX_AUTH_URL = "http://192.168.43.216:8081/index.html";

    /**
     * 微信授权后获取access_token url
     */
    public static final String WX_ACCESS_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/access_token";

    /**
     * 微信授权后刷新access_token url
     */
    public static final String WX_REFRESH_ACCESS_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/refresh_token";

    /**
     * 微信获取access_token后获取用户信息url
     */
    public static final String WX_USER_INFO_URL = "https://api.weixin.qq.com/sns/userinfo";


    public static final String WX_AUTH_SUCCESS_URL = CommonConfigUtil.getValue("wx_auth_success_url");;
    /**
     * 从cookie获取openId
     * @param request
     * @return
     */
    public static String getAuthOpenIdFromCookie(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        String openId =  null;
        if(cookies != null){
            for (Cookie cookie:cookies) {
                if(cookie.getName().equals("wx_openid")){
                    openId = cookie.getValue();
                    break;
                }
            }
        }
        return openId;
    }

}
