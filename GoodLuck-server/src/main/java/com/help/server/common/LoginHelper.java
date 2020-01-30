
package com.help.server.common;

import com.help.server.model.Tuser;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 登录帮助类
 * @Author LY
 * @Date 2020/1/30 11:00
 * @Version 1.0
 **/
public class LoginHelper {

	// 私有构造器
	private LoginHelper() {
	}

	/**
	 * 判断操作员是否登录
	 * 
	 * @return 已登录-true; 未登录-false
	 */
	public static boolean isLogin() {
		return getLoginUser() != null;
	}

	/**
	 * 获取登录操作员
	 * 
	 * <pre>
	 * 未登录则返回null</>
	 * 
	 * @return
	 */
	public static Tuser getLoginUser() {
		HttpSession session = getHttpSession();
		Tuser user = new Tuser();
		if (session != null) {
			user = (Tuser) session.getAttribute("user");
			if (user == null) {
				return null;
			}
		} else {
			return null;
		}

		return user;
	}

	/**
	 * 设置登录操作员
	 * 
	 * <pre>
	 * 未登录则返回null</>
	 * 
	 * @return
	 */
	public static void setLoginUser(Tuser user) {
		HttpSession session = getHttpSession();
		if (user != null) {
			session.setAttribute("user", user);
		}
	}

	/**
	 * 获取登录操作员
	 * 
	 * <pre>
	 * 未登录则返回null</>
	 * 
	 * @return
	 */
	public static String getUserId() {
		return getLoginUser().getId();
	}

	/**
	 * 删除登录信息
	 * 
	 * <pre>
	 * 未登录则返回null</>
	 * 
	 * @return
	 */
	public static void removeLoginUser() {
		HttpSession session = getHttpSession();
		if (session != null) {
			session.removeAttribute("user");
		}
	}

	/**
	 * 获取登录上下文
	 * 
	 * @return
	 */
	private static HttpSession getHttpSession() {
		if (RequestContextHolder.getRequestAttributes() != null) {
			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
			if (request != null) {
				return request.getSession();
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

}
