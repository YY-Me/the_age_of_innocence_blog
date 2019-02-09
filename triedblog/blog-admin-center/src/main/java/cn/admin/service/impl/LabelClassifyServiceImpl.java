package cn.admin.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.admin.dao.BlogArticleClassifyMapper;
import cn.admin.dao.BlogArticleLableMapper;
import cn.admin.dao.BlogLabelClassifyMapper;
import cn.admin.entity.BlogLabelClassify;
import cn.admin.plugin.spring.CacheRemove;
import cn.admin.service.LabelClassifyService;
import cn.commons.common.PublicResultJosn;

@Service
public class LabelClassifyServiceImpl implements LabelClassifyService {

    @Autowired
    private BlogLabelClassifyMapper blogLabelClassifyMapper;

    @Autowired
    private BlogArticleLableMapper blogArticleLableMapper;

    @Autowired
    private BlogArticleClassifyMapper blogArticleClassifyMapper;

    @Override
    public PublicResultJosn listLabelClassify() {
        List<BlogLabelClassify> list = blogLabelClassifyMapper.selectByExample(null);
        List<BlogLabelClassify> label = new ArrayList<>();
        List<BlogLabelClassify> classify = new ArrayList<>();
        Map<String, Object> result = new HashMap<>();
        list.forEach(b -> {
            if ("0".equals(b.getType())) {
                label.add(b);
            } else {
                classify.add(b);
            }
        });
        result.put("label", label);
        result.put("classify", classify);
        return new PublicResultJosn(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), result);
    }

    @Override
    @CacheRemove(value = "blogWebCenter", key = { "blogArticleClassifyLable", "blogArticleLables", "selectByClassify" })
    @Transactional
    public PublicResultJosn addUpd(BlogLabelClassify labelClassify) {
        //更新
        int i = blogLabelClassifyMapper.updateByPrimaryKey(labelClassify);
        //插入
        if (i == 0) {
            blogLabelClassifyMapper.insertSelective(labelClassify);
        }
        return new PublicResultJosn(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), null);
    }

    @Override
    @CacheRemove(value = "blogWebCenter", key = { "blogArticleClassifyLable", "blogArticleLables", "selectByClassify" })
    @Transactional
    public PublicResultJosn del(String type, Long id) {
        if (StringUtils.equals(type, "0")) {
            //删除标签
            blogArticleLableMapper.deleteByLabelId(id);
        } else if (StringUtils.equals(type, "1")) {
            blogArticleClassifyMapper.deletebyClassifyId(id);
        } else {
            throw new IllegalArgumentException("类型错误");
        }
        blogLabelClassifyMapper.deleteByPrimaryKey(id);
        return new PublicResultJosn(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), null);
    }

}
