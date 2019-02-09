package cn.commons.model;

import java.io.Serializable;
import java.util.Date;

public class Role implements Serializable {

	private static final long serialVersionUID = 911131978719887337L;

	/**
	 * 
	 */
	private Long id;

	/**
	 * 
	 */
	private String name;

	/**
	 * 
	 */
	private String description;

	/**
	 * 
	 */
	private Date createtime;

	/**
	 * 
	 */
	private Date updatetime = new Date();

	/**
	 * 
	 * @return id 
	 */
	public Long getId() {
		return id;
	}

	/**
	 * 
	 * @param id 
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 
	 * @return name 
	 */
	public String getName() {
		return name;
	}

	/**
	 * 
	 * @param name 
	 */
	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	/**
	 * 
	 * @return description 
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * 
	 * @param description 
	 */
	public void setDescription(String description) {
		this.description = description == null ? null : description.trim();
	}

	/**
	 * 
	 * @return createtime 
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
	 * @return updatetime 
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

}
