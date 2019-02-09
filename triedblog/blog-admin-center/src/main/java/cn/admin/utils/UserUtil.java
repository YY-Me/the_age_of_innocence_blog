package cn.admin.utils;

import java.util.Map;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

import com.alibaba.fastjson.JSONObject;

import cn.commons.dto.LoginUser;

/**
 * 获取当前登录用户
 * 
 * @author 偶尔有点困
 * @date 2018年5月23日
 */
public class UserUtil {

	@SuppressWarnings("rawtypes")
	public static LoginUser getCurrentUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication instanceof OAuth2Authentication) {
			OAuth2Authentication oAuth2Auth = (OAuth2Authentication) authentication;
			authentication = oAuth2Auth.getUserAuthentication();

			if (authentication instanceof UsernamePasswordAuthenticationToken) {
				UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) authentication;
				Map map = (Map) authenticationToken.getDetails();
				map = (Map) map.get("principal");

				return JSONObject.parseObject(JSONObject.toJSONString(map), LoginUser.class);
			}
		}

		return null;
	}
}