package cn.oauth2.service.impl;

import java.util.List;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.commons.dto.LoginUser;
import cn.commons.model.Menu;
import cn.commons.model.Role;
import cn.oauth2.dao.UserDao;
import cn.oauth2.entity.SystemUser;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;

	/**
	 * 根据username查找用户，包括角色，权限
	 */
	public LoginUser selectByUsername(String username) {
		// 查用户
		SystemUser user = userDao.selectByUserName(username);
		if (null != user) {
			LoginUser loginUser = new LoginUser();
			BeanUtils.copyProperties(user, loginUser);
			// 查角色ids
			List<Long> r = userDao.selectRoleByUid(user.getUid());
			if (!CollectionUtils.isEmpty(r)) {
				// 查角色
				Set<Role> roles = userDao.selectRoleInUid(r);
				loginUser.setRoles(roles);
				// 查menu ids
				List<Long> m = userDao.selectMenuByRid(r);
				if (!CollectionUtils.isEmpty(m)) {
					// 查menus
					Set<Menu> menus = userDao.selectMenuInRid(m);
					loginUser.setMenus(menus);
				}
			}
			return loginUser;
		}
		return null;
	}
}
