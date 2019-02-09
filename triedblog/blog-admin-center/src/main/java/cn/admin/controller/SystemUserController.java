package cn.admin.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.admin.dto.SystemUserAdd;
import cn.admin.entity.SystemUser;
import cn.admin.service.SystemUserService;
import cn.admin.utils.UserUtil;
import cn.commons.common.LayuiTableResult;
import cn.commons.common.PublicResultJosn;
import cn.commons.dto.LoginUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 后台用户接口
 * 
 * @author 偶尔有点困
 * @date 2018年5月4日
 */
@Api(tags = "后台用户")
@RequestMapping("/sysUser")
@RestController
public class SystemUserController {

    @Autowired
    private SystemUserService systemUserService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping
    @ApiOperation(value = "添加用户", notes = "添加用户")
    @PreAuthorize("hasAuthority('sys:admin:add')")
    public PublicResultJosn add(@RequestBody SystemUserAdd user_add) {
        return systemUserService.add(user_add);
    }

    @DeleteMapping
    @ApiOperation(value = "单、批量删除用户")
    @PreAuthorize("hasAuthority('sys:admin:del')")
    public PublicResultJosn delete(@RequestBody List<String> deleteids) {
        if (deleteids.contains("a0fef307-f60a-4ce5-abb0-040c0db450af")) {
            throw new IllegalArgumentException("您不能删除超管");
        }
        return systemUserService.delete(deleteids);
    }

    @PutMapping
    @ApiOperation(value = "更新用户信息")
    @PreAuthorize("hasAuthority('sys:admin:update')")
    public PublicResultJosn update(@RequestBody SystemUserAdd user) {
        return systemUserService.updateByExample(user);
    }

    @GetMapping
    @ApiOperation(value = "用户列表条件模糊查询")
    @PreAuthorize("hasAnyAuthority('sys:admin:query','sys:admin:update','sys:admin:del')")
    public LayuiTableResult selectByExample(SystemUser user, @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer pageSize) {
        return systemUserService.selectByExample(user, page, pageSize);
    }

    @GetMapping(value = "/{uid}")
    @ApiOperation(value = "根据uid查找、包括所拥有的角色")
    @PreAuthorize("hasAnyAuthority('sys:admin:query','sys:admin:update','sys:admin:del')")
    public PublicResultJosn selectByUid(@PathVariable String uid) {
        return systemUserService.selectByUid(uid);
    }

    @GetMapping(value = "/current")
    @ApiOperation(value = "获取当前登录用户")
    public PublicResultJosn getCurrentUser() {
        return new PublicResultJosn(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), UserUtil.getCurrentUser());
    }

    @PutMapping(value = "/current")
    @ApiOperation(value = "更新个人信息")
    public PublicResultJosn updateMe(@RequestBody SystemUser user) {
        if (StringUtils.isBlank(user.getUid())) {
            throw new IllegalArgumentException("uid参数异常");
        }
        return systemUserService.updateCurrentInfo(user);
    }

    @PutMapping(value = "/current/pwd")
    @ApiOperation(value = "更新个人密码")
    public PublicResultJosn updatePassword(@RequestBody SystemUser user) {
        LoginUser loginUser = UserUtil.getCurrentUser();
        if (StringUtils.equals(loginUser.getUsername(), "guest")) {
            throw new IllegalArgumentException("当前用户不支持更新个人密码");
        }
        user.setUid(loginUser.getUid());
        user.setUsername(loginUser.getUsername());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        PublicResultJosn resultJosn = systemUserService.updateCurrentInfo(user);
        return resultJosn;
    }

}
