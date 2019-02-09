package cn.commons.dto;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import cn.commons.model.Menu;
import cn.commons.model.Role;
import cn.commons.model.User;

/**
 * 登录用户基本信息
 * 
 * @author 偶尔有点困
 * @date 2018年5月21日
 */
public class LoginUser extends User implements UserDetails {

	private static final long serialVersionUID = 5912800872038389208L;

	private Set<Role> roles;

	private Set<Menu> menus;

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public Set<Menu> getMenus() {
		return menus;
	}

	public void setMenus(Set<Menu> menus) {
		this.menus = menus;
	}

	@JsonIgnore
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> collection = new HashSet<>();
		// 设置角色
		if (!CollectionUtils.isEmpty(roles)) {
			for (Role r : roles) {
				if (r.getName().startsWith("ROLE_")) {
					collection.add(new SimpleGrantedAuthority(r.getName()));
				} else {
					collection.add(new SimpleGrantedAuthority("ROLE_" + r.getName()));
				}
			}
		}
		// 设置权限标识
		if (!CollectionUtils.isEmpty(menus)) {
			for (Menu m : menus) {
				if (StringUtils.isNotBlank(m.getPermission())) {
					collection.add(new SimpleGrantedAuthority(m.getPermission()));
				}
			}
		}
		return collection;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
