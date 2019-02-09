package cn.web.entity;

import java.util.Date;

public class BlogArticleComment {
	private Long commentId;

	private String articleId;

	private Long parentId;

	private String fromId;

	private String fromName;

	private String fromHeadImg;

	private String toId;

	private String toName;

	private Long likeNum;

	private String areaIp;

	private String area;

	private Date createTime = new Date();

	private String content;

	public Long getCommentId() {
		return commentId;
	}

	public void setCommentId(Long commentId) {
		this.commentId = commentId;
	}

	public String getArticleId() {
		return articleId;
	}

	public void setArticleId(String articleId) {
		this.articleId = articleId == null ? null : articleId.trim();
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getFromId() {
		return fromId;
	}

	public void setFromId(String fromId) {
		this.fromId = fromId == null ? null : fromId.trim();
	}

	public String getFromName() {
		return fromName;
	}

	public void setFromName(String fromName) {
		this.fromName = fromName == null ? null : fromName.trim();
	}

	public String getFromHeadImg() {
		return fromHeadImg;
	}

	public void setFromHeadImg(String fromHeadImg) {
		this.fromHeadImg = fromHeadImg == null ? null : fromHeadImg.trim();
	}

	public String getToId() {
		return toId;
	}

	public void setToId(String toId) {
		this.toId = toId == null ? null : toId.trim();
	}

	public String getToName() {
		return toName;
	}

	public void setToName(String toName) {
		this.toName = toName == null ? null : toName.trim();
	}

	public Long getLikeNum() {
		return likeNum;
	}

	public void setLikeNum(Long likeNum) {
		this.likeNum = likeNum;
	}

	public String getAreaIp() {
		return areaIp;
	}

	public void setAreaIp(String areaIp) {
		this.areaIp = areaIp == null ? null : areaIp.trim();
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area == null ? null : area.trim();
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content == null ? null : content.trim();
	}
}