package cn.admin.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.admin.dao.SystemRoleMapper;
import cn.admin.dao.SystemRoleMenuMapper;
import cn.admin.dao.SystemUserRoleMapper;
import cn.admin.dto.SystemRoleAdd;
import cn.admin.dto.SystemRoleSelect;
import cn.admin.entity.SystemRole;
import cn.admin.entity.SystemRoleMenu;
import cn.admin.example.SystemRoleExample;
import cn.admin.example.SystemRoleMenuExample;
import cn.admin.example.SystemUserRoleExample;
import cn.admin.example.SystemRoleExample.Criteria;
import cn.admin.service.RoleService;
import cn.commons.common.LayuiTableResult;
import cn.commons.common.PublicResultJosn;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private SystemRoleMapper roleMapper;

    @Autowired
    private SystemRoleMenuMapper roleMenuMapper;

    @Autowired
    private SystemUserRoleMapper userRoleMapper;

    /**
     * 添加角色
     * 
     * @param role
     * @return
     */
    @Override
    @Transactional
    public PublicResultJosn add(SystemRoleAdd role_add) {
        if (null != this.checkRoleName(role_add.getName())) {
            throw new IllegalArgumentException(role_add.getName() + "已存在");
        }
        SystemRole role = (SystemRole) role_add;
        role.setCreatetime(new Date());
        roleMapper.insert(role);
        // 保存角色权限
        List<Long> permissionIds = role_add.getPermissionIds();
        this.saveRolePer(role, permissionIds);
        return new PublicResultJosn(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), role);
    }

    /**
     * 删除角色（同时去除角色的权限、取消该角色用户的）
     */
    @Override
    @Transactional
    public PublicResultJosn delete(List<Long> ids) {
        this.deleteRoleMenu(ids);
        this.deleteRoleUser(ids);
        this.deleteRole(ids);
        return new PublicResultJosn(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), null);
    }

    /**
     * 修改角色根据id
     */
    @Override
    @Transactional
    public PublicResultJosn update(SystemRoleAdd role_add) {
        SystemRole check = this.checkRoleName(role_add.getName());
        if (null != check && check.getId() != role_add.getId()) {
            throw new IllegalArgumentException(role_add.getName() + "已存在");
        }

        SystemRole role = (SystemRole) role_add;
        roleMapper.updateByPrimaryKeySelective(role);
        List<Long> permissionIds = role_add.getPermissionIds();
        // 修改权限
        this.saveRolePer(role, permissionIds);
        return new PublicResultJosn(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), role);
    }

    /**
     * 模糊查询
     */
    @Override
    public LayuiTableResult select(SystemRole role, Integer page, Integer pageSize) {
        LayuiTableResult result = new LayuiTableResult();
        // 构造条件
        SystemRoleExample example = new SystemRoleExample();
        example.setOrderByClause("createtime DESC");
        Criteria criteria = example.createCriteria();
        if (null != role.getId()) {
            criteria.andIdEqualTo(role.getId());
        } else {
            if (StringUtils.isNotBlank(role.getName())) {
                criteria.andNameLike("%" + role.getName() + "%");
            }
            if (StringUtils.isNotBlank(role.getDescription())) {
                criteria.andDescriptionLike("%" + role.getDescription() + "%");
            }
        }

        PageHelper.startPage(page, pageSize);
        List<SystemRole> list = roleMapper.selectByExample(example);
        PageInfo<SystemRole> pageInfo = new PageInfo<>(list, pageSize);
        //
        result.setCode(HttpStatus.OK.value());
        result.setMessage(HttpStatus.OK.getReasonPhrase());
        result.setCount(pageInfo.getTotal());
        result.setData(pageInfo.getList());
        return result;
    }

    /**
     * 根据id查找角色、包括角色权限列表
     */
    @Override
    public PublicResultJosn selectByid(Long id) {
        /*
         * 在阿里巴巴开发规范中，单操作两张表的时候应该建立索引； 这里是做链接查询，后期再看
         */
        if (null == id) {
            throw new IllegalArgumentException("id错误");
        }
        SystemRoleSelect result = roleMapper.selectByid(id);
        return new PublicResultJosn(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), result);
    }

    /**
     * 保存角色的权限
     */
    private void saveRolePer(SystemRole role, List<Long> permissionIds) {
        //
        SystemRole role2 = checkRoleName(role.getName());

        // 先删除
        if (null != role2.getId()) {
            List<Long> rids = new ArrayList<>();
            rids.add(role2.getId());
            deleteRoleMenu(rids);
        }
        Long rid = role2.getId();
        // 再添加
        for (Long mid : permissionIds) {
            roleMenuMapper.insert(new SystemRoleMenu(rid, mid));
        }
    }

    /**
     * 根据name查找role
     * 
     * @return
     */
    public SystemRole checkRoleName(String name) {
        SystemRoleExample example = new SystemRoleExample();
        Criteria criteria = example.createCriteria();
        criteria.andNameEqualTo(name);
        List<SystemRole> list = roleMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        return (SystemRole) list.get(0);
    }

    /**
     * 删除角色对应的权限
     */
    public void deleteRoleMenu(List<Long> rids) {
        SystemRoleMenuExample example = new SystemRoleMenuExample();
        cn.admin.example.SystemRoleMenuExample.Criteria criteria = example.createCriteria();
        criteria.andRoleidIn(rids);
        roleMenuMapper.deleteByExample(example);
    }

    /**
     * 删除用户对应的角色
     */
    public void deleteRoleUser(List<Long> rids) {
        SystemUserRoleExample example = new SystemUserRoleExample();
        cn.admin.example.SystemUserRoleExample.Criteria criteria = example.createCriteria();
        criteria.andRoleidIn(rids);
        userRoleMapper.deleteByExample(example);
    }

    /**
     * 删除角色
     */
    public void deleteRole(List<Long> rids) {
        SystemRoleExample example = new SystemRoleExample();
        Criteria criteria = example.createCriteria();
        criteria.andIdIn(rids);
        roleMapper.deleteByExample(example);
    }

}
