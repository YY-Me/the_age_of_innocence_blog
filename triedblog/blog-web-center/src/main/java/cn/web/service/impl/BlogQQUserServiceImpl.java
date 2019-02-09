package cn.web.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.commons.common.PublicResultJosn;
import cn.web.dao.BlogQQUserMapper;
import cn.web.entity.BlogQQUser;
import cn.web.service.BlogQQUserService;

@Service
public class BlogQQUserServiceImpl implements BlogQQUserService {

    @Autowired
    private BlogQQUserMapper blogQQUserMapper;

    @Override
    public BlogQQUser checkOppenId(String oppenId) {
        return blogQQUserMapper.selectByPrimaryKey(oppenId);
    }

    @Override
    @Transactional
    public int insert(BlogQQUser blogQQUser) {
        return blogQQUserMapper.insert(blogQQUser);
    }

    @Override
    @Transactional
    public int updateByPrimaryKeySelective(BlogQQUser blogQQUser) {
        if (StringUtils.isBlank(blogQQUser.getOpenid())) {
            throw new IllegalArgumentException("openid为空");
        }
        return blogQQUserMapper.updateByPrimaryKeySelective(blogQQUser);
    }

    @Override
    @Cacheable(value = "blogWebCenter", key = "#root.methodName")
    public PublicResultJosn listRecentUser() {
        List<BlogQQUser> list = blogQQUserMapper.listRecentUser();
        return new PublicResultJosn(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), list);
    }

}
