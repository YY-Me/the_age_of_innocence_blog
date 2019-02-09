package cn.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.admin.dao.SystemMenuMapper;
import cn.admin.dao.SystemRoleMenuMapper;
import cn.admin.entity.SystemMenu;
import cn.admin.example.SystemMenuExample;
import cn.admin.example.SystemRoleMenuExample;
import cn.admin.example.SystemMenuExample.Criteria;
import cn.admin.service.MenuService;
import cn.commons.common.PublicResultJosn;

@Service
public class MenuServiceImpl implements MenuService {

	@Autowired
	private SystemMenuMapper menuMapper;

	@Autowired
	private SystemRoleMenuMapper roleMenuMapper;

	/**
	 * 增加菜单
	 */
	@Override
	@Transactional
	public PublicResultJosn add(SystemMenu menu) {
		menuMapper.insert(menu);
		return new PublicResultJosn(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), null);
	}

	/**
	 * 删除菜单
	 */
	@Override
	@Transactional
	public PublicResultJosn delete(Long id) {
		// 删除角色-权限表
		this.deleteRoleMenu(id);
		// 删除子菜单
		this.deleteChildMenu(id);
		// 删除自己
		menuMapper.deleteByPrimaryKey(id);
		return new PublicResultJosn(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), null);
	}

	/**
	 * 更新菜单
	 */
	@Override
	@Transactional
	public PublicResultJosn update(SystemMenu menu) {
		if (null == menu.getId()) {
			throw new IllegalArgumentException("id错误");
		}
		menuMapper.updateByPrimaryKeySelective(menu);
		return new PublicResultJosn(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), menu);
	}

	/**
	 * 查询全部
	 */
	@Override
	public PublicResultJosn select(SystemMenu menu) {
		SystemMenuExample example = new SystemMenuExample();
		Criteria criteria = example.createCriteria();
		example.setOrderByClause("sort ASC");
		if (null != menu.getId()) {
			criteria.andIdEqualTo(menu.getId());
		} else if (null != menu.getType()) {
			criteria.andTypeEqualTo(menu.getType());
		}
		List<SystemMenu> list = menuMapper.selectByExample(example);
		return new PublicResultJosn(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), list);
	}

	/**
	 * 删除角色-权限表
	 * 
	 * @param id
	 */
	public void deleteRoleMenu(Long id) {
		SystemRoleMenuExample example = new SystemRoleMenuExample();
		cn.admin.example.SystemRoleMenuExample.Criteria criteria = example.createCriteria();
		criteria.andMenuidEqualTo(id);
		roleMenuMapper.deleteByExample(example);
	}

	/**
	 * 删除子菜单
	 * 
	 * @param pid
	 */
	public void deleteChildMenu(Long pid) {
		SystemMenuExample example = new SystemMenuExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentidEqualTo(pid);
		menuMapper.deleteByExample(example);
	}

}
