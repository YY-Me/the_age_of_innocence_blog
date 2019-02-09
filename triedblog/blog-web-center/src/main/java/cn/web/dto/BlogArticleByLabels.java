package cn.web.dto;

import java.io.Serializable;

public class BlogArticleByLabels implements Serializable {

	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */
	private static final long serialVersionUID = 3733529925420870982L;

	private Integer id;

	private String name;

	private String color;

	private Integer lableid;

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

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Integer getLableid() {
		return lableid;
	}

	public void setLableid(Integer lableid) {
		this.lableid = lableid;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

}
