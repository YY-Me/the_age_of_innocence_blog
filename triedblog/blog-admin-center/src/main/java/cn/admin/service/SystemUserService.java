package cn.admin.service;

import java.util.List;

import cn.admin.dto.SystemUserAdd;
import cn.admin.entity.SystemUser;
import cn.commons.common.LayuiTableResult;
import cn.commons.common.PublicResultJosn;

public interface SystemUserService {

	PublicResultJosn add(SystemUserAdd user);

	PublicResultJosn delete(List<String> uids);

	PublicResultJosn updateByExample(SystemUserAdd user);

	LayuiTableResult selectByExample(SystemUser user, Integer page, Integer pageSize);

	PublicResultJosn selectByUid(String uid);

	PublicResultJosn updateCurrentInfo(SystemUser user);

}
