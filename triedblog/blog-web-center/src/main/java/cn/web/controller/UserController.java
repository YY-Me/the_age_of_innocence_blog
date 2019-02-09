package cn.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.web.utils.RedisUtil;

/**
 * 前台用户操作相关
 * 
 * @author 余勇
 * @email 1396513066@qq.com
 * @date 2018年7月1日
 */
@RestController
public class UserController {

    @Autowired
    private RedisUtil redisUtil;

    // 获取qq当前登录用户
    @GetMapping("/currentUser")
    public UserDetails getQQUserInfoMe(@AuthenticationPrincipal UserDetails user, HttpServletRequest request) {
        redisUtil.set("test00000", "test", -1);
        return user;
    }
}
