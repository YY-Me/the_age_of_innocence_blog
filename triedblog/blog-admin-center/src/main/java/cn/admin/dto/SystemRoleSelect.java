package cn.admin.dto;

import java.util.List;

import cn.admin.entity.SystemMenu;
import cn.admin.entity.SystemRole;

public class SystemRoleSelect extends SystemRole {

	private static final long serialVersionUID = -3667665753132341101L;

	private List<SystemMenu> menus;

	public List<SystemMenu> getMenus() {
		return menus;
	}

	public void setMenus(List<SystemMenu> menus) {
		this.menus = menus;
	}

}
