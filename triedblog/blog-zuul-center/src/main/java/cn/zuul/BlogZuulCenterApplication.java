package cn.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 路由中心
 * 
 * @author 偶尔有点困
 * @date 2018年5月23日
 */
@EnableZuulProxy // 开启路由网关
@EnableDiscoveryClient // 发现服务，并注入到服务中
@SpringBootApplication
public class BlogZuulCenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogZuulCenterApplication.class, args);
    }

    /**
     * http://localhost:8881/actuator/bus-refresh
     */

    String name;

    @GetMapping("/hello")
    public String hello() {
        return name;
    }

}
