package cn.web.service.impl;

import java.net.URI;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.UriComponentsBuilder;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import cn.web.dto.BlogQQLoginUser;
import cn.web.entity.BlogQQUser;
import cn.web.service.BlogQQUserService;

@Service("qqLoginServiceImpl")
@Configuration
public class QQLoginServiceImpl extends DefaultLoginServiceImpl {

	@Autowired
	private BlogQQUserService blogQQUserService;

	private Logger logger = LoggerFactory.getLogger(QQLoginServiceImpl.class);
	// 获取授权码
	private final static String AUTHORIZATION_URL = "https://graph.qq.com/oauth2.0/authorize?response_type=code&client_id=%s&redirect_uri=%s&state=test&scope=%s";
	// 获取Access Token的URL
	private final static String ACCESS_TOKEN_URL = "https://graph.qq.com/oauth2.0/token?grant_type=authorization_code&client_id=%s&client_secret=%s&code=%s&redirect_uri=%s";

	// 获取用户 OpenID 的 URL
	private static final String OPEN_ID_URL = "https://graph.qq.com/oauth2.0/me?access_token=%s";

	// 上面三部完成后就可以获取用户的信息
	// 获取用户信息的 URL，oauth_consumer_key 为 apiKey
	private static final String USER_INFO_URL = "https://graph.qq.com/user/get_user_info?access_token=%s&oauth_consumer_key=%s&openid=%s";

	// 下面的属性可以通过配置读取
	@Value("${oauth2.qq.callback_url}")
	private static String CALLBACK_URL; // QQ 在登陆成功后回调的 URL，这个
										// 必须在 QQ
	@Value("${oauth2.qq.api_key}") // 互联里填写过
	private static String API_KEY; // QQ 互联应用管理中心的 APP ID

	@Value("${oauth2.qq.api_secret}")
	private static String API_SECRET; // QQ 互联应用管理中心的 APP Key

	@Value("${oauth2.qq.scope}")
	private static String SCOPE; // QQ 互联的 API 接口，访问用户资料

	@Override
	public String getAuthorizationUrl() {
		String url = "";
		try {
			url = String.format(AUTHORIZATION_URL, API_KEY, CALLBACK_URL, SCOPE);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return url;
	}

	/**
	 * 根据授权码code获取Access Token
	 */
	@Override
	public String getAccessToken(String code) {
		String url = String.format(ACCESS_TOKEN_URL, API_KEY, API_SECRET, code, CALLBACK_URL);
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
		URI uri = builder.build().encode().toUri();
		String resp = getRestTemplate().getForObject(uri, String.class);
		logger.info("getAccessToken by code:{},response:{}", code, resp);
		if (resp.contains("access_token")) {
			Map<String, String> map = getParam(resp);
			String access_token = map.get("access_token");
			return access_token;
		} else {
			return null;
		}
	}

	/**
	 * 根据accessToken获取用户唯一标识openid
	 */
	@Override
	public String getOpenId(String accessToken) {
		String url = String.format(OPEN_ID_URL, accessToken);
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
		URI uri = builder.build().encode().toUri();

		String resp = getRestTemplate().getForObject(uri, String.class);
		logger.info("getOpenId by accessToken:{},response:{}", accessToken, resp);
		if (resp.contains("openid")) {
			JSONObject jsonObject = ConvertToJson(resp);
			String openid = jsonObject.getString("openid");
			return openid;
		} else {
			return null;
		}
	}

	/**
	 * 获取用户信息
	 */
	@Override
	public BlogQQUser getUserInfo(String accessToken, String openId) {
		openId = getOpenId(accessToken);
		String url = String.format(USER_INFO_URL, accessToken, API_KEY, openId);
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
		URI uri = builder.build().encode().toUri();

		String resp = getRestTemplate().getForObject(uri, String.class);
		JSONObject data = JSONObject.parseObject(resp);
		logger.info("getUserInfo by accessToken:{} and openId:{},response:{}", accessToken, openId, resp);
		// status:0:禁用，1:正常
		BlogQQLoginUser blogQQLoginUser = new BlogQQLoginUser();
		blogQQLoginUser.setOpenid(openId);
		blogQQLoginUser.setNickname(data.getString("nickname"));
		blogQQLoginUser.setArea(data.getString("province"));
		blogQQLoginUser.setFigureurlQq1(data.getString("figureurl_qq_1").replaceAll("http://", "https://"));
		blogQQLoginUser.setGender(data.getString("gender"));
		blogQQLoginUser.setStatus("1");
		blogQQLoginUser.setAccessToken(accessToken);
		blogQQLoginUser.setUpdateTime(new Date());
		return blogQQLoginUser;
	}

	@Override
	public String refreshToken(String code) {
		// TODO Auto-generated method stub
		return null;
	}

	// 由于QQ的几个接口返回类型不一样，此处是获取key-value类型的参数
	private Map<String, String> getParam(String string) {
		Map<String, String> map = new HashMap<>();
		String[] kvArray = string.split("&");
		for (int i = 0; i < kvArray.length; i++) {
			String[] kv = kvArray[i].split("=");
			map.put(kv[0], kv[1]);
		}
		return map;
	}

	// QQ接口返回类型是text/plain，此处将其转为json
	public JSONObject ConvertToJson(String string) {
		string = string.substring(string.indexOf("(") + 1, string.length());
		string = string.substring(0, string.indexOf(")"));
		logger.error("ConvertToJson s = " + string);
		JSONObject jsonObject = JSONObject.parseObject(string);
		return jsonObject;
	}

	/**
	 * 检查qq用户是否在本地，没有则插入
	* @Title: checkQQUserIsLocal
	* @param @param blogQQUser
	* @return void
	* @throws
	 */
	@Transactional
	@Async
	@Override
	public void checkQQUserIsLocal(BlogQQUser blogQQUser) {
		BlogQQUser user = blogQQUserService.checkOppenId(blogQQUser.getOpenid());
		if (StringUtils.isNotBlank(blogQQUser.getOpenid())) {
			if (null == user) {
				// 数据插入本地
				blogQQUserService.insert(blogQQUser);
			} else {
				// 更新数据
				blogQQUserService.updateByPrimaryKeySelective(blogQQUser);
			}
			logger.info("登录用户:{}", JSON.toJSONString(blogQQUser));
		}
	}
}
