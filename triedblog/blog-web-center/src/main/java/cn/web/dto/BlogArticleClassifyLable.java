package cn.web.dto;

import java.io.Serializable;

/**
 * 
 * @author 偶尔有点困
 * @date 2018年6月11日
 */
public class BlogArticleClassifyLable implements Serializable {

	private static final long serialVersionUID = 5033687860180155288L;

	private String name;

	private Integer num;

	public BlogArticleClassifyLable() {
		super();
	}

	public BlogArticleClassifyLable(String name, Integer num) {
		super();
		this.name = name;
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

}
