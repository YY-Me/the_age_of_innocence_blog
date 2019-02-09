package cn.web.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.commons.common.PublicResultJosn;
import cn.commons.utils.MiniConstant;
import cn.web.dao.BlogLeaveMsgMapper;
import cn.web.dao.BlogQQUserMapper;
import cn.web.dto.BlogLeaveMsgSelect;
import cn.web.dto.LayuiFlowArticleComment;
import cn.web.entity.BlogLeaveMsg;
import cn.web.entity.BlogQQUser;
import cn.web.example.BlogLeaveMsgExample;
import cn.web.example.BlogLeaveMsgExample.Criteria;
import cn.web.service.BlogLeaveMsgService;
import cn.web.utils.RedisUtil;

@Service
public class BlogLeaveMsgServiceImpl implements BlogLeaveMsgService {

    private static final Logger log = LoggerFactory.getLogger(BlogLeaveMsgServiceImpl.class);

    @Autowired
    private BlogLeaveMsgMapper blogLeaveMsgMapper;

    @Autowired
    private BlogQQUserMapper blogQQUserMapper;

    @Resource
    private RedisUtil redisUtil;

    /**
     * 添加留言
    * Title: add
    * Description: 
    * @param blogLeaveMsg
    * @return  
    * @see cn.web.service.BlogLeaveMsgService#add(cn.web.entity.BlogLeaveMsg)
     */
    @Override
    @Transactional
    public PublicResultJosn add(BlogLeaveMsg blogLeaveMsg) {
        // 判断下能不能评论
        if (redisUtil.hasKey(MiniConstant.getUserIsLeaveMsg(blogLeaveMsg.getFromId()))) {
            log.info("{}存在留言恶意刷楼", blogLeaveMsg.getFromId());
            throw new IllegalArgumentException("存在恶意刷楼");
        }
        if (blogLeaveMsg.getParentId() == null) {
            // 默认为一级评论
            blogLeaveMsg.setParentId((long) 0);
        }
        if (StringUtils.isNotBlank(blogLeaveMsg.getToId())) {
            // 补全被回复人信息
            BlogQQUser user = blogQQUserMapper.selectByPrimaryKey(blogLeaveMsg.getToId());
            if (null == user) {
                throw new IllegalArgumentException("被回复人不存在");
            }
            blogLeaveMsg.setToName(user.getNickname());
        }
        blogLeaveMsg.setLikeNum((long) 0);
        blogLeaveMsgMapper.insert(blogLeaveMsg);
        // 评论后放到缓存,15s之后才能再次评论
        redisUtil.set(MiniConstant.getUserIsLeaveMsg(blogLeaveMsg.getFromId()), 1, 15);
        return new PublicResultJosn(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), "留言成功");
    }

    /**
     * 分页查询留言信息
    * Title: selectLeaveMsgByPage
    * Description: 
    * @param page
    * @param pageSize
    * @return  
    * @see cn.web.service.BlogLeaveMsgService#selectLeaveMsgByPage(java.lang.Integer, java.lang.Integer)
     */
    @Override
    public LayuiFlowArticleComment selectLeaveMsgByPage(Integer page, Integer pageSize) {
        List<BlogLeaveMsgSelect> result = new ArrayList<>();
        BlogLeaveMsgExample example = new BlogLeaveMsgExample();
        example.setOrderByClause("create_time DESC");
        Criteria criteria = example.createCriteria();
        // 0表示一级评论
        criteria.andParentIdEqualTo((long) 0);
        // 根据ids查找article的基本信息,默认前五条评论
        PageHelper.startPage(page, pageSize);
        // 原始的数据
        List<BlogLeaveMsg> list = blogLeaveMsgMapper.selectByExampleWithBLOBs(example);
        PageInfo<BlogLeaveMsg> pageInfo = new PageInfo<>(list, pageSize);
        if (CollectionUtils.isNotEmpty(list)) {
            for (BlogLeaveMsg blogLeaveMsg : pageInfo.getList()) {
                BlogLeaveMsgSelect blogLeaveMsgSelect = new BlogLeaveMsgSelect();
                // 父评论（一级菜单）
                blogLeaveMsgSelect.setParent(blogLeaveMsg);
                // 设置子评论（子菜单）getChild是递归调用的
                List<BlogLeaveMsgSelect> list2 = this.getChild(blogLeaveMsg.getLeaveId());
                blogLeaveMsgSelect.setChild(list2);
                result.add(blogLeaveMsgSelect);
            }
        }
        // 结果
        LayuiFlowArticleComment results = new LayuiFlowArticleComment();
        results.setCode(HttpStatus.OK.value());
        results.setMessage(HttpStatus.OK.getReasonPhrase());
        results.setPages(pageInfo.getPages());
        results.setData(result);
        return results;
    }

    /**
     * 递归查询留言
    * @Title: getChild
    * @param @param lid
    * @param @return
    * @return List<BlogLeaveMsgSelect>
    * @throws
     */
    private List<BlogLeaveMsgSelect> getChild(Long lid) {
        List<BlogLeaveMsgSelect> blogLeaveMsgSelects = new ArrayList<>();
        // 子评论
        BlogLeaveMsgExample example = new BlogLeaveMsgExample();
        Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(lid);
        List<BlogLeaveMsg> parentList = blogLeaveMsgMapper.selectByExampleWithBLOBs(example);
        for (BlogLeaveMsg blogLeaveMsg : parentList) {
            BlogLeaveMsgSelect blogLeaveMsgSelect = new BlogLeaveMsgSelect();
            blogLeaveMsgSelect.setParent(blogLeaveMsg);
            blogLeaveMsgSelects.add(blogLeaveMsgSelect);
        }
        // 根据comment_id继续找子评论
        for (BlogLeaveMsgSelect blogLeaveMsgSelect : blogLeaveMsgSelects) {
            BlogLeaveMsg parent = blogLeaveMsgSelect.getParent();
            blogLeaveMsgSelect.setChild(getChild(parent.getLeaveId()));
        }
        return blogLeaveMsgSelects;
    }
}
