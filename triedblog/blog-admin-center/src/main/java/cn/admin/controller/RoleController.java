package cn.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.admin.dto.SystemRoleAdd;
import cn.admin.entity.SystemRole;
import cn.admin.service.RoleService;
import cn.commons.common.LayuiTableResult;
import cn.commons.common.PublicResultJosn;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 系统角色管理
 * 
 * @author 偶尔有点困
 * @date 2018年5月6日
 */
@Api(tags = "角色管理")
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping
    @ApiOperation(value = "添加角色")
    @PreAuthorize("hasAuthority('sys:role:add')")
    public PublicResultJosn add(@RequestBody SystemRoleAdd role_add) {
        PublicResultJosn resultJosn = roleService.add(role_add);
        return resultJosn;
    }

    @DeleteMapping
    @ApiOperation(value = "单、批量删除")
    @PreAuthorize("hasAuthority('sys:role:del')")
    public PublicResultJosn delete(@RequestBody List<Long> ids) {
        PublicResultJosn resultJosn = roleService.delete(ids);
        return resultJosn;
    }

    @PutMapping
    @ApiOperation(value = "修改角色信息")
    @PreAuthorize("hasAuthority('sys:role:update')")
    public PublicResultJosn update(@RequestBody SystemRoleAdd role_add) {
        PublicResultJosn resultJosn = roleService.update(role_add);
        return resultJosn;
    }

    @GetMapping
    @ApiOperation(value = "模糊查询")
    @PreAuthorize("hasAnyAuthority('sys:role:query','sys:role:update','sys:role:del')")
    public LayuiTableResult select(SystemRole role, @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer pageSize) {
        LayuiTableResult result = roleService.select(role, page, pageSize);
        return result;
    }

    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAnyAuthority('sys:role:query','sys:role:update','sys:role:del')")
    @ApiOperation(value = "根据id查找角色、包括角色的权限")
    public PublicResultJosn selectById(@PathVariable Long id) {
        PublicResultJosn resultJosn = roleService.selectByid(id);
        return resultJosn;
    }

}
