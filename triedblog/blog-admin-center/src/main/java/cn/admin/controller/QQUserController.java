package cn.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.admin.entity.BlogQQUser;
import cn.admin.service.QQUserService;
import cn.commons.common.LayuiTableResult;
import cn.commons.common.PublicResultJosn;
import io.swagger.annotations.ApiOperation;

/**
 * 
 * @ClassName:  QQUserController   
 * @Description: 前台用户
 * @author: yuyong 
 * @date:   2018年9月21日 下午12:08:33   
 *     
 * @Copyright: 2018 www.tydic.com Inc. All rights reserved. 
 * @note: 注意：本内容仅限于xxx公司内部传阅，禁止外泄以及用于其他的商业目
 */
@RequestMapping("/qqUser")
@RestController
public class QQUserController {

    @Autowired
    private QQUserService qqUserService;

    @DeleteMapping
    @ApiOperation(value = "删除用户")
    @PreAuthorize("hasAuthority('web:user:del')")
    PublicResultJosn delete(@RequestBody List<String> uids) {
        return qqUserService.delete(uids);
    }

    @PutMapping
    @ApiOperation(value = "更新信息（状态）")
    @PreAuthorize("hasAuthority('web:user:upstatus')")
    PublicResultJosn update(@RequestBody BlogQQUser qqUser) {
        return qqUserService.update(qqUser);
    }

    @GetMapping
    @ApiOperation(value = "分页查询所有用户")
    @PreAuthorize("hasAnyAuthority('web:user:query','web:user:upstatus','web:user:del')")
    LayuiTableResult selectByExample(BlogQQUser blogQQUser, @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer pageSize) {
        return qqUserService.selectByExample(blogQQUser, page, pageSize);
    }

}
