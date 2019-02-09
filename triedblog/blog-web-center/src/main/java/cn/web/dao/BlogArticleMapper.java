package cn.web.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import cn.web.dto.BlogArticleByClassify;
import cn.web.dto.BlogArticleByLabels;
import cn.web.dto.BlogArticleClassifyLable;
import cn.web.dto.BlogArticleSelect;
import cn.web.entity.BlogArticle;
import cn.web.entity.BlogLabelClassify;
import cn.web.example.BlogArticleExample;

@Mapper
public interface BlogArticleMapper {
	long countByExample(BlogArticleExample example);

	int deleteByExample(BlogArticleExample example);

	int deleteByPrimaryKey(String id);

	int insert(BlogArticle record);

	int insertSelective(BlogArticle record);

	List<BlogArticle> selectByExampleWithBLOBs(BlogArticleExample example);

	List<BlogArticle> selectByExample(BlogArticleExample example);

	/**
	 * 首页获取分页获取article并且文章是可见的（isvialble=1）,不包括帖子内容
	 * @Title: selectByPageHelper
	 * @param @return
	 * @return List<BlogArticleSelect>
	 * @throws
	 */
	@Select("SELECT blog_article.id,blog_article.title,blog_article.summaryImg,blog_article.`status`,blog_article.isTop,blog_article.isVisible,blog_article.pv,blog_article.createTime,blog_article.summary,system_user.nickname,system_user.headimgurl FROM blog_article LEFT JOIN system_user ON blog_article.publisher = system_user.uid WHERE blog_article.isVisible='1' ORDER BY blog_article.createTime DESC")
	List<BlogArticleSelect> selectByPageHelper();

	/**
	 * 
	 * @Title: selectArticleIdsByClassifyId   
	 * @Description: 根据分类id查帖子的id  
	 * @param: @param id
	 * @param: @return      
	 * @return: List<String>      
	 * @throws
	 */
	@Select("SELECT blog_article_classify.articleid FROM `blog_article_classify` WHERE classifyid=#{id}")
	List<String> selectArticleIdsByClassifyId(Integer id);

	/**
	 * 
	 * @Title: selectArticleIdsByLabelId   
	 * @Description: 根据标签id查帖子的id
	 * @param: @param id
	 * @param: @return      
	 * @return: List<String>      
	 * @throws
	 */
	@Select("SELECT blog_article_lable.articleid FROM blog_article_lable WHERE lableid=#{id}")
	List<String> selectArticleIdsByLabelId(Integer id);

	/**
	 * 
	 * @Title: selectArticleByClassifyId   
	 * @Description: 根据文章ids查询article多个信息（不包括帖子内容），并且isvialble=1
	 * @param: @param ids
	 * @param: @return      
	 * @return: List<BlogArticleSelect>      
	 * @throws
	 */
	List<BlogArticleSelect> selectArticleByIds(List<String> ids);

	/**
	 * 
	 * @Title: selectLableById   
	 * @Description: 根据文章id查标签
	 * @param: @param id
	 * @param: @return      
	 * @return: List<BlogLabelClassify>      
	 * @throws
	 */
	@Select("SELECT blog_label_classify.id,blog_label_classify.`name`,blog_label_classify.color FROM blog_article_lable,blog_label_classify WHERE blog_article_lable.lableid = blog_label_classify.id AND blog_article_lable.articleid=#{id}")
	List<BlogLabelClassify> selectLableById(String id);

	/**
	 * 
	 * @Title: selectClassifyById   
	 * @Description: 根据文章id查分类  
	 * @param: @param id
	 * @param: @return      
	 * @return: List<BlogLabelClassify>      
	 * @throws
	 */
	@Select("SELECT blog_label_classify.id,blog_label_classify.`name`,blog_label_classify.color FROM blog_article_classify,blog_label_classify WHERE blog_article_classify.classifyid = blog_label_classify.id AND blog_article_classify.articleid = #{id}")
	List<BlogLabelClassify> selectClassifyById(String id);

	/**
	 * 
	 * @Title: selectById   
	 * @Description: 根据文章id查询article，并且是可见的
	 * @param: @param id
	 * @param: @return      
	 * @return: BlogArticleSelect      
	 * @throws
	 */
	@Select("SELECT blog_article.id,blog_article.title,blog_article.summaryImg,blog_article.content,blog_article.`status`,blog_article.isTop,blog_article.isVisible,blog_article.pv,blog_article.createTime,blog_article.summary,system_user.nickname FROM blog_article LEFT JOIN system_user ON blog_article.publisher = system_user.uid WHERE blog_article.id=#{id} AND blog_article.isVisible='1'")
	BlogArticleSelect selectById(String id);

	@Select("SELECT blog_article.id,blog_article.title FROM blog_article WHERE createTime>#{date} ORDER BY id LIMIT 1")
	BlogArticle selectByIdPre(Date date);

	@Select("SELECT blog_article.id,blog_article.title FROM blog_article WHERE createTime<#{date} ORDER BY createTime DESC LIMIT 1")
	BlogArticle selectByIdNext(Date date);

	@Select("SELECT blog_article.id,blog_article.title,blog_article.pv,blog_article.createTime FROM `blog_article` WHERE blog_article.isVisible='1' ORDER BY blog_article.createTime DESC")
	List<BlogArticle> selectByTime();

	@Select("SELECT a.id,a.`name`,a.color,b.lableid,COUNT(0) num FROM blog_label_classify a LEFT JOIN blog_article_lable b ON a.id=b.lableid WHERE type = '0' GROUP BY a.id")
	List<BlogArticleByLabels> selectByLabels();

	@Select("SELECT a.id,a.`name`,COUNT(0) num FROM blog_label_classify a LEFT JOIN blog_article_classify b ON a.id=b.classifyid WHERE a.type='1' GROUP BY a.id")
	List<BlogArticleByClassify> selectByClassify();

	@Select("SELECT type name,COUNT(type) num FROM `blog_label_classify` GROUP BY type")
	List<BlogArticleClassifyLable> blogArticleClassifyLable();

	@Select("SELECT COUNT(id) num FROM `blog_article` WHERE blog_article.isVisible='1'")
	BlogArticleClassifyLable blogNum();

	@Select("SELECT id,`name`,color FROM blog_label_classify WHERE blog_label_classify.`type`='0'")
	List<String> blogArticleLables();

	@Select("SELECT blog_article.id,blog_article.title,blog_article.summaryImg,blog_article.pv,blog_article.createTime FROM `blog_article` WHERE blog_article.isVisible='1' ORDER BY blog_article.pv DESC LIMIT 0, 6")
	List<BlogArticle> hotBlogArticle();

	BlogArticle selectByPrimaryKey(String id);

	int updateByExampleSelective(@Param("record") BlogArticle record, @Param("example") BlogArticleExample example);

	int updateByExampleWithBLOBs(@Param("record") BlogArticle record, @Param("example") BlogArticleExample example);

	int updateByExample(@Param("record") BlogArticle record, @Param("example") BlogArticleExample example);

	int updateByPrimaryKeySelective(BlogArticle record);

	int updateByPrimaryKeyWithBLOBs(BlogArticle record);

	int updateByPrimaryKey(BlogArticle record);

	/**
	 * @Title: refreshOneArticle   
	 * @Description: 刷新帖子内容pv  
	 * @param: @param id      
	 * @return: void      
	 * @throws
	 */
	@Update("UPDATE blog_article SET blog_article.pv=(blog_article.pv+1) WHERE blog_article.id=#{id}")
	void refreshOneArticle(String id);
}