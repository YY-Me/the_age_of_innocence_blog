package cn.web.service;

import cn.commons.common.PublicResultJosn;
import cn.web.dto.LayuiFlowArticleComment;
import cn.web.entity.BlogArticleComment;

public interface BlogArticleCommentService {

	/**
	 * 插入一条评论
	 * 
	 * @author 余勇
	 * @email 1396513066@qq.com
	 * @date 2018年6月30日
	 */
	PublicResultJosn add(BlogArticleComment blogArticleComment);

	/**
	 * 根据帖子id分页查询评论列表
	 * 
	 * @author 余勇
	 * @email 1396513066@qq.com
	 * @date 2018年6月30日
	 */
	LayuiFlowArticleComment selectCommentById(String articleId, Integer page, Integer pageSize);

}
