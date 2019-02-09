package cn.admin.dto;

import java.util.List;

import cn.admin.entity.BlogArticle;

public class BlogArticleSelect extends BlogArticle {

	// 文章标签
	private List<Integer> lables;

	// 文章分类
	private List<Integer> classifys;

	public List<Integer> getLables() {
		return lables;
	}

	public void setLables(List<Integer> lables) {
		this.lables = lables;
	}

	public List<Integer> getClassifys() {
		return classifys;
	}

	public void setClassifys(List<Integer> classifys) {
		this.classifys = classifys;
	}

}
