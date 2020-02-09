package com.help.server.service;

import com.help.server.model.Tuser;
import java.util.Map;
import java.util.Set;

/**
 * 用户管理接口
 * @Author LY
 * @Date 2020/1/30 11:18
 * @Version 1.0
 **/
public interface TuserService {

    /**
     * 获取用户信息
     * @param openId
     * @return
     */
    Tuser getUserInfoByOpenId(String openId);

    /**
     * 添加微信登录用户
     * @param tuser
     * @return
     */
    boolean addWxUserInfo(Tuser tuser);

    /**
     * 编辑用户信息
     * @param tuser 用户信息
     * @return
     */
    boolean editUserInfo(Tuser tuser);

    /**
     * 通过ids获取用户集合
     * @param set
     * @return
     */
    Map<String,Tuser> list(Set<String> set);

    /**
     * 获取用户信息转为集合
     * @param openId
     * @return
     */
    Map<String,Tuser> getUserInfoToMap(String openId);

}
