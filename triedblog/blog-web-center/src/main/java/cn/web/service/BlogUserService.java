package cn.web.service;

import cn.commons.common.PublicResultJosn;
import cn.web.entity.BlogUser;

public interface BlogUserService {

	PublicResultJosn register(BlogUser user);

	BlogUser getUserInfo(String email, String password);

}
