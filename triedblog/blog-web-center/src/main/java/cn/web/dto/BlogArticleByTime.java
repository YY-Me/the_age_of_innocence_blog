package cn.web.dto;

import java.io.Serializable;
import java.util.List;

import cn.web.entity.BlogArticle;

public class BlogArticleByTime implements Serializable {

	private static final long serialVersionUID = -3639640973889193822L;

	// 名称（例：2018年5月）
	private String name;
	// 这个月的帖子
	private List<BlogArticle> data;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<BlogArticle> getData() {
		return data;
	}

	public void setData(List<BlogArticle> data) {
		this.data = data;
	}

}
