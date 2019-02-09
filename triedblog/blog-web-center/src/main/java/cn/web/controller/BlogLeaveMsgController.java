package cn.web.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.commons.common.PublicResultJosn;
import cn.web.dto.LayuiFlowArticleComment;
import cn.web.entity.BlogLeaveMsg;
import cn.web.entity.BlogQQUser;
import cn.web.service.BlogLeaveMsgService;
import cn.web.service.impl.BlogArticleCommentServiceImpl;
import cn.web.utils.IPUtils;
import cn.web.utils.RedisUtil;
import cn.web.utils.UserUtils;

/**
 * 留言
 * @author 余勇
 * @email 1396513066@qq.com
 * @date 2018年8月2日
 */
@RestController
@RequestMapping("/leaveMsg")
public class BlogLeaveMsgController {

    private static final Logger log = LoggerFactory.getLogger(BlogArticleCommentServiceImpl.class);

    @Autowired
    private BlogLeaveMsgService blogLeaveMsgService;

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 添加一条留言
    * @Title: add
    * @param @param blogLeaveMsg
    * @param @return
    * @return PublicResultJosn
    * @throws
     */
    @PostMapping
    public PublicResultJosn add(@RequestBody BlogLeaveMsg blogLeaveMsg, HttpServletRequest request) {
        // 获取当前登录用户
        Object object = UserUtils.getCurrent(request, redisUtil);
        if (object instanceof BlogQQUser) {
            BlogQQUser user = (BlogQQUser) object;
            blogLeaveMsg.setFromName(user.getNickname());
            blogLeaveMsg.setFromHeadImg(user.getFigureurlQq1());
            blogLeaveMsg.setFromId(user.getOpenid());
        } else {
            System.err.println("email");
        }
        // 评论内容转义
        // 补全ip
        Map<String, Object> map = IPUtils.getAreaInfo(request);
        blogLeaveMsg.setAreaIp((String) map.get("ip"));
        blogLeaveMsg.setArea((String) map.get("area"));

        PublicResultJosn result = blogLeaveMsgService.add(blogLeaveMsg);
        return result;
    }

    /**
     * 分页加载留言数据
    * @Title: selectByPage
    * @param @param page
    * @param @param pageSize
    * @param @return
    * @return LayuiFlowArticleComment
    * @throws
     */
    @GetMapping("/list")
    public LayuiFlowArticleComment selectByPage(@RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer pageSize) {
        LayuiFlowArticleComment result = blogLeaveMsgService.selectLeaveMsgByPage(page, pageSize);
        return result;
    }

    @GetMapping("/list/test")
    public String testas() {
        String string = Long.toString(System.currentTimeMillis());
        log.info(string);
        return string;
    }

}
