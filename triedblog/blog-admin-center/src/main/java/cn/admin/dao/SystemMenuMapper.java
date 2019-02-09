package cn.admin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import cn.admin.entity.SystemMenu;
import cn.admin.example.SystemMenuExample;

@Mapper
public interface SystemMenuMapper {
	long countByExample(SystemMenuExample example);

	int deleteByExample(SystemMenuExample example);

	int deleteByPrimaryKey(Long id);

	int insert(SystemMenu record);

	int insertSelective(SystemMenu record);

	List<SystemMenu> selectByExample(SystemMenuExample example);

	SystemMenu selectByPrimaryKey(Long id);

	int updateByExampleSelective(@Param("record") SystemMenu record, @Param("example") SystemMenuExample example);

	int updateByExample(@Param("record") SystemMenu record, @Param("example") SystemMenuExample example);

	int updateByPrimaryKeySelective(SystemMenu record);

	int updateByPrimaryKey(SystemMenu record);
}