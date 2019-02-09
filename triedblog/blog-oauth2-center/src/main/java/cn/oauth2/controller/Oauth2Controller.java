package cn.oauth2.controller;

import java.security.Principal;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Oauth2Controller {

	/**
	 * 获取当前登录用户基本信息
	 * 
	 * @param principal
	 * @return
	 */
	@GetMapping("/check_user")
	public Principal principal(Principal principal) {
		return principal;
	}

	@PostMapping("/currentUser")
	public UserDetails get_user_info(@AuthenticationPrincipal UserDetails user) {
		return user;
	}

}
