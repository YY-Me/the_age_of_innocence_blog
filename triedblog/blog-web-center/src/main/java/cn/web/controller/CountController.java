package cn.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.commons.common.PublicResultJosn;
import cn.web.dto.StatQueryVo;
import cn.web.service.CountService;

/**
 * 
 * @ClassName:  CountController   
 * @Description: 统计控制器
 * @author: yuyong 
 * @date:   2018年10月8日 上午11:56:44   
 *     
 * @Copyright: 2018 www.xxx.com Inc. All rights reserved. 
 * @note: 注意：本内容仅限于xxx公司内部传阅，禁止外泄以及用于其他的商业目
 */
@RequestMapping("/count")
@RestController
public class CountController {

	@Autowired
	private CountService countService;

	@GetMapping("/areas")
	PublicResultJosn selectAreaByExample(StatQueryVo queryVo) {
		return countService.selectAreaByExample(queryVo);
	}

	@GetMapping("/oss")
	PublicResultJosn selectOsByExample(StatQueryVo queryVo) {
		return countService.selectOsByExample(queryVo);
	}

	@GetMapping("/browsers")
	PublicResultJosn selectBrowserByExample(StatQueryVo queryVo) {
		return countService.selectBrowserByExample(queryVo);
	}
}
