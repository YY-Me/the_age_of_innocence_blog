package cn.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient // 发现服务，并注入到服务中
@SpringBootApplication
public class BlogWebCenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogWebCenterApplication.class, args);
    }

}
