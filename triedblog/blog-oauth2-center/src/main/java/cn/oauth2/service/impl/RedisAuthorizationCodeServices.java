package cn.oauth2.service.impl;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisStringCommands.SetOption;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.types.Expiration;
import org.springframework.security.oauth2.common.util.SerializationUtils;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.code.RandomValueAuthorizationCodeServices;
import org.springframework.stereotype.Service;

/**
 * 客户端模式，将授权码存储到redis
 * 
 * @author 偶尔有点困
 * @date 2018年5月22日
 */
@Service
public class RedisAuthorizationCodeServices extends RandomValueAuthorizationCodeServices {

	@Autowired
	private RedisTemplate<Object, Object> redisTemplate;

	@Override
	protected void store(String code, OAuth2Authentication authentication) {
		redisTemplate.execute(new RedisCallback<Long>() {

			@Override
			public Long doInRedis(RedisConnection connection) throws DataAccessException {
				connection.set(getKey(code).getBytes(), SerializationUtils.serialize(authentication),
						Expiration.from(10, TimeUnit.MINUTES), SetOption.UPSERT);
				return 1L;
			}
		});
	}

	@Override
	protected OAuth2Authentication remove(final String code) {
		OAuth2Authentication oAuth2Authentication = redisTemplate.execute(new RedisCallback<OAuth2Authentication>() {

			@Override
			public OAuth2Authentication doInRedis(RedisConnection connection) throws DataAccessException {
				byte[] keyByte = getKey(code).getBytes();
				byte[] valueByte = connection.get(keyByte);

				if (valueByte != null) {
					connection.del(keyByte);
					return SerializationUtils.deserialize(valueByte);
				}

				return null;
			}
		});

		return oAuth2Authentication;
	}

	private String getKey(String code) {
		return "Oauth2Authorization:code" + code;
	}
}