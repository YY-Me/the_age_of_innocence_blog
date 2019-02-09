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
import cn.web.dao.BlogArticleCommentMapper;
import cn.web.dao.BlogQQUserMapper;
import cn.web.dto.BlogArticleCommentSelect;
import cn.web.dto.LayuiFlowArticleComment;
import cn.web.entity.BlogArticleComment;
import cn.web.entity.BlogQQUser;
import cn.web.example.BlogArticleCommentExample;
import cn.web.example.BlogArticleCommentExample.Criteria;
import cn.web.service.BlogArticleCommentService;
import cn.web.utils.RedisUtil;

@Service
public class BlogArticleCommentServiceImpl implements BlogArticleCommentService {

    private static final Logger log = LoggerFactory.getLogger(BlogArticleCommentServiceImpl.class);

    @Autowired
    private BlogArticleCommentMapper blogArticleCommentMapper;

    @Autowired
    private BlogQQUserMapper blogQQUserMapper;

    @Resource
    private RedisUtil redisUtil;

    /**
     * 插入一条评论
     * 
     * @author 余勇
     * @email 1396513066@qq.com
     * @date 2018年6月30日
     */
    @Override
    @Transactional
    public PublicResultJosn add(BlogArticleComment blogArticleComment) {
        // 判断下能不能评论
        if (redisUtil.hasKey(MiniConstant.getUserIsCommentKey(blogArticleComment.getFromId()))) {
            log.info("{}存在评论恶意刷楼", blogArticleComment.getFromId());
            throw new IllegalArgumentException("存在恶意刷楼");
        }
        if (blogArticleComment.getParentId() == null) {
            // 默认为一级评论
            blogArticleComment.setParentId((long) 0);
        }
        if (StringUtils.isNotBlank(blogArticleComment.getToId())) {
            // 补全被回复人信息
            BlogQQUser user = blogQQUserMapper.selectByPrimaryKey(blogArticleComment.getToId());
            if (null == user) {
                throw new IllegalArgumentException("被回复人不存在");
            }
            blogArticleComment.setToName(user.getNickname());
        }
        blogArticleComment.setLikeNum((long) 0);
        blogArticleCommentMapper.insert(blogArticleComment);
        // 评论后放到缓存,15s之后才能再次评论
        redisUtil.set(MiniConstant.getUserIsCommentKey(blogArticleComment.getFromId()), 1, 15);
        return new PublicResultJosn(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), "评论成功");
    }

    /**
     * 根据帖子id分页从查询该帖子得评论
     * 
     * @author 余勇
     * @email 1396513066@qq.com
     * @date 2018年6月30日
     */
    @Override
    public LayuiFlowArticleComment selectCommentById(String articleId, Integer page, Integer pageSize) {
        //检查文章的可见性

        List<BlogArticleCommentSelect> result = new ArrayList<>();
        BlogArticleCommentExample example = new BlogArticleCommentExample();
        example.setOrderByClause("create_time DESC");
        Criteria criteria = example.createCriteria();
        criteria.andArticleIdEqualTo(articleId);
        // 0表示一级评论
        criteria.andParentIdEqualTo((long) 0);
        // 根据ids查找article的基本信息,默认前五条评论
        PageHelper.startPage(page, pageSize);
        // 原始的数据
        List<BlogArticleComment> list = blogArticleCommentMapper.selectByExampleWithBLOBs(example);
        PageInfo<BlogArticleComment> pageInfo = new PageInfo<>(list, pageSize);
        if (CollectionUtils.isNotEmpty(list)) {
            for (BlogArticleComment blogArticleComment : pageInfo.getList()) {
                BlogArticleCommentSelect articleCommentSelect = new BlogArticleCommentSelect();
                // 父评论（一级菜单）
                articleCommentSelect.setParent(blogArticleComment);
                // 设置子评论（子菜单）getChild是递归调用的
                List<BlogArticleCommentSelect> list2 = this.getChild(blogArticleComment.getCommentId(), articleId);
                articleCommentSelect.setChild(list2);
                result.add(articleCommentSelect);
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
     * 递归查询子评论
    * @Title: getChild
    * @param @param pid
    * @param @param articleId
    * @param @return
    * @return List<BlogArticleComment>
    * @throws
     */
    private List<BlogArticleCommentSelect> getChild(Long pid, String articleId) {
        List<BlogArticleCommentSelect> articleCommentSelects = new ArrayList<>();
        // 子评论
        BlogArticleCommentExample example = new BlogArticleCommentExample();
        Criteria criteria = example.createCriteria();
        criteria.andArticleIdEqualTo(articleId);
        criteria.andParentIdEqualTo(pid);
        List<BlogArticleComment> parentList = blogArticleCommentMapper.selectByExampleWithBLOBs(example);
        for (BlogArticleComment blogArticleComment : parentList) {
            BlogArticleCommentSelect articleCommentSelect = new BlogArticleCommentSelect();
            articleCommentSelect.setParent(blogArticleComment);
            articleCommentSelects.add(articleCommentSelect);
        }
        // 根据comment_id继续找子评论
        for (BlogArticleCommentSelect blogArticleCommentSelect : articleCommentSelects) {
            BlogArticleComment parent = blogArticleCommentSelect.getParent();
            blogArticleCommentSelect.setChild(getChild(parent.getCommentId(), articleId));
        }
        return articleCommentSelects;
    }

}
