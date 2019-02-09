package cn.web.entity;

import java.io.Serializable;
import java.util.Date;

public class BlogArticle implements Serializable {

	private static final long serialVersionUID = 8669216534045386675L;

	private String id;

	private String title;

	private String summary;

	private String summaryimg;

	private String publisher;

	private String status;

	private String istop;

	private String isvisible;

	private Long pv;

	private Date createtime;

	private Date updatetime = new Date();

	private String content;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title == null ? null : title.trim();
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary == null ? null : summary.trim();
	}

	public String getSummaryimg() {
		return summaryimg;
	}

	public void setSummaryimg(String summaryimg) {
		this.summaryimg = summaryimg == null ? null : summaryimg.trim();
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher == null ? null : publisher.trim();
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status == null ? null : status.trim();
	}

	public String getIstop() {
		return istop;
	}

	public void setIstop(String istop) {
		this.istop = istop == null ? null : istop.trim();
	}

	public String getIsvisible() {
		return isvisible;
	}

	public void setIsvisible(String isvisible) {
		this.isvisible = isvisible == null ? null : isvisible.trim();
	}

	public Long getPv() {
		return pv;
	}

	public void setPv(Long pv) {
		this.pv = pv;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Date getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content == null ? null : content.trim();
	}
}