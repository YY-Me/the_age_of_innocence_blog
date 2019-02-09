package cn.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.admin.entity.SystemDict;
import cn.admin.service.DictionaryService;
import cn.commons.common.LayuiTableResult;
import cn.commons.common.PublicResultJosn;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 系统字典管理
 * 
 * @author 偶尔有点困
 * @date 2018年5月6日
 */
@Api(tags = "字典管理")
@RestController
@RequestMapping("/dictionary")
public class DictionaryController {

	@Autowired
	private DictionaryService dictionaryService;

	@PostMapping
	@ApiOperation(value = "添加字典")
	@PreAuthorize("hasAuthority('sys:dictionary:add')")
	public PublicResultJosn add(@RequestBody SystemDict dict) {
		PublicResultJosn resultJosn = dictionaryService.add(dict);
		return resultJosn;
	}

	@DeleteMapping
	@ApiOperation(value = "单、批量删除字典")
	@PreAuthorize("hasAuthority('sys:dictionary:del')")
	public PublicResultJosn delete(@RequestBody List<Long> ids) {
		PublicResultJosn resultJosn = dictionaryService.delete(ids);
		return resultJosn;
	}

	@PutMapping
	@ApiOperation(value = "修改字典")
	@PreAuthorize("hasAuthority('sys:dictionary:update')")
	public PublicResultJosn update(@RequestBody SystemDict dict) {
		PublicResultJosn resultJosn = dictionaryService.update(dict);
		return resultJosn;
	}

	@GetMapping
	@ApiOperation(value = "模糊查询")
	@PreAuthorize("hasAnyAuthority('sys:dictionary:query','sys:admin:query','sys:dictionary:update')")
	public LayuiTableResult select(SystemDict dict, @RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "10") Integer pageSize) {
		LayuiTableResult result = dictionaryService.select(dict, page, pageSize);
		return result;
	}

}
