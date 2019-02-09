package cn.web.service;

import java.util.List;

import com.github.pagehelper.PageInfo;

import cn.commons.common.PublicResultJosn;
import cn.web.dto.BlogArticleByClassify;
import cn.web.dto.BlogArticleByLabels;
import cn.web.dto.BlogArticleByTime;
import cn.web.dto.BlogArticleClassifyLable;
import cn.web.dto.BlogArticleSelect;
import cn.web.entity.BlogArticle;
import cn.web.entity.BlogArticleComment;

public interface ArticleService {

	PageInfo<BlogArticleSelect> selectByExample(Integer page, Integer pageSize);

	BlogArticleSelect selectById(String id);

	List<BlogArticleByTime> selectByTime();

	List<BlogArticleByClassify> selectByClassify();

	List<BlogArticleClassifyLable> blogArticleClassifyLable();

	List<BlogArticleByLabels> blogArticleLables();

	List<BlogArticle> hotBlogArticle();

	PageInfo<BlogArticleSelect> selectByClassify(Integer id, Integer page, Integer pageSize);

	PageInfo<BlogArticleSelect> selectByLabelId(Integer id, Integer page, Integer pageSize);

	PublicResultJosn articleComment(BlogArticleComment comment);

	void refreshOneArticle(BlogArticleSelect article);

}
