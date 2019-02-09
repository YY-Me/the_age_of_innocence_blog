package cn.admin.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.admin.dao.BlogQQUserMapper;
import cn.admin.entity.BlogQQUser;
import cn.admin.example.BlogQQUserExample;
import cn.admin.example.BlogQQUserExample.Criteria;
import cn.admin.service.QQUserService;
import cn.commons.common.LayuiTableResult;
import cn.commons.common.PublicResultJosn;

@Service
public class QQUserServiceImpl implements QQUserService {

    @Autowired
    private BlogQQUserMapper qqUserMapper;

    /**
     * 
     * <p>Title: delete</p>   
     * <p>Description: 单、批量删除</p>   
     * @param uids
     * @return   
     * @see cn.admin.service.QQUserService#delete(java.util.List)
     */
    @Override
    public PublicResultJosn delete(List<String> uids) {
        BlogQQUserExample example = new BlogQQUserExample();
        example.createCriteria().andOpenidIn(uids);
        qqUserMapper.deleteByExample(example);
        return new PublicResultJosn(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), "操作成功");
    }

    /**
     * 
     * <p>Title: selectByExample</p>   
     * <p>Description: 模糊分页查询</p>   
     * @param qqUser
     * @param page
     * @param pageSize
     * @return   
     * @see cn.admin.service.QQUserService#selectByExample(cn.admin.entity.BlogQQUser, java.lang.Integer, java.lang.Integer)
     */
    @Override
    public LayuiTableResult selectByExample(BlogQQUser qqUser, Integer page, Integer pageSize) {
        LayuiTableResult resultJosn = new LayuiTableResult();
        //构造查询条件
        BlogQQUserExample example = new BlogQQUserExample();
        Criteria criteria = example.createCriteria();
        example.setOrderByClause("update_time DESC");
        if (StringUtils.isNotBlank(qqUser.getNickname())) {
            criteria.andNicknameLike("%" + qqUser.getNickname() + "%");
        }
        if (StringUtils.isNotBlank(qqUser.getArea())) {
            criteria.andAreaLike("%" + qqUser.getArea() + "%");
        }
        if (StringUtils.isNotBlank(qqUser.getGender())) {
            criteria.andGenderEqualTo(qqUser.getGender());
        }
        if (StringUtils.isNotBlank(qqUser.getStatus())) {
            criteria.andStatusEqualTo(qqUser.getStatus());
        }
        // 开启分页查找
        PageHelper.startPage(page, pageSize);
        List<BlogQQUser> list = qqUserMapper.selectByExample(example);
        PageInfo<BlogQQUser> pageInfo = new PageInfo<BlogQQUser>(list, pageSize);
        resultJosn.setCode(HttpStatus.OK.value());
        resultJosn.setMessage(HttpStatus.OK.getReasonPhrase());
        resultJosn.setCount(pageInfo.getTotal());
        resultJosn.setData(pageInfo.getList());
        return resultJosn;
    }

    /**
     * 
     * <p>Title: update</p>   
     * <p>Description: 更新信息（状态）</p>   
     * @param qqUser
     * @return   
     * @see cn.admin.service.QQUserService#update(cn.admin.entity.BlogQQUser)
     */
    @Override
    public PublicResultJosn update(BlogQQUser qqUser) {
        qqUserMapper.updateByPrimaryKeySelective(qqUser);
        return new PublicResultJosn(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), "操作成功");
    }

}
