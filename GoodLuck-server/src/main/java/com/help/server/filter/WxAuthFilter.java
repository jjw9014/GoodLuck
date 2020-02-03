
package com.help.server.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.help.server.common.AuthUtil;
import com.help.server.common.CommonConfigUtil;
import com.help.server.common.JedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * 微信授权登录过滤器
 * @Author LY
 * @Date 2020/2/2 10:00
 * @Version 1.0
 */
@Component
@Slf4j
@WebFilter(urlPatterns = "/*", filterName = "wxAuthFilter")
public class WxAuthFilter extends OncePerRequestFilter {

	private static final String[] FILTER_URL = CommonConfigUtil.getValue("wx_auth_url_array").split(",");

	private static final List<String> FILTER_URL_LIST = Arrays.asList(FILTER_URL);

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		// 请求的uri
		String uri = request.getRequestURI();
		if(FILTER_URL_LIST.contains(uri)){
			String openId =  AuthUtil.getAuthOpenIdFromCookie(request);
			if(openId != null){
				Object user = JedisUtils.getObject(openId);
				if(user != null){
					filterChain.doFilter(request, response);
				}else{
					log.info("没有openid对应的记录，用户未授权,即将进入微信授权页面");
					response.sendRedirect(AuthUtil.WX_AUTH_URL);
				}
			}else{
				log.info("openid不存在，用户未授权,即将进入微信授权页面");
				response.sendRedirect(AuthUtil.WX_AUTH_URL);
			}
		}else{
			filterChain.doFilter(request, response);
		}
	}

}
