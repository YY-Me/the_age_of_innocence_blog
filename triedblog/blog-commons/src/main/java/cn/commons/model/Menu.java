package cn.commons.model;

import java.io.Serializable;

public class Menu implements Serializable {

	private static final long serialVersionUID = -5847807676132649472L;

	/**
	 * 主键
	 */
	private Long id;

	/**
	 * 父类id
	 */
	private Long parentid;

	/**
	 * 菜单名称
	 */
	private String name;

	/**
	 * 父类菜单的ico图标
	 */
	private String ico;

	/**
	 * 需要跳转的url
	 */
	private String href;

	/**
	 * 菜单类型，1：菜单是个url，2:是一个按钮
	 */
	private Integer type;

	/**
	 * 菜单权限的唯一标识
	 */
	private String permission;

	/**
	 * 菜单的排序
	 */
	private Integer sort;

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
	 * @return parentid 
	 */
	public Long getParentid() {
		return parentid;
	}

	/**
	 * 
	 * @param parentid 
	 */
	public void setParentid(Long parentid) {
		this.parentid = parentid;
	}

	/**
	 * 菜单名称
	 * @return name 菜单名称
	 */
	public String getName() {
		return name;
	}

	/**
	 * 菜单名称
	 * @param name 菜单名称
	 */
	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	/**
	 * 父类菜单的ico图标
	 * @return ico 父类菜单的ico图标
	 */
	public String getIco() {
		return ico;
	}

	/**
	 * 父类菜单的ico图标
	 * @param ico 父类菜单的ico图标
	 */
	public void setIco(String ico) {
		this.ico = ico == null ? null : ico.trim();
	}

	/**
	 * 需要跳转的url
	 * @return href 需要跳转的url
	 */
	public String getHref() {
		return href;
	}

	/**
	 * 需要跳转的url
	 * @param href 需要跳转的url
	 */
	public void setHref(String href) {
		this.href = href == null ? null : href.trim();
	}

	/**
	 * 菜单类型，1：菜单是个url，2:是一个按钮
	 * @return type 菜单类型，1：菜单是个url，2:是一个按钮
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * 菜单类型，1：菜单是个url，2:是一个按钮
	 * @param type 菜单类型，1：菜单是个url，2:是一个按钮
	 */
	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * 菜单权限的唯一标识
	 * @return permission 菜单权限的唯一标识
	 */
	public String getPermission() {
		return permission;
	}

	/**
	 * 菜单权限的唯一标识
	 * @param permission 菜单权限的唯一标识
	 */
	public void setPermission(String permission) {
		this.permission = permission == null ? null : permission.trim();
	}

	/**
	 * 菜单的排序
	 * @return sort 菜单的排序
	 */
	public Integer getSort() {
		return sort;
	}

	/**
	 * 菜单的排序
	 * @param sort 菜单的排序
	 */
	public void setSort(Integer sort) {
		this.sort = sort;
	}
}
