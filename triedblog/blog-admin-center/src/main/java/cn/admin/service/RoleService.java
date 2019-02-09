package cn.admin.service;

import java.util.List;

import cn.admin.dto.SystemRoleAdd;
import cn.admin.entity.SystemRole;
import cn.commons.common.LayuiTableResult;
import cn.commons.common.PublicResultJosn;

public interface RoleService {

	public PublicResultJosn add(SystemRoleAdd role);

	public PublicResultJosn delete(List<Long> ids);

	public PublicResultJosn update(SystemRoleAdd role);

	public LayuiTableResult select(SystemRole role, Integer page, Integer pageSize);

	PublicResultJosn selectByid(Long id);

}
