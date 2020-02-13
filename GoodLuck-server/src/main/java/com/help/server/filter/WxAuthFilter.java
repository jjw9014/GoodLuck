
package com.help.server.filter;

import java.io.IOException;
import java.util.*;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.help.server.common.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * 微信授权登录过滤器
 *
 * @Author LY
 * @Date 2020/2/2 10:00
 * @Version 1.0
 */
@Component
@Slf4j
@WebFilter(urlPatterns = "/*", filterName = "wxAuthFilter")
public class WxAuthFilter extends OncePerRequestFilter {

    private static final List<String> FILTER_URL_LIST ;
    private static final List<String> SYS_UN_FILTER_URL_LIST;

    static{
        String[] FILTER_URL = CommonConfigUtil.getValue("wx_auth_url_array").split(",");
        FILTER_URL_LIST = Arrays.asList(FILTER_URL);

        String[] SYS_UN_FILTER_URL = CommonConfigUtil.getValue("sys_un_filter_url_array").split(",");
        SYS_UN_FILTER_URL_LIST = Arrays.asList(SYS_UN_FILTER_URL);
    }

    private static final String SYS_LOGIN_URL = "/login";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    	// 请求的uri
        String uri = request.getRequestURI();

        //系统名称，若sysName为sys_goodluck，则为后端管理系统
        String sysName = request.getParameter("system");
        if (sysName != null && sysName.equals("sys_goodluck")) {
            if (SYS_UN_FILTER_URL_LIST.contains(uri)) {
                filterChain.doFilter(request, response);
            } else {
                // 执行过滤
                if (!LoginHelper.isLogin()) {
                    response.sendRedirect(SYS_LOGIN_URL);
                } else {
                    // 如果session中存在登录者实体，则继续
                    filterChain.doFilter(request, response);
                }
            }
        } else {
            if (FILTER_URL_LIST.contains(uri)) {
                String openId = AuthUtil.getAuthOpenIdFromCookie(request);
                if (openId != null) {
                    Object user = JedisUtils.getObject(openId);
                    if (user != null) {
                        filterChain.doFilter(request, response);
                    } else {
                        log.info("没有openid对应的记录，用户未授权,即将进入微信授权页面");
                        response.sendRedirect(AuthUtil.WX_AUTH_URL);
                    }
                } else {
                    log.info("openid不存在，用户未授权,即将进入微信授权页面");
                    response.sendRedirect(AuthUtil.WX_AUTH_URL);
                }
            } else {
                filterChain.doFilter(request, response);
            }
        }
    }




}
