package cn.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.admin.entity.BlogLabelClassify;
import cn.admin.service.LabelClassifyService;
import cn.commons.common.PublicResultJosn;
import io.swagger.annotations.ApiOperation;

/**
 * 
 * @ClassName:  LabelClassifyController   
 * @Description: 标签、分类管理器 
 * @author: yuyong 
 * @date:   2018年9月1日 下午10:17:24   
 *     
 * @Copyright: 2018 www.tydic.com Inc. All rights reserved. 
 * @note: 注意：本内容仅限于xxx公司内部传阅，禁止外泄以及用于其他的商业目
 */
@RestController
@RequestMapping("/labelClassify")
public class LabelClassifyController {

    @Autowired
    private LabelClassifyService classifyService;

    /**
     * 获取所有标签、分类
     * @Title: getlabelClassify
     * @param @return
     * @return PublicResultJosn
     * @throws
     */
    @GetMapping
    @ApiOperation(value = "查询所有标签、分类")
    @PreAuthorize("hasAnyAuthority('sys:classifyLabel:query','sys:classifyLabel:addUp','sys:classifyLabel:del')")
    public PublicResultJosn getlabelClassify() {
        return classifyService.listLabelClassify();
    }

    @PostMapping
    @ApiOperation(value = "添加、修改标签分类")
    @PreAuthorize("hasAuthority('sys:classifyLabel:addUp')")
    public PublicResultJosn addUpd(@RequestBody BlogLabelClassify labelClassify) {
        return classifyService.addUpd(labelClassify);
    }

    @DeleteMapping("/{type}/{id}")
    @ApiOperation(value = "删除标签、分类")
    @PreAuthorize("hasAuthority('sys:classifyLabel:del')")
    public PublicResultJosn getlabelClassify(@PathVariable(value = "type") String type,
            @PathVariable(value = "id") Long id) {
        return classifyService.del(type, id);
    }
}
