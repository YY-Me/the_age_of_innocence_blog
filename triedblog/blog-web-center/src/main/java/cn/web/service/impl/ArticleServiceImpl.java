package cn.web.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.commons.common.PublicResultJosn;
import cn.web.dao.BlogArticleCommentMapper;
import cn.web.dao.BlogArticleMapper;
import cn.web.dao.BlogLeaveMsgMapper;
import cn.web.dao.BlogQQUserMapper;
import cn.web.dto.BlogArticleByClassify;
import cn.web.dto.BlogArticleByLabels;
import cn.web.dto.BlogArticleByTime;
import cn.web.dto.BlogArticleClassifyLable;
import cn.web.dto.BlogArticleSelect;
import cn.web.entity.BlogArticle;
import cn.web.entity.BlogArticleComment;
import cn.web.entity.BlogLabelClassify;
import cn.web.example.BlogArticleCommentExample;
import cn.web.service.ArticleService;
import cn.web.utils.MyUtils;

@Service
public class ArticleServiceImpl implements ArticleService {

	@Resource
	private RedisTemplate<String, Object> redisTemplate;

	@Autowired
	private BlogArticleMapper articleMapper;

	@Autowired
	private BlogArticleCommentMapper articleCommentMapper;

	@Autowired
	private BlogQQUserMapper qqUserMapper;

	@Autowired
	private BlogLeaveMsgMapper blogLeaveMsgMapper;

	/**
	 * 
	 * <p>Title: selectByExample</p>   
	 * <p>Description: 首页帖子分页查找（不包括帖子内容）</p>   
	 * @param page
	 * @param pageSize
	 * @return   
	 * @see cn.web.service.ArticleService#selectByExample(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	@Cacheable(value = "blogWebCenter", key = "#root.methodName + '_' + #page + '_' + #pageSize")
	public PageInfo<BlogArticleSelect> selectByExample(Integer page, Integer pageSize) {
		List<BlogArticleSelect> result = new ArrayList<>();
		// 开启分页查找
		PageHelper.startPage(page, pageSize);
		List<BlogArticleSelect> list = articleMapper.selectByPageHelper();
		PageInfo<BlogArticleSelect> pageInfo = new PageInfo<>(list, pageSize);
		for (BlogArticleSelect b : pageInfo.getList()) {
			// 根据文章id查找标签、分类
			this.setLableClassify(b);
			result.add(b);
		}
		pageInfo.setList(result);
		return pageInfo;
	}

	/**
	 * 
	 * <p>Title: selectByClassify</p>   
	 * <p>Description: 根据分类id分页获取article,不包括帖子内容</p>   
	 * @param id
	 * @param page
	 * @param pageSize
	 * @return   
	 * @see cn.web.service.ArticleService#selectByClassify(java.lang.Integer, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	@Cacheable(value = "blogWebCenter", key = "#root.methodName + '_' + #id + '_' + #page + '_' + #pageSize")
	public PageInfo<BlogArticleSelect> selectByClassify(Integer id, Integer page, Integer pageSize) {
		List<BlogArticleSelect> result = new ArrayList<BlogArticleSelect>();
		List<BlogArticleSelect> list = null;
		PageInfo<BlogArticleSelect> pageInfo = null;
		// 根据classify id查有哪些帖子
		List<String> ids = articleMapper.selectArticleIdsByClassifyId(id);
		if (CollectionUtils.isNotEmpty(ids)) {
			// 根据ids查找article的基本信息
			PageHelper.startPage(page, pageSize);
			list = articleMapper.selectArticleByIds(ids);
			pageInfo = new PageInfo<BlogArticleSelect>(list, pageSize);
			for (BlogArticleSelect b : pageInfo.getList()) {
				// 根据文章id查找标签、分类
				this.setLableClassify(b);
				result.add(b);
			}
			pageInfo.setList(result);
		} else {
			pageInfo = new PageInfo<BlogArticleSelect>(list, pageSize);
			pageInfo.setList(result);
		}
		return pageInfo;
	}

	/**
	 * 
	 * <p>Title: selectByLabelId</p>   
	 * <p>Description: 根据标签的id分页获取article,不包括帖子内容</p>   
	 * @param id
	 * @param page
	 * @param pageSize
	 * @return   
	 * @see cn.web.service.ArticleService#selectByLabelId(java.lang.Integer, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	@Cacheable(value = "blogWebCenter", key = "#root.methodName + '_' + #id + '_' + #page + '_' + #pageSize")
	public PageInfo<BlogArticleSelect> selectByLabelId(Integer id, Integer page, Integer pageSize) {
		List<BlogArticleSelect> result = new ArrayList<BlogArticleSelect>();
		List<BlogArticleSelect> list = null;
		PageInfo<BlogArticleSelect> pageInfo = null;
		// 根据label id查有哪些帖子
		List<String> ids = articleMapper.selectArticleIdsByLabelId(id);
		if (CollectionUtils.isNotEmpty(ids)) {
			// 根据ids查找article的基本信息
			PageHelper.startPage(page, pageSize);
			list = articleMapper.selectArticleByIds(ids);
			pageInfo = new PageInfo<BlogArticleSelect>(list, pageSize);
			for (BlogArticleSelect b : pageInfo.getList()) {
				// 根据文章id查找标签、分类
				this.setLableClassify(b);
				result.add(b);
			}
			pageInfo.setList(result);
		} else {
			pageInfo = new PageInfo<BlogArticleSelect>(list, pageSize);
			pageInfo.setList(result);
		}
		return pageInfo;

	}

	/**
	 * 根据帖子id查帖子信息，包括上一条、下一条
	 */
	@Override
	@Cacheable(value = "blogWebCenter", key = "#root.methodName + '_' + #id")
	public BlogArticleSelect selectById(String id) {
		BlogArticleSelect blogArticleSelect = articleMapper.selectById(id);
		if (blogArticleSelect != null && StringUtils.isNotBlank(blogArticleSelect.getId())) {
			//评论量
			BlogArticleCommentExample example = new BlogArticleCommentExample();
			example.createCriteria().andArticleIdEqualTo(id);
			long comPv = articleCommentMapper.countByExample(example);
			blogArticleSelect.setComPv(comPv);
			// 上一条
			BlogArticle pre = articleMapper.selectByIdPre(blogArticleSelect.getCreatetime());
			blogArticleSelect.setPre(pre);
			// 下一条
			BlogArticle next = articleMapper.selectByIdNext(blogArticleSelect.getCreatetime());
			blogArticleSelect.setNext(next);
		}
		return blogArticleSelect;
	}

	/**
	 * 查询帖子数、分类数、标签数
	 */
	@Override
	@Cacheable(value = "blogWebCenter", key = "#root.methodName")
	public HashMap<String, BlogArticleClassifyLable> blogArticleClassifyLable() {
		HashMap<String, BlogArticleClassifyLable> map = new HashMap<>(5);
		// 分类数、标签数
		map.put("label", new BlogArticleClassifyLable("标签", 0));
		map.put("classify", new BlogArticleClassifyLable("分类", 0));
		List<BlogArticleClassifyLable> list = articleMapper.blogArticleClassifyLable();
		for (BlogArticleClassifyLable b : list) {
			if (b.getName().equals("0")) {
				map.put("label", b);
			} else {
				map.put("classify", b);
			}
		}
		// 帖子数
		BlogArticleClassifyLable blogArticleClassifyLable = articleMapper.blogNum();
		blogArticleClassifyLable.setName("文章");
		map.put("article", blogArticleClassifyLable);
		//访客数
		Long qqCount = qqUserMapper.countByExample(null);
		map.put("guest", new BlogArticleClassifyLable("访客", qqCount.intValue()));
		//留言数
		Long leaveCount = blogLeaveMsgMapper.countByExample(null);
		map.put("leave", new BlogArticleClassifyLable("留言", leaveCount.intValue()));
		//TODO:友站数
		return map;
	}

	/**
	 * 查询标签
	 */
	@Override
	@Cacheable(value = "blogWebCenter", key = "#root.methodName")
	public List<BlogArticleByLabels> blogArticleLables() {
		List<BlogArticleByLabels> list = articleMapper.selectByLabels();
		list.forEach(data -> {
			if (null == data.getLableid()) {
				data.setNum(0);
			}
		});
		return list;
	}

	/**
	 * 热门文章（浏览量排名）
	 */
	@Override
	@Cacheable(value = "blogWebCenter", key = "#root.methodName")
	public List<BlogArticle> hotBlogArticle() {
		List<BlogArticle> list = articleMapper.hotBlogArticle();
		return list;
	}

	/**
	 * 根据日期区分帖子
	 */
	@Override
	@Cacheable(value = "blogWebCenter", key = "#root.methodName")
	public List<BlogArticleByTime> selectByTime() {
		List<BlogArticle> list = articleMapper.selectByTime();
		List<BlogArticleByTime> result = MyUtils.TimeClassify(list);
		// 将结果集分类
		return result;
	}

	/**
	 * 根据帖子分类区分帖子
	 */
	@Override
	@Cacheable(value = "blogWebCenter", key = "#root.methodName")
	public List<BlogArticleByClassify> selectByClassify() {
		List<BlogArticleByClassify> result = articleMapper.selectByClassify();
		return result;
	}

	/**
	 * 评论回复
	 */
	@Override
	public PublicResultJosn articleComment(BlogArticleComment comment) {
		// 补全数据
		articleCommentMapper.insert(comment);
		return new PublicResultJosn(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), null);
	}

	/**
	 * @Title: refreshOneArticle   
	 * @Description: 刷新帖子内容pv
	 * @param: @param article
	 * @param: @return      
	 * @return: BlogArticleSelect      
	 * @throws
	 */
	public void refreshOneArticle(BlogArticleSelect article) {
		//更新redis
		article.setPv(article.getPv() + 1);
		redisTemplate.opsForValue().set("blogWebCenter::selectById_" + article.getId(), article);
		//更新数据库pv
		articleMapper.refreshOneArticle(article.getId());
	}

	/**
	 * 
	 * @Title: setLableClassify   
	 * @Description: 设置当前文章的标签、分类
	 * @param: @param articleSelect      
	 * @return: void      
	 * @throws
	 */
	private void setLableClassify(BlogArticleSelect articleSelect) {
		String id = articleSelect.getId();
		List<BlogLabelClassify> lables = articleMapper.selectLableById(id);
		articleSelect.setLable(lables);
		List<BlogLabelClassify> classifys = articleMapper.selectClassifyById(id);
		articleSelect.setClassify(classifys);
	}

}
