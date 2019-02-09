package cn.admin.dto;

import java.util.List;

import cn.admin.entity.SystemRole;

public class SystemRoleAdd extends SystemRole {

	private static final long serialVersionUID = 3692121066628241637L;

	private List<Long> permissionIds;

	public List<Long> getPermissionIds() {
		return permissionIds;
	}

	public void setPermissionIds(List<Long> permissionIds) {
		this.permissionIds = permissionIds;
	}
}
