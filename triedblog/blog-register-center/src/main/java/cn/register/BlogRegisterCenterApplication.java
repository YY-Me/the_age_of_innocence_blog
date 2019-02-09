package cn.register;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * 注册中心
 * 
 * @author 偶尔有点困
 * @date 2018年5月23日
 */
@SpringBootApplication
@EnableEurekaServer
public class BlogRegisterCenterApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogRegisterCenterApplication.class, args);
	}
}
