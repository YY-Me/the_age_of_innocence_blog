package cn.oauth2.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.common.util.OAuth2Utils;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.commons.common.PublicResultJosn;
import cn.oauth2.feign.Oauth2Client;
import cn.oauth2.utils.RedisUtil;

/**
 * 登录
 * 
 * @author 偶尔有点困
 * @date 2018年5月23日
 */
@RestController
public class LoginController {

	@Autowired
	private ConsumerTokenServices tokenServices;

	@Autowired
	private Oauth2Client client;

	@Value("${spring.security.oauth2.client_secret}")
	private String oauth2_client_secret;

	@Resource
	private RedisUtil redisUtil;

	/**
	 * 密码模式登录
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	@PostMapping("/oauth2/login")
	public Map<String, Object> login(@RequestParam(required = true) String username,
			@RequestParam(required = true) String password, @RequestParam(required = true) String code,
			@RequestParam(required = true) String codeStr) {
		this.checkLoginVerifyCode(code, codeStr);
		Map<String, String> parameters = new HashMap<>();
		parameters.put(OAuth2Utils.GRANT_TYPE, "password");
		parameters.put(OAuth2Utils.CLIENT_ID, "system");
		parameters.put("client_secret", oauth2_client_secret);
		parameters.put(OAuth2Utils.SCOPE, "app");
		parameters.put("username", username);
		parameters.put("password", password);
		Map<String, Object> result = client.postAccessToken(parameters);
		return result;
	}

	/**
	 * 
	 * @Title: checkLoginVerifyCode   
	 * @Description: 校验登录验证码
	 * @param: @param code
	 * @param: @param codeStr      
	 * @return: void      
	 * @throws
	 */
	private void checkLoginVerifyCode(String code, String codeStr) {
		if (StringUtils.isBlank(code) || StringUtils.isBlank(codeStr)) {
			throw new IllegalArgumentException("请填写验证码");
		}
		//redis取验证码
		String verifyCode = (String) redisUtil.get(code);
		if (null == verifyCode) {
			throw new IllegalArgumentException("验证码过期");
		}
		if (!StringUtils.equals(codeStr.toUpperCase(), verifyCode.toUpperCase())) {
			throw new IllegalArgumentException("验证码错误");
		}
		//校验通过，删除验证码
		redisUtil.del(code);
	}

	/**
	 * 通过refresh_token刷新access_token
	 * 
	 * @param refresh_token
	 * @return
	 */
	@PostMapping("/oauth2/refresh")
	public Map<String, Object> refresh(String access_token, String refresh_token) {
		Map<String, String> parameters = new HashMap<>();
		parameters.put(OAuth2Utils.GRANT_TYPE, "refresh_token");
		parameters.put(OAuth2Utils.CLIENT_ID, "system");
		parameters.put("client_secret", oauth2_client_secret);
		parameters.put(OAuth2Utils.SCOPE, "app");
		parameters.put("refresh_token", refresh_token);
		Map<String, Object> result = client.postAccessToken(parameters);
		return result;
	}

	/**
	 * 退出登录，移除access_token和refresh_token
	 */
	/*    @PostMapping(value = "/oauth2/loginout", params = "access_token")
	public PublicResultJosn loginOut(Principal principal, String access_token, HttpServletRequest request,
	        HttpServletResponse response) {
	    OAuth2AccessToken accessToken = tokenStore.readAccessToken(access_token);
	    if (accessToken != null) {
	        // 移除access_token
	        tokenStore.removeAccessToken(accessToken);
	        // 移除refresh_token
	        if (accessToken.getRefreshToken() != null) {
	            tokenStore.removeRefreshToken(accessToken.getRefreshToken());
	        }
	    }
	    return new PublicResultJosn(200, "退出成功", "");
	}*/

	/**
	 * 退出登录
	 * 移除access_token和refresh_token<br>
	 * 改为用ConsumerTokenServices，该接口的实现类DefaultTokenServices已有相关实现，我们不再重复造轮子
	* @Title: loginOut
	* @param @param principal
	* @param @param access_token
	* @param @param request
	* @param @param response
	* @param @return
	* @return PublicResultJosn
	* @throws
	 */
	@PostMapping(value = "/oauth2/loginout", params = "access_token")
	public PublicResultJosn loginOut(Principal principal, String access_token, HttpServletRequest request,
			HttpServletResponse response) {
		tokenServices.revokeToken(access_token);
		return new PublicResultJosn(200, "退出成功", "");
	}

}
