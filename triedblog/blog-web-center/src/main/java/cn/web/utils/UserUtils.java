package cn.web.utils;

import javax.servlet.http.HttpServletRequest;

import cn.commons.utils.CookieUtils;
import cn.commons.utils.MiniConstant;
import cn.web.entity.BlogQQUser;
import cn.web.entity.BlogUser;

public class UserUtils {

	/**
	 * 获取当前登录用户基本信息
	 * 
	 * @author 余勇
	 * @email 1396513066@qq.com
	 * @date 2018年7月1日
	 */
	public static Object getCurrent(HttpServletRequest request, RedisUtil redisUtil) {
		// cookie读取access token获取用户信息
		String accessToken = CookieUtils.getCookieValue(request, "accessToken", true);
		Object object = redisUtil.get(MiniConstant.getQQaccessTokenKey(accessToken));
		if (accessToken.startsWith("BLOG")) {
			return (BlogUser) object;
		}
		return (BlogQQUser) object;
	}
}
