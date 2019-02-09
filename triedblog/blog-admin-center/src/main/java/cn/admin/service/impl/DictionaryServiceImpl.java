package cn.admin.service.impl;

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

import cn.admin.dao.SystemDictMapper;
import cn.admin.entity.SystemDict;
import cn.admin.example.SystemDictExample;
import cn.admin.example.SystemDictExample.Criteria;
import cn.admin.service.DictionaryService;
import cn.commons.common.LayuiTableResult;
import cn.commons.common.PublicResultJosn;

@Service
public class DictionaryServiceImpl implements DictionaryService {

    @Autowired
    private SystemDictMapper dictMapper;

    /**
     * 添加
     */
    @Override
    @Transactional
    public PublicResultJosn add(SystemDict dict) {
        // 判断type、key是否存在
        if (null != this.checkTypeKey(dict)) {
            throw new IllegalArgumentException("类型和key已存在");
        }
        dict.setCreatetime(new Date());
        dictMapper.insert(dict);
        return new PublicResultJosn(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), dict);
    }

    /**
     * 单、批量删除
     */
    @Override
    @Transactional
    public PublicResultJosn delete(List<Long> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            throw new IllegalArgumentException("参数异常");
        }
        SystemDictExample example = new SystemDictExample();
        Criteria criteria = example.createCriteria();
        criteria.andIdIn(ids);
        dictMapper.deleteByExample(example);
        return new PublicResultJosn(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), null);
    }

    /**
     * 根据id修改信息
     */
    @Override
    @Transactional
    public PublicResultJosn update(SystemDict dict) {
        if (null == dict.getId()) {
            throw new IllegalArgumentException("id参数异常");
        }
        SystemDict check = checkTypeKey(dict);
        if (null != check && check.getId() != dict.getId()) {
            throw new IllegalArgumentException("类型和key已存在");
        }
        dictMapper.updateByPrimaryKeySelective(dict);
        return new PublicResultJosn(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), dict);
    }

    /**
     * 模糊查询
     */
    @Override
    public LayuiTableResult select(SystemDict dict, Integer page, Integer pageSize) {
        LayuiTableResult resultJosn = new LayuiTableResult();
        // 构造条件
        SystemDictExample example = new SystemDictExample();
        example.setOrderByClause("createtime DESC");
        Criteria criteria = example.createCriteria();
        if (null != dict.getId()) {
            criteria.andIdEqualTo(dict.getId());
        } else {
            if (StringUtils.isNotBlank(dict.getType())) {
                criteria.andTypeLike("%" + dict.getType() + "%");
            }
            if (StringUtils.isNotBlank(dict.getK())) {
                criteria.andKLike("%" + dict.getK() + "%");
            }
            if (StringUtils.isNotBlank(dict.getV())) {
                criteria.andVLike("%" + dict.getV() + "%");
            }
        }
        // 开启分页查找
        PageHelper.startPage(page, pageSize);
        List<SystemDict> list = dictMapper.selectByExample(example);
        PageInfo<SystemDict> pageInfo = new PageInfo<>(list, pageSize);
        //
        resultJosn.setCode(HttpStatus.OK.value());
        resultJosn.setMessage(HttpStatus.OK.getReasonPhrase());
        resultJosn.setData(pageInfo.getList());
        resultJosn.setCount(pageInfo.getTotal());
        return resultJosn;
    }

    /**
     * 判断Type，Key是否存在，true:存在，反之
     * 
     * @return
     */
    public SystemDict checkTypeKey(SystemDict dict) {
        SystemDictExample example = new SystemDictExample();
        Criteria criteria = example.createCriteria();
        criteria.andTypeEqualTo(dict.getType());
        criteria.andKEqualTo(dict.getK());
        List<SystemDict> list = dictMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        return (SystemDict) list.get(0);
    }

}
