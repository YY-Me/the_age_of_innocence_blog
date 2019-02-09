package cn.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import cn.web.entity.BlogUser;
import cn.web.example.BlogUserExample;

@Mapper
public interface BlogUserMapper {
	long countByExample(BlogUserExample example);

	int deleteByExample(BlogUserExample example);

	int deleteByPrimaryKey(String uid);

	int insert(BlogUser record);

	int insertSelective(BlogUser record);

	List<BlogUser> selectByExample(BlogUserExample example);

	BlogUser selectByPrimaryKey(String uid);

	@Select("SELECT * FROM blog_user WHERE email=#{email}")
	BlogUser selectByUsername(String email);

	int updateByExampleSelective(@Param("record") BlogUser record, @Param("example") BlogUserExample example);

	int updateByExample(@Param("record") BlogUser record, @Param("example") BlogUserExample example);

	int updateByPrimaryKeySelective(BlogUser record);

	int updateByPrimaryKey(BlogUser record);
}