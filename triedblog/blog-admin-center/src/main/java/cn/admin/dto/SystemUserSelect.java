package cn.admin.dto;

import java.util.List;

import cn.admin.entity.SystemRole;
import cn.admin.entity.SystemUser;

public class SystemUserSelect extends SystemUser {

	private static final long serialVersionUID = 6082639282203291034L;

	List<SystemRole> roles;

	public List<SystemRole> getRoles() {
		return roles;
	}

	public void setRoles(List<SystemRole> roles) {
		this.roles = roles;
	}

}
