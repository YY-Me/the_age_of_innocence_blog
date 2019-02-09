package cn.admin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import cn.admin.entity.SystemDict;
import cn.admin.example.SystemDictExample;

@Mapper
public interface SystemDictMapper {
	long countByExample(SystemDictExample example);

	int deleteByExample(SystemDictExample example);

	int deleteByPrimaryKey(Long id);

	int insert(SystemDict record);

	int insertSelective(SystemDict record);

	List<SystemDict> selectByExample(SystemDictExample example);

	SystemDict selectByPrimaryKey(Long id);

	int updateByExampleSelective(@Param("record") SystemDict record, @Param("example") SystemDictExample example);

	int updateByExample(@Param("record") SystemDict record, @Param("example") SystemDictExample example);

	int updateByPrimaryKeySelective(SystemDict record);

	int updateByPrimaryKey(SystemDict record);
}