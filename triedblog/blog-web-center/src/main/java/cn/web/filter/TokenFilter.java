package cn.web.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import cn.commons.utils.CookieUtils;
import cn.commons.utils.MiniConstant;
import cn.web.dto.BlogQQLoginUser;
import cn.web.utils.RedisUtil;

@Component
public class TokenFilter extends OncePerRequestFilter {

    private static final String TOKEN_KEY = "accessToken";

    @Autowired
    private RedisUtil redisUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String accessToken = getToken(request);
        if (StringUtils.isNotBlank(accessToken)) {
            // redis获取用户
            // 根据access token获取登录用户的openid
            BlogQQLoginUser loginUser = (BlogQQLoginUser) redisUtil.get(MiniConstant.getQQaccessTokenKey(accessToken));
            if (loginUser != null) {
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(loginUser,
                        null, loginUser.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        filterChain.doFilter(request, response);
    }

    /**
     * 根据参数或者header获取token
     * 
     * @param request
     * @return
     */
    public static String getToken(HttpServletRequest request) {
        String token = request.getParameter(TOKEN_KEY);
        if (StringUtils.isBlank(token)) {
            token = request.getHeader(TOKEN_KEY);
        }
        if (StringUtils.isBlank(token)) {
            token = CookieUtils.getCookieValue(request, TOKEN_KEY, true);
        }
        return token;
    }

}