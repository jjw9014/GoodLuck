package com.help.server.service;

import com.help.api.ResultDTO;
import com.help.api.TuserPageParam;
import com.help.api.TuserParam;
import com.help.server.model.Tuser;

import java.util.List;
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
    TuserParam getUserInfoByOpenId(String openId);

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
    Map<String,TuserParam> list(Set<String> set);

    /**
     * 获取用户信息转为集合
     * @param openId
     * @return
     */
    Map<String,TuserParam> getUserInfoToMap(String openId);

    /**
     * 注册
     * @param tuser 用户信息
     * @return
     */
    void register(Tuser tuser);

    /**
     * 通过条件查询用户集合
     * @param pageParam
     * @return
     */
    ResultDTO getListByConditon(TuserPageParam pageParam);

    /**
     * 获取用户列表
     * @param pageParam
     * @return
     */
    List<TuserParam> getUserList(TuserPageParam pageParam);

    /**
     * 身份认证
     * @param userId 用户id
     * @param identityType 身份类别
     * @return
     */
    boolean indentityUser(String userId,int identityType);





}
