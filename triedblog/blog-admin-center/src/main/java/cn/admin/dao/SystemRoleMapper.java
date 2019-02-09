package cn.admin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import cn.admin.dto.SystemRoleSelect;
import cn.admin.entity.SystemRole;
import cn.admin.example.SystemRoleExample;

@Mapper
public interface SystemRoleMapper {
	long countByExample(SystemRoleExample example);

	int deleteByExample(SystemRoleExample example);

	int deleteByPrimaryKey(Long id);

	int insert(SystemRole record);

	int insertSelective(SystemRole record);

	List<SystemRole> selectByExample(SystemRoleExample example);

	SystemRole selectByPrimaryKey(Long id);

	int updateByExampleSelective(@Param("record") SystemRole record, @Param("example") SystemRoleExample example);

	int updateByExample(@Param("record") SystemRole record, @Param("example") SystemRoleExample example);

	int updateByPrimaryKeySelective(SystemRole record);

	int updateByPrimaryKey(SystemRole record);

	SystemRoleSelect selectByid(Long id);
}