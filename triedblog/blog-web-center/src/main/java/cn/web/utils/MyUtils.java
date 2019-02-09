package cn.web.utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import cn.web.dto.BlogArticleByTime;
import cn.web.entity.BlogArticle;

public class MyUtils {

	public static List<BlogArticleByTime> TimeClassify(List<BlogArticle> list) {
		int size = list.size();
		List<BlogArticleByTime> result = new ArrayList<>();
		int i = 0, j = 0;
		for (i = 0; i < size; i++) {
			BlogArticleByTime blogArticleByTime = new BlogArticleByTime();
			List<BlogArticle> data = new ArrayList<>();
			blogArticleByTime.setName(getTime(list.get(i).getCreatetime()));
			data.add(list.get(i));
			for (j = i + 1; j < size; j++) {
				if (checkTwoDate(list.get(i).getCreatetime(), list.get(j).getCreatetime())) {
					data.add(list.get(j));
				} else {
					break;
				}
			}
			i = j - 1;
			blogArticleByTime.setData(data);
			result.add(blogArticleByTime);
		}
		return result;
	}

	public static String getTime(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.YEAR) + "年" + (c.get(Calendar.MONTH) + 1) + "月";
	}

	/**
	 * 判断两个日期是否属于同年同月月，是：true反之false
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean checkTwoDate(Date date1, Date date2) {
		Calendar c1 = Calendar.getInstance();
		c1.setTime(date1);
		Calendar c2 = Calendar.getInstance();
		c2.setTime(date2);
		if (c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR) && c1.get(Calendar.MONTH) == c2.get(Calendar.MONTH)) {
			return true;
		}
		return false;
	}

}
