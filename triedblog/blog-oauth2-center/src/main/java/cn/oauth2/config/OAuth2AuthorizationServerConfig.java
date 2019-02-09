package cn.oauth2.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.DefaultUserAuthenticationConverter;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import cn.oauth2.service.impl.RandomAuthenticationKeyGenerator;
import cn.oauth2.service.impl.RedisAuthorizationCodeServices;

/**
 * 授权服务器配置
 * @ClassName:  OAuth2AuthorizationServerConfig   
 * @Description: 授权服务器配置
 * @author: yuyong 
 * @date:   2018年5月12日 下午8:49:53   
 *     
 * @Copyright: 2018 www.xxx.com Inc. All rights reserved. 
 * @note: 注意：本内容仅限于xxx公司内部传阅，禁止外泄以及用于其他的商业目
 */
@Configuration
@EnableAuthorizationServer
public class OAuth2AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

	/**
	 * 认证管理器
	 * @see SecurityConfig authenticationManagerBean()
	 */
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private RedisConnectionFactory redisConnectionFactory;

	@Autowired
	private DataSource dataSource;
	/**
	 * 使用jwt或者redis
	 * 默认redis
	 */
	@Value("${access_token.store-jwt:false}")
	private boolean storeWithJwt;

	@Value("${access_token.jwt-secret}")
	private String jwtSecret;

	@Autowired
	private RedisAuthorizationCodeServices redisAuthorizationCodeServices;

	/**
	 * 令牌存储
	 * 
	 * @return
	 */
	@Bean
	public TokenStore tokenStore() {
		if (storeWithJwt) {
			return new JwtTokenStore(accessTokenConverter());
		}
		RedisTokenStore redisTokenStore = new RedisTokenStore(redisConnectionFactory);
		//解决同一username每次登陆access_token都相同的问题
		redisTokenStore.setAuthenticationKeyGenerator(new RandomAuthenticationKeyGenerator());
		return redisTokenStore;
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.authenticationManager(this.authenticationManager);
		endpoints.tokenStore(tokenStore());
		// 授权码模式下，code存储
		endpoints.authorizationCodeServices(redisAuthorizationCodeServices);
		if (storeWithJwt) {
			endpoints.accessTokenConverter(accessTokenConverter());
		}
	}

	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		security.allowFormAuthenticationForClients();
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.jdbc(dataSource);
	}

	@Autowired
	public UserDetailsService userDetailsService;

	/**
	 * Jwt资源令牌转换器
	 * 
	 * @return accessTokenConverter
	 */
	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
		DefaultAccessTokenConverter defaultAccessTokenConverter = (DefaultAccessTokenConverter) jwtAccessTokenConverter
				.getAccessTokenConverter();
		DefaultUserAuthenticationConverter userAuthenticationConverter = new DefaultUserAuthenticationConverter();
		userAuthenticationConverter.setUserDetailsService(userDetailsService);

		defaultAccessTokenConverter.setUserTokenConverter(userAuthenticationConverter);
		// 2018-6-29 设置同一个key，否则多个认证中心启动鉴权，jwt方式 ，access_token将解析错误，jwt无状态
		jwtAccessTokenConverter.setSigningKey(jwtSecret);
		return jwtAccessTokenConverter;
	}

}
