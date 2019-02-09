package cn.oauth2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * 认证中心
 * 
 * @author 偶尔有点困
 * @date 2018年5月23日
 */
@EnableEurekaClient // 发现服务，并注入到服务中
@EnableFeignClients
@SpringBootApplication
public class BlogOauth2CenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogOauth2CenterApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
