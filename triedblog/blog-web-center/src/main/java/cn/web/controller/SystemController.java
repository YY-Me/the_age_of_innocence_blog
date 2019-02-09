package cn.web.controller;

import javax.annotation.Resource;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.commons.common.PublicResultJosn;
import cn.commons.model.WebSiteBaseInfo;
import cn.web.utils.RedisUtil;
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

	@GetMapping("/baseInfo")
	@ApiOperation(value = "查询网站基本信息")
	PublicResultJosn getWebSiteInfo() {
		Object object = (WebSiteBaseInfo) redisTemplate.opsForValue().get("BlogWebSiteBaseInfo:baseinfo");
		return new PublicResultJosn(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), object);
	}

}
