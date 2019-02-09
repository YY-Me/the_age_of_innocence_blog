package cn.web.dto;

import java.io.Serializable;

public class BlogArticleByClassify implements Serializable {

	private static final long serialVersionUID = -305754045584590747L;

	private Integer id;

	private String name;

	private Integer num;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
