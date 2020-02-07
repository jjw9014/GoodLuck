package com.help.server.service;

import com.help.api.ResultDTO;
import com.help.server.common.LoginHelper;
import com.help.server.common.ResultHandler;
import com.help.server.common.RsaUtils;
import com.help.server.dao.SysUserMapper;
import com.help.server.model.SysUser;
import com.help.server.model.SysUserExample;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.interfaces.RSAPrivateKey;
import java.util.Date;
import java.util.List;

/**
 * 用户管理服务
 */
@Service
@Slf4j
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public ResultDTO login(String userName, String password) {
        SysUserExample example = new SysUserExample();
        SysUserExample.Criteria criteria = example.createCriteria();

        if (StringUtils.isNotEmpty(userName)) {
            criteria.andUserNameEqualTo(userName);
        }
        List<SysUser> userList =  sysUserMapper.selectByExample(example);
        if(userList.isEmpty()){
            return ResultHandler.createErrorResult("用户名或者密码错误");
        }
        SysUser user = userList.get(0);
        String pwd = password;
        //密码加密
        try {
            RSAPrivateKey privateKey = RsaUtils.getDefaultPrivateKey();
            pwd = StringUtils.reverse(RsaUtils.decrypt(privateKey, password));
        } catch (Exception e) {
            log.error("登录用户密码解密失败", e);
            return ResultHandler.createErrorResult("登录异常");
        }
        String mm = DigestUtils.md5Hex(pwd);
        if (!DigestUtils.md5Hex(pwd).equals(user.getPassword())) {
            log.info("用户：{},密码输入错误" + DigestUtils.md5Hex(pwd),userName);
            return ResultHandler.createErrorResult("用户名或者密码错误");
        }else {
            LoginHelper.setLoginUser(user);
            user.setLoginTime(new Date());
            sysUserMapper.updateByPrimaryKeySelective(user);
            return ResultHandler.handleSuccess(null);
        }
    }

    @Override
    public boolean addSysUser(String userName, String password) {
        SysUser user = new SysUser();
        user.setUserName(userName);
        user.setPassword(DigestUtils.md5Hex(password));
        user.setCreateTime(new Date());
        try{
            int addNum = sysUserMapper.insertSelective(user);
            if(addNum > 0){
                return true;
            }else{
                return false;
            }
        }catch (Exception e){
            log.error("添加用户失败",e);
            return false;
        }

    }

    @Override
    public boolean editSysUser(String userName, String password) {
        SysUserExample example = new SysUserExample();
        SysUserExample.Criteria criteria = example.createCriteria();

        if (StringUtils.isNotEmpty(userName)) {
            criteria.andUserNameEqualTo(userName);
        }
        List<SysUser> userList =  sysUserMapper.selectByExample(example);
        if(userList.isEmpty()){
            log.error("没有该用户，username:{}",userList);
            return false;
        }
        SysUser user = userList.get(0);
        user.setUpdateTime(new Date());
        user.setPassword(DigestUtils.md5Hex(password));
        int updateNum = sysUserMapper.updateByPrimaryKey(user);
        if(updateNum > 0){
            return true;
        }else{
            log.error("更新密码失败，username:{},userName",password);
            return false;
        }

    }
}
