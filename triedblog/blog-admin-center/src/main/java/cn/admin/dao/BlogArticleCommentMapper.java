/*
*
* BlogArticleCommentMapper.java
* Copyright(C) 2017-2020 fendo公司
* @date 2018-09-01
*/
package cn.admin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import cn.admin.entity.BlogArticleComment;
import cn.admin.example.BlogArticleCommentExample;

@Mapper
public interface BlogArticleCommentMapper {
    /**
     *
     * @mbg.generated
     */
    long countByExample(BlogArticleCommentExample example);

    /**
     *
     * @mbg.generated
     */
    int deleteByExample(BlogArticleCommentExample example);

    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long commentId);

    /**
     *
     * @mbg.generated
     */
    int insert(BlogArticleComment record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(BlogArticleComment record);

    /**
     *
     * @mbg.generated
     */
    List<BlogArticleComment> selectByExampleWithBLOBs(BlogArticleCommentExample example);

    /**
     *
     * @mbg.generated
     */
    List<BlogArticleComment> selectByExample(BlogArticleCommentExample example);

    /**
     *
     * @mbg.generated
     */
    BlogArticleComment selectByPrimaryKey(Long commentId);

    /**
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") BlogArticleComment record,
            @Param("example") BlogArticleCommentExample example);

    /**
     *
     * @mbg.generated
     */
    int updateByExampleWithBLOBs(@Param("record") BlogArticleComment record,
            @Param("example") BlogArticleCommentExample example);

    /**
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") BlogArticleComment record,
            @Param("example") BlogArticleCommentExample example);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(BlogArticleComment record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeyWithBLOBs(BlogArticleComment record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(BlogArticleComment record);
}