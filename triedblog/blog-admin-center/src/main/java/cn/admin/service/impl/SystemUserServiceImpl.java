package cn.admin.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.admin.dao.SystemUserMapper;
import cn.admin.dao.SystemUserRoleMapper;
import cn.admin.dto.SystemUserAdd;
import cn.admin.dto.SystemUserSelect;
import cn.admin.entity.SystemUser;
import cn.admin.entity.SystemUserRole;
import cn.admin.example.SystemUserExample;
import cn.admin.example.SystemUserRoleExample;
import cn.admin.example.SystemUserExample.Criteria;
import cn.admin.service.SystemUserService;
import cn.commons.common.LayuiTableResult;
import cn.commons.common.PublicResultJosn;

@Service
public class SystemUserServiceImpl implements SystemUserService {

    @Autowired
    private SystemUserMapper userMapper;

    @Autowired
    private SystemUserRoleMapper userRoleMapper;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    /**
     * 添加用户
     */
    @Override
    @Transactional
    public PublicResultJosn add(SystemUserAdd user) {
        SystemUser check = this.checkusername(user.getUsername());
        if (null != check) {
            throw new IllegalArgumentException("用户名已存在");
        }
        user.setUid(UUID.randomUUID().toString());
        SystemUser u1 = (SystemUser) user;
        if (StringUtils.isBlank(u1.getNickname())) {
            u1.setNickname(u1.getUsername());
        }
        u1.setPassword(passwordEncoder.encode(u1.getPassword()));
        u1.setStatus(1);
        u1.setCreatetime(new Date());
        u1.setUpdatetime(new Date());
        userMapper.insertSelective(u1);
        // 保存user权限
        saveUserRoles(u1.getUid(), user.getRoleIds());
        return new PublicResultJosn(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), user);
    }

    /**
     * 单个、批量删除（在数据框中真正的删除了）
     */
    @Override
    @Transactional
    public PublicResultJosn delete(List<String> uids) {
        if (CollectionUtils.isEmpty(uids)) {
            throw new IllegalArgumentException("参数错误");
        }
        SystemUserExample example = new SystemUserExample();
        Criteria criteria = example.createCriteria();
        criteria.andUidIn(uids);
        userMapper.deleteByExample(example);
        return new PublicResultJosn(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), null);
    }

    /**
     * 更新信息 根据uid
     */
    @Override
    @Transactional
    public PublicResultJosn updateByExample(SystemUserAdd user) {
        PublicResultJosn resultJosn = null;
        if (StringUtils.isBlank(user.getUid())) {
            throw new IllegalArgumentException("uid错误");
        }
        SystemUser check = this.checkusername(user.getUsername());
        if (null != check && !(check.getUid()).equals(user.getUid())) {
            throw new IllegalArgumentException("用户名已存在");
        }
        user.setUpdatetime(new Date());
        SystemUser u1 = (SystemUser) user;
        userMapper.updateByPrimaryKeySelective(u1);
        // 保存user权限
        saveUserRoles(u1.getUid(), user.getRoleIds());
        resultJosn = new PublicResultJosn(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), null);
        return resultJosn;
    }

    /**
     * 用户模糊查询
     */
    @Override
    @Transactional
    public LayuiTableResult selectByExample(SystemUser user, Integer page, Integer pageSize) {
        LayuiTableResult resultJosn = new LayuiTableResult();
        // 构造条件
        SystemUserExample example = new SystemUserExample();
        example.setOrderByClause("createtime DESC");
        Criteria criteria = example.createCriteria();
        if (StringUtils.isNotBlank(user.getUid())) {
            // uid精确查找
            criteria.andUidEqualTo(user.getUid());
        } else {
            if (StringUtils.isNotBlank(user.getUsername())) {
                criteria.andUsernameLike("%" + user.getUsername() + "%");
            }
            if (StringUtils.isNotBlank(user.getNickname())) {
                criteria.andNicknameLike("%" + user.getNickname() + "%");
            }
            if (null != user.getStatus()) {
                criteria.andStatusEqualTo(user.getStatus());
            }
        }
        // 开启分页查找
        PageHelper.startPage(page, pageSize);
        List<SystemUser> list = userMapper.selectByExample(example);
        PageInfo<SystemUser> pageInfo = new PageInfo<>(list, pageSize);
        //
        resultJosn.setCode(HttpStatus.OK.value());
        resultJosn.setMessage(HttpStatus.OK.getReasonPhrase());
        resultJosn.setCount(pageInfo.getTotal());
        resultJosn.setData(pageInfo.getList());
        return resultJosn;
    }

    /**
     * 根据uid查找用户信息、包括角色
     */
    @Override
    public PublicResultJosn selectByUid(String uid) {
        /*
         * 在阿里巴巴开发规范中，单操作两张表的时候应该建立索引； 这里是做链接查询，后期再看
         */
        if (StringUtils.isBlank(uid)) {
            throw new IllegalArgumentException("uid错误");
        }
        SystemUserSelect result = userMapper.selectByUid(uid);
        return new PublicResultJosn(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), result);
    }

    /**
     * 根据username判断是否注册过，返回根据username查询出来的对象
     * 
     * @param username
     * @return
     */
    public SystemUser checkusername(String username) {
        SystemUserExample example = new SystemUserExample();
        Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(username);
        List<SystemUser> list = userMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        return (SystemUser) list.get(0);
    }

    /**
     * 保存用户角色
     * 
     * @param uid
     * @param roleIds
     */
    private void saveUserRoles(String uid, List<Long> roleIds) {
        // 先删除user权限，再添加
        if (CollectionUtils.isNotEmpty(roleIds)) {
            SystemUserRoleExample example = new SystemUserRoleExample();
            cn.admin.example.SystemUserRoleExample.Criteria criteria = example.createCriteria();
            criteria.andUseridEqualTo(uid);
            userRoleMapper.deleteByExample(example);

            SystemUserRole user_role = new SystemUserRole();
            user_role.setUserid(uid);
            for (Long r : roleIds) {
                user_role.setRoleid(r);
                userRoleMapper.insert(user_role);
            }
        }
    }

    /**
     * 根据uid更新个人信息
     */
    @Override
    public PublicResultJosn updateCurrentInfo(SystemUser user) {
        userMapper.updateByPrimaryKeySelective(user);
        user.setUpdatetime(new Date());
        return new PublicResultJosn(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), user);
    }

}
