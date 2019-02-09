package cn.admin.dto;

import java.util.List;

import cn.admin.entity.SystemUser;
import io.swagger.annotations.ApiModelProperty;

/**
 * 添加用户类包装
 * 
 * @author 偶尔有点困
 * @date 2018年5月5日
 */
public class SystemUserAdd extends SystemUser {

	private static final long serialVersionUID = 5930867791570860196L;

	@ApiModelProperty(value = "角色id集合", name = "roleIds", example = "[1,2,3]")
	private List<Long> roleIds;

	public List<Long> getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(List<Long> roleIds) {
		this.roleIds = roleIds;
	}
}
