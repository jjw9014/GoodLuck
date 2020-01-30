package com.help.server.service;

import com.help.api.ResultDTO;

/**
 * 用户管理接口
 * @Author LY
 * @Date 2020/1/30 11:18
 * @Version 1.0
 **/
public interface TuserService {

    /**
     * 登录
     * @param wxId
     * @return
     */
    ResultDTO login(String wxId);

    /**
     * 编辑用户信息
     * @param wxId 微信id
     * @param mobile 手机号
     * @param name 姓名
     * @return
     */
    ResultDTO editUserInfo(String wxId, String mobile, String name);
}
