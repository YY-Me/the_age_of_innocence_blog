package cn.admin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import cn.admin.entity.BlogArticle;
import cn.admin.example.BlogArticleExample;

@Mapper
public interface BlogArticleMapper {
	long countByExample(BlogArticleExample example);

	int deleteByExample(BlogArticleExample example);

	int deleteByPrimaryKey(String id);

	int insert(BlogArticle record);

	int insertSelective(BlogArticle record);

	List<BlogArticle> selectByExampleWithBLOBs(BlogArticleExample example);

	List<BlogArticle> selectByExample(BlogArticleExample example);

	BlogArticle selectByPrimaryKey(String id);

	int updateByExampleSelective(@Param("record") BlogArticle record, @Param("example") BlogArticleExample example);

	int updateByExampleWithBLOBs(@Param("record") BlogArticle record, @Param("example") BlogArticleExample example);

	int updateByExample(@Param("record") BlogArticle record, @Param("example") BlogArticleExample example);

	int updateByPrimaryKeySelective(BlogArticle record);

	int updateByPrimaryKeyWithBLOBs(BlogArticle record);

	int updateByPrimaryKey(BlogArticle record);
}