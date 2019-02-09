package cn.admin.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import cn.admin.dto.BlogArticleAdd;
import cn.admin.feign.FileClient;
import cn.admin.service.ArticleService;
import cn.admin.utils.UserUtil;
import cn.commons.common.LayuiTableResult;
import cn.commons.common.PublicResultJosn;
import cn.commons.dto.LoginUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "文章管理")
@RestController
@RequestMapping("/article")
public class ArticleController {

	@Autowired
	private ArticleService articleService;

	@Autowired
	private FileClient fileClient;

	@PostMapping
	@ApiOperation(value = "添加文章")
	@PreAuthorize("hasAuthority('sys:article:add')")
	public PublicResultJosn add(@RequestBody BlogArticleAdd blog) {
		// 校验
		if (StringUtils.isBlank(blog.getTitle()) || StringUtils.isBlank(blog.getSummary())
				|| StringUtils.isBlank(blog.getContent())) {
			throw new IllegalArgumentException("参数不全");
		}
		LoginUser loginUser = UserUtil.getCurrentUser();
		blog.setPublisher(loginUser.getUid());
		PublicResultJosn resultJosn = articleService.add(blog);
		return resultJosn;
	}

	@DeleteMapping
	@ApiOperation(value = "单、批量删除文章")
	@PreAuthorize("hasAuthority('sys:article:del')")
	public PublicResultJosn delete(@RequestBody List<String> deleteids) {
		PublicResultJosn resultJosn = null;
		resultJosn = articleService.delete(deleteids);
		return resultJosn;
	}

	@PutMapping
	@ApiOperation(value = "更新文章信息")
	@PreAuthorize("hasAuthority('sys:article:update')")
	public PublicResultJosn update(@RequestBody BlogArticleAdd blog) {
		PublicResultJosn resultJosn = null;
		resultJosn = articleService.updateByExample(blog, true);
		return resultJosn;
	}

	@PutMapping(value = "/switch")
	@ApiOperation(value = "更新文章信息（switch开关用这个接口）")
	@PreAuthorize("hasAuthority('sys:article:update')")
	public PublicResultJosn updateSwitch(@RequestBody BlogArticleAdd blog) {
		PublicResultJosn resultJosn = null;
		resultJosn = articleService.updateByExample(blog, false);
		return resultJosn;
	}

	@GetMapping
	@ApiOperation(value = "文章列表条件模糊查询")
	@PreAuthorize("hasAnyAuthority('sys:article:update','sys:article:query')")
	public LayuiTableResult selectByExample(BlogArticleAdd blog, @RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "10") Integer pageSize, HttpSession session) {
		LayuiTableResult resultJosn = articleService.selectByExample(blog, page, pageSize);
		return resultJosn;
	}

	@GetMapping(value = "/{id}")
	@ApiOperation(value = "根据id查找文章信息")
	@PreAuthorize("hasAnyAuthority('sys:article:update','sys:article:query')")
	public PublicResultJosn selectByUid(@PathVariable(value = "id") String id) {
		PublicResultJosn resultJosn = articleService.selectByBid(id);
		return resultJosn;
	}

	@PostMapping(value = "/upImg")
	@ApiOperation(value = "文章图片上传接口")
	@PreAuthorize("hasAnyAuthority('sys:article:add','sys:article:update')")
	public Object uploadImg(MultipartFile file) {
		if (null == file) {
			throw new IllegalArgumentException("文件为空");
		}
		Map<String, Object> result = new HashMap<>();
		//将文章上传的图片放在aliyun oss的web/2018-8-30/目录下
		//目录命名规范：
		//    不允许使用表情符，请使用符合要求的 UTF-8 字符
		//    「/」用于分割路径，可快速创建子目录，但，不要以「/」打头，不要出现连续的「/」（folder名称web/2018-8-30/）
		//    不允许出现名为「..」的子目录
		//    总长度控制在 1-254 个字符
		// 1.创建文件夹
		String folderName = "web/" + DateFormatUtils.format(new Date(), "yyyy-MM-dd") + "/";
		fileClient.createFolder(folderName);
		// 2.上传图片
		fileClient.upload(file, folderName);
		result.put("errno", 0);
		String[] data = { "https://triedblog.oss-cn-beijing.aliyuncs.com/" + folderName + file.getOriginalFilename() };
		result.put("data", data);
		return result;
	}
}
