package cn.commons.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * 配置不需要拦截的url
 * 
 * @author 偶尔有点困
 * @date 2018年5月22日
 */
public final class PermitAllUrl {

	/**
	 * 监控中心和swagger需要访问的url
	 */
	private static final String[] ENDPOINTS = { "/actuator/health", "/actuator/env", "/actuator/metrics/**",
			"/actuator/trace", "/actuator/dump", "/actuator/jolokia", "/actuator/info", "/actuator/logfile",
			"/actuator/refresh", "/actuator/flyway", "/actuator/liquibase", "/actuator/heapdump", "/actuator/loggers",
			"/actuator/auditevents", "/actuator/env/PID", "/actuator/jolokia/**", "/actuator/**", "/v2/api-docs/**",
			"/swagger-ui.html", "/swagger-resources/**", "/webjars/**", "/druid/**" };

	/**
	 * 需要放开权限的url
	 *
	 * @param urls
	 *            自定义的url
	 * @return 自定义的url和监控中心需要访问的url集合
	 */
	public static String[] permitAllUrl(String... urls) {
		if (urls == null || urls.length == 0) {
			return ENDPOINTS;
		}

		List<String> list = new ArrayList<>();
		for (String url : ENDPOINTS) {
			list.add(url);
		}
		for (String url : urls) {
			list.add(url);
		}

		return list.toArray(new String[list.size()]);
	}

}