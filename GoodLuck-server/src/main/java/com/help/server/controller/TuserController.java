package com.help.server.controller;

import com.help.api.ResultDTO;
import com.help.server.common.AuthUtil;
import com.help.server.common.ResultHandler;
import com.help.server.model.Tuser;
import com.help.server.model.WxUserInfo;
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
    public ResultDTO editUserInfo(Tuser user) {
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
    public ResultDTO addWxUserInfo(WxUserInfo user) {
        Tuser tuser= user.wxUserToTuser();
        boolean result = tuserService.addWxUserInfo(tuser);
        if(result){
            return ResultHandler.handleSuccess("添加用户成功",tuser);
        }else{
            return ResultHandler.createErrorResult("添加用户失败，请稍后重试");
        }
    }


}
