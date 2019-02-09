package cn.web.service.impl;

import java.util.Date;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import cn.commons.common.PublicResultJosn;
import cn.web.dao.BlogUserMapper;
import cn.web.entity.BlogUser;
import cn.web.service.BlogUserService;

@Service
public class BlogUserServiceImpl implements BlogUserService {

	@Autowired
	private BlogUserMapper userMapper;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	/**
	 * 注册
	 */
	@Override
	public PublicResultJosn register(BlogUser user) {
		user.setUid(UUID.randomUUID().toString());
		// 如果昵称为空则设置为email
		if (StringUtils.isBlank(user.getNickname())) {
			user.setNickname(user.getEmail());
		}
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		// 用户状态，0：禁用，1：正常
		user.setStatus("1");
		user.setCreatetime(new Date());
		userMapper.insert(user);
		return null;
	}

	/**
	 * 登录校验
	 */
	@Override
	public BlogUser getUserInfo(String email, String password) {
		BlogUser blogUser = userMapper.selectByUsername(email);
		if (null == blogUser) {
			throw new IllegalArgumentException("用户名不存在");
		}
		if ("0".equals(blogUser.getStatus())) {
			throw new LockedException("账户被锁定，请联系管理员");
		}
		if (!passwordEncoder.matches(passwordEncoder.encode(password), blogUser.getPassword())) {
			throw new BadCredentialsException("用户名或密码错误");
		}
		return blogUser;
	}

}
