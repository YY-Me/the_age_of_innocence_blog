package cn.admin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import cn.admin.entity.SystemRoleMenu;
import cn.admin.example.SystemRoleMenuExample;

@Mapper
public interface SystemRoleMenuMapper {
	long countByExample(SystemRoleMenuExample example);

	int deleteByExample(SystemRoleMenuExample example);

	int insert(SystemRoleMenu record);

	int insertSelective(SystemRoleMenu record);

	List<SystemRoleMenu> selectByExample(SystemRoleMenuExample example);

	int updateByExampleSelective(@Param("record") SystemRoleMenu record,
			@Param("example") SystemRoleMenuExample example);

	int updateByExample(@Param("record") SystemRoleMenu record, @Param("example") SystemRoleMenuExample example);
}