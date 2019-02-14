package cn.web.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;

import cn.commons.common.PublicResultJosn;
import cn.web.dto.BlogArticleByClassify;
import cn.web.dto.BlogArticleByLabels;
import cn.web.dto.BlogArticleByTime;
import cn.web.dto.BlogArticleClassifyLable;
import cn.web.dto.BlogArticleSelect;
import cn.web.entity.BlogArticle;
import cn.web.service.ArticleService;
import cn.web.service.BlogArticleCommentService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/article")
public class ArticleController {

	@Autowired
	private ArticleService articleService;

	@Autowired
	private BlogArticleCommentService articleCommentService;

	@GetMapping
	@ApiOperation(value = "首页获取分页查询article,不包括帖子内容")
	public PageInfo<BlogArticleSelect> selectByExample(@RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "10") Integer pageSize) {
		PageInfo<BlogArticleSelect> selectByExample = articleService.selectByExample(page, pageSize);
		return selectByExample;
	}

	@GetMapping("/classify/{id}")
	@ApiOperation(value = "根据 分类id 分页获取article,不包括帖子内容")
	public PageInfo<BlogArticleSelect> selectByClassify(@PathVariable Integer id,
			@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer pageSize) {
		PageInfo<BlogArticleSelect> result = articleService.selectByClassify(id, page, pageSize);
		return result;
	}

	@GetMapping("/label/{id}")
	@ApiOperation(value = "根据 标签id 分页获取article,不包括帖子内容")
	public PageInfo<BlogArticleSelect> selectByLabelId(@PathVariable Integer id,
			@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer pageSize) {
		PageInfo<BlogArticleSelect> result = articleService.selectByLabelId(id, page, pageSize);
		return result;
	}

	@GetMapping("/{id}")
	@ApiOperation(value = "根据id获取article信息，包括上一条、下一条")
	public Object selectById(@PathVariable String id) {
		BlogArticleSelect selectById = articleService.selectById(id);
		refreshOneArticle(selectById);
		return selectById;
	}

	@GetMapping("/blogArticleClassifyLable")
	@ApiOperation(value = "查询帖子数、分类数、标签数、访客数、留言数、友站数")
	public HashMap<String, BlogArticleClassifyLable> blogArticleClassifyLable() {
		return articleService.blogArticleClassifyLable();
	}

	@GetMapping("/blogArticleLable")
	@ApiOperation(value = "查询所有标签")
	public List<BlogArticleByLabels> blogArticleLables() {
		return articleService.blogArticleLables();
	}

	@GetMapping("/classify")
	@ApiOperation(value = "根据分类来区分帖子（比如：校园时光（5））")
	public List<BlogArticleByClassify> selectByClassify() {
		return articleService.selectByClassify();
	}

	@GetMapping("/hotBlogArticle")
	@ApiOperation(value = "查询热门文章（根据article 的pv来查询）")
	public List<BlogArticle> hotBlogArticle() {
		return articleService.hotBlogArticle();
	}

	@GetMapping("/time")
	@ApiOperation(value = "根据时间段区分帖子")
	public List<BlogArticleByTime> selectByTime() {
		return articleService.selectByTime();
	}

	@GetMapping("/comment/{articleId}")
	@ApiOperation(value = "根据article的id查询文章评论")
	public PublicResultJosn articleComment(@PathVariable String articleId,
			@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "5") Integer pageSize) {
		return articleCommentService.selectCommentById(articleId, page, pageSize);
	}

	/**
	 * 
	 * @Title: refreshOneArticle   
	 * @Description: 刷新pv，更新缓存  
	 * @param: @param id      
	 * @return: void      
	 * @throws
	 */
	@Async
	public void refreshOneArticle(BlogArticleSelect article) {
		articleService.refreshOneArticle(article);
	}

}
