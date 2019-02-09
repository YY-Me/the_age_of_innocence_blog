package cn.web.controller;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.commons.common.PublicResultJosn;
import cn.commons.utils.CookieUtils;
import cn.commons.utils.MiniConstant;
import cn.web.entity.BlogQQUser;
import cn.web.plugin.spring.CacheRemove;
import cn.web.service.LoginService;
import cn.web.utils.RedisUtil;

@Controller
@RequestMapping("/oauth2")
public class QQLoginController {

	@Autowired
	private LoginService qqLoginServiceImpl;

	@Autowired
	private RedisUtil redisUtil;

	/**
	 * 返回登录地址，前台调用即可
	 * 
	 * @return
	 * @throws IOException
	 */
	@GetMapping("/qqLogin")
	public void qqLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String url = "";
		try {
			url = qqLoginServiceImpl.getAuthorizationUrl();
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.sendRedirect(url);
	}

	/**
	 * qq退出登录
	 * 
	 * @return
	 * @throws IOException
	 */
	@PostMapping("/qqLogOut")
	@ResponseBody
	public PublicResultJosn qqLogOut(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String accessToken = CookieUtils.getCookieValue(request, "accessToken", true);
		// 清除cookie
		CookieUtils.deleteCookie(request, response, "accessToken");
		CookieUtils.deleteCookie(request, response, "nickname");
		CookieUtils.deleteCookie(request, response, "figureurlQq1");
		// 清除redis
		redisUtil.del(MiniConstant.getQQaccessTokenKey(accessToken));
		return new PublicResultJosn(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), "退出登录");
	}

	// qq授权后会回调此方法，并将code传过来
	@RequestMapping("/qqCode")
	@CacheRemove(value = "blogWebCenter", key = { "listRecentUser" })
	public void getQQCode(String code, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (StringUtils.isBlank(code)) {
			return;
		}
		// 根据code获取token
		String accessToken = qqLoginServiceImpl.getAccessToken(code);
		// 根据openId判断用户是否已经绑定过
		String openId = qqLoginServiceImpl.getOpenId(accessToken);
		BlogQQUser userInfo = (BlogQQUser) qqLoginServiceImpl.getUserInfo(accessToken, openId);
		// 判断是否存在本地数据库
		qqLoginServiceImpl.checkQQUserIsLocal(userInfo);
		// 用户信息存储到redis
		redisUtil.set(MiniConstant.getQQaccessTokenKey(accessToken), userInfo, (long) 7776000);
		// 保存 accessToken 到 cookie，过期时间为 30 天，便于以后使用
		Cookie cookie = new Cookie("accessToken", accessToken);
		cookie.setMaxAge(7776000);
		cookie.setPath("/");
		response.addCookie(cookie);
		//CookieUtils.setCookie(request, response, "accessToken", accessToken, 7776000);
		Cookie cookie1 = new Cookie("nickname", URLEncoder.encode(userInfo.getNickname(), "utf-8"));
		cookie1.setMaxAge(7776000);
		cookie1.setPath("/");
		response.addCookie(cookie1);
		//CookieUtils.setCookie(request, response, "nickname", userInfo.getNickname(), 7776000, true);
		Cookie cookie2 = new Cookie("figureurlQq1", userInfo.getFigureurlQq1());
		cookie2.setMaxAge(7776000);
		cookie2.setPath("/");
		response.addCookie(cookie2);
		//CookieUtils.setCookie(request, response, "figureurlQq1", userInfo.getFigureurlQq1(), 7776000);
		response.sendRedirect("https://www.bblog.vip");
	}

}
