package cn.admin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import cn.admin.entity.SystemUserRole;
import cn.admin.example.SystemUserRoleExample;

@Mapper
public interface SystemUserRoleMapper {
	long countByExample(SystemUserRoleExample example);

	int deleteByExample(SystemUserRoleExample example);

	int insert(SystemUserRole record);

	int insertSelective(SystemUserRole record);

	List<SystemUserRole> selectByExample(SystemUserRoleExample example);

	int updateByExampleSelective(@Param("record") SystemUserRole record,
			@Param("example") SystemUserRoleExample example);

	int updateByExample(@Param("record") SystemUserRole record, @Param("example") SystemUserRoleExample example);
}