package cn.web.utils;

/**
 * 文章html过滤
 * @author 余勇
 * @email 1396513066@qq.com
 * @date 2018年8月3日
 */
public class MyHtmlUtils {

    /**
     * 转义换行
    * @Title: filterWrap
    * @param @param html
    * @param @return
    * @return String
    * @throws
     */
    public static String filterWrap(String html) {
        return html.replaceAll("\n", html);
    }

    /**
     * 转义链接
    * @Title: filterLink
    * @param @param html
    * @param @return
    * @return String
    * @throws
     */
    public static String filterLink(String html) {
        return null;
    }

    /**
     * 转义代码
    * @Title: filterHtml
    * @param @param html
    * @param @return
    * @return String
    * @throws
     */
    public static String filterHtml(String html) {
        return null;
    }

}
