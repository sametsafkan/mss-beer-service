package com.sametsafkan.mssbeerservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@Configuration
@EnableAsync
public class TaskConfig {
    @Bean
    TaskExecutor taskExecutor(){
        return new SimpleAsyncTaskExecutor();
    }
}
