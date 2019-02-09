package cn.commons.utils;

public class MiniConstant {

    public static String QQ_USER_IFNO = "QQ_USER_IFNO";

    public static String QQ_USER_IS_COMMENTS = "QQ_USER_IS_COMMENTS";

    public static String QQ_USER_IS_LEAVEMSG = "QQ_USER_IS_LEAVEMSG";

    public static String BLOG_ADMIN_LOGIN_VERIFY_CODE = "BLOG_ADMIN_LOGIN_VERIFY_CODE";

    public static String getQQaccessTokenKey(String accessToken) {
        return MiniConstant.QQ_USER_IFNO + ":" + accessToken;
    }

    public static String getUserIsCommentKey(String id) {
        return MiniConstant.QQ_USER_IS_COMMENTS + ":" + id;
    }

    public static String getUserIsLeaveMsg(String fromId) {
        return MiniConstant.QQ_USER_IS_LEAVEMSG + ":" + fromId;
    }

    public static String getVerifyCodeBycodeKey(String codeKey) {
        return MiniConstant.BLOG_ADMIN_LOGIN_VERIFY_CODE + ":" + codeKey;
    }

}