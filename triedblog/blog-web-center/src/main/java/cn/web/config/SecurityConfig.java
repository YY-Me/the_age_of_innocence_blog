package cn.web.config;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import cn.commons.utils.PermitAllUrl;
import cn.web.filter.TokenFilter;

/**
 * spring security安全配置
 * 
 * @author 偶尔有点困
 * @date 2018年5月22日
 */
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private TokenFilter tokenFilter;

	// 密码加密
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers(PermitAllUrl.permitAllUrl("/article/**", "/oauth2/**", "/leaveMsg/list/**",
						"/recentUser/**", "/count/**", "/system/**"))
				.permitAll().anyRequest().authenticated().and().csrf().disable();
		http.exceptionHandling().authenticationEntryPoint(
				(request, response, authException) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED));

		http.addFilterBefore(tokenFilter, UsernamePasswordAuthenticationFilter.class);
	}
}