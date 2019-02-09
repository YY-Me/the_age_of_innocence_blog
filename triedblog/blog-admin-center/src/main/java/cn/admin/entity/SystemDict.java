/*
*
* SystemDict.java
* Copyright(C) 2017-2020 fendo公司
* @date 2018-09-17
*/
package cn.admin.entity;

import java.util.Date;

public class SystemDict {
	/**
	 * 
	 */
	private Long id;

	/**
	 * 
	 */
	private String type;

	/**
	 * 
	 */
	private String k;

	/**
	 * 
	 */
	private String v;

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
	 * @return type 
	 */
	public String getType() {
		return type;
	}

	/**
	 * 
	 * @param type 
	 */
	public void setType(String type) {
		this.type = type == null ? null : type.trim();
	}

	/**
	 * 
	 * @return k 
	 */
	public String getK() {
		return k;
	}

	/**
	 * 
	 * @param k 
	 */
	public void setK(String k) {
		this.k = k == null ? null : k.trim();
	}

	/**
	 * 
	 * @return v 
	 */
	public String getV() {
		return v;
	}

	/**
	 * 
	 * @param v 
	 */
	public void setV(String v) {
		this.v = v == null ? null : v.trim();
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
}