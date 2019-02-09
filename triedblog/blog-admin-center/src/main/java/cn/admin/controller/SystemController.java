package cn.admin.controller;

import javax.annotation.Resource;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.admin.utils.RedisUtil;
import cn.commons.common.PublicResultJosn;
import cn.commons.model.WebSiteBaseInfo;
import io.swagger.annotations.ApiOperation;

/**
 * 
 * @ClassName:  SystemController   
 * @Description:系统控制器   
 * @author: yuyong 
 * @date:   2018年9月17日 下午10:08:15   
 *     
 * @Copyright: 2018 www.tydic.com Inc. All rights reserved. 
 * @note: 注意：本内容仅限于xxx公司内部传阅，禁止外泄以及用于其他的商业目
 */
@RestController
@RequestMapping("/system")
public class SystemController {

	@Resource
	private RedisTemplate<String, Object> redisTemplate;

	@Resource
	private RedisUtil redisUtil;

	@PutMapping("/baseInfo")
	@ApiOperation(value = "更新网站基本信息")
	@PreAuthorize("hasAuthority('sys:website:update')")
	PublicResultJosn updateWebSiteInfo(@RequestBody WebSiteBaseInfo baseInfo) {
		redisUtil.set("BlogWebSiteBaseInfo:baseinfo", baseInfo, -1);
		return new PublicResultJosn(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), "设置成功");
	}

	@GetMapping("/baseInfo")
	@ApiOperation(value = "查询网站基本信息")
	@PreAuthorize("hasAnyAuthority('sys:website:query','sys:website:update')")
	PublicResultJosn getWebSiteInfo() {
		Object object = (WebSiteBaseInfo) redisTemplate.opsForValue().get("BlogWebSiteBaseInfo:baseinfo");
		return new PublicResultJosn(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), object);
	}

}
