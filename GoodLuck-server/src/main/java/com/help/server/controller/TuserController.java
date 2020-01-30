package com.help.server.controller;

import com.help.api.ResultDTO;
import com.help.server.service.TuserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * 用户控制层接口
 * @Author LY
 * @Date 2020/1/30 11:00
 * @Version 1.0
 **/
@RestController
@RequestMapping(path = "/user/")
public class TuserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TuserController.class);

    @Autowired
    private TuserService tuserService;

    /**
     * 用户登录
     * @param wxId 微信id
     * @return
     */
    @RequestMapping(value = "/login")
    public ResultDTO login(String wxId) {
        return tuserService.login(wxId);
    }

    /**
     * 用户信息修改
     * @param wxId 微信id
     * @param mobile 手机号
     * @param name 姓名
     * @return
     */
    @RequestMapping(value = "/edit")
    public ResultDTO editUserInfo(String wxId,String mobile, String name) {
        return tuserService.editUserInfo(wxId,mobile,name);
    }
}
