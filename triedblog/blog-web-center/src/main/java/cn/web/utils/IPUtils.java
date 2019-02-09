package cn.web.utils;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

public class IPUtils {

	public static Map<String, Object> getAreaInfo(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<>();
		map.put("ip", getIp2(request));
		String userAgent = request.getHeader("User-Agent");
		map.put("area", getUserAgent(userAgent));
		return map;
	}

	/**
	 * 获取ip
	 * 
	 * @author 余勇
	 * @email 1396513066@qq.com
	 * @date 2018年7月4日
	 */
	public static String getIp2(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		if (StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
			// 多次反向代理后会有多个ip值，第一个ip才是真实ip
			int index = ip.indexOf(",");
			if (index != -1) {
				return ip.substring(0, index);
			} else {
				return ip;
			}
		}
		ip = request.getHeader("X-Real-IP");
		if (StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
			return ip;
		}
		return request.getRemoteAddr();
	}

	/**
	 * 用途：根据客户端 User Agent Strings 判断其浏览器、操作平台 if 判断的先后次序：
	 * 根据设备的用户使用量降序排列，这样对于大多数用户来说可以少判断几次即可拿到结果： >>操作系统:Windows > 苹果 > 安卓 > Linux >
	 * ... >>Browser:Chrome > FF > IE > ...
	 * 
	 * @param userAgentStr
	 * @return
	 */
	public static String getUserAgent(String userAgent) {
		if (StringUtils.isBlank(userAgent)) {
			return "未知";
		}

		if (userAgent.contains("Windows")) {// 主流应用靠前
			/**
			 * ****************** 台式机 Windows 系列 ****************** Windows NT 6.2 - Windows
			 * 8 Windows NT 6.1 - Windows 7 Windows NT 6.0 - Windows Vista Windows NT 5.2 -
			 * Windows Server 2003; Windows XP x64 Edition Windows NT 5.1 - Windows XP
			 * Windows NT 5.01 - Windows 2000, Service Pack 1 (SP1) Windows NT 5.0 - Windows
			 * 2000 Windows NT 4.0 - Microsoft Windows NT 4.0 Windows 98; Win 9x 4.90 -
			 * Windows Millennium Edition (Windows Me) Windows 98 - Windows 98 Windows 95 -
			 * Windows 95 Windows CE - Windows CE
			 * 判断依据:http://msdn.microsoft.com/en-us/library/ms537503(v=vs.85).aspx
			 */
			if (userAgent.contains("Windows NT 10.0")) {// Windows 10
				return "Windows 10";// 判断浏览器
			} else if (userAgent.contains("Windows NT 6.2")) {// Windows 8
				return "Windows 8";// 判断浏览器
			} else if (userAgent.contains("Windows NT 6.1")) {// Windows 7
				return "Windows 7";
			} else if (userAgent.contains("Windows NT 6.0")) {// Windows Vista
				return "Windows Vista";
			} else if (userAgent.contains("Windows NT 5.2")) {// Windows XP x64 Edition
				return "Windows XP x64 Edition";
			} else if (userAgent.contains("Windows NT 5.1")) {// Windows XP
				return "Windows XP";
			} else if (userAgent.contains("Windows NT 5.01")) {// Windows 2000, Service Pack 1 (SP1)
				return "Windows 2000, Service Pack 1 (SP1)";
			} else if (userAgent.contains("Windows NT 5.0")) {// Windows 2000
				return "Windows 2000";
			} else if (userAgent.contains("Windows NT 4.0")) {// Microsoft Windows NT 4.0
				return "Microsoft Windows NT 4.0";
			} else if (userAgent.contains("Windows 98; Win 9x 4.90")) {// Windows Millennium Edition (Windows Me)
				return "Windows Millennium Edition (Windows Me)";
			} else if (userAgent.contains("Windows 98")) {// Windows 98
				return "Windows 98";
			} else if (userAgent.contains("Windows 95")) {// Windows 95
				return "Windows 95";
			} else if (userAgent.contains("Windows CE")) {// Windows CE
				return "Windows CE";
			}
		} else if (userAgent.contains("Mac OS X")) {
			/**
			 * ******** 苹果系列 ******** iPod - Mozilla/5.0 (iPod; U; CPU iPhone OS 4_3_1 like
			 * Mac OS X; zh-cn) AppleWebKit/533.17.9 (KHTML, like Gecko) Version/5.0.2
			 * Mobile/8G4 Safari/6533.18.5 iPad - Mozilla/5.0 (iPad; U; CPU OS 3_2 like Mac
			 * OS X; en-us) AppleWebKit/531.21.10 (KHTML, like Gecko) Version/4.0.4
			 * Mobile/7B334b Safari/531.21.10 iPad2 - Mozilla/5.0 (iPad; CPU OS 5_1 like Mac
			 * OS X; en-us) AppleWebKit/534.46 (KHTML, like Gecko) Version/5.1 Mobile/9B176
			 * Safari/7534.48.3 iPhone 4 - Mozilla/5.0 (iPhone; U; CPU iPhone OS 4_0 like
			 * Mac OS X; en-us) AppleWebKit/532.9 (KHTML, like Gecko) Version/4.0.5
			 * Mobile/8A293 Safari/6531.22.7 iPhone 5 - Mozilla/5.0 (iPhone; CPU iPhone OS
			 * 5_0 like Mac OS X) AppleWebKit/534.46 (KHTML, like Gecko) Version/5.1
			 * Mobile/9A334 Safari/7534.48.3
			 * 判断依据:http://www.useragentstring.com/pages/Safari/
			 * 参考:http://stackoverflow.com/questions/7825873/what-is-the-ios-5-0-user-agent-string
			 * 参考:http://stackoverflow.com/questions/3105555/what-is-the-iphone-4-user-agent
			 */
			if (userAgent.contains("iPod")) {
				return "iPod";// 判断浏览器
			}
		}
		return null;
	}
}
