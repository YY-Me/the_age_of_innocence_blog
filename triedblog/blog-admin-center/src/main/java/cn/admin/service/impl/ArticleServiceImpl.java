package cn.admin.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.admin.dao.BlogArticleClassifyMapper;
import cn.admin.dao.BlogArticleCommentMapper;
import cn.admin.dao.BlogArticleLableMapper;
import cn.admin.dao.BlogArticleMapper;
import cn.admin.dto.BlogArticleAdd;
import cn.admin.dto.BlogArticleSelect;
import cn.admin.entity.BlogArticle;
import cn.admin.entity.BlogArticleClassify;
import cn.admin.entity.BlogArticleLable;
import cn.admin.example.BlogArticleClassifyExample;
import cn.admin.example.BlogArticleCommentExample;
import cn.admin.example.BlogArticleExample;
import cn.admin.example.BlogArticleLableExample;
import cn.admin.example.BlogArticleExample.Criteria;
import cn.admin.plugin.spring.CacheRemove;
import cn.admin.service.ArticleService;
import cn.commons.common.LayuiTableResult;
import cn.commons.common.PublicResultJosn;

@Service
public class ArticleServiceImpl implements ArticleService {

	@Autowired
	private BlogArticleMapper articleMapper;

	@Autowired
	private BlogArticleLableMapper articleLableMapper;

	@Autowired
	private BlogArticleClassifyMapper articleClassifyMapper;

	@Autowired
	private BlogArticleCommentMapper blogArticleCommentMapper;

	/**
	 * 添加文章
	 */
	@Override
	@Transactional
	@CacheRemove(value = "blogWebCenter", key = { "blogArticleClassifyLable", "hotBlogArticle", "selectByClassify",
			"selectByExample", "selectByTime", "blogArticleLables" })
	public PublicResultJosn add(BlogArticleAdd blog) {
		// 补全字段
		blog.setId(Long.toString(System.currentTimeMillis()));// id
		blog.setCreatetime(new Date());
		blog.setPv((long) 0);
		if (StringUtils.isBlank(blog.getStatus())) {
			blog.setStatus("1");// 状态 1:审核通过，0:不通过
		}
		if (StringUtils.isBlank(blog.getIstop())) {
			blog.setIstop("0");// 是否顶置 1:顶置，0:默认不顶置
		}
		if (StringUtils.isBlank(blog.getIsvisible())) {
			blog.setIsvisible("0");// 是否可见 1:显示，0:默认不显示，需要手动设置显示
		}
		// 插入文章
		articleMapper.insert((BlogArticle) blog);
		// 添加标签
		this.saveLable(blog.getId(), blog.getLabelIds());
		// 添加分类
		this.saveClassify(blog.getId(), blog.getClassifyIds());
		return new PublicResultJosn(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), null);
	}

	/**
	 * 单、批量删除
	 */
	@Override
	@Transactional
	@CacheRemove(value = "blogWebCenter", key = { "blogArticleClassifyLable", "hotBlogArticle", "selectByClassify",
			"selectByExample", "selectByTime", "blogArticleLables" })
	public PublicResultJosn delete(List<String> bids) {
		// 删除条件
		BlogArticleExample example = new BlogArticleExample();
		Criteria criteria = example.createCriteria();
		criteria.andIdIn(bids);
		// 删除关联标签
		this.deleteLable(bids);
		// 删除关联分类
		this.deleteClassify(bids);
		// 删除关联评论或留言
		this.deleteDiscuss(bids);
		//删除文章
		articleMapper.deleteByExample(example);
		return new PublicResultJosn(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), null);
	}

	@Override
	@Transactional
	@CacheRemove(value = "blogWebCenter", key = { "blogArticleClassifyLable", "hotBlogArticle", "selectByClassify",
			"selectByExample", "selectByTime", "selectById", "blogArticleClassifyLable" })
	public PublicResultJosn updateByExample(BlogArticleAdd blog, Boolean flag) {
		// 更新信息
		articleMapper.updateByPrimaryKeySelective(blog);
		if (flag) {
			// 添加标签
			this.saveLable(blog.getId(), blog.getLabelIds());
			// 添加分类
			this.saveClassify(blog.getId(), blog.getClassifyIds());
		}
		return new PublicResultJosn(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), null);
	}

	/**
	 * 模糊查询
	 */
	@Override
	public LayuiTableResult selectByExample(BlogArticleAdd blog, Integer page, Integer pageSize) {
		LayuiTableResult resultJosn = new LayuiTableResult();
		// 构造条件
		BlogArticleExample example = new BlogArticleExample();
		example.setOrderByClause("createTime DESC");
		Criteria criteria = example.createCriteria();
		if (StringUtils.isNotBlank(blog.getId())) {
			criteria.andIdEqualTo(blog.getId());
		} else {
			if (StringUtils.isNotBlank(blog.getTitle())) {
				criteria.andTitleLike("%" + blog.getTitle() + "%");
			}
			if (StringUtils.isNotBlank(blog.getPublisher())) {

			}
			if (StringUtils.isNotBlank(blog.getStatus())) {
				criteria.andStatusEqualTo(blog.getStatus());
			}
			if (StringUtils.isNotBlank(blog.getIstop())) {
				criteria.andIstopEqualTo(blog.getIstop());
			}
			if (StringUtils.isNotBlank(blog.getIsvisible())) {
				criteria.andIsvisibleEqualTo(blog.getIsvisible());
			}
		}
		// 开启分页查找
		PageHelper.startPage(page, pageSize);
		List<BlogArticle> list = articleMapper.selectByExample(example);
		PageInfo<BlogArticle> pageInfo = new PageInfo<>(list, pageSize);
		//
		resultJosn.setCode(HttpStatus.OK.value());
		resultJosn.setMessage(HttpStatus.OK.getReasonPhrase());
		resultJosn.setCount(pageInfo.getTotal());
		resultJosn.setData(pageInfo.getList());
		return resultJosn;
	}

	/**
	 * 根据id查找文章、包括分类，标签
	 */
	@Override
	public PublicResultJosn selectByBid(String bid) {
		BlogArticle article = articleMapper.selectByPrimaryKey(bid);
		// 查标签
		List<Integer> lables = articleLableMapper.selectByArticleId(bid);
		// 查分类
		List<Integer> classifys = articleClassifyMapper.selectByBid(bid);
		BlogArticleSelect result = new BlogArticleSelect();
		BeanUtils.copyProperties(article, result);
		result.setLables(lables);
		result.setClassifys(classifys);
		return new PublicResultJosn(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), result);
	}

	/**
	 * 保存标签
	 * 
	 * @param aid
	 * @param ids
	 */
	private void saveLable(String bid, List<Integer> ids) {
		// 删除原有的
		List<String> bids = new ArrayList<>();
		bids.add(bid);
		this.deleteLable(bids);
		// 添加
		BlogArticleLable record = new BlogArticleLable();
		record.setArticleid(bid);
		for (Integer i : ids) {
			record.setLableid(i);
			articleLableMapper.insert(record);
		}
	}

	/**
	 * 保存分类
	 * 
	 * @param aid
	 * @param ids
	 */
	private void saveClassify(String bid, List<Integer> ids) {
		// 删除原有的
		List<String> bids = new ArrayList<>();
		bids.add(bid);
		this.deleteClassify(bids);
		// 添加
		BlogArticleClassify record = new BlogArticleClassify();
		record.setArticleid(bid);
		for (Integer i : ids) {
			record.setClassifyid(i);
			articleClassifyMapper.insert(record);
		}
	}

	/**
	 * 删除文章关联标签
	 * 
	 * @param aid
	 */
	private void deleteLable(List<String> bids) {
		BlogArticleLableExample example = new BlogArticleLableExample();
		example.createCriteria().andArticleidIn(bids);
		articleLableMapper.deleteByExample(example);
	}

	/**
	 * 删除文章关联分类
	 * 
	 * @param aid
	 */
	private void deleteClassify(List<String> bids) {
		BlogArticleClassifyExample example = new BlogArticleClassifyExample();
		cn.admin.example.BlogArticleClassifyExample.Criteria criteria = example.createCriteria();
		criteria.andArticleidIn(bids);
		articleClassifyMapper.deleteByExample(example);
	}

	/**
	 * 删除文章关联的评论
	 * 
	 * @param aid
	 */
	private void deleteDiscuss(List<String> bids) {
		BlogArticleCommentExample example = new BlogArticleCommentExample();
		example.createCriteria().andArticleIdIn(bids);
		blogArticleCommentMapper.deleteByExample(example);
	}

}
