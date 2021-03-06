package cn.admin.config;

import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;

/**
 * 使用feign传递请求头
 * Copyright (c) 2017 by 
 * @ClassName: RequestInterceptorConfig.java
 * @Description: TODO
 * 
 * @author: yuyong
 * @version: V1.0  
 * @Date: 2018年8月30日 下午5:04:23
 */
@Configuration
public class RequestInterceptorConfig implements RequestInterceptor {

	@Override
	public void apply(RequestTemplate template) {
		Map<String, String> headers = getHeaders(getHttpServletRequest());
		for (String headerName : headers.keySet()) {
			template.header(headerName, getHeaders(getHttpServletRequest()).get(headerName));
		}
	}

	private HttpServletRequest getHttpServletRequest() {
		try {
			return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private Map<String, String> getHeaders(HttpServletRequest request) {
		Map<String, String> map = new LinkedHashMap<>();
		Enumeration<String> enumeration = request.getHeaderNames();
		while (enumeration.hasMoreElements()) {
			String key = enumeration.nextElement();
			String value = request.getHeader(key);
			map.put(key, value);
		}
		return map;
	}

	@Bean
	public Encoder feignFormEncoder() {
		return new SpringFormEncoder();
	}
}
