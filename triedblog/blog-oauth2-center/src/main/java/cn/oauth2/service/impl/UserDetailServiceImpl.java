package cn.oauth2.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import cn.commons.dto.LoginUser;

/**
 * 实现UserDetailsService，到数据库查找信息
 * 
 * @author 偶尔有点困
 * @date 2018年5月21日
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {

	@Autowired
	private UserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		LoginUser loginUser = userService.selectByUsername(username);
		if (null == loginUser) {
			throw new AuthenticationCredentialsNotFoundException("用户名不存在");
		}
		return loginUser;
	}

}