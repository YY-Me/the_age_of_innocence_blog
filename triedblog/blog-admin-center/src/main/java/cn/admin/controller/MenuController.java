package cn.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.admin.entity.SystemMenu;
import cn.admin.service.MenuService;
import cn.commons.common.PublicResultJosn;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "菜单管理")
@RestController
@RequestMapping("/menus")
public class MenuController {

	@Autowired
	private MenuService menuService;

	@PostMapping
	@ApiOperation(value = "添加菜单")
	@PreAuthorize("hasAuthority('sys:menu:add')")
	public PublicResultJosn add(@RequestBody SystemMenu menu) {
		PublicResultJosn resultJosn = menuService.add(menu);
		return resultJosn;
	}

	@DeleteMapping("/{id}")
	@ApiOperation(value = "根据id删除菜单")
	@PreAuthorize("hasAuthority('sys:menu:del')")
	public PublicResultJosn delete(@PathVariable Long id) {
		PublicResultJosn resultJosn = menuService.delete(id);
		return resultJosn;
	}

	@PutMapping
	@ApiOperation(value = "更新菜单")
	@PreAuthorize("hasAuthority('sys:menu:update')")
	public PublicResultJosn update(@RequestBody SystemMenu menu) {
		PublicResultJosn resultJosn = menuService.update(menu);
		return resultJosn;
	}

	@GetMapping
	@ApiOperation(value = "查询菜单")
	@PreAuthorize("hasAnyAuthority('sys:menu:query','sys:menu:update','sys:role:add','sys:role:update')")
	public PublicResultJosn select(SystemMenu menu) {
		PublicResultJosn resultJosn = menuService.select(menu);
		return resultJosn;
	}

}
