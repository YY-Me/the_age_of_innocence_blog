package cn.admin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import cn.admin.dto.SystemUserSelect;
import cn.admin.entity.SystemUser;
import cn.admin.example.SystemUserExample;

@Mapper
public interface SystemUserMapper {
	long countByExample(SystemUserExample example);

	int deleteByExample(SystemUserExample example);

	int deleteByPrimaryKey(String uid);

	int insert(SystemUser record);

	int insertSelective(SystemUser record);

	List<SystemUser> selectByExample(SystemUserExample example);

	SystemUser selectByPrimaryKey(String uid);

	int updateByExampleSelective(@Param("record") SystemUser record, @Param("example") SystemUserExample example);

	int updateByExample(@Param("record") SystemUser record, @Param("example") SystemUserExample example);

	int updateByPrimaryKeySelective(SystemUser record);

	int updateByPrimaryKey(SystemUser record);

	SystemUserSelect selectByUid(String uid);
}