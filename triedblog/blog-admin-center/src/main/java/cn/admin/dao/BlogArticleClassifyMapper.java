package cn.admin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import cn.admin.entity.BlogArticleClassify;
import cn.admin.example.BlogArticleClassifyExample;

@Mapper
public interface BlogArticleClassifyMapper {
    long countByExample(BlogArticleClassifyExample example);

    int deleteByExample(BlogArticleClassifyExample example);

    int insert(BlogArticleClassify record);

    int insertSelective(BlogArticleClassify record);

    List<BlogArticleClassify> selectByExample(BlogArticleClassifyExample example);

    int updateByExampleSelective(@Param("record") BlogArticleClassify record,
            @Param("example") BlogArticleClassifyExample example);

    int updateByExample(@Param("record") BlogArticleClassify record,
            @Param("example") BlogArticleClassifyExample example);

    @Select("SELECT * FROM blog_label_classify b WHERE b.type='1' AND b.id IN (SELECT a.classifyid FROM blog_article_classify a WHERE a.articleid=#{bid})")
    List<Integer> selectByBid(String bid);

    @Delete("DELETE FROM blog_article_classify WHERE classifyid=#{id}")
    void deletebyClassifyId(Long id);
}