package cn.oauth2.dao;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import cn.commons.model.Menu;
import cn.commons.model.Role;
import cn.oauth2.entity.SystemUser;

@Mapper
public interface UserDao {

	@Select("SELECT DISTINCT system_user.* FROM `system_user` WHERE system_user.username = #{username}")
	SystemUser selectByUserName(String username);

	@Select("SELECT system_user_role.roleid FROM `system_user_role` WHERE system_user_role.userid=#{uid}")
	List<Long> selectRoleByUid(String uid);

	Set<Role> selectRoleInUid(@Param("list") List<Long> list);

	List<Long> selectMenuByRid(@Param("list") List<Long> list);

	Set<Menu> selectMenuInRid(@Param("list") List<Long> list);

}
