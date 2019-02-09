package cn.web.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.commons.common.PublicResultJosn;
import cn.web.entity.BlogArticleComment;
import cn.web.entity.BlogQQUser;
import cn.web.service.BlogArticleCommentService;
import cn.web.utils.IPUtils;
import cn.web.utils.RedisUtil;
import cn.web.utils.UserUtils;

/**
 * 帖子评论得插入、查询
 * 
 * @author 余勇
 * @email 1396513066@qq.com
 * @date 2018年6月30日
 */
@RestController
@RequestMapping("/article_comment")
public class BlogArticleCommentController {

    @Autowired
    private BlogArticleCommentService articleCommentService;

    @Autowired
    private RedisUtil redisUtil;

    @PostMapping
    public PublicResultJosn add(@RequestBody BlogArticleComment blogArticleComment, HttpServletRequest request,
            HttpServletResponse response) {
        // 校验
        if (StringUtils.isBlank(blogArticleComment.getArticleId())) {
            throw new IllegalArgumentException("articleid错误");
        }
        // 获取当前登录用户
        Object object = UserUtils.getCurrent(request, redisUtil);
        if (object instanceof BlogQQUser) {
            BlogQQUser user = (BlogQQUser) object;
            blogArticleComment.setFromName(user.getNickname());
            blogArticleComment.setFromHeadImg(user.getFigureurlQq1());
            blogArticleComment.setFromId(user.getOpenid());
        } else {
            System.err.println("email");
        }
        // 评论内容转义
        // 补全ip
        Map<String, Object> map = IPUtils.getAreaInfo(request);
        blogArticleComment.setAreaIp((String) map.get("ip"));
        blogArticleComment.setArea((String) map.get("area"));
        PublicResultJosn resultJosn = articleCommentService.add(blogArticleComment);
        return resultJosn;
    }

}
