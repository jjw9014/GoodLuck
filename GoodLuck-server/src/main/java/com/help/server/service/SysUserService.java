package com.help.server.service;

import com.help.api.ResultDTO;

/**
 * 系统用户服务接口
 */
public interface SysUserService {

    /**
     * 后台用户登录
     * @param userName 用户名
     * @param password 密码
     * @return
     */
    ResultDTO login(String userName, String password);

    /**
     * 添加用户
     * @param userName 用户名
     * @param password 密码
     * @return
     */
    boolean addSysUser(String userName, String password);

    /**
     * 修改用户
     * @param userName 用户名
     * @param password 密码
     * @return
     */
    boolean editSysUser(String userName, String password);




}
