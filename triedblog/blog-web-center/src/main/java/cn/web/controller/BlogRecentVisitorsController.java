package cn.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.commons.common.PublicResultJosn;
import cn.web.service.BlogQQUserService;

/**
 * 最近访客
 * @author 余勇
 * @email 1396513066@qq.com
 * @date 2018年8月10日
 */
@RestController
@RequestMapping("/recentUser")
public class BlogRecentVisitorsController {

    @Autowired
    private BlogQQUserService blogQQUserService;

    /**
     * 获取最近登录用户
    * @Title: listRecentUser
    * @param @return
    * @return PublicResultJosn
    * @throws
     */
    @GetMapping
    public PublicResultJosn listRecentUser() {
        return blogQQUserService.listRecentUser();
    }

}
