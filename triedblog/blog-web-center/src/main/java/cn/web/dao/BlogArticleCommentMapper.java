package cn.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import cn.web.entity.BlogArticleComment;
import cn.web.example.BlogArticleCommentExample;

@Mapper
public interface BlogArticleCommentMapper {
	long countByExample(BlogArticleCommentExample example);

	int deleteByExample(BlogArticleCommentExample example);

	int deleteByPrimaryKey(Long commentId);

	int insert(BlogArticleComment record);

	int insertSelective(BlogArticleComment record);

	List<BlogArticleComment> selectByExampleWithBLOBs(BlogArticleCommentExample example);

	List<BlogArticleComment> selectByExample(BlogArticleCommentExample example);

	BlogArticleComment selectByPrimaryKey(Long commentId);

	int updateByExampleSelective(@Param("record") BlogArticleComment record,
			@Param("example") BlogArticleCommentExample example);

	int updateByExampleWithBLOBs(@Param("record") BlogArticleComment record,
			@Param("example") BlogArticleCommentExample example);

	int updateByExample(@Param("record") BlogArticleComment record,
			@Param("example") BlogArticleCommentExample example);

	int updateByPrimaryKeySelective(BlogArticleComment record);

	int updateByPrimaryKeyWithBLOBs(BlogArticleComment record);

	int updateByPrimaryKey(BlogArticleComment record);
}