/*
*
* BlogArticleLableMapper.java
* Copyright(C) 2017-2020 fendo公司
* @date 2018-09-21
*/
package cn.admin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import cn.admin.entity.BlogArticleLable;
import cn.admin.example.BlogArticleLableExample;

@Mapper
public interface BlogArticleLableMapper {
    /**
     *
     * @mbg.generated
     */
    long countByExample(BlogArticleLableExample example);

    /**
     *
     * @mbg.generated
     */
    int deleteByExample(BlogArticleLableExample example);

    /**
     *
     * @mbg.generated
     */
    int insert(BlogArticleLable record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(BlogArticleLable record);

    /**
     *
     * @mbg.generated
     */
    List<BlogArticleLable> selectByExample(BlogArticleLableExample example);

    /**
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") BlogArticleLable record,
            @Param("example") BlogArticleLableExample example);

    /**
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") BlogArticleLable record, @Param("example") BlogArticleLableExample example);

    @Select("SELECT lableid FROM `blog_article_lable` WHERE articleid=#{bid}")
    List<Integer> selectByArticleId(String bid);

    @Delete("DELETE FROM blog_article_lable WHERE lableid=#{id}")
    void deleteByLabelId(Long id);
}