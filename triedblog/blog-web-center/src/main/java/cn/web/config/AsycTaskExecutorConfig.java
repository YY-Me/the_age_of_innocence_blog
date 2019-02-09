package cn.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * 线程池、开启异步
 * @author 余勇
 * @email 1396513066@qq.com
 * @date 2018年8月9日
 */
@EnableAsync(proxyTargetClass = true)
@Configuration
public class AsycTaskExecutorConfig {

    @Bean
    public TaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(50);
        taskExecutor.setMaxPoolSize(100);

        return taskExecutor;
    }
}
