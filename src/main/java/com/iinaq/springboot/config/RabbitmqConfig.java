package com.iinaq.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Queue;

@Configuration
public class RabbitmqConfig {
    public static final String DEFAULT_USER_QUEUE = "dev.book.register.default.queue";
    public static final String MANUAL_USER_QUEUE = "dev.book.register.manual.queue";

    @Bean
    public Queue defaultUserQueue(){
        // 第一个是 QUEUE 的名字,第二个是消息是否需要持久化处理
        return new Queue(DEFAULT_USER_QUEUE, true);
    }

    @Bean
    public Queue manualUserQueue(){
        // 第一个是 QUEUE 的名字,第二个是消息是否需要持久化处理
        return new Queue(MANUAL_USER_QUEUE, true);
    }
}
