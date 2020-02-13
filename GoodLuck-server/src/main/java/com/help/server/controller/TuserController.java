package com.help.server.controller;

import com.alibaba.fastjson.JSONObject;
import com.help.api.ResultDTO;
import com.help.server.common.AuthUtil;
import com.help.server.common.HttpClientUtil;
import com.help.server.common.ResultHandler;
import com.help.server.common.SenInfoCheckUtil;
import com.help.server.model.Tuser;
import com.help.server.model.WxUserInfo;
import com.help.server.service.SenInfoCheckService;
import com.help.server.service.TuserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


/**
 * 用户控制层接口
 * @Author LY
 * @Date 2020/1/30 11:00
 * @Version 1.0
 **/
@RestController
@RequestMapping(path = "/user/")
public class TuserController {

    @Autowired
    private TuserService tuserService;

    @Autowired
    private SenInfoCheckService senInfoCheckService;

    /**
     * 获取用户信息
     * @param openId
     * @return
     */
    @RequestMapping(value = "/get")
    public ResultDTO get(String openId, HttpServletRequest request) {
        if(openId == null){
            openId = AuthUtil.getAuthOpenIdFromCookie(request);
        }
        if(openId != null){
            Tuser tuser = tuserService.getUserInfoByOpenId(openId);
            if(tuser != null){
                return ResultHandler.handleSuccess(tuser);
            }else{
                return ResultHandler.createErrorResult("获取用户信息失败");
            }
        }else{
            return ResultHandler.createErrorResult("缺少用户标识信息，无法获取");
        }

    }

    /**
     * 用户信息修改
     * @param user 用户信息
     * @return
     */
    @RequestMapping(value = "/edit")
    public ResultDTO editUserInfo(Tuser user,HttpServletRequest request) {
        String content = SenInfoCheckUtil.makeUpParams(request);
        if(!senInfoCheckService.checkContent(content)){
            return ResultHandler.createErrorResult("输入内容非法");
        }

        boolean result = tuserService.editUserInfo(user);
        if(result){
            return ResultHandler.handleSuccess("修改用户信息成功",null);
        }else{
            return ResultHandler.createErrorResult("修改用户信息失败，请稍后重试");
        }
    }

    /**
     * 用户信息新增
     * @param user 用户信息
     * @return
     */
    @RequestMapping(value = "/addWxUserInfo")
    public ResultDTO register(WxUserInfo user) {
        if(user.getId() == null){
            String code = user.getCode();
            if(code == null){
                return ResultHandler.createErrorResult("登录code不存在");
            }
            String url = AuthUtil.MINI_PRO_LOGIN_VALIDATE_URL + "?appid=" + AuthUtil.MINI_PRO_APP_ID + "&secret="
                    + AuthUtil.MINI_PRO_APP_SECRET + "&js_code=" + code + "&grant_type=authorization_code";
            JSONObject json = HttpClientUtil.doGetJson(url);
            String openId = json.getString("openid");
            if(openId == null){
                return ResultHandler.createErrorResult(json.getString("errmsg"));
            }
            user.setId(openId);
        }

        Tuser tuser= user.wxUserToTuser();
        tuserService.register(tuser);
        if(tuser != null){
            return ResultHandler.handleSuccess("注册成功",tuser);
        }else{
            return ResultHandler.createErrorResult("注册失败，请稍后重试");
        }
    }


}
