package com.help.server.controller;

import com.help.api.ResultDTO;
import com.help.server.common.LoginHelper;
import com.help.server.common.ResultHandler;
import com.help.server.common.RsaUtils;
import com.help.server.service.SysUserService;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.interfaces.RSAPublicKey;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(path = "/sysUser/")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    /**
     *
     * @return
     */
    @RequestMapping("/getPublicKey")
    public Object getPublicKey() {
        RSAPublicKey publicKey = RsaUtils.getDefaultPublicKey();
        Map<String,String> publicKeyMap = new HashMap<String,String>();
        publicKeyMap.put("modulus", new String(Hex.encodeHex(publicKey.getModulus().toByteArray())));
        publicKeyMap.put("exponent", new String(Hex.encodeHex(publicKey.getPublicExponent().toByteArray())));
        return publicKeyMap;
    }

    /**
     * 用户登录
     * @param userName 用户名
     * @param password 密码
     * @param request
     * @return
     */
    @RequestMapping(value = "/login")
    public ResultDTO login(String userName, String password, HttpServletRequest request) {
        if(StringUtils.isBlank(userName) || StringUtils.isBlank(password)){
            return ResultHandler.createErrorResult("用户名或密码不能为空");
        }
        return sysUserService.login(userName,password);
    }

    /**
     * 注销用户
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/logout")
    public void loginout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        LoginHelper.removeLoginUser();
        response.sendRedirect("/login");
    }

    /**
     * 添加用户
     * @param userName 用户名
     * @param password 密码
     * @return
     */
    @RequestMapping(value = "/addSysUser")
    public ResultDTO addSysUser(String userName, String password) {
        if(StringUtils.isBlank(userName) || StringUtils.isBlank(password)){
            return ResultHandler.createErrorResult("用户名或密码不能为空");
        }
        boolean result = sysUserService.addSysUser(userName,password);
        if(result){
            return ResultHandler.handleSuccess("添加用户成功",result);
        }else{
            return ResultHandler.createErrorResult("添加用户失败");
        }
    }

    /**
     * 修改用户名
     * @param userName 用户名
     * @param password 密码
     * @return
     */
    @RequestMapping(value = "/editSysUser")
    public ResultDTO editSysUser(String userName, String password) {
        if(StringUtils.isBlank(userName) || StringUtils.isBlank(password)){
            return ResultHandler.createErrorResult("用户名或密码不能为空");
        }
        boolean result = sysUserService.editSysUser(userName,password);
        if(result){
            return ResultHandler.handleSuccess("修改用户信息成功",result);
        }else{
            return ResultHandler.createErrorResult("修改用户信息失败");
        }
    }
}
