/*
*
* BlogArticle.java
* Copyright(C) 2017-2020 fendo公司
* @date 2018-09-17
*/
package cn.admin.entity;

import java.util.Date;

public class BlogArticle {
	/**
	 * 文章id
	 */
	private String id;

	/**
	 * 文章标题
	 */
	private String title;

	/**
	 * 文章摘要（简介）
	 */
	private String summary;

	/**
	 * 摘要图片，封面
	 */
	private String summaryimg;

	/**
	 * 发布人id
	 */
	private String publisher;

	/**
	 * 状态，具体到字典数据库看
	 */
	private String status;

	/**
	 * 1:顶置，0:默认不顶置
	 */
	private String istop;

	/**
	 * 1:显示，0:默认不显示，需要手动设置显示
	 */
	private String isvisible;

	/**
	 * 浏览量
	 */
	private Long pv;

	/**
	 * 
	 */
	private Date createtime;

	/**
	 * 
	 */
	private Date updatetime = new Date();

	/**
	 * 文章内容
	 */
	private String content;

	/**
	 * 文章id
	 * @return id 文章id
	 */
	public String getId() {
		return id;
	}

	/**
	 * 文章id
	 * @param id 文章id
	 */
	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	/**
	 * 文章标题
	 * @return title 文章标题
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * 文章标题
	 * @param title 文章标题
	 */
	public void setTitle(String title) {
		this.title = title == null ? null : title.trim();
	}

	/**
	 * 文章摘要（简介）
	 * @return summary 文章摘要（简介）
	 */
	public String getSummary() {
		return summary;
	}

	/**
	 * 文章摘要（简介）
	 * @param summary 文章摘要（简介）
	 */
	public void setSummary(String summary) {
		this.summary = summary == null ? null : summary.trim();
	}

	/**
	 * 摘要图片，封面
	 * @return summaryImg 摘要图片，封面
	 */
	public String getSummaryimg() {
		return summaryimg;
	}

	/**
	 * 摘要图片，封面
	 * @param summaryimg 摘要图片，封面
	 */
	public void setSummaryimg(String summaryimg) {
		this.summaryimg = summaryimg == null ? null : summaryimg.trim();
	}

	/**
	 * 发布人id
	 * @return publisher 发布人id
	 */
	public String getPublisher() {
		return publisher;
	}

	/**
	 * 发布人id
	 * @param publisher 发布人id
	 */
	public void setPublisher(String publisher) {
		this.publisher = publisher == null ? null : publisher.trim();
	}

	/**
	 * 状态，具体到字典数据库看
	 * @return status 状态，具体到字典数据库看
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * 状态，具体到字典数据库看
	 * @param status 状态，具体到字典数据库看
	 */
	public void setStatus(String status) {
		this.status = status == null ? null : status.trim();
	}

	/**
	 * 1:顶置，0:默认不顶置
	 * @return isTop 1:顶置，0:默认不顶置
	 */
	public String getIstop() {
		return istop;
	}

	/**
	 * 1:顶置，0:默认不顶置
	 * @param istop 1:顶置，0:默认不顶置
	 */
	public void setIstop(String istop) {
		this.istop = istop == null ? null : istop.trim();
	}

	/**
	 * 1:显示，0:默认不显示，需要手动设置显示
	 * @return isVisible 1:显示，0:默认不显示，需要手动设置显示
	 */
	public String getIsvisible() {
		return isvisible;
	}

	/**
	 * 1:显示，0:默认不显示，需要手动设置显示
	 * @param isvisible 1:显示，0:默认不显示，需要手动设置显示
	 */
	public void setIsvisible(String isvisible) {
		this.isvisible = isvisible == null ? null : isvisible.trim();
	}

	/**
	 * 浏览量
	 * @return pv 浏览量
	 */
	public Long getPv() {
		return pv;
	}

	/**
	 * 浏览量
	 * @param pv 浏览量
	 */
	public void setPv(Long pv) {
		this.pv = pv;
	}

	/**
	 * 
	 * @return createTime 
	 */
	public Date getCreatetime() {
		return createtime;
	}

	/**
	 * 
	 * @param createtime 
	 */
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	/**
	 * 
	 * @return updateTime 
	 */
	public Date getUpdatetime() {
		return updatetime;
	}

	/**
	 * 
	 * @param updatetime 
	 */
	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}

	/**
	 * 文章内容
	 * @return content 文章内容
	 */
	public String getContent() {
		return content;
	}

	/**
	 * 文章内容
	 * @param content 文章内容
	 */
	public void setContent(String content) {
		this.content = content == null ? null : content.trim();
	}
}