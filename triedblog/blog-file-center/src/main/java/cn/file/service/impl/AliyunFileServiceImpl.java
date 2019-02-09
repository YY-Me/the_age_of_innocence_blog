package cn.file.service.impl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.DeleteObjectsRequest;
import com.aliyun.oss.model.DeleteObjectsResult;
import com.aliyun.oss.model.ListObjectsRequest;
import com.aliyun.oss.model.OSSObjectSummary;
import com.aliyun.oss.model.ObjectListing;
import com.aliyun.oss.model.PutObjectResult;

import cn.file.service.FileService;

@Service(value = "aliyunFileServiceImpl")
public class AliyunFileServiceImpl implements FileService {

	@Autowired
	private OSSClient ossClient;

	@Value("${oss.aliyun.bucketName}")
	private String bucketName;

	@Override
	public PutObjectResult upload(MultipartFile file, String prefix) {
		PutObjectResult result = null;
		try {
			result = ossClient.putObject(bucketName, prefix + file.getOriginalFilename(), file.getInputStream());
		} catch (OSSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
		}
		return result;
	}

	/**
	 * 批量删除
	 */
	@Override
	public List<String> delete(List<String> keys) {
		DeleteObjectsResult deleteObjectsResult = ossClient
				.deleteObjects(new DeleteObjectsRequest(bucketName).withKeys(keys));
		List<String> deletedObjects = deleteObjectsResult.getDeletedObjects();
		return deletedObjects;
	}

	/**
	 * 文件夹创建
	 */
	@Override
	public PutObjectResult createFolder(String folderName) {
		PutObjectResult result = null;
		result = ossClient.putObject(bucketName, folderName, new ByteArrayInputStream(new byte[0]));
		return result;
	}

	/**
	 * 列举指定前缀的文件
	 */
	@Override
	public Map<Object, Object> selectByPrefix(String folderName) {
		Map<Object, Object> map = new HashMap<>();
		ListObjectsRequest listObjectsRequest = new ListObjectsRequest(bucketName);
		listObjectsRequest.setDelimiter("/");
		listObjectsRequest.setPrefix(folderName);
		ObjectListing listing = ossClient.listObjects(listObjectsRequest);
		List<OSSObjectSummary> objectSummaries = listing.getObjectSummaries();
		List<String> commonPrefixes = listing.getCommonPrefixes();
		map.put("objectSummaries", objectSummaries);
		map.put("commonPrefixes", commonPrefixes);
		return map;
	}

	@Override
	public Map<String, Object> selectFileInfo(String name) {
		Date expiration = new Date(new Date().getTime() + 3600l * 1000 * 24 * 365 * 10);
		URL url = ossClient.generatePresignedUrl(bucketName, name, expiration);
		if (url != null) {
			Map<String, Object> map = new HashMap<>();
			map.put("url", url.toString());
			return map;
		}
		return null;
	}

}
