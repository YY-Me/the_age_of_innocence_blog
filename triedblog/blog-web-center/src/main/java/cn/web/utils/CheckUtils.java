package cn.web.utils;

public class CheckUtils {
	/**
	 * 判断用户是否评论过（默认两次评论或留言不能小于15s）
	 * 
	 * @author 余勇
	 * @email 1396513066@qq.com
	 * @date 2018年7月5日
	 */
	public static Boolean checkUserIsComment(String id, RedisUtil redisUtil) {
		// System.out.println(MiniConstant.getUserIsCommentKey(id));
		// System.out.println(redisUtil.get(MiniConstant.getUserIsCommentKey(id)));
		// redisUtil.set("names", "askdfjkalsfd");
		return true;
	}

}
