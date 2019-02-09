package cn.file.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.aliyun.oss.OSSClient;

/**
 * 阿里云oss
 * 
 * @author 偶尔有点困
 * @date 2018年5月31日
 */
@Configuration
public class AliyunConfig {

	@Value("${oss.aliyun.endpoint}")
	private String endpoint;

	@Value("${oss.aliyun.accessKeyId}")
	private String accessKeyId;

	@Value("${oss.aliyun.secretAccessKey}")
	private String secretAccessKey;

	/**
	 * aliyun oss
	 * 
	 * @return
	 */
	@Bean
	public OSSClient ossClient() {
		return new OSSClient(endpoint, accessKeyId, secretAccessKey);
	}

}
