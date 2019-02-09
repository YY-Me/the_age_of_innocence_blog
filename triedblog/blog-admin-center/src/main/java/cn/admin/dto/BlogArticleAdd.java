package cn.admin.dto;

import java.util.List;

import cn.admin.entity.BlogArticle;

public class BlogArticleAdd extends BlogArticle {

	// 文章标签
	private List<Integer> labelIds;

	// 文章分类
	private List<Integer> classifyIds;

	public List<Integer> getLabelIds() {
		return labelIds;
	}

	public void setLabelIds(List<Integer> labelIds) {
		this.labelIds = labelIds;
	}

	public List<Integer> getClassifyIds() {
		return classifyIds;
	}

	public void setClassifyIds(List<Integer> classifyIds) {
		this.classifyIds = classifyIds;
	}

}
