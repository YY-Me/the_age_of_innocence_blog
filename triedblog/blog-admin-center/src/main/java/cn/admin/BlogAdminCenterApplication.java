package cn.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 用户中心
 * 
 * @author 偶尔有点困
 * @date 2018年5月23日
 */
@EnableFeignClients
@EnableDiscoveryClient // 发现服务，并注入到服务中
@SpringBootApplication
public class BlogAdminCenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogAdminCenterApplication.class, args);
    }
}
