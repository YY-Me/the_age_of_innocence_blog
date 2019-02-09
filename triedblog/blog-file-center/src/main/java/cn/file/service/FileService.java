package cn.file.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.aliyun.oss.model.PutObjectResult;

public interface FileService {

	// 上传
	PutObjectResult upload(MultipartFile file, String prefix);

	// 批量删除删除(返回没有删除的文件名)
	List<String> delete(List<String> keys);

	// 创建文件夹
	PutObjectResult createFolder(String folderName);

	// 列举指定前缀的文件
	Map<Object, Object> selectByPrefix(String folderName);

	// 获取文件详细信息
	Map<String, Object> selectFileInfo(String name);
}
