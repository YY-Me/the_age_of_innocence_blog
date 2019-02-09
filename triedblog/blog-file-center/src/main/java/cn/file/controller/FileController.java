package cn.file.controller;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.aliyun.oss.model.PutObjectResult;

import cn.file.service.FileService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/file")
public class FileController {

	@Autowired
	private FileService fileService;

	@PostMapping(value = "/upload_file")
	@ApiOperation(value = "二进制上传文件")
	@PreAuthorize("hasAuthority('file:upload')")
	PutObjectResult upload(MultipartFile file, @RequestParam(defaultValue = "") String prefix) {
		if (null == file) {
			throw new IllegalArgumentException("文件为空");
		}
		if (!file.getOriginalFilename().contains(".")) {
			throw new IllegalArgumentException("缺少后缀名");
		}
		return fileService.upload(file, prefix);
	}

	@DeleteMapping
	@ApiOperation(value = "单、批量删除文件（文件夹）一次最多100个")
	@PreAuthorize("hasAuthority('file:del')")
	List<String> delete(@RequestBody List<String> keys) {
		if (CollectionUtils.isEmpty(keys)) {
			throw new IllegalArgumentException("文件名为空");
		}
		return fileService.delete(keys);
	}

	@PostMapping(value = "/create_folder")
	@ApiOperation(value = "创建文件夹")
	@PreAuthorize("hasAuthority('file:upload')")
	PutObjectResult createFolder(@RequestParam(defaultValue = "") String folderName) {
		if (StringUtils.isBlank(folderName) || !folderName.endsWith("/")) {
			throw new IllegalArgumentException("文件夹名称错误");
		}
		return fileService.createFolder(folderName);
	}

	@GetMapping
	@ApiOperation(value = "列举folderName文件下的文件及文件夹")
	@PreAuthorize("hasAuthority('file:query')")
	Map<Object, Object> selectByPrefix(String folderName) {
		return fileService.selectByPrefix(folderName);
	}

	@GetMapping("/get_info")
	@ApiOperation(value = "查询文件链接")
	@PreAuthorize("hasAuthority('file:query')")
	Map<String, Object> selectFileInfo(String name) {
		if (StringUtils.isBlank(name)) {
			throw new IllegalArgumentException("文件名错误");
		}
		return fileService.selectFileInfo(name);
	}
}
