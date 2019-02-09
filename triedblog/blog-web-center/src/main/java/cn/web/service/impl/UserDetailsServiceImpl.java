package cn.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import cn.web.dao.BlogUserMapper;
import cn.web.entity.BlogUser;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private BlogUserMapper blogUserMapper;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		BlogUser blogUser = blogUserMapper.selectByUsername(username);
		// 用户状态，0：禁用，1：正常
		if (null == blogUser) {
			throw new AuthenticationCredentialsNotFoundException("用户名不存在");
		}
		return blogUser;
	}

}