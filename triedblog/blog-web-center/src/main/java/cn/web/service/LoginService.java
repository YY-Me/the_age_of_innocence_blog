package cn.web.service;

import cn.web.entity.BlogQQUser;

public interface LoginService {

	public abstract String getAccessToken(String code);

	public abstract String getOpenId(String accessToken);

	public abstract String refreshToken(String code);

	public abstract String getAuthorizationUrl();

	public abstract Object getUserInfo(String accessToken, String openId);

	public abstract void checkQQUserIsLocal(BlogQQUser blogQQUser);
}
