/*
*
* BlogQQUserMapper.java
* Copyright(C) 2017-2020 fendo公司
* @date 2018-09-22
*/
package cn.admin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import cn.admin.entity.BlogQQUser;
import cn.admin.example.BlogQQUserExample;

@Mapper
public interface BlogQQUserMapper {
    /**
     *
     * @mbg.generated
     */
    long countByExample(BlogQQUserExample example);

    /**
     *
     * @mbg.generated
     */
    int deleteByExample(BlogQQUserExample example);

    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(String openid);

    /**
     *
     * @mbg.generated
     */
    int insert(BlogQQUser record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(BlogQQUser record);

    /**
     *
     * @mbg.generated
     */
    List<BlogQQUser> selectByExample(BlogQQUserExample example);

    /**
     *
     * @mbg.generated
     */
    BlogQQUser selectByPrimaryKey(String openid);

    /**
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") BlogQQUser record, @Param("example") BlogQQUserExample example);

    /**
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") BlogQQUser record, @Param("example") BlogQQUserExample example);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(BlogQQUser record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(BlogQQUser record);
}