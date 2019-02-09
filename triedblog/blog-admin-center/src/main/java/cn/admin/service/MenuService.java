package cn.admin.service;

import cn.admin.entity.SystemMenu;
import cn.commons.common.PublicResultJosn;

public interface MenuService {

	public PublicResultJosn add(SystemMenu menu);

	public PublicResultJosn delete(Long id);

	public PublicResultJosn update(SystemMenu menu);

	public PublicResultJosn select(SystemMenu menu);

}
