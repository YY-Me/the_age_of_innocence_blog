package cn.web.utils;

import java.util.HashMap;
import java.util.List;

import cn.web.entity.DayAreaTimesStat;
import cn.web.entity.DayBrowserTimesStat;
import cn.web.entity.DayOsTimesStat;

/**
 * 
 * @ClassName:  StatUtils   
 * @Description: 统计工具处理类
 * @author: yuyong 
 * @date:   2018年10月8日 下午8:40:07   
 *     
 * @Copyright: 2018 www.xxx.com Inc. All rights reserved. 
 * @note: 注意：本内容仅限于xxx公司内部传阅，禁止外泄以及用于其他的商业目
 */
public class StatUtils {

	private static final String[] NAMES = { "广西", "宁夏", "内蒙古", "黑龙江", "西藏", "新疆", "香港", "澳门" };

	/**
	 * @Title: ParseAreaState   
	 * @Description: 格式化地区
	 * @param: @param data
	 * @param: @return      
	 * @return: Object      
	 * @throws
	 */
	public static HashMap<String, Long> ParseAreaState(List<DayAreaTimesStat> data) {
		HashMap<String, Long> hashMap = new HashMap<String, Long>();
		for (DayAreaTimesStat dayAreaTimesStat : data) {
			String key = parseName(dayAreaTimesStat.getArea());
			Long value = hashMap.get(key);
			if (null == value) {
				hashMap.put(key, dayAreaTimesStat.getTimes());
			} else {
				hashMap.put(key, value + dayAreaTimesStat.getTimes());
			}
		}
		return hashMap;
	}

	/**
	 * @Title: ParseOsState   
	 * @Description: 统计os
	 * @param: @param data
	 * @param: @return      
	 * @return: Object      
	 * @throws
	 */
	public static HashMap<String, Long> ParseOsState(List<DayOsTimesStat> data) {
		HashMap<String, Long> hashMap = new HashMap<String, Long>();
		for (DayOsTimesStat dayOsTimesStat : data) {
			String key = dayOsTimesStat.getOs();
			Long value = hashMap.get(key);
			if (null == value) {
				hashMap.put(key, dayOsTimesStat.getTimes());
			} else {
				hashMap.put(key, value + dayOsTimesStat.getTimes());
			}
		}
		return hashMap;
	}

	/**
	 * @Title: ParseBrowserState   
	 * @Description: 统计浏览器
	 * @param: @param data
	 * @param: @return      
	 * @return: Object      
	 * @throws
	 */
	public static HashMap<String, Long> ParseBrowserState(List<DayBrowserTimesStat> data) {
		HashMap<String, Long> hashMap = new HashMap<>();
		for (DayBrowserTimesStat dayBrowserTimesStat : data) {
			String key = dayBrowserTimesStat.getBrowser();
			Long value = hashMap.get(key);
			if (null == value) {
				hashMap.put(key, dayBrowserTimesStat.getTimes());
			} else {
				hashMap.put(key, value + dayBrowserTimesStat.getTimes());
			}
		}
		return hashMap;
	}

	public static String parseName(String name) {
		for (String string : NAMES) {
			if (name.contains(string)) {
				return string;
			}
		}
		return name.substring(0, 2);
	}
}
