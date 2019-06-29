package com.iinaq.springboot.rabbitmq;

import com.iinaq.springboot.config.RabbitmqConfig;
import org.springframework.beans.factory.annotation.Autowired;

public class Produce<T> {
    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public Produce(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMsg(T t){
        rabbitTemplate.convertAndSend(RabbitmqConfig.DEFAULT_USER_QUEUE, t);
        this.rabbitTemplate.convertAndSend(RabbitmqConfig.MANUAL_USER_QUEUE, t);
    }
}
