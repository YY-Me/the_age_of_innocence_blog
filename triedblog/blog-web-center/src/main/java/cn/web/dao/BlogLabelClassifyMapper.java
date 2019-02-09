/*
*
* BlogLabelClassifyMapper.java
* Copyright(C) 2017-2020 fendo公司
* @date 2018-09-21
*/
package cn.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import cn.web.entity.BlogLabelClassify;
import cn.web.example.BlogLabelClassifyExample;

@Mapper
public interface BlogLabelClassifyMapper {
	/**
	 *
	 * @mbg.generated
	 */
	long countByExample(BlogLabelClassifyExample example);

	/**
	 *
	 * @mbg.generated
	 */
	int deleteByExample(BlogLabelClassifyExample example);

	/**
	 *
	 * @mbg.generated
	 */
	int deleteByPrimaryKey(Long id);

	/**
	 *
	 * @mbg.generated
	 */
	int insert(BlogLabelClassify record);

	/**
	 *
	 * @mbg.generated
	 */
	int insertSelective(BlogLabelClassify record);

	/**
	 *
	 * @mbg.generated
	 */
	List<BlogLabelClassify> selectByExample(BlogLabelClassifyExample example);

	/**
	 *
	 * @mbg.generated
	 */
	BlogLabelClassify selectByPrimaryKey(Long id);

	/**
	 *
	 * @mbg.generated
	 */
	int updateByExampleSelective(@Param("record") BlogLabelClassify record,
			@Param("example") BlogLabelClassifyExample example);

	/**
	 *
	 * @mbg.generated
	 */
	int updateByExample(@Param("record") BlogLabelClassify record, @Param("example") BlogLabelClassifyExample example);

	/**
	 *
	 * @mbg.generated
	 */
	int updateByPrimaryKeySelective(BlogLabelClassify record);

	/**
	 *
	 * @mbg.generated
	 */
	int updateByPrimaryKey(BlogLabelClassify record);
}